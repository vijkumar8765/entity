package uk.gov.scotland.afrc.applications.dao.impl;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ApplicationQuestionTypeResponseDBO;

public class ApplicationQuestionTypeResponseDaoImplTest {

    private ApplicationQuestionTypeResponseDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ApplicationQuestionTypeResponseDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ApplicationQuestionTypeResponseDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void getQuestionResponseByApplicationIdAndQuestionTypeCodeTest() {

        Mockito.when(
                entityManager.createNamedQuery(
                        "ApplicationQuestionTypeResponseDBO.findByApplicationIdAndQuestionTypeCode",
                        ApplicationQuestionTypeResponseDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.getQuestionResponseByApplicationIdAndQuestionTypeCode(1L, 2L);

        Mockito.verify(entityManager).createNamedQuery(
                "ApplicationQuestionTypeResponseDBO.findByApplicationIdAndQuestionTypeCode",
                ApplicationQuestionTypeResponseDBO.class);

        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).setParameter("questionTypeCode", 2L);
    }

    @Test
    public void getQuestionResponseByApplicationIdTest() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationQuestionTypeResponseDBO.findByApplicationId",
                        ApplicationQuestionTypeResponseDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.getQuestionResponseByApplicationId(1L);

        Mockito.verify(entityManager).createNamedQuery("ApplicationQuestionTypeResponseDBO.findByApplicationId",
                ApplicationQuestionTypeResponseDBO.class);

        Mockito.verify(query).setParameter("applicationId", 1L);
    }

}
