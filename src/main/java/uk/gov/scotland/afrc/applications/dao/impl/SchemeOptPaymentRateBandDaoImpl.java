package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.SchemeOptPaymentRateBandDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SchemeOptPaymentRateBandDBO;

/**
 * Created by d608947 on 17/11/2014.
 */
public class SchemeOptPaymentRateBandDaoImpl extends EntityManagerBaseImpl<SchemeOptPaymentRateBandDBO, Long>
        implements SchemeOptPaymentRateBandDao {

    private final Logger logger = Logger.getLogger(SchemeOptPaymentRateBandDaoImpl.class);

    public SchemeOptPaymentRateBandDaoImpl() {
        super(SchemeOptPaymentRateBandDBO.class);
    }

    @Override
    public List<Object[]> findPaymentRateBandsBySchemeIds(List<Long> schemeIds) {
        TypedQuery<Object[]> query =
                getEntityManager().createNamedQuery("SchemeOptPaymentRateBandDBO.findPaymentRateBandsBySchemeIds",
                        Object[].class);
        query.setParameter("schemeIds", schemeIds);
        logger.debug("query = " + query.toString() + " " + schemeIds);
        return query.getResultList();
    }
}
