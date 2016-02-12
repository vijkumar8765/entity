package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationCommonGrazingDBO;

public interface ApplicationCommonGrazingDao extends EntityManagerBase<ApplicationCommonGrazingDBO, Long>{

    ApplicationCommonGrazingDBO getByAppIdAndLocationId(long appId, long locationId);

    List<ApplicationCommonGrazingDBO> findByApplicationId(long applcaitionId);

    List<ApplicationCommonGrazingDBO> findByLocationId(long locationId);
}
