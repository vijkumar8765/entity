package uk.gov.scotland.afrc.applications.dao.impl;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ApplicationDBO;

public class ApplicationDaoImplTest {

    private ApplicationDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ApplicationDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ApplicationDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void getApplicationsByCustomerAndStatusTest() {

        Mockito.when(entityManager.createNamedQuery("Application.findByBrnAndStatus", ApplicationDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.getApplicationsByCustomerAndStatus(1L, 2L);

        Mockito.verify(entityManager).createNamedQuery("Application.findByBrnAndStatus", ApplicationDBO.class);
        Mockito.verify(query).setParameter("brn", 1L);
        Mockito.verify(query).setParameter("currentStatusType", 2L);
    }

    @Test
    public void getApplicationsByCustomerTypeAndStatusTest() {

        Mockito.when(entityManager.createNamedQuery("Application.findByBrnAndTypeAndStatus", ApplicationDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.getApplicationsByCustomerTypeAndStatus(1L, 2L, 3L);

        Mockito.verify(entityManager).createNamedQuery("Application.findByBrnAndTypeAndStatus", ApplicationDBO.class);
        Mockito.verify(query).setParameter("brn", 1L);
        Mockito.verify(query).setParameter("applicationType", 2L);
        Mockito.verify(query).setParameter("currentStatusType", 3L);
    }

    @Test
    public void findByIdTest() {

        Mockito.when(entityManager.find(ApplicationDBO.class, 1L)).thenReturn(new ApplicationDBO());

        dao.findById(1L);

        Mockito.verify(entityManager).find(ApplicationDBO.class, 1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdTest_ExecptionThrown() {

        dao.findById(1L);

        Mockito.verify(entityManager).find(ApplicationDBO.class, 1L);
    }
    
    @Test
    public void getApplicationByBRNTest() {

        Mockito.when(entityManager.createNamedQuery("Application.findByBrnAndAppId", ApplicationDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyLong())).thenReturn(query);

        dao.getApplicationByBRNAndAppId(2L, 1L);

        Mockito.verify(query).setParameter("brn", 1L);
        Mockito.verify(query).setParameter("applicationId", 2L);
    }
}
