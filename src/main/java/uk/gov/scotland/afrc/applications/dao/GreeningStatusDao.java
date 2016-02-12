package uk.gov.scotland.afrc.applications.dao;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.GreeningStatusDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

/**
 * Manages all the DB Operations related to {@link uk.gov.scotland.afrc.applications.model.domain.GreeningStatusDBO} Objects.
 * 
 * @author d608985
 * 
 */
public interface GreeningStatusDao extends
		EntityManagerBase<GreeningStatusDBO, Long> {
	/**
	 * Retrieves of {@link uk.gov.scotland.afrc.applications.model.domain.GreeningStatusDBO} based on the
	 * application ID sent in the request.
	 * 
	 * @param appId
	 * @return
	 */
	GreeningStatusDBO getByApplicationId(Long appId);
	
	void deleteByApplicationId(Long appId);
	
	

	/**
	 * Stores or Updates the {@link uk.gov.scotland.afrc.applications.model.domain.GreeningStatusDBO} based on greeningStatusId.
	 * 
	 * @param greeningStatusDBO
	 * @return
	 * @throws uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException
	 */
	GreeningStatusDBO createOrUpdateGreeningStatus(GreeningStatusDBO greeningStatusDBO)throws ConcurrentAccessException;
	
	
}
