/**
 * 
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.LocationCommonGrazingHistoryDBO;

/**
 * 
 *
 */
public interface LocationCommonGrazingHistoryDao extends EntityManagerBase<LocationCommonGrazingHistoryDBO, Long> {

	/**
	 * This api will provide the Location common grazing history for the given
	 * locationCGId
	 * 
	 * @param locationCGId
	 * @return LocationCommonGrazingHistoryDBO
	 */
	List<LocationCommonGrazingHistoryDBO> getCGHistoryByLocationId(long locationCGId);
	
	/**
	*
	*
	*/
	long insertLocationCGHistory(LocationCommonGrazingHistoryDBO locationCommonGrazingHistoryDBO);

}
