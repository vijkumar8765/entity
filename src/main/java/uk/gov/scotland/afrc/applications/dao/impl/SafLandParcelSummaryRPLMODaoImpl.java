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

import uk.gov.scotland.afrc.applications.dao.SafLandParcelSummaryRPMLODao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SafLandParcelSummaryRPLMO;

public class SafLandParcelSummaryRPLMODaoImpl extends EntityManagerBaseImpl<SafLandParcelSummaryRPLMO, Long> implements SafLandParcelSummaryRPMLODao {

	private final Logger log = LoggerFactory.getLogger(SafLandParcelSummaryRPLMODaoImpl.class);
	
    public SafLandParcelSummaryRPLMODaoImpl() {
        super(SafLandParcelSummaryRPLMO.class);
    }

	@Override
	public List<SafLandParcelSummaryRPLMO> findSafLandParcelByApplicationId(
			Long applicationId) {
		TypedQuery<SafLandParcelSummaryRPLMO> query =
                getEntityManager().createNamedQuery("SafLandParcelSummaryRPLMO.findSafLandParcelRPLMOByApplicationId",
                		SafLandParcelSummaryRPLMO.class);
        log.info("applicationId ========================= : " + applicationId);
        query.setParameter("applicationId", applicationId);
        if(!query.getResultList().isEmpty()){
        	return query.getResultList();
        }else{
        	return null;
        }
	}

	
}
