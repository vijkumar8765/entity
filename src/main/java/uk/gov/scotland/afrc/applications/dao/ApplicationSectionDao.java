/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSectionDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public interface ApplicationSectionDao extends EntityManagerBase<ApplicationSectionDBO, Long> {
    ApplicationSectionDBO getApplicationSectionByApplicationId(long applicationId, String section);

    ApplicationSectionDBO getApplicationSectionByApplicationIdAndSectionType(long applicationId, long sectionTypeCode);

    List<ApplicationSectionDBO> getAllApplicationSections(long applicationId);

    ApplicationSectionDBO createOrUpdateApplicationSection(long applicationId, String sectionName, Long version)
        throws ConcurrentAccessException;

    ApplicationSectionDBO createApplicationSectionIfNotExist(long applicationId, String sectionName);

    ApplicationSectionDBO findErrorSectionByApplicationId(long applicationId);
    
    List<ApplicationSectionDBO> findByApplicationId(long applicationId);

}
