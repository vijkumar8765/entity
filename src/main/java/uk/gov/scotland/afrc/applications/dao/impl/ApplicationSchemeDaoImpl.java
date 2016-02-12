package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationSchemeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSchemeDBO;

public class ApplicationSchemeDaoImpl extends EntityManagerBaseImpl<ApplicationSchemeDBO, Long> implements
    ApplicationSchemeDao {

    private static Logger logger = Logger.getLogger(ApplicationSchemeDaoImpl.class);

    public ApplicationSchemeDaoImpl() {
        super(ApplicationSchemeDBO.class);
    }

    @Override
    public List<ApplicationSchemeDBO> findSchemesByApplicationId(long applicationId) {

        TypedQuery<ApplicationSchemeDBO> query =
                getEntityManager().createNamedQuery("ApplicationSchemeDBO.schemeList", ApplicationSchemeDBO.class);

        logger.debug("query = " + query.toString());

        return query.setParameter("applicationId", Long.valueOf(applicationId)).getResultList();
    }

    @Override
    public ApplicationSchemeDBO findByApplicationIdAndSchemeId(long applicationId, long schemeId) {
        TypedQuery<ApplicationSchemeDBO> query =
                getEntityManager().createNamedQuery("ApplicationSchemeDBO.findByApplicationIdAndSchemeId",
                        ApplicationSchemeDBO.class);
        query.setParameter("applicationId", applicationId).setParameter("schemeId", schemeId);

        logger.debug("query = " + query.toString());
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public ApplicationSchemeDBO findByApplicationSchemeIdAndSchemeId(long applicationId, long applicationSchemeId) {
        TypedQuery<ApplicationSchemeDBO> query =
                getEntityManager().createNamedQuery("ApplicationSchemeDBO.findByApplicationSchemeIdAndSchemeId",
                        ApplicationSchemeDBO.class);
        query.setParameter("applicationId", applicationId).setParameter("applicationSchemeId", applicationSchemeId);

        logger.debug("query = " + query.toString());
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e);
            return null;
        }
    }

}
