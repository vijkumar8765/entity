package uk.gov.scotland.afrc.applications.dao;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.LocationClassificationDBO;

public interface LocationClassificationDao extends EntityManagerBase<LocationClassificationDBO, Long>{
	LocationClassificationDBO getByLocationId(Long locationId);
	boolean isLFALocation(Long locationId);
}
