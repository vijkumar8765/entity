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

import uk.gov.scotland.afrc.applications.dao.SafCgSchemeRPSummaryDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SafCgSchemeRPSummary;

public class SafCGSchemeRPSummaryDaoImpl extends EntityManagerBaseImpl<SafCgSchemeRPSummary, Long> implements SafCgSchemeRPSummaryDao {

	static final Logger LOGGER = LoggerFactory.getLogger(SafCGSchemeRPSummaryDaoImpl.class);
	
    public SafCGSchemeRPSummaryDaoImpl() {
        super(SafCgSchemeRPSummary.class);
    }

	@Override
	public List<SafCgSchemeRPSummary> findByApplicationId(Long applicationId) {
		 TypedQuery<SafCgSchemeRPSummary> query =
	                getEntityManager().createNamedQuery("SafCgSchemeRPSummary.findByApplicationId",
	                		SafCgSchemeRPSummary.class);
	        
	        query.setParameter("applicationId", applicationId);
	        if(!query.getResultList().isEmpty()){
	        	return query.getResultList();
	        }else{
	        	return null;
	        }
	}
	
}
