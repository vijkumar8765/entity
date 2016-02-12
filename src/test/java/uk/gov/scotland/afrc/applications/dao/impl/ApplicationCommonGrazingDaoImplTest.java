package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ApplicationCommonGrazingDBO;

public class ApplicationCommonGrazingDaoImplTest {
	
    private ApplicationCommonGrazingDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ApplicationCommonGrazingDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ApplicationCommonGrazingDaoImpl();
        dao.setEntityManager(entityManager);
    }
    
    @Test
    public void findByApplicationId() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationCommonGrazing.findByApplicationId",
                        ApplicationCommonGrazingDBO.class))
                .thenReturn(query);

        dao.findByApplicationId(1L);

        Mockito.verify(entityManager).createNamedQuery("ApplicationCommonGrazing.findByApplicationId",
                ApplicationCommonGrazingDBO.class);
    }
}
