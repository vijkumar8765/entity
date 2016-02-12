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

public interface LndPrclClassificationDao {

    boolean isLFAParcel(long lpisLndPrclId);
}
