/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationQuestionTypeResponseDBO;

public interface ApplicationQuestionTypeResponseDao extends EntityManagerBase<ApplicationQuestionTypeResponseDBO, Long> {

    /**
     * Gets the question response by application id and question type code.
     *
     * @param applicationId the application id
     * @param questionChoiceCode the question type code
     * @return the question response by application id and question type code
     */
    List<ApplicationQuestionTypeResponseDBO>
        getQuestionResponseByApplicationIdAndQuestionTypeCode(Long applicationId, Long questionTypeCode);

    /**
     * Gets the question response by application id.
     *
     * @param applicationId the application id
     * @return the question response by application id
     */
    List<ApplicationQuestionTypeResponseDBO> getQuestionResponseByApplicationId(Long applicationId);

    /**
     * Clears the entity manager
     */
    void clear();
    
    List<ApplicationQuestionTypeResponseDBO>
        getQuestionResponseByApplicationIdAndQuestionTypeCodeAndAssessmentElementId(Long applicationId,
                                                                                    Long questionTypeCode,
                                                                                    Long assessmentElementId);
    
    List<ApplicationQuestionTypeResponseDBO>
    getQuestionResponseByApplicationIdAndQuestionTypeCodeAndAssessmentElementIds(Long applicationId,
                                                                                 Long questionTypeCode,
                                                                                     List<Long> assessmentElementIds);
}
