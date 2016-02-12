package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApprovalChangeReasonTypeDBO;

public interface ApprovalChangeReasonTypeDao extends EntityManagerBase<ApprovalChangeReasonTypeDBO, Long> {

    List<ApprovalChangeReasonTypeDBO> getAllApprovalChangeReasonTypeCodes();

}
