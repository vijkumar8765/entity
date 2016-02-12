package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.ApprovalChangeReasonTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApprovalChangeReasonTypeDBO;

public class ApprovalChangeReasonTypeDaoImpl extends EntityManagerBaseImpl<ApprovalChangeReasonTypeDBO, Long> implements
    ApprovalChangeReasonTypeDao {

    public ApprovalChangeReasonTypeDaoImpl() {
        super(ApprovalChangeReasonTypeDBO.class);
    }

    @Override
    public List<ApprovalChangeReasonTypeDBO> getAllApprovalChangeReasonTypeCodes() {
        TypedQuery<ApprovalChangeReasonTypeDBO> query =
                getEntityManager().createNamedQuery("ApprovalChangeReasonTypeDBO.getAllApprovalChangeReasonTypeCodes",
                        ApprovalChangeReasonTypeDBO.class);
        return new ArrayList<ApprovalChangeReasonTypeDBO>(query.getResultList());
		
    }
}