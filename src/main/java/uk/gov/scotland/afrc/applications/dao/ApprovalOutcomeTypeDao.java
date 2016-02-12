package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApprovalOutcomeTypeDBO;

public interface ApprovalOutcomeTypeDao extends EntityManagerBase<ApprovalOutcomeTypeDBO, Long>{
	Long findCodeByName(String name);
	
	List<ApprovalOutcomeTypeDBO> findApprovalOutcomeTypes();
}
