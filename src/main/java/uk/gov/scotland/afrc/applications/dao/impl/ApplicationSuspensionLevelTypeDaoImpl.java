/*
 * Project : AFRC Futures

 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationSuspensionLevelTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AppSuspensionLevelTypeDBO;

/**
 * @author d609030
 * 
 */
public class ApplicationSuspensionLevelTypeDaoImpl extends EntityManagerBaseImpl<AppSuspensionLevelTypeDBO, Long>
    implements ApplicationSuspensionLevelTypeDao
{

    private static Logger logger = Logger.getLogger(ApplicationSuspensionLevelTypeDaoImpl.class);

    public ApplicationSuspensionLevelTypeDaoImpl() {
        super(AppSuspensionLevelTypeDBO.class);
    }

    @Override
    public AppSuspensionLevelTypeDBO findByCode(long code) {
        AppSuspensionLevelTypeDBO appSuspensionLevelTypeDBO = null;
        try {
            TypedQuery<AppSuspensionLevelTypeDBO> query =
                    getEntityManager().createNamedQuery("AppSuspensionLevelTypeDBO.findByCode",
                            AppSuspensionLevelTypeDBO.class);
            query.setParameter("code", code);
            logger.debug("query = " + query.toString() + " " + code);
            appSuspensionLevelTypeDBO = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return appSuspensionLevelTypeDBO;
    }

    @Override
    public List<AppSuspensionLevelTypeDBO> findAll() {
        logger.error("List<AppSuspensionLevelTypeDBO> findAll() not implemented ");
        return null;
    }

}
