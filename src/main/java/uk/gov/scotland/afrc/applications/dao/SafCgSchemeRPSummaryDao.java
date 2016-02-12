package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SafCgSchemeRPSummary;

public interface SafCgSchemeRPSummaryDao extends EntityManagerBase<SafCgSchemeRPSummary, Long>{
	
	public List<SafCgSchemeRPSummary> findByApplicationId(Long applicationId);
	
}