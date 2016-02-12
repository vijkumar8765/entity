package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.ApprovalDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApprovalDBO;

public class ApprovalDaoImpl extends EntityManagerBaseImpl<ApprovalDBO, Long>
		implements ApprovalDao {

	public ApprovalDaoImpl() {
		super(ApprovalDBO.class);
	}

	@Override
	public List<ApprovalDBO> findByAssessmentIdAndOutcomeType(
			long assessmentId, long approvalOutcomeTypeCode) {
		TypedQuery<ApprovalDBO> query = getEntityManager().createNamedQuery(
				"ApprovalDBO.findByAssessmentIdAndOutcomeType",
				ApprovalDBO.class);
		query.setParameter("assessmentId", assessmentId);
		query.setParameter("approvalOutcomeTypeCode", approvalOutcomeTypeCode);
		return new ArrayList<ApprovalDBO>(query.getResultList());
	}

	@Override
	public ApprovalDBO findByApprovalId(long approvalId) {
		ApprovalDBO approvalDBO = null;

		TypedQuery<ApprovalDBO> query = getEntityManager().createNamedQuery(
				"ApprovalDBO.findByApprovalId", ApprovalDBO.class);
		approvalDBO = query.getSingleResult();
		return approvalDBO;

	}

	@Override
	public boolean updateApprovalList(List<ApprovalDBO> approvals) {
		for (ApprovalDBO approvalDBO : approvals) {
			getEntityManager().merge(approvalDBO);
		}
		getEntityManager().flush();
		return true;

	}

	@Override
	public ApprovalDBO findByAssessmentId(long assessmentId) {
		ApprovalDBO approvalDBO = null;

		TypedQuery<ApprovalDBO> query = getEntityManager().createNamedQuery(
				"ApprovalDBO.findByAssessmentId", ApprovalDBO.class);
		query.setParameter("assessmentId", assessmentId);
		approvalDBO = query.getSingleResult();
		return approvalDBO;
	}

}
