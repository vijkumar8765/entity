package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.EntitlementCatgDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.EntitlementCatgDBO;

public class EntitlementCatgDaoImpl extends EntityManagerBaseImpl<EntitlementCatgDBO, Long> implements EntitlementCatgDao {
    public EntitlementCatgDaoImpl() {
        super(EntitlementCatgDBO.class);
    }

	@Override
	public List<EntitlementCatgDBO> getCategoriesByApplicationTypeAndRoute(
			Long applicationTypeCode, long routeTypeCode) {
        TypedQuery<EntitlementCatgDBO> query =
                getEntityManager().createNamedQuery("EntitlementCatg.findCategoriesByApplicationTypeAndRoute", EntitlementCatgDBO.class);
        query.setParameter("applicationTypeCode", applicationTypeCode);
        query.setParameter("routeTypeCode", routeTypeCode);
        return query.getResultList();
	}

	@Override
	public EntitlementCatgDBO getByDescription(String description) {
		try {
			TypedQuery<EntitlementCatgDBO> query = getEntityManager()
					.createNamedQuery("EntitlementCatg.findByDescription",
							EntitlementCatgDBO.class);
			query.setParameter("description", description);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
