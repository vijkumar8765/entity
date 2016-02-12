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

import uk.gov.scotland.afrc.applications.model.domain.CgShareClaimDBO;

public class CgShareClaimDaoImplTest {

    private CgShareClaimDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<CgShareClaimDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new CgShareClaimDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void findBySchemeAndApplicationId() {
        Mockito.when(entityManager.createNamedQuery("CgShareClaim.findBySchemeAndApplicationId", CgShareClaimDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findBySchemeAndApplicationId(1L, 1L);

        Mockito.verify(entityManager).createNamedQuery("CgShareClaim.findBySchemeAndApplicationId",
                CgShareClaimDBO.class);
        Mockito.verify(query).setParameter("schemeId", 1L);
        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).setParameter("isConfirmed", true);
        Mockito.verify(query).getResultList();
    }
}
