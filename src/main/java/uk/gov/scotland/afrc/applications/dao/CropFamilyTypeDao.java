/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.CropFamilyTypeDBO;


public interface CropFamilyTypeDao extends EntityManagerBase<CropFamilyTypeDBO, Long> {

	CropFamilyTypeDBO findByName(String name);
	List<CropFamilyTypeDBO> findAll();
	CropFamilyTypeDBO findByCode(String code);

}
