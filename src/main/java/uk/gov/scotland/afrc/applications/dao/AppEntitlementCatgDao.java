/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AppEntitlementCatgDBO;

/**
 * The Interface AppEntitlementCatgDao.
 */
public interface AppEntitlementCatgDao extends EntityManagerBase<AppEntitlementCatgDBO, Long> {

    /**
     * Removes the by application id.
     *
     * @param applicationId the application id
     */
    void removeByApplicationId(long applicationId);
    
    List<AppEntitlementCatgDBO> findByApplicationId(long applicationId);

	List<AppEntitlementCatgDBO> findByBrnAndApplicationTypeCode(long brn, long applicationTypeCode);
}
