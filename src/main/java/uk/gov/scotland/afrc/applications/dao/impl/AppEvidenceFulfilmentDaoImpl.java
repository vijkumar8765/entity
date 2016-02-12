/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AppEvidenceFulfilmentDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AppEvidenceFulfilmentDBO;

public class AppEvidenceFulfilmentDaoImpl extends EntityManagerBaseImpl<AppEvidenceFulfilmentDBO, Long> implements
    AppEvidenceFulfilmentDao {

    private static Logger logger = Logger.getLogger(AppEvidenceFulfilmentDaoImpl.class);

    public AppEvidenceFulfilmentDaoImpl() {
        super(AppEvidenceFulfilmentDBO.class);
    }

    @Override
    public List<AppEvidenceFulfilmentDBO> findByApplicationId(long applicationId) {
        TypedQuery<AppEvidenceFulfilmentDBO> query =
                getEntityManager().createNamedQuery("AppEvidenceFulfilmentDBO.findByApplicationId",
                        AppEvidenceFulfilmentDBO.class);
        logger.debug("query = " + query.toString() + " " + applicationId);
        return query.setParameter("applicationId", applicationId).getResultList();
    }

}