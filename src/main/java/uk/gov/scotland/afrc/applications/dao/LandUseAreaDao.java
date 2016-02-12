/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.LandUseAreaDBO;

public interface LandUseAreaDao extends EntityManagerBase<LandUseAreaDBO, Long> {
    List<LandUseAreaDBO> getPermLandUseForLndPrcl(long appLndPrclId);
	
	List<LandUseAreaDBO> findByApplicationId(long applicationId);

    List<LandUseAreaDBO> findByApplicationIdAndParcelType(long applicationId, boolean isPermanent);
    
    List<LandUseAreaDBO> findByApplicationIdAndAppLndPrclId(long applicationId, long appLndPrclId);

}
