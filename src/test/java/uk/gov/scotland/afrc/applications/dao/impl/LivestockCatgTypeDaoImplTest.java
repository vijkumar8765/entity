package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.LivestockCatgTypeDBO;
import uk.gov.scotland.afrc.applications.model.domain.LivestockDBO;

public class LivestockCatgTypeDaoImplTest {

    private LivestockCatgTypeDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<LivestockDBO> query;

    @SuppressWarnings("unchecked")
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new LivestockCatgTypeDaoImpl();
        dao.setEntityManager(entityManager);

        Mockito.when(entityManager.createNamedQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
    }

    @Test
    public void testRetrieveLivestockByApplicationId() {

        dao.findAll();

        Mockito.verify(entityManager).createNamedQuery("LivestockCatgType.findAll", LivestockCatgTypeDBO.class);
        Mockito.verify(query).getResultList();
    }

}
