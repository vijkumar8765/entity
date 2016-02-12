package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ApplicationCommonGrazingDBO;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationCommonGrazingShareDBO;

public class ApplicationCommonGrazingShareDaoImplTest {
	
    private ApplicationCommonGrazingShareDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ApplicationCommonGrazingShareDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ApplicationCommonGrazingShareDaoImpl();
        dao.setEntityManager(entityManager);
    }
    
    @Test
    public void getByAppCGId() {

        Mockito.when(
                entityManager.createNamedQuery("AppCgShareBDO.findByAppCG", ApplicationCommonGrazingShareDBO.class))
                .thenReturn(query);

        dao.getByAppCGId(new ApplicationCommonGrazingDBO());

        Mockito.verify(entityManager).createNamedQuery("AppCgShareBDO.findByAppCG",
                ApplicationCommonGrazingShareDBO.class);
    }

    @Test
    public void getActiveShareByAppCGId() {

        Mockito.when(
                entityManager.createNamedQuery("AppCgShareBDO.getActiveSharesByAppCG",
                        ApplicationCommonGrazingShareDBO.class)).thenReturn(query);

        dao.getActiveShareByAppCGId(new ApplicationCommonGrazingDBO());

        Mockito.verify(entityManager).createNamedQuery("AppCgShareBDO.getActiveSharesByAppCG",
                ApplicationCommonGrazingShareDBO.class);
    }
}
