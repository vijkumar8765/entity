package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SafLandUseClaims;

public interface SafLandUseClaimsDao extends EntityManagerBase<SafLandUseClaims, Long>{
	
	public List<SafLandUseClaims> findByApplicationId(Long applicationId);
	
}