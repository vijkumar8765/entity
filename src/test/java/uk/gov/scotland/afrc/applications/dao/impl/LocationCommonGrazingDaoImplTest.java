package uk.gov.scotland.afrc.applications.dao.impl;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.LocationCommonGrazingDBO;

public class LocationCommonGrazingDaoImplTest {
	
	
	private LocationCommonGrazingDaoImpl locationCommonGrazingDaoImpl;
	
	@Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<LocationCommonGrazingDBO> query;
    
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        locationCommonGrazingDaoImpl = new LocationCommonGrazingDaoImpl();
        locationCommonGrazingDaoImpl.setEntityManager(entityManager);
    }

    @Test
    public void testGetCommonGrazingByLocationId()
    {
    	long locationId= 1l;
    	Mockito.when(entityManager.createNamedQuery("LocationCommonGrazingDBO.findByLocationId", LocationCommonGrazingDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        locationCommonGrazingDaoImpl.getCommonGrazingByLocationId(1l);

        Mockito.verify(entityManager).createNamedQuery("LocationCommonGrazingDBO.findByLocationId", LocationCommonGrazingDBO.class);
        Mockito.verify(query).setParameter("locationIdCommonGrazing", new BigDecimal(locationId));
        Mockito.verify(query).getResultList();
    }
    
    @Test
    public void getCommonGrazingByLocationIdCroft()
    {
    	long locationId= 1L;
    	Mockito.when(entityManager.createNamedQuery("LocationCommonGrazingDBO.findByLocationIdCroft", LocationCommonGrazingDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        locationCommonGrazingDaoImpl.getCommonGrazingByLocationIdCroft(1L);

        Mockito.verify(entityManager).createNamedQuery("LocationCommonGrazingDBO.findByLocationIdCroft", LocationCommonGrazingDBO.class);
        Mockito.verify(query).setParameter("locationIdCroft", new BigDecimal(locationId));
        Mockito.verify(query).getResultList();
    }
    
    @Test
    public void getCommonGrazingByLocationIdCommonGrazing()
    {
    	long locationId= 1l;
    	Mockito.when(entityManager.createNamedQuery("LocationCommonGrazingDBO.findByLocationIdCommonGrazing", LocationCommonGrazingDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        locationCommonGrazingDaoImpl.getCommonGrazingByLocationIdCommonGrazing(1L);

        Mockito.verify(entityManager).createNamedQuery("LocationCommonGrazingDBO.findByLocationIdCommonGrazing", LocationCommonGrazingDBO.class);
        Mockito.verify(query).setParameter("locationIdCommonGrazing", new BigDecimal(locationId));
        Mockito.verify(query).getResultList();
    }

    @Test
    public void getLocationCommonGrazingByCGandCroft()
    {
        Mockito.when(entityManager.createNamedQuery("LocationCommonGrazingDBO.findByCGandCroft", LocationCommonGrazingDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        locationCommonGrazingDaoImpl.getLocationCommonGrazingByCGandCroft(1L,2L);

        Mockito.verify(entityManager).createNamedQuery("LocationCommonGrazingDBO.findByCGandCroft", LocationCommonGrazingDBO.class);
        Mockito.verify(query).setParameter("locationIdCommonGrazing",1L);
        Mockito.verify(query).setParameter("locationIdCroft", 2L);
        Mockito.verify(query).getResultList();
    }
}
