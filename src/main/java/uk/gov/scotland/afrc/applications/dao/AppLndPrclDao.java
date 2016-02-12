package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AppLndPrclDBO;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationDBO;

public interface AppLndPrclDao extends EntityManagerBase<AppLndPrclDBO, Long> {
    List<AppLndPrclDBO> getByApplicationandLPISParcel(ApplicationDBO application, Long lpisLandParcelId);

    AppLndPrclDBO getByApplicationIdandLPISParcelId(Long applicationId, Long lpisLandParcelId);

    List<AppLndPrclDBO> findByApplicationId(Long id);

    List<AppLndPrclDBO> findByApplicationIdAndLocationId(long applicationId, long locationId);

    List<AppLndPrclDBO> getAppLndPrcls(long appId, long locationId);

    List<AppLndPrclDBO> getAppLndPrcl(long appId, long locationId, String prclId);

    List<AppLndPrclDBO> getSeasonalLndPrcls(long appId);

    List<AppLndPrclDBO> getLandSeasonalitiesByApplicationId(long applicationId);

    List<AppLndPrclDBO> findUnverifiedByApplicationId(long applicationId);

    List<AppLndPrclDBO> findByApplicationAndParcelId(long applicationId, String parcelId);
    
    List<AppLndPrclDBO> findByLPISParcelId(long lpisLndPrclId);      

    List<AppLndPrclDBO> findByApplicationAndGridReference(long applicationId, String gridReference);

    List<AppLndPrclDBO> findByApplicationIdAndSchemeId(long applicationId, long schemeId);
    
    List<AppLndPrclDBO> findDesignatedSiteAppLandParcelsByApplicationId(long applicationId);
    /**
     * This method returns list of Land parcel data by application Id 
     * @param applicationId
     * @return List of Application Land Parcel data 
     */
    List<Object> retrieveLocationsAndLandParcels(long applicationId);
    
    List<Object[]> findErrorByApplicationId(long applicationId);
    
    Boolean findByApplicationVerified(long applicationId);


    List<AppLndPrclDBO> findByLocationId(long locationId);
    
    List<AppLndPrclDBO> findNotNullParcelsByApplicationId(Long applicationId);
}
