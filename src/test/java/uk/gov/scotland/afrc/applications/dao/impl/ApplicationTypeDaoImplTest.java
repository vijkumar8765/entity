package uk.gov.scotland.afrc.applications.dao.impl;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ApplicationTypeDBO;

public class ApplicationTypeDaoImplTest {

    private ApplicationTypeDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ApplicationTypeDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ApplicationTypeDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void getApplicationTypesTest() {

        Mockito.when(entityManager.createNamedQuery("ApplicationType.findAll", ApplicationTypeDBO.class)).thenReturn(
                query);

        dao.getApplicationTypes();

        Mockito.verify(entityManager).createNamedQuery("ApplicationType.findAll", ApplicationTypeDBO.class);
    }

    @Test
    public void getApplicationTypeByYearAndDescriptionTest() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationType.findByApplicableYearAndDescription",
                        ApplicationTypeDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        List<ApplicationTypeDBO> returnList = new ArrayList<ApplicationTypeDBO>();
        returnList.add(new ApplicationTypeDBO());
        Mockito.when(query.getResultList()).thenReturn(returnList);

        Assert.assertNotNull(dao.getApplicationTypeByYearAndDescription("year", "description"));

        Mockito.verify(entityManager).createNamedQuery("ApplicationType.findByApplicableYearAndDescription",
                ApplicationTypeDBO.class);
        Mockito.verify(query).setParameter("year", "year");
        Mockito.verify(query).setParameter("description", "description");
    }

    @Test
    public void getApplicationTypeByYearAndDescriptionTest_ApplicationTypeNotFound() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationType.findByApplicableYearAndDescription",
                        ApplicationTypeDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        Assert.assertNull(dao.getApplicationTypeByYearAndDescription("year", "description"));

        Mockito.verify(entityManager).createNamedQuery("ApplicationType.findByApplicableYearAndDescription",
                ApplicationTypeDBO.class);
        Mockito.verify(query).setParameter("year", "year");
        Mockito.verify(query).setParameter("description", "description");
    }

    @Test
    public void getApplicationTypeByYearAndNameTest() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationType.findByApplicableYearAndName", ApplicationTypeDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        List<ApplicationTypeDBO> returnList = new ArrayList<ApplicationTypeDBO>();
        returnList.add(new ApplicationTypeDBO());
        Mockito.when(query.getResultList()).thenReturn(returnList);

        Assert.assertNotNull(dao.getApplicationTypeByYearAndName("year", "name"));

        Mockito.verify(entityManager).createNamedQuery("ApplicationType.findByApplicableYearAndName",
                ApplicationTypeDBO.class);
        Mockito.verify(query).setParameter("year", "year");
        Mockito.verify(query).setParameter("name", "name");
    }

    @Test
    public void getApplicationTypeByYearAndNameTest_ApplicationTypeNotFound() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationType.findByApplicableYearAndName", ApplicationTypeDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        Assert.assertNull(dao.getApplicationTypeByYearAndName("year", "name"));

        Mockito.verify(entityManager).createNamedQuery("ApplicationType.findByApplicableYearAndName",
                ApplicationTypeDBO.class);
        Mockito.verify(query).setParameter("year", "year");
        Mockito.verify(query).setParameter("name", "name");
    }
    
    @Test
    public void testGetApplicationTypeByCode() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationTypeDBO.findByApplicableYearByCode", ApplicationTypeDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.getApplicationTypeByCode(1l);
        
        Mockito.verify(entityManager).createNamedQuery("ApplicationTypeDBO.findByApplicableYearByCode",  ApplicationTypeDBO.class);
        Mockito.verify(query).setParameter("applicationTypeCode", 1l);
        Mockito.verify(query).getSingleResult();
        
    }
    
}
