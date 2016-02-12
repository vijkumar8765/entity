package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.EligibleCapitalItemTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.EligibleCapitalItemTypeDBO;

public class EligibleCapitalItemTypeDaoImpl extends
		EntityManagerBaseImpl<EligibleCapitalItemTypeDBO, Long> implements
		EligibleCapitalItemTypeDao {

	public EligibleCapitalItemTypeDaoImpl() {
		super(EligibleCapitalItemTypeDBO.class);
	}
	

	public List<EligibleCapitalItemTypeDBO> findEligibleCapitalItemTypesBySchemeOptionId(long schemeOptionId) {
		TypedQuery<EligibleCapitalItemTypeDBO> query = getEntityManager()
				.createNamedQuery(
						"EligibleCapitalItemTypeDBO.findEligibleCapitalItemTypesBySchemeOptionId",
						EligibleCapitalItemTypeDBO.class);
		return query.setParameter("schemeOptionId", Long.valueOf(schemeOptionId))
				.getResultList();
	}
}
