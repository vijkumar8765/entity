/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LocationCGHistoryDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LocationCGHistoryDBO;

public class LocationCGHistoryDaoImpl extends EntityManagerBaseImpl<LocationCGHistoryDBO, Long> implements
    LocationCGHistoryDao {

    private final Logger logger = Logger.getLogger(LocationCGHistoryDaoImpl.class);

    public LocationCGHistoryDaoImpl() {
        super(LocationCGHistoryDBO.class);
    }

    @Override
    public LocationCGHistoryDBO findByLocationCgId(long locationCgId) {

        TypedQuery<LocationCGHistoryDBO> query =
                getEntityManager().createNamedQuery("LocationCGHistoryDBO.findByApplicationId",
                        LocationCGHistoryDBO.class);
        logger.debug("query  = " + query + " " + locationCgId);
        return query.setParameter("locationCgId", locationCgId).getSingleResult();
    }

}
