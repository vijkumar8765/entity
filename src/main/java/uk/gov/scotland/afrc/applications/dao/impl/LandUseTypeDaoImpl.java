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
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LandUseTypeDBO;

public class LandUseTypeDaoImpl extends EntityManagerBaseImpl<LandUseTypeDBO, Long> implements LandUseTypeDao {

    private final Logger logger = Logger.getLogger(LandUseTypeDaoImpl.class);
    private static final String QUERY = "query = ";

    public LandUseTypeDaoImpl() {
        super(LandUseTypeDBO.class);
    }

    @Override
    public LandUseTypeDBO findByName(String name) {
        TypedQuery<LandUseTypeDBO> query =
                getEntityManager().createNamedQuery("LandUseTypeDBO.findByName", LandUseTypeDBO.class);
        logger.debug(QUERY + query + " " + name);
        return query.setParameter("name", name).getSingleResult();
    }

    @Override
    public LandUseTypeDBO findByCode(String code) {
        TypedQuery<LandUseTypeDBO> query =
                getEntityManager().createNamedQuery("LandUseTypeDBO.findByCode", LandUseTypeDBO.class);
        logger.debug(QUERY + query + " " + code);
        return query.setParameter("code", code).getSingleResult();
    }

    @Override
    public List<LandUseTypeDBO> getLandUseTypes() {

        TypedQuery<LandUseTypeDBO> query =
                getEntityManager().createNamedQuery("LandUseTypeDBO.findAll", LandUseTypeDBO.class);
        logger.debug(QUERY + query);
        return query.getResultList();
    }

    @Override
    public List<Long> typeCodesForApplication(long applicationId) {
        TypedQuery<Long> query =
                getEntityManager().createNamedQuery("LandUseTypeDBO.typeCodesForApplication", Long.class);
        logger.debug(QUERY + query);
        return query.getResultList();
    }

}
