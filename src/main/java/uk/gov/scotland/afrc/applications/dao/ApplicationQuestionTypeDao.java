/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.Date;
import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationQuestionTypeDBO;

public interface ApplicationQuestionTypeDao extends EntityManagerBase<ApplicationQuestionTypeDBO, Long> {

    /**
     * Gets the application questions.
     *
     * @param applicationTypeCode the application type code
     * @param questionSetName the question set name
     * @param applicableYear the applicable year
     * @return the applications questions
     */
    List<ApplicationQuestionTypeDBO> getApplicationQuestions(long applicationTypeCode, String questionSetName,
                                                             Date applicableYear);

    /**
     * Gets the all application questions.
     *
     * @param applicationTypeCode the application type code
     * @param questionSetName the question set name
     * @param applicableYear the applicable year
     * @return the all application questions
     */
    List<ApplicationQuestionTypeDBO> getAllApplicationQuestions(long applicationTypeCode, String questionSetName,
                                                                Date applicableYear);

    List<ApplicationQuestionTypeDBO> getApplicationQuestionsByGivenType(long typeId, String type,
                                                                        String questionSetName, Date applicableYear);

    /**
     * Gets the application questions.
     *
     * @param applicationTypeCode the application type code
     * @param applicableYear the applicable year
     * @return the applications questions
     */
    List<ApplicationQuestionTypeDBO> getApplicationQuestions(long applicationTypeCode, Date applicationApplicableYear);

     ApplicationQuestionTypeDBO findByQuestionCode(String qCpde);
    
    /**
     * Gets the all questions by assessment type code.
     * 
     * @param assessmentTypeCode the assessment type code
     * @return the assessment questions
     */
    List<ApplicationQuestionTypeDBO> getAssessmentQuestions(long assessmentTypeCode);
    

}
