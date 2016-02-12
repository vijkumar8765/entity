package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.ApprovalOutcomeTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApprovalOutcomeTypeDBO;

public class ApprovalOutcomeTypeDaoImpl extends EntityManagerBaseImpl<ApprovalOutcomeTypeDBO, Long> implements ApprovalOutcomeTypeDao {

	public ApprovalOutcomeTypeDaoImpl(Class<ApprovalOutcomeTypeDBO> type) {
		super(type);
	}
	
	public ApprovalOutcomeTypeDaoImpl() {
		super(ApprovalOutcomeTypeDBO.class);
	}


	@Override
	public Long findCodeByName(String name) {
		TypedQuery<Long> tQuery = getEntityManager().createNamedQuery("ApprovalOutcomeTypeDBO.findCodeByName", Long.class);
		tQuery.setParameter("name", name);
		return tQuery.getSingleResult();
	}

    @Override
    public List<ApprovalOutcomeTypeDBO> findApprovalOutcomeTypes() {
        TypedQuery<ApprovalOutcomeTypeDBO> query =
                getEntityManager().createNamedQuery("ApprovalOutcomeTypeDBO.findApprovalOutcomeTypes",
                        ApprovalOutcomeTypeDBO.class);
        return new ArrayList<ApprovalOutcomeTypeDBO>(query.getResultList());
    }
	
}
