package uk.gov.scotland.afrc.applications.dao.impl;

import uk.gov.scotland.afrc.applications.dao.AssessmentElementDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AssessmentElementDBO;

public class AssessmentElementDaoImpl extends EntityManagerBaseImpl<AssessmentElementDBO, Long> implements AssessmentElementDao{

	public AssessmentElementDaoImpl() {
		super(AssessmentElementDBO.class);
	}
	
}
