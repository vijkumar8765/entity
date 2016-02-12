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

import uk.gov.scotland.afrc.applications.dao.SafCgSchemeGreeningSummaryDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SafCgSchemeGreeningSummary;

public class SafCGSchemeGreeningSummaryDaoImpl extends EntityManagerBaseImpl<SafCgSchemeGreeningSummary, Long> implements SafCgSchemeGreeningSummaryDao {

	static final Logger LOGGER = LoggerFactory.getLogger(SafCGSchemeGreeningSummaryDaoImpl.class);
	
    public SafCGSchemeGreeningSummaryDaoImpl() {
        super(SafCgSchemeGreeningSummary.class);
    }

	@Override
	public List<SafCgSchemeGreeningSummary> findByApplicationId(Long applicationId) {
		 TypedQuery<SafCgSchemeGreeningSummary> query =
	                getEntityManager().createNamedQuery("SafCgSchemeGreeningSummary.findByApplicationId",
	                		SafCgSchemeGreeningSummary.class);
	        
	        query.setParameter("applicationId", applicationId);
	        if(!query.getResultList().isEmpty()){
	        	return query.getResultList();
	        }else{
	        	return null;
	        }
	}
	
}
