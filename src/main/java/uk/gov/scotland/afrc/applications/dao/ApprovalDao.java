package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApprovalDBO;

public interface ApprovalDao extends EntityManagerBase<ApprovalDBO, Long>{
	ApprovalDBO findByApprovalId(long approvalId);
	ApprovalDBO findByAssessmentId(long assessmentId);
	List<ApprovalDBO> findByAssessmentIdAndOutcomeType(long assessmentId,long approvalOutcomeTypeCode);
	boolean updateApprovalList(List<ApprovalDBO> approvals);
	
}
