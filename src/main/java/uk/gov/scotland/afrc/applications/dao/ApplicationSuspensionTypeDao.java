/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AppSuspensionTypeDBO;

/**
 * @author d609030
 *
 */
public interface ApplicationSuspensionTypeDao extends EntityManagerBase<AppSuspensionTypeDBO, Long>  {
	
	
	// TODO need to refactor the method names after real trigger of DB
	
	List<AppSuspensionTypeDBO> findByBRN(long brn);	
	
	AppSuspensionTypeDBO findByCode(long code);	

}
