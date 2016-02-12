/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationSectionDao;
import uk.gov.scotland.afrc.applications.dao.ApplicationSectionTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSectionDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

/**
 * @author d607693
 *
 */
public class ApplicationSectionDaoImpl extends EntityManagerBaseImpl<ApplicationSectionDBO, Long> implements

ApplicationSectionDao {

    private static Logger logger = Logger.getLogger(ApplicationSectionDaoImpl.class);

    private ApplicationSectionTypeDao applicationSectionTypeDao;

    private static final String COLUMN_APPLICATION_ID = "applicationId";
    private static final String QUERY = "query = ";

    public ApplicationSectionDaoImpl() {
        super(ApplicationSectionDBO.class);
    }

    @Override
    public ApplicationSectionDBO getApplicationSectionByApplicationId(long applicationId, String sectionName) {
        TypedQuery<ApplicationSectionDBO> query =
                getEntityManager()
                        .createNamedQuery("ApplicationSection.findBySectionName", ApplicationSectionDBO.class);

        List<ApplicationSectionDBO> sections =
                query.setParameter(COLUMN_APPLICATION_ID, Long.valueOf(applicationId))
                        .setParameter("applicationSectionName", sectionName).getResultList();
        ApplicationSectionDBO response = null;

        logger.debug(QUERY + query.toString());

        if (sections != null && sections.size() > 0) {
            logger.debug("sections.size() = " + sections.size());
            response = sections.get(0);
        }
        return response;
    }

    @Override
    public ApplicationSectionDBO getApplicationSectionByApplicationIdAndSectionType(long applicationId,
                                                                                    long sectionTypeCode) {
        TypedQuery<ApplicationSectionDBO> query =
                getEntityManager().createNamedQuery("ApplicationSection.findByApplicationIdAndSectionType",
                        ApplicationSectionDBO.class);

        List<ApplicationSectionDBO> sections =
                query.setParameter(COLUMN_APPLICATION_ID, Long.valueOf(applicationId))
                        .setParameter("applicationSectionTypeCode", sectionTypeCode).getResultList();
        ApplicationSectionDBO response = null;
        logger.debug(QUERY + query.toString());
        if (sections != null && sections.size() > 0) {
            logger.debug("sections.size() = " + sections.size());
            response = sections.get(0);
        }
        return response;
    }

    @Override
    public List<ApplicationSectionDBO> getAllApplicationSections(long applicationId) {
        TypedQuery<ApplicationSectionDBO> query =
                getEntityManager().createNamedQuery("ApplicationSection.findByApplicationId",
                        ApplicationSectionDBO.class);

        List<ApplicationSectionDBO> sections =
                query.setParameter(COLUMN_APPLICATION_ID, Long.valueOf(applicationId)).getResultList();
        logger.debug(QUERY + query.toString());
        return sections;
    }

    @Override
    public ApplicationSectionDBO createOrUpdateApplicationSection(long applicationId, String sectionName, Long version)
        throws ConcurrentAccessException {
        ApplicationSectionDBO section = getApplicationSectionByApplicationId(applicationId, sectionName);

        ApplicationSectionDBO sectionDbo = new ApplicationSectionDBO();

        if (section == null) {
            logger.debug("section == null");
            sectionDbo.setApplicationId(applicationId);
            sectionDbo
                    .setApplicationSectionType(applicationSectionTypeDao.getApplicationSectionTypeByName(sectionName));
            return create(sectionDbo);
        }

        sectionDbo.setApplicationId(section.getApplicationId());
        sectionDbo.setApplicationSectionId(section.getApplicationSectionId());
        sectionDbo.setApplicationSectionType(applicationSectionTypeDao.getApplicationSectionTypeByName(sectionName));
        sectionDbo.setJpaVersionNumber(version);
        sectionDbo.getBaseTable().setLastUpdatedDate(new Date());
        logger.debug("section != null");
        return update(sectionDbo);
    }

    @Override
    public ApplicationSectionDBO createApplicationSectionIfNotExist(long applicationId, String sectionName) {
        ApplicationSectionDBO section = getApplicationSectionByApplicationId(applicationId, sectionName);

        if (section == null) {
            logger.debug("section == null");
            ApplicationSectionDBO sectionDbo = new ApplicationSectionDBO();
            sectionDbo.setApplicationId(applicationId);
            sectionDbo
                    .setApplicationSectionType(applicationSectionTypeDao.getApplicationSectionTypeByName(sectionName));
            return create(sectionDbo);
        }
        logger.debug("section != null");
        return section;
    }

    public ApplicationSectionTypeDao getApplicationSectionTypeDao() {
        return applicationSectionTypeDao;
    }

    public void setApplicationSectionTypeDao(ApplicationSectionTypeDao applicationSectionTypeDao) {
        this.applicationSectionTypeDao = applicationSectionTypeDao;
    }

    @Override
    public ApplicationSectionDBO findErrorSectionByApplicationId(long applicationId) {
        TypedQuery<ApplicationSectionDBO> query =
                getEntityManager().createNamedQuery("ApplicationSection.findErrorSectionByApplicationId",
                        ApplicationSectionDBO.class);

        List<ApplicationSectionDBO> sections =
                query.setParameter(COLUMN_APPLICATION_ID, Long.valueOf(applicationId))
                        .setParameter("isErrorSection", true).getResultList();
        ApplicationSectionDBO response = null;
        logger.debug(QUERY + query.toString());
        if (sections != null && sections.size() > 0) {
            logger.debug("sections.size() = " + sections.size());
            response = sections.get(0);
        }
        return response;
    }

    @Override
    public List<ApplicationSectionDBO> findByApplicationId(long applicationId) {

        TypedQuery<ApplicationSectionDBO> query =
                getEntityManager().createNamedQuery("ApplicationSection.findApplicationByID",
                        ApplicationSectionDBO.class);
        logger.debug(QUERY + query.toString());
        return query.setParameter("applicationId", Long.valueOf(applicationId)).getResultList();

    }

}
