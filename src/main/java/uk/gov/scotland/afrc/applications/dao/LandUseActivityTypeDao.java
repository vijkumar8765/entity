package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.LandUseActivityTypeDBO;

public interface LandUseActivityTypeDao extends EntityManagerBase<LandUseActivityTypeDBO, Long>{

	List<LandUseActivityTypeDBO> getAllActivity();
	
	List<LandUseActivityTypeDBO> getActivity(long code);
}
