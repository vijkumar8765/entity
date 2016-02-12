package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ValidationTypeCatgDaoImplTest {

    private ValidationTypeCatgDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    
    @Mock
    private TypedQuery<Object> objectQuery;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ValidationTypeCatgDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void testErrorTypes() {

        Mockito.when(
                entityManager.createNamedQuery("ValidationTypeCatgDBO.findByApplicationId",
                		Object.class)).thenReturn(objectQuery);

        Mockito.when(objectQuery.setParameter("applicationId", 1L)).thenReturn(objectQuery);

        dao.errorTypes(1L);

        Mockito.verify(objectQuery).setParameter("applicationId", 1L);
    }
    
}
