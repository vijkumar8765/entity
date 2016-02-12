/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AppSuspensionLevelTypeDBO;

/**
 * @author d609030
 *
 */
public interface ApplicationSuspensionLevelTypeDao extends EntityManagerBase<AppSuspensionLevelTypeDBO, Long>  {
	
	List<AppSuspensionLevelTypeDBO> findAll();

	AppSuspensionLevelTypeDBO findByCode(long code);
	

}
