package uk.gov.scotland.afrc.applications.dao.impl;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ClaimDBO;

public class ClaimDaoImplTest {

    private ClaimDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ClaimDBO> query;

    @Mock
    Query nativeQuery;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ClaimDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void getByApplicationAndParcelId() {

        Mockito.when(entityManager.createNamedQuery("ClaimDBO.findByApplicationAndParcelId", ClaimDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.getByApplicationAndParcelId(1L, 1L);

        Mockito.verify(entityManager).createNamedQuery("ClaimDBO.findByApplicationAndParcelId", ClaimDBO.class);
        Mockito.verify(query).setParameter("appId", 1L);
        Mockito.verify(query).setParameter("appLndPrclId", 1L);
        Mockito.verify(query).getResultList();
    }

    @Test
    public void getByApplicationAndShareId() {

        Mockito.when(entityManager.createNamedQuery("ClaimDBO.findByApplicationAndShareId", ClaimDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.getByApplicationAndShareId(1L, 1L);

        Mockito.verify(entityManager).createNamedQuery("ClaimDBO.findByApplicationAndShareId", ClaimDBO.class);
        Mockito.verify(query).setParameter("appId", 1L);
        Mockito.verify(query).setParameter("appCgShareId", 1L);
        Mockito.verify(query).getResultList();
    }

    @Test
    public void getEFADetails() {

        Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(nativeQuery);
        Mockito.when(nativeQuery.setParameter(Mockito.anyInt(), Mockito.anyObject())).thenReturn(nativeQuery);
        List<Object[]> dto = new ArrayList<Object[]>();

        Mockito.when(nativeQuery.getResultList()).thenReturn(dto);
        List<Object[]> real = dao.getEFADetails(1L);
        Mockito.verify(entityManager).createNativeQuery(Mockito.anyString());

        Assert.assertEquals(dto, real);
    }
}
