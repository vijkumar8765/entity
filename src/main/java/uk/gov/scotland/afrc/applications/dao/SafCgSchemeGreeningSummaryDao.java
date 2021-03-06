package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SafCgSchemeGreeningSummary;

public interface SafCgSchemeGreeningSummaryDao extends EntityManagerBase<SafCgSchemeGreeningSummary, Long>{
	
	public List<SafCgSchemeGreeningSummary> findByApplicationId(Long applicationId);
	
}