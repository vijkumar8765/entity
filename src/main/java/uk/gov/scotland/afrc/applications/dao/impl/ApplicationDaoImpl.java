/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationDBO;
import uk.gov.scotland.afrc.applications.model.domain.LandUseTypeDBO;
import uk.gov.scotland.afrc.applications.model.dto.CodeNameDTO;

public class ApplicationDaoImpl extends EntityManagerBaseImpl<ApplicationDBO, Long> implements ApplicationDao {

    private static Logger logger = Logger.getLogger(ApplicationDaoImpl.class);

    private static final String COLUMN_BRN = "brn";
    private static final String COLUMN_YEAR = "year";
    private static final String APPLICATION_ID = "applicationId";
    private static final String SUBMITTED_REF_NUMBER = "submittedRef";
    private static final String QUERY = "query = ";

    public ApplicationDaoImpl() {
        super(ApplicationDBO.class);
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationDBO> getApplicationsByCustomerAndStatus(long brn1, long status) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findByBrnAndStatus", ApplicationDBO.class);

        logger.debug(QUERY + query.toString() + " " + brn1 + " " + status);
        return query.setParameter(COLUMN_BRN, Long.valueOf(brn1))
                .setParameter("currentStatusType", Long.valueOf(status)).getResultList();
    }
    
    /** {@inheritDoc} */
    @Override
    public List<ApplicationDBO> getApplicationsByTypeAndStatus(Long type, Long status) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findByTypeAndStatus", ApplicationDBO.class);

        logger.debug(QUERY + query.toString() + " " + type + " " + status);
        return query.setParameter("applicationTypeCode", type)
                .setParameter("currentStatusType", status).getResultList();
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationDBO> getApplicationsByCustomerTypeAndStatus(long brn2, long type, long status) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findByBrnAndTypeAndStatus", ApplicationDBO.class);

        logger.debug(QUERY + query.toString() + " " + brn2 + " " + status + " " + type);

        return query.setParameter(COLUMN_BRN, Long.valueOf(brn2)).setParameter("applicationType", Long.valueOf(type))
                .setParameter("currentStatusType", Long.valueOf(status)).getResultList();
    }

    /** {@inheritDoc} */
    @Override
    public ApplicationDBO findById(Long id) {
        ApplicationDBO application = super.findById(id);
        if (application == null) {
            logger.error("findById error = " + id);
            throw new IllegalArgumentException("Unable to find Application: " + id);
        }

        logger.debug("findById = " + id);

        return application;
    }

    @Override
    public List<ApplicationDBO> getApplicationsByCustomerAndType(long brn3, long type) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findByBrnAndType", ApplicationDBO.class);

        logger.debug(QUERY + query.toString() + " " + brn3 + " " + type);
        return query.setParameter(COLUMN_BRN, Long.valueOf(brn3))
                .setParameter("applicationTypeCode", Long.valueOf(type)).getResultList();
    }

    @Override
    public ApplicationDBO getApplicationByBRNAndAppId(long appId, long brn4) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findByBrnAndAppId", ApplicationDBO.class);
        logger.debug(QUERY + query.toString() + " " + appId + " " + brn4);
        return query.setParameter(COLUMN_BRN, Long.valueOf(brn4)).setParameter(APPLICATION_ID, Long.valueOf(appId))
                .getSingleResult();
    }

    @Override
    public Set<LandUseTypeDBO> eligibleLandUseTypes(long appId) {
        final TypedQuery<LandUseTypeDBO> query =
                getEntityManager().createNamedQuery("Application.eligibleLandUseTypes", LandUseTypeDBO.class);

        final Set<LandUseTypeDBO> result = new HashSet<LandUseTypeDBO>();

        result.addAll(query.setParameter(APPLICATION_ID, appId).getResultList());

        logger.debug(QUERY + query.toString() + " " + appId);

        return result;
    }

    /** {@inheritDoc} */
    @Override
    public long getApplicationReferenceNumber(String applicationTypeName) {
        String sequenceName = applicationTypeName.toUpperCase() + "_APP_REF_SEQ";

        String query = "select " + sequenceName + ".NEXTVAL from dual";
        BigDecimal seq = (BigDecimal) getEntityManager().createNativeQuery(query).getResultList().get(0);

        logger.debug(QUERY + query);

        return seq.longValue();
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationDBO> getApplicationByBRNAndYear(long brn, String year) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findByBrnAndYear", ApplicationDBO.class);

        logger.debug(QUERY + query.toString() + brn + " " + year);

        return query.setParameter(COLUMN_BRN, Long.valueOf(brn)).setParameter(COLUMN_YEAR, year).getResultList();
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationDBO> getApplicationByBRN(long brn) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findByBrn", ApplicationDBO.class);
        logger.debug(QUERY + query.toString() + brn);
        return query.setParameter(COLUMN_BRN, Long.valueOf(brn)).getResultList();
    }

    @Override
    public ApplicationDBO getApplicationByID(long applicationID) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findApplicationByID", ApplicationDBO.class);
        logger.debug(QUERY + query.toString() + applicationID);
        return query.setParameter(APPLICATION_ID, Long.valueOf(applicationID)).getSingleResult();
    }

    /** {@inheritDoc} */
    @Override
    public CodeNameDTO getApplicationTypeByApplicationId(long applicationId) {
        TypedQuery<CodeNameDTO> query =
                getEntityManager()
                        .createNamedQuery("Application.findApplicationTypeByApplicationId", CodeNameDTO.class);

        List<CodeNameDTO> codeNameDTOs =
                query.setParameter(APPLICATION_ID, Long.valueOf(applicationId)).getResultList();

        logger.debug("applicationId  " + applicationId);

        if (codeNameDTOs != null && codeNameDTOs.size() > 0) {
            logger.debug("codeNameDTOs size " + codeNameDTOs.size());
            return codeNameDTOs.get(0);
        }
        return null;
    }

    @Override
    public ApplicationDBO getApplicationBySubmissionReferenceNumber(String appSubmissionReferenceNumber) {

        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findApplicationBySubmittedRefNumber",
                        ApplicationDBO.class);

        List<ApplicationDBO> applicationDBOs =
                query.setParameter(SUBMITTED_REF_NUMBER, appSubmissionReferenceNumber).getResultList();

        logger.debug("appSubmissionReferenceNumber  " + appSubmissionReferenceNumber);

        if (applicationDBOs != null) {
            logger.debug("applicationDBOs size " + applicationDBOs.size());
            return applicationDBOs.get(0);
        }

        return null;

    }

    @Override
    public List<ApplicationDBO> getLastUpdatedApplicationsByBRN(long brn) {

        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findRcentByBrn", ApplicationDBO.class);
        logger.debug("query  " + query.toString() + " " + brn);
        return query.setParameter(COLUMN_BRN, Long.valueOf(brn)).getResultList();

    }

    @Override
    public String getSubmittedRefByApplicationId(long applicationId) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.getSubmittedRefByApplicationId", ApplicationDBO.class);
        logger.debug("query  " + query.toString() + " " + applicationId);
        return query.setParameter(APPLICATION_ID, applicationId).getSingleResult().getSubmittedRef();
    }

    @Override
    public List<ApplicationDBO> findByBrnAndTypeAndRef(long brn, long type) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("Application.findByBrnAndTypeAndRef", ApplicationDBO.class);
        logger.debug("query  " + query.toString() + " " + brn + " " + type);
        return query.setParameter(COLUMN_BRN, Long.valueOf(brn))
                .setParameter("applicationTypeCode", Long.valueOf(type)).getResultList();
    }

}
