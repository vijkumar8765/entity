package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.model.domain.CgShareReasonTypeDBO;

public interface CgShareReasonTypeDao {

	/**
	 * @param code
	 * @return List<CgShareReasonTypeDBO>
	 */
	List<CgShareReasonTypeDBO> getShareReasonByLocationId();

}
