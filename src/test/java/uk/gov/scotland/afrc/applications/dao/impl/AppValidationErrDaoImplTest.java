package uk.gov.scotland.afrc.applications.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.AppValidationErrDBO;
import uk.gov.scotland.afrc.applications.model.domain.RelatedApplicationsDBO;
import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeCatgDBO;
import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeClassDBO;
import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeDBO;
import uk.gov.scotland.afrc.applications.model.dto.ErrorCountDTO;
import uk.gov.scotland.afrc.applications.model.dto.ValidationJustificationDTO;

public class AppValidationErrDaoImplTest {

    private AppValidationErrDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<AppValidationErrDBO> query;
    @Mock
    private TypedQuery<Object> queryObject;
    @Mock
    private TypedQuery<Long> queryLong;
    
    @Mock
    private TypedQuery<Integer> queryInteger;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<Long> criteriaQueryLong;
    @Mock
    private Expression<Long> selectExpresionLong;
    @Mock
    private Root<AppValidationErrDBO> rootAppValidationErrDBO;
    @Mock
    private Path<Long> pathLong;
    @Mock
    private Path<ValidationTypeDBO> pathValidationTypeDBO;
    @Mock
    private Path<ValidationTypeCatgDBO> pathValidationTypeCatgDBO;
    @Mock
    private Path<ValidationTypeClassDBO> pathValidationTypeClassDBO;
    @Mock
    private Predicate predicate;
    @Mock
    private TypedQuery<ValidationJustificationDTO> justificationsQuery;
    @Mock    
	private Metamodel metaModel;

	@Mock    
	private EntityType<RelatedApplicationsDBO> RelatedApplicationsDBOentity;
	@Mock    
	private SingularAttribute singularAttribute;
	@Mock    
	private Join<Object, Object> join;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new AppValidationErrDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void findAppValidationErrByApplicationIdTest() {

        Mockito.when(entityManager.createNamedQuery("AppValidationErr.findByApplicationId", AppValidationErrDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.findAppValidationErrByApplicationId(1);

        Mockito.verify(entityManager).createNamedQuery("AppValidationErr.findByApplicationId",
                AppValidationErrDBO.class);

        Mockito.verify(query).setParameter("applicationId", 1L);
    }

    @Test
    public void findByApplicationAndSectionTypeCodeTest() {

        Mockito.when(
                entityManager.createNamedQuery("AppValidationErr.findByApplicationAndSectionTypeCode",
                        AppValidationErrDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.findByApplicationAndSectionTypeCode(1, 1);

        Mockito.verify(entityManager).createNamedQuery("AppValidationErr.findByApplicationAndSectionTypeCode",
                AppValidationErrDBO.class);

        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).setParameter("appSectTypeCode", 1L);
    }

    @Test
    public void findUnresolvedErrorsByApplicationIdTest() {

        Mockito.when(
                entityManager.createNamedQuery("AppValidationErr.findUnresolvedErrorsByApplicationId",
                        AppValidationErrDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.findUnresolvedErrorsByApplicationId(1);

        Mockito.verify(entityManager).createNamedQuery("AppValidationErr.findUnresolvedErrorsByApplicationId",
                AppValidationErrDBO.class);

        Mockito.verify(query).setParameter("applicationId", 1L);
    }

    @Test
    public void findCountByApplicationAndSectionTypeCodeTest() {

        Mockito.when(
                entityManager.createNamedQuery("AppValidationErr.findCountByApplicationAndSectionTypeCode",
                        Object.class)).thenReturn(queryObject);

        Mockito.when(queryObject.setParameter(anyString(), anyObject())).thenReturn(queryObject);

        dao.findCountByApplicationAndSectionTypeCode(1, 1);

        Mockito.verify(entityManager).createNamedQuery("AppValidationErr.findCountByApplicationAndSectionTypeCode",
                Object.class);

        Mockito.verify(queryObject).setParameter("applicationId", 1L);
        Mockito.verify(queryObject).setParameter("appSectTypeCode", 1L);
    }

    @Test
    public void findByApplicationIdAndValidationTypeCodeTest() {

        Mockito.when(
                entityManager.createNamedQuery("AppValidationErrDBO.findByApplicationIdAndValidationTypeCode",
                        AppValidationErrDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.findByApplicationIdAndValidationTypeCode(1, 1);

        Mockito.verify(entityManager).createNamedQuery("AppValidationErrDBO.findByApplicationIdAndValidationTypeCode",
                AppValidationErrDBO.class);

        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).setParameter("validationTypeCode", 1L);
    }

    @Test
    public void getOpenErrorCountsTest() {

        Mockito.when(entityManager.createNamedQuery("AppValidationErrDBO.getOpenErrorsCount", Long.class)).thenReturn(
                queryLong);

        Mockito.when(queryLong.setParameter(anyString(), anyObject())).thenReturn(queryLong);
        Mockito.when(queryLong.getSingleResult()).thenReturn(1L);
        
        dao.getOpenErrorCounts(1);

        Mockito.verify(entityManager).createNamedQuery("AppValidationErrDBO.getOpenErrorsCount", Long.class);
        Mockito.verify(queryLong).setParameter("applicationId", 1L);
    }
    
    @Test
    public void getOpenErrorsCountByCategoryTest() {

        Mockito.when(entityManager.createNamedQuery("AppValidationErrDBO.getOpenErrorsCountByClass", Long.class)).thenReturn(
                queryLong);

        Mockito.when(queryLong.setParameter(anyString(), anyObject())).thenReturn(queryLong);
        Mockito.when(queryLong.getSingleResult()).thenReturn(1L);
        
        dao.getOpenErrorCountsClass(1L, "INSP_SEL_ERRORS");

        Mockito.verify(entityManager).createNamedQuery("AppValidationErrDBO.getOpenErrorsCountByClass", Long.class);
        Mockito.verify(queryLong).setParameter("applicationId", 1L);
        Mockito.verify(queryLong).setParameter("className", "INSP_SEL_ERRORS");
    }

	@Test 
	public void shouldSuppressError(){
		AppValidationErrDBO appValidationErrDBO = new AppValidationErrDBO();
		dao.suppressError(appValidationErrDBO);
	}
	
	@Test
	public void getJustificationReasonsTest() {

		Mockito.when(
				entityManager.createNamedQuery(
						"AppValidationErrDBO.getJustificationReasons",
						ValidationJustificationDTO.class)).thenReturn(
				justificationsQuery);
		Mockito.when(justificationsQuery.setParameter(anyString(), anyObject())).thenReturn(justificationsQuery);
		Mockito.when(justificationsQuery.setParameter(anyString(), anyObject())).thenReturn(justificationsQuery);

		dao.getJustificationReasons(1L, 1L);
		Mockito.verify(entityManager).createNamedQuery(
				"AppValidationErrDBO.getJustificationReasons",
				ValidationJustificationDTO.class);

		Mockito.verify(justificationsQuery).setParameter("applicationId", 1L);
		Mockito.verify(justificationsQuery).setParameter("validationTypeClass", 1L);
	}  
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAppValidationErrCountTest() {
		Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		Mockito.when(entityManager.getMetamodel()).thenReturn(metaModel);
		Mockito.when(metaModel.entity(RelatedApplicationsDBO.class)).thenReturn(RelatedApplicationsDBOentity);
		Mockito.when(RelatedApplicationsDBOentity.getSingularAttribute(anyString())).thenReturn(singularAttribute);
        Mockito.when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQueryLong);
        Mockito.when(criteriaQueryLong.from(AppValidationErrDBO.class)).thenReturn(rootAppValidationErrDBO);
		Mockito.when(criteriaQueryLong.select((Selection<? extends Long>) anyObject())).thenReturn(criteriaQueryLong);
		Mockito.when(rootAppValidationErrDBO.<Long>get(anyString())).thenReturn(pathLong);
		Mockito.when(rootAppValidationErrDBO.join(AppValidationErrDaoImpl.RELATED_APPLICATIONS, JoinType.LEFT)).thenReturn(join);
		Mockito.when(join.<Long>get(anyString())).thenReturn(pathLong);
		Mockito.when(rootAppValidationErrDBO.<ValidationTypeDBO>get(anyString())).thenReturn(pathValidationTypeDBO);
		Mockito.when(pathValidationTypeDBO.<ValidationTypeClassDBO>get(anyString())).thenReturn(pathValidationTypeClassDBO);
		Mockito.when(pathValidationTypeClassDBO.<Long>get(anyString())).thenReturn(pathLong);
		Mockito.when(criteriaBuilder.count((Expression<Long>) anyObject())).thenReturn(selectExpresionLong);
		Mockito.when(criteriaBuilder.equal((Expression<?>) anyObject(),anyObject())).thenReturn(predicate);
		
        Mockito.when(entityManager.createQuery((CriteriaQuery<Long>)anyObject())).thenReturn(queryLong);
        Mockito.when(queryLong.getSingleResult()).thenReturn(1L).thenReturn(2L).thenReturn(3L).thenReturn(4L);
        
        ErrorCountDTO result = dao.getAppValidationErrCount(1L, 1l, false, null, null);
        assertEquals((long)1L, (long)result.getTotalOutstanding());       
        assertEquals((long)2L, (long)result.getTotalSuppressed());       
        assertEquals((long)3L, (long)result.getFilteredOutstanding());       
        assertEquals((long)4L, (long)result.getFilteredSuppressed());       
        
        Mockito.verify(criteriaBuilder, Mockito.times(5)).and((Predicate[]) anyObject());
        Mockito.verify(queryLong, Mockito.times(5)).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAppValidationErrCount_DontIncludeSuppressionTypeFilterIfSupressionTypeIsNull_Test() {
		Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		Mockito.when(entityManager.getMetamodel()).thenReturn(metaModel);
		Mockito.when(metaModel.entity(RelatedApplicationsDBO.class)).thenReturn(RelatedApplicationsDBOentity);
		Mockito.when(RelatedApplicationsDBOentity.getSingularAttribute(anyString())).thenReturn(singularAttribute);
        Mockito.when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQueryLong);
        Mockito.when(criteriaQueryLong.from(AppValidationErrDBO.class)).thenReturn(rootAppValidationErrDBO);
		Mockito.when(criteriaQueryLong.select((Selection<? extends Long>) anyObject())).thenReturn(criteriaQueryLong);
		Mockito.when(rootAppValidationErrDBO.<Long>get(anyString())).thenReturn(pathLong);
		Mockito.when(rootAppValidationErrDBO.join(AppValidationErrDaoImpl.RELATED_APPLICATIONS, JoinType.LEFT)).thenReturn(join);
		Mockito.when(join.<Long>get(anyString())).thenReturn(pathLong);
		Mockito.when(rootAppValidationErrDBO.<ValidationTypeDBO>get(anyString())).thenReturn(pathValidationTypeDBO);
		Mockito.when(pathValidationTypeDBO.<ValidationTypeClassDBO>get(anyString())).thenReturn(pathValidationTypeClassDBO);
		Mockito.when(pathValidationTypeClassDBO.<Long>get(anyString())).thenReturn(pathLong);
		Mockito.when(criteriaBuilder.count((Expression<Long>) anyObject())).thenReturn(selectExpresionLong);
		Mockito.when(criteriaBuilder.equal((Expression<?>) anyObject(),anyObject())).thenReturn(predicate);
		
        Mockito.when(entityManager.createQuery((CriteriaQuery<Long>)anyObject())).thenReturn(queryLong);
        Mockito.when(queryLong.getSingleResult()).thenReturn(1L).thenReturn(2L).thenReturn(3L).thenReturn(4L).thenReturn(5L);
        
        ErrorCountDTO result = dao.getAppValidationErrCount(1L, 1l, false, null, null);
        assertEquals((long)1L, (long)result.getTotalOutstanding());       
        assertEquals((long)2L, (long)result.getTotalSuppressed());       
        assertEquals((long)3L, (long)result.getFilteredOutstanding());       
        assertEquals((long)4L, (long)result.getFilteredSuppressed());       
        assertEquals((long)5L, (long)result.getFilteredSuppressedForSuppressionType());       
        
        Mockito.verify(criteriaBuilder, Mockito.times(5)).and((Predicate[]) anyObject());
        Mockito.verify(queryLong, Mockito.times(5)).getSingleResult();
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAppValidationErrCount_IncludeSuppressionTypeFilterIfSupressionTypeIsNotNull_Test() {
		Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		Mockito.when(entityManager.getMetamodel()).thenReturn(metaModel);
		Mockito.when(metaModel.entity(RelatedApplicationsDBO.class)).thenReturn(RelatedApplicationsDBOentity);
		Mockito.when(RelatedApplicationsDBOentity.getSingularAttribute(anyString())).thenReturn(singularAttribute);
        Mockito.when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQueryLong);
        Mockito.when(criteriaQueryLong.from(AppValidationErrDBO.class)).thenReturn(rootAppValidationErrDBO);
		Mockito.when(criteriaQueryLong.select((Selection<? extends Long>) anyObject())).thenReturn(criteriaQueryLong);
		Mockito.when(rootAppValidationErrDBO.<Long>get(anyString())).thenReturn(pathLong);
		Mockito.when(rootAppValidationErrDBO.join(AppValidationErrDaoImpl.RELATED_APPLICATIONS, JoinType.LEFT)).thenReturn(join);
		Mockito.when(join.<Long>get(anyString())).thenReturn(pathLong);
		Mockito.when(rootAppValidationErrDBO.<ValidationTypeDBO>get(anyString())).thenReturn(pathValidationTypeDBO);
		Mockito.when(pathValidationTypeDBO.<ValidationTypeClassDBO>get(anyString())).thenReturn(pathValidationTypeClassDBO);
		Mockito.when(pathValidationTypeClassDBO.<Long>get(anyString())).thenReturn(pathLong);
		Mockito.when(criteriaBuilder.count((Expression<Long>) anyObject())).thenReturn(selectExpresionLong);
		Mockito.when(criteriaBuilder.equal((Expression<?>) anyObject(),anyObject())).thenReturn(predicate);
		
        Mockito.when(entityManager.createQuery((CriteriaQuery<Long>)anyObject())).thenReturn(queryLong);
        Mockito.when(queryLong.getSingleResult()).thenReturn(1L).thenReturn(2L).thenReturn(3L).thenReturn(4L).thenReturn(5L);
        
        ErrorCountDTO result = dao.getAppValidationErrCount(1L, 1l, false, null, 1L);
        assertEquals((long)1L, (long)result.getTotalOutstanding());       
        assertEquals((long)2L, (long)result.getTotalSuppressed());       
        assertEquals((long)3L, (long)result.getFilteredOutstanding());       
        assertEquals((long)4L, (long)result.getFilteredSuppressed());       
        assertEquals((long)5L, (long)result.getFilteredSuppressedForSuppressionType());       
        
        Mockito.verify(criteriaBuilder, Mockito.times(6)).and((Predicate[]) anyObject());
        Mockito.verify(queryLong, Mockito.times(5)).getSingleResult();
	}
}
