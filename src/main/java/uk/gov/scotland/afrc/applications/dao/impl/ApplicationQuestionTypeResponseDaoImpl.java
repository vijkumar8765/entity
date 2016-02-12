package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationQuestionTypeResponseDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationQuestionTypeResponseDBO;

public class ApplicationQuestionTypeResponseDaoImpl extends
    EntityManagerBaseImpl<ApplicationQuestionTypeResponseDBO, Long> implements ApplicationQuestionTypeResponseDao {

    private static Logger logger = Logger.getLogger(ApplicationQuestionTypeResponseDaoImpl.class);
    private static final String QUERY = "query = ";
    private static final String APPLICATION_ID = "applicationId";

    public ApplicationQuestionTypeResponseDaoImpl() {
        super(ApplicationQuestionTypeResponseDBO.class);
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationQuestionTypeResponseDBO>
        getQuestionResponseByApplicationIdAndQuestionTypeCode(Long applicationId, Long questionTypeCode) {
        TypedQuery<ApplicationQuestionTypeResponseDBO> query =
                getEntityManager().createNamedQuery(
                        "ApplicationQuestionTypeResponseDBO.findByApplicationIdAndQuestionTypeCode",
                        ApplicationQuestionTypeResponseDBO.class);

        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter("questionTypeCode", questionTypeCode);

        logger.debug(QUERY + query.toString());

        return query.getResultList();
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationQuestionTypeResponseDBO> getQuestionResponseByApplicationId(Long applicationId) {
        TypedQuery<ApplicationQuestionTypeResponseDBO> query =
                getEntityManager().createNamedQuery("ApplicationQuestionTypeResponseDBO.findByApplicationId",
                        ApplicationQuestionTypeResponseDBO.class);

        query.setParameter(APPLICATION_ID, applicationId);

        logger.debug(QUERY + query.toString());

        return query.getResultList();
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationQuestionTypeResponseDBO>
        getQuestionResponseByApplicationIdAndQuestionTypeCodeAndAssessmentElementId(Long applicationId,
                                                                                    Long questionTypeCode,
                                                                                    Long assessmentElementId) {
        TypedQuery<ApplicationQuestionTypeResponseDBO> query =
                getEntityManager()
                        .createNamedQuery(
                                "ApplicationQuestionTypeResponseDBO.findByApplicationIdAndQuestionTypeCodeAndAssessmentElementId",
                                ApplicationQuestionTypeResponseDBO.class);

        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter("questionTypeCode", questionTypeCode);
        query.setParameter("assessmentElementId", assessmentElementId);

        logger.debug(QUERY + query.toString());

        return query.getResultList();
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationQuestionTypeResponseDBO>
        getQuestionResponseByApplicationIdAndQuestionTypeCodeAndAssessmentElementIds(Long applicationId,
                                                                                     Long questionTypeCode,
                                                                                     List<Long> assessmentElementIds) {
        TypedQuery<ApplicationQuestionTypeResponseDBO> query =
                getEntityManager()
                        .createNamedQuery(
                                "ApplicationQuestionTypeResponseDBO.findByApplicationIdAndQuestionTypeCodeAndAssessmentElementIds",
                                ApplicationQuestionTypeResponseDBO.class);

        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter("questionTypeCode", questionTypeCode);
        query.setParameter("assessmentElementIds", assessmentElementIds);

        logger.debug(QUERY + query.toString());

        return query.getResultList();
    }

    /**
     * Clears the entity manager
     */
    @Override
    public void clear() {
        getEntityManager().clear();
    }

}
