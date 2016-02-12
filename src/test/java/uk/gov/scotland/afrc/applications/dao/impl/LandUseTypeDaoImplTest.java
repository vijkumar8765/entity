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

import uk.gov.scotland.afrc.applications.model.domain.LandUseTypeDBO;

public class LandUseTypeDaoImplTest {

    private LandUseTypeDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<LandUseTypeDBO> query;
    
    @Mock
    private TypedQuery<Long> queryLong;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new LandUseTypeDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void findByNameTest() {

        Mockito.when(
                entityManager.createNamedQuery("LandUseTypeDBO.findByName", LandUseTypeDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.findByName("name");

        Mockito.verify(entityManager).createNamedQuery("LandUseTypeDBO.findByName", LandUseTypeDBO.class);
        Mockito.verify(query).setParameter("name", "name");
        Mockito.verify(query).getSingleResult();

    }
    
    @Test
    public void getLandUseTypesTest()
    {
    	Mockito.when(
                entityManager.createNamedQuery("LandUseTypeDBO.findAll", LandUseTypeDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.getLandUseTypes();

        Mockito.verify(entityManager).createNamedQuery("LandUseTypeDBO.findAll", LandUseTypeDBO.class);
       
        Mockito.verify(query).getResultList();
    }
    
    @Test
    public void typeCodesForApplication()
    {
    	Mockito.when(
                entityManager.createNamedQuery("LandUseTypeDBO.typeCodesForApplication", Long.class))
                .thenReturn(queryLong);

        Mockito.when(queryLong.setParameter(anyString(), anyObject())).thenReturn(queryLong);

        dao.typeCodesForApplication(1l);

        Mockito.verify(entityManager).createNamedQuery("LandUseTypeDBO.typeCodesForApplication", Long.class);
        Mockito.verify(queryLong).getResultList();
    }

}
