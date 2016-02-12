package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SafCgSchemeLocationSummary;

public interface SafCgSchemeLocationSummaryDao extends EntityManagerBase<SafCgSchemeLocationSummary, Long>{
	
	public List<SafCgSchemeLocationSummary> findByApplicationId(Long applicationId);
	
}