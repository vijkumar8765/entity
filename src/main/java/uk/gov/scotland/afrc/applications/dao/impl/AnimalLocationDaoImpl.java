/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AnimalLocationDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AnimalLocationDBO;

public class AnimalLocationDaoImpl extends EntityManagerBaseImpl<AnimalLocationDBO, Long> implements AnimalLocationDao {

    private static Logger logger = Logger.getLogger(AnimalLocationDaoImpl.class);

    public AnimalLocationDaoImpl() {
        super(AnimalLocationDBO.class);
    }

    /** {@inheritDoc} */
    @Override
    public List<AnimalLocationDBO> animalLocationListByApplicationId(long applicationId) {
        logger.debug("applicationId = " + applicationId);
        return super.queryByKey("AnimalLocationDBO.animalLocationList", "applicationId", applicationId);
    }

    @Override
    public AnimalLocationDBO create(AnimalLocationDBO animalLocationDBO) {
        super.create(animalLocationDBO);

        getEntityManager().refresh(animalLocationDBO);
        logger.debug("create = " + animalLocationDBO.getApplicationId());
        return animalLocationDBO;
    }
}