package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.CustomerLocationDBO;
import uk.gov.scotland.afrc.applications.model.domain.LocationCommonGrazingDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public interface LocationCommonGrazingDao extends EntityManagerBase<LocationCommonGrazingDBO, Long> {

    /**
     * To find the currency type code for requested locationId
     * @param locationId
     * @return LocationCommonGrazing
     */
    List<LocationCommonGrazingDBO> getCommonGrazingByLocationId(long locationId);

    List<LocationCommonGrazingDBO> getCommonGrazingByLocationIdCroft(long locationId);

    List<LocationCommonGrazingDBO> getCommonGrazingByLocationIdCommonGrazing(long locationId);

	List<LocationCommonGrazingDBO> getLocationCommonGrazingByCGandCroft(long locationIdCG, long croftLocation);

    LocationCommonGrazingDBO getLocationCommonGrazingByCGAndCroftAndName(long locationIdCG, long locationIdCroft,
                                                                         String shareName);

    void saveLocationCommonGrazingDBO(LocationCommonGrazingDBO locationCommonGrazingDBO) throws ConcurrentAccessException;

    List<CustomerLocationDBO> getCommonGrazingsByBRN(long brn);

    CustomerLocationDBO getCGsByLocationCode(String locationCode);
    
    List<LocationCommonGrazingDBO> getLocationCommonGrazingByCG(long locationIdCG);

    LocationCommonGrazingDBO getLocationCommonGrazingByCGID(long locationIdCG);

	LocationCommonGrazingDBO getLocationCommonGrazingByCGAndName(long locationId, String croftName);   

}
