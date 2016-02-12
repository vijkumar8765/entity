package uk.gov.scotland.afrc.applications.dao.impl;

/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AppLndPrclDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AppLndPrclDBO;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationDBO;

public class AppLndPrclDaoImpl extends EntityManagerBaseImpl<AppLndPrclDBO, Long> implements AppLndPrclDao {

    private static final String APP_ID = "appId";
    private static final String LOCATION_ID = "locationId";
    private static final String COLUMN_APPLICATION_ID = "applicationId";
    private static final String QUERY = "query = ";

    private static Logger logger = Logger.getLogger(AppLndPrclDaoImpl.class);

    public AppLndPrclDaoImpl() {
        super(AppLndPrclDBO.class);
    }

    @Override
    public List<AppLndPrclDBO> getByApplicationandLPISParcel(ApplicationDBO application, Long appLndPrclId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByApplicationAndLPISParcel", AppLndPrclDBO.class);
        query.setParameter(COLUMN_APPLICATION_ID, application.getApplicationId());
        query.setParameter("appLndPrclId", appLndPrclId);
        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

    /**
     * Find by application id and LPIS land parcel id should only return one value
     */
    @Override
    public AppLndPrclDBO getByApplicationIdandLPISParcelId(Long applicationId, Long lpisLandParcelId) {
        try {
            TypedQuery<AppLndPrclDBO> query =
                    getEntityManager().createNamedQuery("AppLndPrcl.findByApplicationIdAndLPISParcelId",
                            AppLndPrclDBO.class);
            query.setParameter(COLUMN_APPLICATION_ID, applicationId);
            query.setParameter("lpisLndPrclId", lpisLandParcelId);
            logger.debug(QUERY + query.toString());
            return query.getSingleResult();
        } catch (NoResultException nre) {
            logger.error(nre);
            return null;
        }
    }

    @Override
    public List<AppLndPrclDBO> findByApplicationId(Long applicationId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByApplicationId", AppLndPrclDBO.class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

    @Override
    public List<AppLndPrclDBO> getAppLndPrcls(long appId, long locationId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrclDBO.findByAppAndLocationId", AppLndPrclDBO.class);
        logger.debug(QUERY + query.toString());
        return query.setParameter(APP_ID, appId).setParameter(LOCATION_ID, locationId).getResultList();
    }

    @Override
    public List<AppLndPrclDBO> findByApplicationAndParcelId(long applicationId, String parcelId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByApplicationIdAndParcelId", AppLndPrclDBO.class);
        query.setParameter("applicationId", applicationId);
        query.setParameter("parcelId", parcelId);
        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

    @Override
    public List<AppLndPrclDBO> getAppLndPrcl(long appId, long locationId, String prclId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByAppAndLocationIdAndPrclId", AppLndPrclDBO.class);
        logger.debug(QUERY + query.toString());
        return query.setParameter(APP_ID, appId).setParameter(LOCATION_ID, locationId).setParameter("prclId", prclId)
                .getResultList();
    }

    @Override
    public List<AppLndPrclDBO> getSeasonalLndPrcls(long appId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findSeasonal", AppLndPrclDBO.class);
        logger.debug(QUERY + query.toString());
        return query.setParameter(APP_ID, appId).getResultList();
    }

    @Override
    public List<AppLndPrclDBO> getLandSeasonalitiesByApplicationId(long applicationId) {

        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByApplicationId", AppLndPrclDBO.class);
        logger.debug(QUERY + query.toString());
        return query.setParameter(COLUMN_APPLICATION_ID, applicationId).getResultList();
    }

    @Override
    public List<AppLndPrclDBO> findUnverifiedByApplicationId(long applicationId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByApppplicationIdAndStatus", AppLndPrclDBO.class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        query.setParameter("isConfirmed", true);
        query.setParameter("isVerified", false);
        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

    @Override
    public List<AppLndPrclDBO> findByApplicationIdAndSchemeId(long applicationId, long schemeId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByApplicationIdAndSchemeId", AppLndPrclDBO.class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        query.setParameter("isConfirmed", true);
        query.setParameter("schemeId", schemeId);
        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

    @Override
    public List<AppLndPrclDBO> findByApplicationAndGridReference(long applicationId, String gridReference) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager()
                        .createNamedQuery("AppLndPrcl.findByApplicationAndGridReference", AppLndPrclDBO.class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        query.setParameter("gridReference", gridReference);
        return query.getResultList();
    }

    @Override
    public List<AppLndPrclDBO> findByApplicationIdAndLocationId(long applicationId, long locationId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByApplicationIdAndLocationId", AppLndPrclDBO.class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        query.setParameter(LOCATION_ID, locationId);
        return query.getResultList();
    }

    @Override
    public List<AppLndPrclDBO> findDesignatedSiteAppLandParcelsByApplicationId(long applicationId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findDesignatedSiteAppLandParcelsByApplicationId",
                        AppLndPrclDBO.class);
        query.setParameter("applicationId", applicationId);
        query.setParameter("isDesignatedSite", true);
        return query.getResultList();
    }

    @Override
    public List<Object> retrieveLocationsAndLandParcels(long applicationId) {
        TypedQuery<Object> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findErrorByApplicationId", Object.class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        return query.getResultList();
    }

    @Override
    public List<Object[]> findErrorByApplicationId(long applicationId) {
        TypedQuery<Object[]> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findErrorByApplicationId", Object[].class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        return query.getResultList();
    }

    @Override
    public Boolean findByApplicationVerified(long applicationId) {

        TypedQuery<Object[]> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByApppplicationVerified", Object[].class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        query.setParameter("isVerified", true);
        List<Object[]> result = query.getResultList();
        return (result != null && result.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<AppLndPrclDBO> findByLPISParcelId(long lpisLndPrclId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByLPISParcelId", AppLndPrclDBO.class);
        query.setParameter("lpisLndPrclId", lpisLndPrclId);
        return query.getResultList();
    }

    @Override
    public List<AppLndPrclDBO> findByLocationId(long locationId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findByLocationId", AppLndPrclDBO.class);
        query.setParameter(LOCATION_ID, locationId);
        return query.getResultList();
    }
    
    @Override
    public List<AppLndPrclDBO> findNotNullParcelsByApplicationId(Long applicationId) {
        TypedQuery<AppLndPrclDBO> query =
                getEntityManager().createNamedQuery("AppLndPrcl.findNotNullParcelsByApplicationId", AppLndPrclDBO.class);
        query.setParameter(COLUMN_APPLICATION_ID, applicationId);
        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

}
