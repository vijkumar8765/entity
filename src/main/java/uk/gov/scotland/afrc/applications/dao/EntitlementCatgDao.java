package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.EntitlementCatgDBO;

public interface EntitlementCatgDao extends EntityManagerBase<EntitlementCatgDBO, Long> {

	List<EntitlementCatgDBO> getCategoriesByApplicationTypeAndRoute(Long applicationTypeCode, long routeTypeCode);

	EntitlementCatgDBO getByDescription(String description);
}
