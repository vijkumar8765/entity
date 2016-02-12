/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationSuspensionReasonDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AppSuspensionReasonDBO;

/**
 * @author d609030
 * 
 */
public class ApplicationSuspensionReasonDaoImpl extends EntityManagerBaseImpl<AppSuspensionReasonDBO, Long> implements
    ApplicationSuspensionReasonDao {

    private static Logger logger = Logger.getLogger(ApplicationSuspensionReasonDaoImpl.class);

    public ApplicationSuspensionReasonDaoImpl() {
        super(AppSuspensionReasonDBO.class);
    }

    @Override
    public List<AppSuspensionReasonDBO> findAll() {
        TypedQuery<AppSuspensionReasonDBO> query =
                getEntityManager().createNamedQuery("AppSuspensionReasonDBO.findAll", AppSuspensionReasonDBO.class);

        logger.debug("query = " + query.toString());

        return query.getResultList();
    }

    @Override
    public AppSuspensionReasonDBO findByCode(long code) {
        AppSuspensionReasonDBO appSuspensionReasonDBO = null;
        try {
            TypedQuery<AppSuspensionReasonDBO> query =
                    getEntityManager().createNamedQuery("AppSuspensionReasonDBO.findByCode",
                            AppSuspensionReasonDBO.class);
            query.setParameter("code", code);
            logger.debug("query = " + query.toString());
            appSuspensionReasonDBO = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
        return appSuspensionReasonDBO;
    }

}
