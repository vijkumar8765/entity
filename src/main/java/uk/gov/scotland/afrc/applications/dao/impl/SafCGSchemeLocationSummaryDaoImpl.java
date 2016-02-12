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

import uk.gov.scotland.afrc.applications.dao.SafCgSchemeLocationSummaryDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SafCgSchemeLocationSummary;

public class SafCGSchemeLocationSummaryDaoImpl extends EntityManagerBaseImpl<SafCgSchemeLocationSummary, Long> implements SafCgSchemeLocationSummaryDao {

	static final Logger LOGGER = LoggerFactory.getLogger(SafCGSchemeLocationSummaryDaoImpl.class);
	
    public SafCGSchemeLocationSummaryDaoImpl() {
        super(SafCgSchemeLocationSummary.class);
    }

	@Override
	public List<SafCgSchemeLocationSummary> findByApplicationId(Long applicationId) {
		 TypedQuery<SafCgSchemeLocationSummary> query =
	                getEntityManager().createNamedQuery("SafCgSchemeLocationSummary.findByApplicationId",
	                		SafCgSchemeLocationSummary.class);
	        
	        query.setParameter("applicationId", applicationId);
	        if(!query.getResultList().isEmpty()){
	        	return query.getResultList();
	        }else{
	        	return null;
	        }
	}
	
}
