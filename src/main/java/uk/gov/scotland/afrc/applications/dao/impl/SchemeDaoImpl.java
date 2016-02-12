/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LandUseTypeDao;
import uk.gov.scotland.afrc.applications.dao.SchemeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.SchemeDBO;

public class SchemeDaoImpl extends EntityManagerBaseImpl<SchemeDBO, Long> implements SchemeDao {
	private static final String COLUMN_APPLICATION_ID = "applicationId";
	
	private static final String APPLICATION_ID = "applicationId";
	
    private final Logger logger = Logger.getLogger(SchemeDaoImpl.class);

	private LandUseTypeDao landUseTypeDao;
	
    public LandUseTypeDao getLandUseTypeDao() {
		return landUseTypeDao;
	}

	public void setLandUseTypeDao(LandUseTypeDao landUseTypeDao) {
		this.landUseTypeDao = landUseTypeDao;
	}

	public SchemeDaoImpl() {
        super(SchemeDBO.class);
    }

    /** {@inheritDoc} */
    @Override
    public SchemeDBO getSchemeByName(String schemeName) {
        logger.debug("schemeName = " + schemeName);
        return super.queryByKey("SchemeDBO.findByName", "schemeName", schemeName);
    }
    
    
    @Override
	public List<Object> retrieveSchemeNamesForApplication(long applicationId) {
        TypedQuery<Object> query =
                getEntityManager()
                        .createNamedQuery("SchemeDBO.findByApplicationId", Object.class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        logger.debug("query = " + query.toString() + " " + applicationId);
        return query.getResultList();

	}
    
	@Override
	public List<Object[]> getSchemeByApplicationId(long applicationId) {
		 TypedQuery<Object[]> query = getEntityManager().createNamedQuery("SchemeDBO.findByApplicationId",Object[].class);
	        query.setParameter(APPLICATION_ID, applicationId);
        logger.debug("query = " + query.toString() + " " + applicationId);
	        return query.getResultList();
	}
	
	@Override
	public List<SchemeDBO> getAllSchemes() {
		TypedQuery<SchemeDBO> query =
                getEntityManager()
                        .createNamedQuery("SchemeDBO.findAll", SchemeDBO.class);
        logger.debug("query = " + query.toString());
        return query.getResultList();
	}

    @Override
    public SchemeDBO getSchemeById(long schemeId) {
        logger.debug("schemeId = " + schemeId);
        return super.queryByKey("SchemeDBO.findById", "schemeId", schemeId).get(0);
    }



}