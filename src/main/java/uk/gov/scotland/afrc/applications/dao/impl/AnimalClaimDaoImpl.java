/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AnimalClaimDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AnimalClaimDBO;

public class AnimalClaimDaoImpl extends EntityManagerBaseImpl<AnimalClaimDBO, Long> implements AnimalClaimDao {

    private static Logger logger = Logger.getLogger(AnimalClaimDaoImpl.class);

    public AnimalClaimDaoImpl() {
        super(AnimalClaimDBO.class);
    }

    /** {@inheritDoc} */
    @Override
    public List<AnimalClaimDBO> animalClaimsListByApplicationId(long applicationId) {
        logger.debug("applicationId = " + applicationId);
        return super.queryByKey("AnimalClaimDBO.animalClaimsListByApplication", "applicationId", applicationId);
    }

    /** {@inheritDoc} */
    @Override
    public AnimalClaimDBO findAnimalClaimByEarTagAndApplicationId(String earTag, long applicationId) {
        logger.debug("applicationId = " + applicationId);
        TypedQuery<AnimalClaimDBO> query =
                getEntityManager().createNamedQuery("AnimalClaimDBO.findByEarTagAndApplication", AnimalClaimDBO.class);

        query.setParameter("earTag", earTag);
        query.setParameter("applicationId", applicationId);
        List<AnimalClaimDBO> earTags = query.getResultList();

        if (earTags != null && earTags.isEmpty()) {
            return null;
        }
        if (earTags != null) {
            logger.debug("earTags size = " + earTags.size());
        }
        return earTags != null ? earTags.get(0) : null;
    }

    /** {@inheritDoc} */
    @Override
    public List<AnimalClaimDBO> animalClaimsListByEarTag(String earTag) {
        TypedQuery<AnimalClaimDBO> query =
                getEntityManager().createNamedQuery("AnimalClaimDBO.animalClaimsListByEarTag", AnimalClaimDBO.class);

        query.setParameter("earTag", earTag);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }
}