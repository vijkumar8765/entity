package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationSectionTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSectionTypeDBO;

public class ApplicationSectionTypeDaoImpl extends EntityManagerBaseImpl<ApplicationSectionTypeDBO, Long> implements
    ApplicationSectionTypeDao {

    private static Logger logger = Logger.getLogger(ApplicationSectionTypeDaoImpl.class);

    private static final String COLUMN_APPLICATION_ID = "applicationId";

    private static final String APPLICATION_ID = "applicationId";
    private static final String QUERY = "query = ";

    public ApplicationSectionTypeDaoImpl() {
        super(ApplicationSectionTypeDBO.class);
    }

    @Override
    public ApplicationSectionTypeDBO getApplicationSectionTypeByName(String name) {

        TypedQuery<ApplicationSectionTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationSectionTypeDBO.findBySectionName",
                        ApplicationSectionTypeDBO.class);
        logger.debug(QUERY + query.toString());
        return query.setParameter("applicationSectionName", name).getSingleResult();

    }

    @Override
    public List<ApplicationSectionTypeDBO> getApplicationSectionsByApplicatioTypeCode(long applicationTypeCode) {

        TypedQuery<ApplicationSectionTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationSectionTypeDBO.findByApplicationTypeCode",
                        ApplicationSectionTypeDBO.class);
        logger.debug(QUERY + query.toString());
        return query.setParameter("applicationTypeCode", applicationTypeCode).getResultList();
    }

    @Override
    public List<Object> retrieveSections(long applicationId) {
        TypedQuery<Object> query =
                getEntityManager().createNamedQuery("ApplicationSectionTypeDBO.findErrorByApplicationID", Object.class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

    @Override
    public List<Object[]> findErrorByApplicationId(long applicationId) {
        TypedQuery<Object[]> query =
                getEntityManager().createNamedQuery("ApplicationSectionTypeDBO.findErrorByApplicationID",
                        Object[].class);
        logger.debug(QUERY + query.toString());
        return query.setParameter(APPLICATION_ID, applicationId).getResultList();
    }

}
