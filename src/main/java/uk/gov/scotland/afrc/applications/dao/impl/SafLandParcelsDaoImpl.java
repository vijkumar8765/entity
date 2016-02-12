/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.scotland.afrc.applications.dao.SafLandParcelsDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SafLandParcels;

public class SafLandParcelsDaoImpl extends EntityManagerBaseImpl<SafLandParcels, Long> implements SafLandParcelsDao {

	static final Logger LOGGER = LoggerFactory.getLogger(SafLandParcelsDaoImpl.class);
	
    public SafLandParcelsDaoImpl() {
        super(SafLandParcels.class);
    }

    @Override
    public List<SafLandParcels> findByApplicationId(Long applicationId) {
    	
        TypedQuery<SafLandParcels> query =
                getEntityManager().createNamedQuery("SafLandParcels.findSafLandParcels",
                		SafLandParcels.class);
        
        query.setParameter("applicationId", applicationId);
        if(!query.getResultList().isEmpty()){
        	return query.getResultList();
        }else{
        	return null;
        }
    }
    	
}
