/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AnimalClaimDBO;

/**
 * AnimalClaimDao provides finder queries on the {@link AnimalClaimDBO} entity.
 *
 * <pre><p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th></tr>
 * <tr><td> Query maximum ear tag entry order </td></tr>
 * </table></pre>
 */
public interface AnimalClaimDao extends EntityManagerBase<AnimalClaimDBO, Long> {
    /**
     * Returns the list of animal claims for given application number.
     *
     * @param   applicationId     The application number.
     *
     * @return  The list of animal claims.
     */
    List<AnimalClaimDBO> animalClaimsListByApplicationId(long applicationId);

    /**
     * Returns the animal claim for given ear tag number.
     *
     * @param   eartag     The eartag value.
     * @param   applicationId     The application number.
     *
     * @return  The animal claim.
     */    
    AnimalClaimDBO findAnimalClaimByEarTagAndApplicationId(String earTag, long applicationId);
    
    /**
     * Returns the list of animal claims for given eartag.
     *
     * @param   eartag     The eartag value.
     *
     * @return  The list of animal claims.
     */
    List<AnimalClaimDBO> animalClaimsListByEarTag(String earTag);
}
