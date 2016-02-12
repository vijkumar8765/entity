/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationSchemeOptionDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSchemeOptionDBO;

public class ApplicationSchemeOptionDaoImpl extends EntityManagerBaseImpl<ApplicationSchemeOptionDBO, Long> implements
    ApplicationSchemeOptionDao {

    private static Logger logger = Logger.getLogger(ApplicationSchemeOptionDaoImpl.class);

    private static final String APPLICATION_ID = "applicationId";

    public ApplicationSchemeOptionDaoImpl() {
        super(ApplicationSchemeOptionDBO.class);
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationSchemeOptionDBO>
        schemeOptionsListByApplicationIdAndSchmeId(long applicationId, long schemeId) {
        logger.debug("applicationId = " + applicationId + " " + schemeId);
        return super.queryByKey("ApplicationSchemeDBO.schemeList", APPLICATION_ID, applicationId);
    }

    @Override
    public ApplicationSchemeOptionDBO create(ApplicationSchemeOptionDBO applicationSchemeOptionDBO) {
        super.create(applicationSchemeOptionDBO);

        getEntityManager().refresh(applicationSchemeOptionDBO);
        logger.debug("create = ");
        return applicationSchemeOptionDBO;
    }

    @Override
    public List<ApplicationSchemeOptionDBO> findSchemeOptionsByApplicationIdAndSchemeId(long applicationId,
                                                                                        long schemeId) {
        TypedQuery<ApplicationSchemeOptionDBO> query =
                getEntityManager().createNamedQuery("ApplicationSchemeOptionDBO.findByApplicationIdAndSchemeId",
                        ApplicationSchemeOptionDBO.class);

        logger.debug("applicationId = " + applicationId + " schemeId = " + schemeId);
        logger.debug("query = " + query.toString());
        return query.setParameter(APPLICATION_ID, Long.valueOf(applicationId))
                .setParameter("schemeId", Long.valueOf(schemeId)).getResultList();
    }

    @Override
    public List<ApplicationSchemeOptionDBO> findSchemeOptionsByApplicationId(long applicationId) {
        TypedQuery<ApplicationSchemeOptionDBO> query =
                getEntityManager().createNamedQuery("ApplicationSchemeOptionDBO.findByApplicationId",
                        ApplicationSchemeOptionDBO.class);
        logger.debug("applicationId = " + applicationId);
        logger.debug("query = " + query.toString());
        return query.setParameter(APPLICATION_ID, Long.valueOf(applicationId)).getResultList();
    }

    @Override
    public ApplicationSchemeOptionDBO findByApplicationAndSchemeOptionId(long appId, long schemeOptId) {
        TypedQuery<ApplicationSchemeOptionDBO> query =
                getEntityManager().createNamedQuery("ApplicationSchemeOptionDBO.findByApplicationAndSchemeOptionId",
                        ApplicationSchemeOptionDBO.class);
        query.setParameter(APPLICATION_ID, Long.valueOf(appId)).setParameter("schemeOptionId",
                Long.valueOf(schemeOptId));
        logger.debug("query = " + query.toString());
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e);
            return null;
        }
    }

}
