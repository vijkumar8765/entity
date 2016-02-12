/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationTypeDBO;

public interface ApplicationTypeDao extends EntityManagerBase<ApplicationTypeDBO, Long> {
    List<ApplicationTypeDBO> getApplicationTypes();

    ApplicationTypeDBO getApplicationTypeByYearAndDescription(String year, String description);

    ApplicationTypeDBO getApplicationTypeByYearAndName(String year, String name);
    
    ApplicationTypeDBO getApplicationTypeByYearAndDocumentSubCategory(String year, long docSubCategory);
    
    ApplicationTypeDBO getApplicationTypeByCode(long applicationTypeCode);
    
    ApplicationTypeDBO getApplicationTypeByName(String name);

	ApplicationTypeDBO getApplicationTypeByYearAndCode(String year, long code);
}
