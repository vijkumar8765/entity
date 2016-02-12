package uk.gov.scotland.afrc.applications.dao.impl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uk.gov.scotland.afrc.applications.model.domain.AssessmentDBO;

@RunWith(MockitoJUnitRunner.class)
public class AssessmentDaoImplTest {
	@InjectMocks
	private AssessmentDaoImpl assessemntDao=new AssessmentDaoImpl();
	@Mock
	private  EntityManager entityManager; 
	 @Mock
	 private TypedQuery<AssessmentDBO> query;
	@Test
	public void findByApplicationIdAndAssessmentTypeCodeTest(){
		Long applicationId=1l;
		Long assessmentTypeCode=1L;
		when(entityManager
		    .createNamedQuery("AssessmentDBO.findByApplicationIdAndAssessmentTypeCode", AssessmentDBO.class))
		     .thenReturn(query);
		when(query.getSingleResult()).thenReturn(mockAssessementDBO(1l));
		AssessmentDBO assessment=assessemntDao.findByApplicationIdAndAssessmentTypeCode(applicationId, assessmentTypeCode);
		assertEquals((long)assessment.getApplicationId(),1l);
		assertEquals((long)assessment.getAssessmentTypeCode(),1l);
		assertEquals((long)assessment.getAssessmentId(),1l);
		
	}
	
	@Test
	public void findByApplicationIdAndAssessmentTypeCodeExceptionTest(){
		Long applicationId=1l;
		Long assessmentTypeCode=1L;
		when(entityManager
		    .createNamedQuery("AssessmentDBO.findByApplicationIdAndAssessmentTypeCode", AssessmentDBO.class))
		     .thenReturn(query);
		doThrow(new NoResultException()).when(query).getSingleResult();
		
		AssessmentDBO assessment=assessemntDao.findByApplicationIdAndAssessmentTypeCode(applicationId, assessmentTypeCode);
		assertNull(assessment);
		
	}
	@Test
	public void findByApplicationIdTest(){	
		List<AssessmentDBO> assessmentList=mockAssessementDBOList();
		Long assessmentId=1l;		
		when(entityManager
			    .createNamedQuery("AssessmentDBO.findByApplicationId", AssessmentDBO.class))
			     .thenReturn(query);
		when(query.getResultList()).thenReturn(assessmentList);
		List<AssessmentDBO> resulttList=assessemntDao.findByApplicationId(assessmentId);
		
		for (int i=0;i<2;++i){
			assertEquals((long)resulttList.get(i).getApplicationId(),1l);
			assertEquals((long)resulttList.get(i).getAssessmentTypeCode(),1l);
			assertEquals((long)resulttList.get(i).getAssessmentId(),i);
		}
		
		
	}
	
	@Test
	public void updateAssesmentListTest(){
		List<AssessmentDBO> assessmentList=mockAssessementDBOList();		
		when(entityManager.merge(any(AssessmentDBO.class))).thenReturn(null);
		boolean status=assessemntDao.updateAssesmentList(assessmentList);
		assertEquals(status,true);		
		verify(entityManager, times(2)).merge(any(AssessmentDBO.class));
		
	}
	
	private AssessmentDBO mockAssessementDBO(long assessmentId){
		AssessmentDBO assessment=new AssessmentDBO();
		assessment.setApplicationId(1l);
		assessment.setAssessmentId(assessmentId);
		assessment.setAssessmentTypeCode(1l);
		return assessment;
	}
	private List<AssessmentDBO> mockAssessementDBOList(){
		List<AssessmentDBO> assessmentList=new ArrayList<AssessmentDBO>();
		AssessmentDBO assessment=null;
		for (long i=0;i<2;++i){
			assessment=mockAssessementDBO(i);
			assessmentList.add(assessment);
		}
		
		return assessmentList;
		
	}

}
