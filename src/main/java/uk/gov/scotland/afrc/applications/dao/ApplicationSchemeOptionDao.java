/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSchemeOptionDBO;

/**
 * ApplicationSchemeDao provides finder queries on the {@link ApplicationSchemeOptionDBO} entity.
 */
public interface ApplicationSchemeOptionDao extends EntityManagerBase<ApplicationSchemeOptionDBO, Long> {

    /**
     * Returns the list of schemes for given application number.
     *
     * @param   applicationId     The application number.
     * @param   schemeId    The scheme id
     * @return  The list of scheme options.
     */
    List<ApplicationSchemeOptionDBO> schemeOptionsListByApplicationIdAndSchmeId(long applicationId, long schemeId);

    /**
     * Returns the list of scheme options for given application number of a given scheme.
     *  
     * @param   applicationId     The application Id.
     * @param   schemeId           The scheme id
     * 
     * @return  The list of scheme options.
     */
    List<ApplicationSchemeOptionDBO> findSchemeOptionsByApplicationIdAndSchemeId(long applicationId, long schemeId);

    /**
     * Returns the list of scheme options for given application number of a given scheme.
     *  
     * @param   applicationId     The application Id.
     * 
     * @return  The list of scheme options.
     */

	List<ApplicationSchemeOptionDBO> findSchemeOptionsByApplicationId(
			long applicationId);
	
	/**
	 * 
	 */
	ApplicationSchemeOptionDBO findByApplicationAndSchemeOptionId(long appId, long schemeOptId);
}