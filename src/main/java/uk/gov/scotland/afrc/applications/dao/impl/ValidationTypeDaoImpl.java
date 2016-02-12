package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ValidationTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeDBO;

public class ValidationTypeDaoImpl extends EntityManagerBaseImpl<ValidationTypeDBO, Long> implements ValidationTypeDao {
    private final Logger logger = Logger.getLogger(ValidationTypeDaoImpl.class);

    private static final String APPLICATION_ID = "applicationId";
    private static final String QUERY = "query = ";

    public ValidationTypeDaoImpl() {
        super(ValidationTypeDBO.class);
    }

    /** {@inheritDoc} */
    @Override
    public ValidationTypeDBO findByName(String name) {
        TypedQuery<ValidationTypeDBO> query =
                getEntityManager().createNamedQuery("ValidationTypeDBO.findByName", ValidationTypeDBO.class);

        query.setParameter("name", name);

        logger.debug(QUERY + query.toString());

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<ValidationTypeDBO> findValidationTypesByCategoryName(String categoryName,
                                                                     Set<String> validationTypeNames) {
        TypedQuery<ValidationTypeDBO> query =
                getEntityManager().createNamedQuery("ValidationTypeDBO.findValidationTypesByCategoryName",
                        ValidationTypeDBO.class);
        query.setParameter("categoryName", categoryName);
        query.setParameter("validationTypeNames", validationTypeNames);
        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

    @Override
    public Map<Long, ValidationTypeDBO> findByCodeList(List<Long> codes) {
        Map<Long, ValidationTypeDBO> results = new HashMap<Long, ValidationTypeDBO>();

        TypedQuery<Object[]> query =
                getEntityManager().createNamedQuery("ValidationTypeDBO.findByCodeList", Object[].class);

        query.setParameter("codeList", codes);
        logger.debug(QUERY + query.toString());
        try {
            List<Object[]> outcomes = query.getResultList();
            if (outcomes != null) {
                logger.debug("outcomes = " + outcomes.size());

                for (Object[] outcome : outcomes) {
                    results.put((Long) outcome[0], (ValidationTypeDBO) outcome[1]);
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return results;
    }

    @Override
    public List<Object[]> findErrorByApplicationId(long applicationId) {
        TypedQuery<Object[]> query =
                getEntityManager().createNamedQuery("ValidationTypeDBO.findByApplicationId", Object[].class);
        query.setParameter(APPLICATION_ID, applicationId);
        logger.debug(QUERY + query.toString() + " " + applicationId);
        return query.getResultList();
    }

    /** {@inheritDoc} */
    @Override
    public ValidationTypeDBO findByCode(long code) {
        TypedQuery<ValidationTypeDBO> query =
                getEntityManager().createNamedQuery("ValidationTypeDBO.findByCode", ValidationTypeDBO.class);

        query.setParameter("code", code);

        logger.debug(QUERY + query.toString() + " " + code);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
