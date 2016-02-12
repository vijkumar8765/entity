/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.CustomerLocationDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

/**
 * @author d607693
 *
 */
public interface CustomerLocationDao extends EntityManagerBase<CustomerLocationDBO, Long> {
    List<CustomerLocationDBO> getLocationsByCode(String locationCode);
    
    CustomerLocationDBO getLocationByGrazing(long locationId);

    CustomerLocationDBO getLocationByLocationId(long locationId);
    
    void updateLocationDBO(CustomerLocationDBO customerLocationDBO) throws ConcurrentAccessException;
}
