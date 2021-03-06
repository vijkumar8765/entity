package uk.gov.scotland.afrc.applications.dao;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AppEntitlementCatgStsTypeDBO;

public interface AppEntitlementCatgStsTypeDao extends EntityManagerBase<AppEntitlementCatgStsTypeDBO, Long>{
	AppEntitlementCatgStsTypeDBO findByName(String name);
}
