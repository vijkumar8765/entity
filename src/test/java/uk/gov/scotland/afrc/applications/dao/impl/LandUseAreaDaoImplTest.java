package uk.gov.scotland.afrc.applications.dao.impl;

import static org.mockito.Matchers.anyObject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.LandUseAreaDBO;

public class LandUseAreaDaoImplTest {

    private LandUseAreaDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<LandUseAreaDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new LandUseAreaDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void creatTest() {


        final LandUseAreaDBO area=new LandUseAreaDBO();
        dao.create(area);

        Mockito.verify(entityManager).persist(anyObject());
       // Mockito.verify(entityManager).refresh(anyObject());
        
    }

}
