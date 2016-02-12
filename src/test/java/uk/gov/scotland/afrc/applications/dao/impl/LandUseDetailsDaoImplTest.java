package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.dto.LandUserDetailSummaryDTO;

public class LandUseDetailsDaoImplTest {

	@Mock
	EntityManager entityManager;
	
	@Mock
	Query query;
	
	LandUseDetailsDaoImpl dao;
	
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new LandUseDetailsDaoImpl();
        dao.setEntityManager(entityManager);
    }
    
	@Test
	public void findLandUseDetails(){
		List<LandUserDetailSummaryDTO> dto = new ArrayList<LandUserDetailSummaryDTO>();
		Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.setParameter(Mockito.anyInt(), Mockito.anyObject())).thenReturn(query);

		Mockito.when(query.getResultList()).thenReturn(dto);
		List<LandUserDetailSummaryDTO> real = dao.findLandUseDetails(6L, 7L);
		Mockito.verify(entityManager).createNativeQuery(Mockito.anyString());

		Assert.assertEquals(dto, real);
		
	}

    @Test
    public void findCGLandUseDetails() {
        List<LandUserDetailSummaryDTO> dto = new ArrayList<LandUserDetailSummaryDTO>();
        Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyInt(), Mockito.anyObject())).thenReturn(query);

        Mockito.when(query.getResultList()).thenReturn(dto);
        List<LandUserDetailSummaryDTO> real = dao.findCGLandUseDetails(6L, 7L);
        Mockito.verify(entityManager).createNativeQuery(Mockito.anyString());

        Assert.assertEquals(dto, real);

    }
}
