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

import uk.gov.scotland.afrc.applications.model.domain.LandUseActivityTypeDBO;

public class LandUseActivityTypeDaoImplTest {
	
    private LandUseActivityTypeDaoImpl dao;
	
	@Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<LandUseActivityTypeDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new LandUseActivityTypeDaoImpl();
        dao.setEntityManager(entityManager);
    }
    
    @Test
    public void getActivity() {

        Mockito.when(entityManager.createNamedQuery("LandUseActivityTypeDBO.findByCode", LandUseActivityTypeDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.getActivity(1L);

        Mockito.verify(entityManager).createNamedQuery("LandUseActivityTypeDBO.findByCode", LandUseActivityTypeDBO.class);
        Mockito.verify(query).setParameter("code", 1L);
        Mockito.verify(query).getResultList();
    }
    
    @Test
    public void getAllActivity() {

        Mockito.when(entityManager.createNamedQuery("LandUseActivityTypeDBO.findAll", LandUseActivityTypeDBO.class))
                .thenReturn(query);

        dao.getAllActivity();
        Mockito.verify(entityManager).createNamedQuery("LandUseActivityTypeDBO.findAll", LandUseActivityTypeDBO.class);
        Mockito.verify(query).getResultList();
    }

}
