/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SchemeDBO;

/**
 * ApplicationSchemeDao provides finder queries on the {@link SchemeDBO} entity.
 */
public interface SchemeDao extends EntityManagerBase<SchemeDBO, Long> {

    /**
     * Returns the scheme object for given scheme name.
     * 
     * @param schemeName
     * @return
     */
    SchemeDBO getSchemeByName(String schemeName);
    
    /**
     * Returns the scheme object for given scheme Id.
     * 
     * @param schemeId
     * @return
     */
    SchemeDBO getSchemeById(long schemeId);
    
    /**
     * This method returns Schemes based on application Id
     * @param applicationId
     * @return List of Schemes
     */
    List<Object> retrieveSchemeNamesForApplication(long applicationId);
    
    /**
     * 
     * @param applicationId
     * @return
     */
    List<Object[]> getSchemeByApplicationId(long applicationId);

    /**
     * 
     * @return
     */
    List<SchemeDBO> getAllSchemes();

}