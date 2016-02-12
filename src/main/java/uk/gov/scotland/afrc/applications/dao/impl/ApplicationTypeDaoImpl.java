/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationTypeDBO;

public class ApplicationTypeDaoImpl extends EntityManagerBaseImpl<ApplicationTypeDBO, Long> implements
    ApplicationTypeDao {

    private static Logger logger = Logger.getLogger(ApplicationTypeDaoImpl.class);

    private static final String YEAR = "year";
    private static final String QUERY = "query = ";
    private static final String APPLICATION_TYPE_NOT_EMPTY = "!applicationTypes.isEmpty()";
    private static final String APPLICATION_TYPE_EMPTY = "applicationTypes.isEmpty()";

    public ApplicationTypeDaoImpl() {
        super(ApplicationTypeDBO.class);
    }

    @Override
    public List<ApplicationTypeDBO> getApplicationTypes() {
        logger.debug("getApplicationTypes");
        return super.queryAll("ApplicationType.findAll");
    }

    @Override
    public ApplicationTypeDBO getApplicationTypeByYearAndDescription(String year, String description) {

        TypedQuery<ApplicationTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationType.findByApplicableYearAndDescription",
                        ApplicationTypeDBO.class);

        List<ApplicationTypeDBO> applicationTypes =
                query.setParameter(YEAR, year).setParameter("description", description).getResultList();

        logger.debug(QUERY + query.toString());

        if (!applicationTypes.isEmpty()) {
            logger.debug(APPLICATION_TYPE_NOT_EMPTY);
            return applicationTypes.get(0);
        } else {
            logger.debug(APPLICATION_TYPE_EMPTY);
            return null;
        }
    }

    @Override
    public ApplicationTypeDBO getApplicationTypeByYearAndName(String year, String name) {

        TypedQuery<ApplicationTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationType.findByApplicableYearAndName",
                        ApplicationTypeDBO.class);

        List<ApplicationTypeDBO> applicationTypes =
                query.setParameter(YEAR, year).setParameter("name", name).getResultList();
        logger.debug(QUERY + query.toString());

        if (!applicationTypes.isEmpty()) {
            logger.debug(APPLICATION_TYPE_NOT_EMPTY);
            return applicationTypes.get(0);
        } else {
            logger.debug(APPLICATION_TYPE_EMPTY);
            return null;
        }
    }

    @Override
    public ApplicationTypeDBO getApplicationTypeByYearAndCode(String year, long code) {

        TypedQuery<ApplicationTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationTypeDBO.findByApplicableYearAndCode",
                        ApplicationTypeDBO.class);

        List<ApplicationTypeDBO> applicationTypes =
                query.setParameter(YEAR, year).setParameter("code", code).getResultList();

        if (!applicationTypes.isEmpty()) {
            logger.debug(APPLICATION_TYPE_NOT_EMPTY);
            return applicationTypes.get(0);
        } else {
            logger.debug(APPLICATION_TYPE_EMPTY);
            return null;
        }
    }

    @Override
    public ApplicationTypeDBO getApplicationTypeByCode(long applicationTypeCode) {

        TypedQuery<ApplicationTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationTypeDBO.findByApplicableYearByCode",
                        ApplicationTypeDBO.class);
        query.setParameter("applicationTypeCode", applicationTypeCode);
        logger.debug(QUERY + query.toString());
        return query.getSingleResult();
    }

    @Override
    public ApplicationTypeDBO getApplicationTypeByName(String name) {
        TypedQuery<ApplicationTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationType.findByName", ApplicationTypeDBO.class);

        List<ApplicationTypeDBO> applicationTypes = query.setParameter("name", name).getResultList();

        if (!applicationTypes.isEmpty()) {
            logger.debug(APPLICATION_TYPE_NOT_EMPTY);
            return applicationTypes.get(0);
        } else {
            logger.debug(APPLICATION_TYPE_EMPTY);
            return null;
        }
    }

    @Override
    public ApplicationTypeDBO getApplicationTypeByYearAndDocumentSubCategory(String year, long docSubCategory) {

        TypedQuery<ApplicationTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationType.findByApplicableYearAndDocSubCategory",
                        ApplicationTypeDBO.class);

        List<ApplicationTypeDBO> applicationTypes =
                query.setParameter(YEAR, year).setParameter("docSubCategoryCode", docSubCategory).getResultList();

        if (!applicationTypes.isEmpty()) {
            logger.debug(APPLICATION_TYPE_NOT_EMPTY);
            return applicationTypes.get(0);
        } else {
            logger.debug(APPLICATION_TYPE_EMPTY);
            return null;
        }

    }

}
