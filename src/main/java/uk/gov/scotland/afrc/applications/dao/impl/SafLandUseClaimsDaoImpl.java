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

import uk.gov.scotland.afrc.applications.dao.SafLandUseClaimsDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SafLandUseClaims;

public class SafLandUseClaimsDaoImpl extends EntityManagerBaseImpl<SafLandUseClaims, Long> implements SafLandUseClaimsDao {

	static final Logger LOGGER = LoggerFactory.getLogger(SafLandUseClaimsDaoImpl.class);
	
    public SafLandUseClaimsDaoImpl() {
        super(SafLandUseClaims.class);
    }

    @Override
    public List<SafLandUseClaims> findByApplicationId(Long applicationId) {
    	
        TypedQuery<SafLandUseClaims> query =
                getEntityManager().createNamedQuery("SafLandUseClaims.findSafLandParcels",
                		SafLandUseClaims.class);
        
        query.setParameter("applicationId", applicationId);
        if(!query.getResultList().isEmpty()){
        	return query.getResultList();
        }else{
        	return null;
        }
    }
    	
}
