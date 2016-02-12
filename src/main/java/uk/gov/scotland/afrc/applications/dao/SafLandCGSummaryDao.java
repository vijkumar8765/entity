package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SafLandCGSummary;

public interface SafLandCGSummaryDao extends EntityManagerBase<SafLandCGSummary, Long>{
	
	public List<SafLandCGSummary> findByApplicationId(Long applicationId);
	
}