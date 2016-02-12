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

import uk.gov.scotland.afrc.applications.dao.SafLandCGSummaryDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SafLandCGSummary;

public class SafLandCGSummaryDaoImpl extends EntityManagerBaseImpl<SafLandCGSummary, Long> implements SafLandCGSummaryDao {

	static final Logger LOGGER = LoggerFactory.getLogger(SafLandCGSummaryDaoImpl.class);
	
    public SafLandCGSummaryDaoImpl() {
        super(SafLandCGSummary.class);
    }

	@Override
	public List<SafLandCGSummary> findByApplicationId(Long applicationId) {
				 
		 TypedQuery<SafLandCGSummary> query =				 
	                getEntityManager().createNamedQuery("SafLandCGSummary.findByApplicationId",
	                		SafLandCGSummary.class);
	        
	        query.setParameter("applicationId", applicationId);
	        if(!query.getResultList().isEmpty()){
	        	return query.getResultList();
	        }else{
	        	return null;
	        }
	}
	
}
