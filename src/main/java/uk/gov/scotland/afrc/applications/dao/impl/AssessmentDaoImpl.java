package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.AssessmentDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AssessmentDBO;


public class AssessmentDaoImpl extends EntityManagerBaseImpl<AssessmentDBO, Long> implements AssessmentDao{
	
	public AssessmentDaoImpl(){
		super(AssessmentDBO.class);	
	}

	@Override
	public AssessmentDBO findByApplicationIdAndAssessmentTypeCode(Long applicationId,Long assessmentTypeCode) {
		AssessmentDBO assessment=null;
		try{					
		TypedQuery<AssessmentDBO> query=getEntityManager().createNamedQuery("AssessmentDBO.findByApplicationIdAndAssessmentTypeCode", AssessmentDBO.class);
		query.setParameter("applicationId", applicationId);
		query.setParameter("assessmentTypeCode", assessmentTypeCode);
		 assessment=query.getSingleResult();
		}
		catch(Exception e){
			return null;
		}
		return assessment;
	}
	@Override
	public List<AssessmentDBO> findByApplicationId(long applicationId){
		TypedQuery<AssessmentDBO> query=getEntityManager().createNamedQuery("AssessmentDBO.findByApplicationId", AssessmentDBO.class);
		query.setParameter("applicationId", applicationId);
		return  new ArrayList<AssessmentDBO>(query.getResultList());		
		
	}
	
	@Override
	public boolean updateAssesmentList(List<AssessmentDBO> assessments){
		for(AssessmentDBO assessment:assessments){
		getEntityManager().merge(assessment);		
		
	} 
		getEntityManager().flush();
		return true;

    }
	
	@Override
	public List<Object[]> findAssessmentCounts(long applicationId) {
		// TODO Auto-generated method stub
		
		TypedQuery<Object[]> query=getEntityManager().createNamedQuery("AssessmentDBO.findAssessmentCounts", Object[].class);
		query.setParameter("applicationId", applicationId);
		return new ArrayList<Object[]>(query.getResultList());
	}

	
	
    }
