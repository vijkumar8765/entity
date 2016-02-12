package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.EntitlementRouteDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.EntitlementRouteDBO;

public class EntitlementRouteDaoImpl extends EntityManagerBaseImpl<EntitlementRouteDBO, Long> implements EntitlementRouteDao {
    public EntitlementRouteDaoImpl() {
        super(EntitlementRouteDBO.class);
    }

	@Override
	public List<EntitlementRouteDBO> findAll() {
        TypedQuery<EntitlementRouteDBO> query =
                getEntityManager().createNamedQuery("EntitlementRoute.findAll", EntitlementRouteDBO.class);
        return query.getResultList();
	}
}
