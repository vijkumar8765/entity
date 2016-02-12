package uk.gov.scotland.afrc.applications.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.AppLndPrclDBO;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationDBO;

public class AppLndPrclDaoImplTest {

	private AppLndPrclDaoImpl dao;

	@Mock
	private EntityManager entityManager;
	
	@Mock
	private TypedQuery<AppLndPrclDBO> query;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		dao = new AppLndPrclDaoImpl();
		dao.setEntityManager(entityManager);
	}


//	@Test
	public void findByApplicationAndLPISParcelTest() {

		assertNotNull(query);
		
		Mockito.when(
				entityManager.createNamedQuery(
						"AppLndPrcl.findByApplicationAndLPISParcel",
						AppLndPrclDBO.class)).thenReturn(query);

		Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

		
		
		
		
		final ApplicationDBO application = new ApplicationDBO();
		application.setApplicationId(1L);
		
		dao.getByApplicationandLPISParcel(application, 3L);

		Mockito.verify(entityManager).createNamedQuery(
				"AppLndPrcl.findByApplicationAndLPISParcel",
				AppLndPrclDBO.class);
		
		Mockito.verify(query).setParameter("applicationId", 1l);
		Mockito.verify(query).setParameter("appLndPrclId", 3L);
        Mockito.verify(query).getResultList();
	}
	
	@Test
    public void findByApplicationIdTest() {

        assertNotNull(query);
        
        Mockito.when(
                entityManager.createNamedQuery(
                        "AppLndPrcl.findByApplicationId",
                        AppLndPrclDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
    
        dao.findByApplicationId(1l);

        Mockito.verify(entityManager).createNamedQuery(
                "AppLndPrcl.findByApplicationId",
                AppLndPrclDBO.class);
        
        Mockito.verify(query).setParameter("applicationId", 1l);
        Mockito.verify(query).getResultList();
    }
	
	
	
	@Test
	public void getAppLndPrclsTest()
	{
	    assertNotNull(query);
        
        Mockito.when(
                entityManager.createNamedQuery(
                        "AppLndPrclDBO.findByAppAndLocationId",
                        AppLndPrclDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.getAppLndPrcls(1l, 2l);
        Mockito.verify(entityManager).createNamedQuery(
                "AppLndPrclDBO.findByAppAndLocationId",
                AppLndPrclDBO.class);
        
        Mockito.verify(query).setParameter("appId", 1l);
        Mockito.verify(query).setParameter("locationId", 2l);
        Mockito.verify(query).getResultList();  
	}
	
	@Test
    public void getAppLndPrclswithlocIdappIdPrclIdTest()
    {
        assertNotNull(query);
        
        Mockito.when(
                entityManager.createNamedQuery(
                        "AppLndPrcl.findByAppAndLocationIdAndPrclId",
                        AppLndPrclDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.getAppLndPrcl(1l, 2l,"2l");
        
        Mockito.verify(entityManager).createNamedQuery(
                "AppLndPrcl.findByAppAndLocationIdAndPrclId",
                AppLndPrclDBO.class);
        
        Mockito.verify(query).setParameter("appId", 1l);
        Mockito.verify(query).setParameter("locationId", 2l);
        Mockito.verify(query).setParameter("prclId", "2l");
        Mockito.verify(query).getResultList();  
    }
	
	@Test
	public void getSeasonalLndPrclsTest()
	{
	    assertNotNull(query);
        
        Mockito.when(
                entityManager.createNamedQuery(
                        "AppLndPrcl.findSeasonal", AppLndPrclDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.getSeasonalLndPrcls(1l);
        Mockito.verify(entityManager).createNamedQuery(
                "AppLndPrcl.findSeasonal", AppLndPrclDBO.class);
        
        Mockito.verify(query).setParameter("appId", 1l);
        
        Mockito.verify(query).getResultList();  

	}
	
	@Test
	public void getLandSeasonalitiesByApplicationIdTest()
	{
	    assertNotNull(query);
        
        Mockito.when(
                entityManager.createNamedQuery(
                        "AppLndPrcl.findByApplicationId", AppLndPrclDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.getLandSeasonalitiesByApplicationId(1l);
        Mockito.verify(entityManager).createNamedQuery(
                "AppLndPrcl.findByApplicationId", AppLndPrclDBO.class);
        
        Mockito.verify(query).setParameter("applicationId", 1l);
        
        Mockito.verify(query).getResultList();  

	}
	
}
