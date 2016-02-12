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

import uk.gov.scotland.afrc.applications.dao.SafLandCGShareSummaryDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SafLandCGShareSummary;

public class SafLandCGShareSummaryDaoImpl extends EntityManagerBaseImpl<SafLandCGShareSummary, Long> implements SafLandCGShareSummaryDao {

	static final Logger LOGGER = LoggerFactory.getLogger(SafLandCGShareSummaryDaoImpl.class);
	
    public SafLandCGShareSummaryDaoImpl() {
        super(SafLandCGShareSummary.class);
    }

	@Override
	public List<SafLandCGShareSummary> findByApplicationId(Long applicationId) {
		 TypedQuery<SafLandCGShareSummary> query =
	                getEntityManager().createNamedQuery("SafLandCGShareSummary.findByApplicationId",
	                		SafLandCGShareSummary.class);
	        
	        query.setParameter("applicationId", applicationId);
	        if(!query.getResultList().isEmpty()){
	        	return query.getResultList();
	        }else{
	        	return null;
	        }
	}

}
