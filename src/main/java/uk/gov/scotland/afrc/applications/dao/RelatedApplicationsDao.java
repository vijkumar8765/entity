/**
 * 
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.RelatedApplicationsDBO;

/**
 * @author d609094
 *
 */
public interface RelatedApplicationsDao extends EntityManagerBase<RelatedApplicationsDBO, Long> {
	
	/**
	 * @param applicationID
	 * @return relatedApplications
	 */
	List<RelatedApplicationsDBO> getRelatedApplications(long crossErrorID);
	
	/**
	 * 
	 * @param appId
	 * @return brn
	 */
	long getBrnfromAssociatedApplication(long appId);
}
