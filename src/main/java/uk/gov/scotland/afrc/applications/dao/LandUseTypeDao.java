/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.LandUseTypeDBO;


public interface LandUseTypeDao extends EntityManagerBase<LandUseTypeDBO, Long> {

	LandUseTypeDBO findByName(String name);
	List<LandUseTypeDBO> getLandUseTypes();
	List<Long> typeCodesForApplication(long applicationId);
    LandUseTypeDBO findByCode(String code);

}
