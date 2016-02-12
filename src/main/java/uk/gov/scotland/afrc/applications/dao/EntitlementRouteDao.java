package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.EntitlementRouteDBO;

public interface EntitlementRouteDao extends EntityManagerBase<EntitlementRouteDBO, Long> {
	List<EntitlementRouteDBO> findAll();
}
