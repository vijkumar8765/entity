/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AppEntitlementCatgDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AppEntitlementCatgDBO;

public class AppEntitlementCatgDaoImpl extends EntityManagerBaseImpl<AppEntitlementCatgDBO, Long> implements
    AppEntitlementCatgDao {

    private static Logger logger = Logger.getLogger(AppEntitlementCatgDaoImpl.class);

    public AppEntitlementCatgDaoImpl() {
        super(AppEntitlementCatgDBO.class);
    }

    /** {@inheritDoc} */
    @Override
    public void removeByApplicationId(long applicationId) {
        TypedQuery<AppEntitlementCatgDBO> query =
                getEntityManager().createNamedQuery("AppEntitlementCatgDBO.deleteByApplicationId",
                        AppEntitlementCatgDBO.class);
        query.setParameter("applicationId", applicationId);

        logger.debug("query = " + query.toString());

        query.executeUpdate();

    }

    @Override
    public List<AppEntitlementCatgDBO> findByApplicationId(long applicationId) {
        TypedQuery<AppEntitlementCatgDBO> query =
                getEntityManager().createNamedQuery("AppEntitlementCatgDBO.findByApplicationId",
                        AppEntitlementCatgDBO.class);

        query.setParameter("applicationId", applicationId);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }

    @Override
    public List<AppEntitlementCatgDBO> findByBrnAndApplicationTypeCode(long brn, long applicationTypeCode) {
        TypedQuery<AppEntitlementCatgDBO> query =
                getEntityManager().createNamedQuery("AppEntitlementCatgDBO.findByBrnAndApplicationTypeCode",
                        AppEntitlementCatgDBO.class);

        query.setParameter("brn", brn);
        query.setParameter("applicationTypeCode", applicationTypeCode);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }

}