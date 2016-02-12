/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AnimalLocationDBO;

/**
 * AnimalLocationDao provides finder queries on the {@link AnimalLocationDBO} entity.
 */
public interface AnimalLocationDao extends EntityManagerBase<AnimalLocationDBO, Long> {

    /**
     * Returns the list of animal claims for given application number.
     *
     * @param   applicationId     The application number.
     *
     * @return  The list of claim locations.
     */
    List<AnimalLocationDBO> animalLocationListByApplicationId(long applicationId);

}