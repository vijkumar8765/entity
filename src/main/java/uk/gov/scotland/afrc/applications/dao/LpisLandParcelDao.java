/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 * 
 * *****************************************************************************************************
 * THIS IS A READ ONLY ENTITY SERVICE TO ACCESS LPIS_LAN_PARCEL TABLE. 
 * DO NOT USE IT TO MODIFY ANYTHING IN THE DATABASE
 * DO NOT EXTEND EntityManagerBaseImpl INTERFACE
 * *****************************************************************************************************
 * 
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.model.domain.LpisLandParcelDBO;

public interface LpisLandParcelDao {

    List<LpisLandParcelDBO> getLandparcelsByLocationId(long locationId);
    List<LpisLandParcelDBO> findByParcelId(String parcelId);

    LpisLandParcelDBO findById(Long lpisLndPrclId);
}
