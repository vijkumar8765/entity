package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SafLandParcels;

public interface SafLandParcelsDao extends EntityManagerBase<SafLandParcels, Long>{
	
	public List<SafLandParcels> findByApplicationId(Long applicationId);
	
}