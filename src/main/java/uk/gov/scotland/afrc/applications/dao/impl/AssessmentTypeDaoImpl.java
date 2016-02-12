package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.AssessmentTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AssessmentTypeDBO;

public class AssessmentTypeDaoImpl extends EntityManagerBaseImpl<AssessmentTypeDBO, Long> implements AssessmentTypeDao {

    public AssessmentTypeDaoImpl() {
        super(AssessmentTypeDBO.class);
    }

    @Override
    public AssessmentTypeDBO findByAssessmentTypeName(String assessmentTypeName) {
        AssessmentTypeDBO assessmentTypeDBO = null;
        try {
            TypedQuery<AssessmentTypeDBO> query =
                    getEntityManager().createNamedQuery("AssessmentTypeDBO.findByAssessmentTypeName",
                            AssessmentTypeDBO.class);
            query.setParameter("name", assessmentTypeName);
            assessmentTypeDBO = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return assessmentTypeDBO;
    }
}