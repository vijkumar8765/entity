/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AppSuspensionDBO;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationDBO;

/**
 * @author d609030
 *
 */
public interface ApplicationSuspensionDao extends EntityManagerBase<AppSuspensionDBO, Long> {

    List<AppSuspensionDBO> findAllHoldsByBRN(Long brn);

    Boolean findCustomerHoldExists(Long brn, Long holdLevelId, Long holdTypeId);
    
    Boolean findApplicationOrSchemeHoldExists(Long applicationId, Long holdLevelId, Long holdTypeId);

    Long findApplicationHoldCount(Long brn, Long holdTypeId);

    List<Object[]> findSchemeTypes(Long brn);
    
    List<Object[]> findApplicationTypes(Long brn);

    List<AppSuspensionDBO> getApplicationHoldStatus(long applicationId);

    List<ApplicationDBO> findApplicationsBySchemeId(long brn, long schemeId);
}
