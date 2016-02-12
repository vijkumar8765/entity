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

import uk.gov.scotland.afrc.applications.model.domain.CgShareLndUseAreaDBO;

public class CgShareLndUseAreaDaoImplTest {

    private CgShareLndUseAreaDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<CgShareLndUseAreaDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new CgShareLndUseAreaDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void findBySchemeAndApplicationId() {
        Mockito.when(
                entityManager.createNamedQuery("CgShareLndUseArea.findByApplicationId", CgShareLndUseAreaDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findByByApplicationId(1L);

        Mockito.verify(entityManager).createNamedQuery("CgShareLndUseArea.findByApplicationId",
                CgShareLndUseAreaDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).getResultList();
    }

    @Test
    public void findByByAppCGShareId() {
        Mockito.when(entityManager.createNamedQuery("CgShareLndUseArea.findByAppCGShareId", CgShareLndUseAreaDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findByByAppCGShareId(1L);

        Mockito.verify(entityManager).createNamedQuery("CgShareLndUseArea.findByAppCGShareId",
                CgShareLndUseAreaDBO.class);
        Mockito.verify(query).setParameter("appCGShareId", 1L);
        Mockito.verify(query).getResultList();
    }
}
