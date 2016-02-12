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

import uk.gov.scotland.afrc.applications.model.domain.LpisFeatureDBO;

public class LpisFeatureDaoImplTest {
	
	private LpisFeatureDaoImpl lpisFeatureDaoImpl;
	
	@Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<LpisFeatureDBO> query;
    @Mock
    private TypedQuery<BigDecimal> queryArea;
    @Mock
    private TypedQuery<Long> queryLong;
    
    
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        lpisFeatureDaoImpl = new LpisFeatureDaoImpl();
        lpisFeatureDaoImpl.setEntityManager(entityManager);
    }
    
    @Test
    public void testGetLpisFeatureByLandPrclId()
    {
    	Mockito.when(entityManager.createNamedQuery("LpisFeatureDBO.findBylpisLandPrclId", LpisFeatureDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        lpisFeatureDaoImpl.getLpisFeatureByLandPrclId(1l);

        Mockito.verify(entityManager).createNamedQuery("LpisFeatureDBO.findBylpisLandPrclId", LpisFeatureDBO.class);
        Mockito.verify(query).setParameter("lpisLndPrclId", 1l);
    }
    
    @Test
    public void testGetFeatureAreaByLandPrclIdAndType()
    {
        Mockito.when(entityManager.createNamedQuery("LpisFeatureDBO.sumBylpisLandPrclIdAndType", BigDecimal.class))
                .thenReturn(queryArea);
    	
        Mockito.when(queryArea.setParameter(anyString(), anyObject())).thenReturn(queryArea);
        lpisFeatureDaoImpl.getFeatureAreaByLandPrclIdAndType(1l, 1l);

        Mockito.verify(entityManager).createNamedQuery("LpisFeatureDBO.sumBylpisLandPrclIdAndType", BigDecimal.class);
    }
    
    @Test
    public void testGetLUTypesByLpisFeature()
    {
    	Mockito.when(entityManager.createNamedQuery("LpisFeatureDBO.findLUTypeBylpisLandPrclId", Long.class)).thenReturn(queryLong);

        Mockito.when(queryLong.setParameter("lpisLndPrclId", 1l)).thenReturn(queryLong);
        lpisFeatureDaoImpl.getLUTypesByLpisFeature(1l);

        Mockito.verify(entityManager).createNamedQuery("LpisFeatureDBO.findLUTypeBylpisLandPrclId", Long.class);
      
    }

    @Test
    public void getFeatureAreaByLandPrclIdAndLocation()
    {
    	Mockito.when(entityManager.createNamedQuery("LpisFeatureDBO.sumBylpisLandPrclIdAndLocationId", BigDecimal.class)).thenReturn(queryArea);

        Mockito.when(queryArea.setParameter(anyString(), anyObject())).thenReturn(queryArea);
        lpisFeatureDaoImpl.getFeatureAreaByLandPrclIdAndLocation(1l);

        Mockito.verify(entityManager).createNamedQuery("LpisFeatureDBO.sumBylpisLandPrclIdAndLocationId", BigDecimal.class);
    }

}
