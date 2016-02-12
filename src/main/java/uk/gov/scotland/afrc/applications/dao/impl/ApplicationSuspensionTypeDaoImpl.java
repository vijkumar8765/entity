/*
 * Project : AFRC Futures

 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationSuspensionTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AppSuspensionTypeDBO;

/**
 * @author d609030
 * 
 */
public class ApplicationSuspensionTypeDaoImpl extends EntityManagerBaseImpl<AppSuspensionTypeDBO, Long> implements
    ApplicationSuspensionTypeDao
{

    private static Logger logger = Logger.getLogger(ApplicationSuspensionTypeDaoImpl.class);

    public ApplicationSuspensionTypeDaoImpl() {
        super(AppSuspensionTypeDBO.class);
    }

    @Override
    public List<AppSuspensionTypeDBO> findByBRN(long brn) {
        logger.error("List<AppSuspensionTypeDBO> findByBRN(long brn) not implemented");
        return null;
    }

    @Override
    public AppSuspensionTypeDBO findByCode(long code) {
        AppSuspensionTypeDBO appSuspensionTypeDBO = null;
        try {
            TypedQuery<AppSuspensionTypeDBO> query =
                    getEntityManager().createNamedQuery("AppSuspensionTypeDBO.findByCode", AppSuspensionTypeDBO.class);
            query.setParameter("code", code);

            logger.debug("query = " + query.toString() + " " + code);

            appSuspensionTypeDBO = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
        return appSuspensionTypeDBO;
    }

}
