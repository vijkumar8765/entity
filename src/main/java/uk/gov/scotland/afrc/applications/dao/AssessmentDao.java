package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AssessmentDBO;

public interface AssessmentDao extends EntityManagerBase<AssessmentDBO, Long>{
	AssessmentDBO findByApplicationIdAndAssessmentTypeCode(Long applicationId,Long assessmentTypeCode);
	List<AssessmentDBO> findByApplicationId(long applicationId);
	boolean updateAssesmentList(List<AssessmentDBO> assessments);
	
    List<Object[]> findAssessmentCounts(long applicationId);
}
