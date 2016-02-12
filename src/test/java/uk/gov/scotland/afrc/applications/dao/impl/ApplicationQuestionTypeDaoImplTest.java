package uk.gov.scotland.afrc.applications.dao.impl;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ApplicationQuestionTypeDBO;

public class ApplicationQuestionTypeDaoImplTest {

    private ApplicationQuestionTypeDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ApplicationQuestionTypeDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ApplicationQuestionTypeDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void getApplicationQuestionsTest() {

        Mockito.when(entityManager.createNamedQuery("ApplicationQuestionType.findByApplicationCode", ApplicationQuestionTypeDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        Date dt = new Date();
        
        dao.getApplicationQuestions(1, "", dt);

        Mockito.verify(entityManager).createNamedQuery("ApplicationQuestionType.findByApplicationCode", ApplicationQuestionTypeDBO.class);
        
        Mockito.verify(query).setParameter("applicationTypeCode", 1L);
        Mockito.verify(query).setParameter("questionSetName", "");
        Mockito.verify(query).setParameter("applicableYear", dt);
    }

    @Test
    public void getAllApplicationQuestionsTest() {

        Mockito.when(entityManager.createNamedQuery("ApplicationQuestionType.findAllByApplicationCode", ApplicationQuestionTypeDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        Date dt = new Date();
        
        dao.getAllApplicationQuestions(1, "", dt);

        Mockito.verify(entityManager).createNamedQuery("ApplicationQuestionType.findAllByApplicationCode", ApplicationQuestionTypeDBO.class);
        
        Mockito.verify(query).setParameter("applicationTypeCode", 1L);
        Mockito.verify(query).setParameter("questionSetName", "");
        Mockito.verify(query).setParameter("applicableYear", dt);
    }
    
    @Test
    public void getApplicationQuestionsByCodeAndYear() {
        Mockito.when(entityManager.createNamedQuery("ApplicationQuestionType.findByApplicationCodeAndYear", ApplicationQuestionTypeDBO.class))
        .thenReturn(query);
        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.getApplicationQuestions(1, new Date());
    }
    
    @Test
    public void getApplicationQuestionsByGivenType() {
        final Class<ApplicationQuestionTypeDBO> type = ApplicationQuestionTypeDBO.class;   
        
        final CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);   
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);

        final CriteriaQuery<ApplicationQuestionTypeDBO> criteriaQuery = Mockito.mock(CriteriaQuery.class);   
        Mockito.when(criteriaBuilder.createQuery(type)).thenReturn(criteriaQuery);           
           
        final Root<ApplicationQuestionTypeDBO> root = Mockito.mock(Root.class);   
        Mockito.when(criteriaQuery.from(type)).thenReturn(root); 
        Mockito.when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        dao.getApplicationQuestionsByGivenType(1, "ENTITLEMENT", "Test", new Date());
    }
	
	   
    @Test
    public void getAssessmentQuestions() {
        long assessmentTypeCode = 1L;
        Mockito.when(entityManager.createNamedQuery("ApplicationQuestionType.findByAssessmentTypeCode", ApplicationQuestionTypeDBO.class))
        .thenReturn(query);
        
        Mockito.when(query.setParameter("assessmentTypeCode", assessmentTypeCode)).thenReturn(query);
        dao.getAssessmentQuestions(assessmentTypeCode);
        
        Mockito.verify(entityManager).createNamedQuery("ApplicationQuestionType.findByAssessmentTypeCode", ApplicationQuestionTypeDBO.class);
        
        Mockito.verify(query).setParameter("assessmentTypeCode", 1L);
    }
      
    
}
