package uk.gov.scotland.afrc.applications.dao;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AssessmentTypeDBO;

public interface AssessmentTypeDao extends EntityManagerBase<AssessmentTypeDBO, Long> {

    AssessmentTypeDBO findByAssessmentTypeName(String assessmentTypeName);
}
