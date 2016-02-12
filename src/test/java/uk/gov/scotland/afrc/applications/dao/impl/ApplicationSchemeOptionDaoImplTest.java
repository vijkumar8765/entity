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

import uk.gov.scotland.afrc.applications.model.domain.ApplicationSchemeOptionDBO;

public class ApplicationSchemeOptionDaoImplTest {

    private ApplicationSchemeOptionDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ApplicationSchemeOptionDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ApplicationSchemeOptionDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void schemeOptionsListByApplicationIdAndSchmeIdTest() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationSchemeDBO.schemeList", ApplicationSchemeOptionDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.schemeOptionsListByApplicationIdAndSchmeId(1L, 2L);

        Mockito.verify(entityManager).createNamedQuery("ApplicationSchemeDBO.schemeList",
                ApplicationSchemeOptionDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
    }

    @Test
    public void createTest() {

        ApplicationSchemeOptionDBO dbo = new ApplicationSchemeOptionDBO();

        dao.create(dbo);

        Mockito.verify(entityManager).persist(dbo);
        Mockito.verify(entityManager).flush();
        Mockito.verify(entityManager).refresh(dbo);
    }
    
    @Test
    public void testFindSchemeOptionsByApplicationIdAndSchemeId()
    {
    	long applicationId = 1l;
    	long schemeId = 2l;
    	 Mockito.when(
                 entityManager.createNamedQuery("ApplicationSchemeOptionDBO.findByApplicationIdAndSchemeId",
                         ApplicationSchemeOptionDBO.class))
                 .thenReturn(query);

         Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

         dao.findSchemeOptionsByApplicationIdAndSchemeId(1L, 2L);

         Mockito.verify(entityManager).createNamedQuery("ApplicationSchemeOptionDBO.findByApplicationIdAndSchemeId",
                 ApplicationSchemeOptionDBO.class);
         Mockito.verify(query).setParameter("applicationId",Long.valueOf(applicationId));
         Mockito.verify(query).setParameter("schemeId", Long.valueOf(schemeId));
         Mockito.verify(query).getResultList();
    }
    
    @Test
    public void testFindSchemeOptionsByApplicationId()
    {
    	long applicationId = 1l;
    	 Mockito.when(
                 entityManager.createNamedQuery("ApplicationSchemeOptionDBO.findByApplicationId",
                         ApplicationSchemeOptionDBO.class))
                 .thenReturn(query);

         Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

         dao.findSchemeOptionsByApplicationId(1L);

         Mockito.verify(entityManager).createNamedQuery("ApplicationSchemeOptionDBO.findByApplicationId",
                 ApplicationSchemeOptionDBO.class);
         Mockito.verify(query).setParameter("applicationId",Long.valueOf(applicationId));
         Mockito.verify(query).getResultList();
    }
}
