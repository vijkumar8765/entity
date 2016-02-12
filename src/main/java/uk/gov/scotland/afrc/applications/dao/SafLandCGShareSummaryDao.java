package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SafLandCGShareSummary;

public interface SafLandCGShareSummaryDao extends EntityManagerBase<SafLandCGShareSummary, Long>{
	
	public List<SafLandCGShareSummary> findByApplicationId(Long applicationId);
	
}