package uk.gov.scotland.afrc.applications.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.SchemeOptionDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SchemeOptionDBO;

public class SchemeOptionDaoImpl extends EntityManagerBaseImpl<SchemeOptionDBO, Long> implements SchemeOptionDao {

    private static final int INDEX0 = 0;
    private static final int INDEX1 = 1;

    private final Logger logger = Logger.getLogger(SchemeOptionDaoImpl.class);
    private static final String QUERY = "query = ";

    public SchemeOptionDaoImpl() {
        super(SchemeOptionDBO.class);
    }

    @Override
    public List<SchemeOptionDBO> findAllSchemeOptions() {
        TypedQuery<SchemeOptionDBO> query =
                getEntityManager().createNamedQuery("SchemeOptionDBO.findAll", SchemeOptionDBO.class);
        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

    @Override
    public List<SchemeOptionDBO> findAllSchemeOptionsBySchemeId(long schemeId) {
        TypedQuery<SchemeOptionDBO> query =
                getEntityManager().createNamedQuery("SchemeOptionDBO.findByScheme", SchemeOptionDBO.class);
        List<SchemeOptionDBO> schemeOptionDBOs = query.setParameter("schemeId", Long.valueOf(schemeId)).getResultList();
        updateSchemePrice(schemeOptionDBOs, schemeId);
        logger.debug(QUERY + query.toString() + " " + schemeId);
        return schemeOptionDBOs;
    }

    @Override
    public BigDecimal getConversionFactor(long schemeOptionId) {

        BigDecimal cnvFactor = null;
        TypedQuery<BigDecimal> query =
                getEntityManager().createNamedQuery("SchemeOptionDBO.getConversionFactor", BigDecimal.class);
        List<BigDecimal> result = query.setParameter("schemeOptionId", schemeOptionId).getResultList();

        logger.debug(QUERY + query.toString() + " " + schemeOptionId);

        if (null != result && !result.isEmpty()) {
            logger.debug("null != result && !result.isEmpty()");
            cnvFactor = result.get(0);
        }

        return cnvFactor;
    }

    private void updateSchemePrice(List<SchemeOptionDBO> schemeOptionDBOs, long schemeId) {

        final String nsql =
                "SELECT SOL.MAX_LIMIT,SO.SCHEME_OPT_ID FROM SCHEME_OPTION SO, SCHEME_OPTION_LIMIT SOL WHERE SO.SCHEME_OPT_ID            =SOL.SCHEME_OPTION_ID"
                    + " AND SO.SCHEME_ID= ?1";
        Query query = getEntityManager().createNativeQuery(nsql);
        query.setParameter(1, schemeId);
        logger.debug(QUERY + query.toString());
        @SuppressWarnings("unchecked")
        List<Object[]> rowList = query.getResultList();
        if (!rowList.isEmpty()) {
            logger.debug("!rowList.isEmpty()");
            for (SchemeOptionDBO schemeOptionDBO : schemeOptionDBOs) {
                inner: for (Object[] row : rowList) {
                    logger.debug("rowList" + rowList.size());
                    double paymentRate = ((BigDecimal) row[INDEX0]).doubleValue();
                    int schemeOptionId = ((BigDecimal) row[INDEX1]).intValue();
                    if (schemeOptionDBO.getSchemeOptionId() == schemeOptionId) {
                        logger.debug("schemeOptionId " + schemeOptionId);
                        schemeOptionDBO.setPaymentRate(paymentRate);
                        break inner;
                    }
                }
            }
        }

    }
}
