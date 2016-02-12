package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SafLandParcelSummaryRPLMO;

public interface SafLandParcelSummaryRPMLODao extends EntityManagerBase<SafLandParcelSummaryRPLMO, Long>{
	
	public List<SafLandParcelSummaryRPLMO> findSafLandParcelByApplicationId(Long applicationId);
	
}