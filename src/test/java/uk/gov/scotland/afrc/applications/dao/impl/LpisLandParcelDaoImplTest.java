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

import uk.gov.scotland.afrc.applications.model.domain.LpisLandParcelDBO;

public class LpisLandParcelDaoImplTest {

	private LpisLandParcelDaoImpl dao;

	@Mock
	private EntityManager entityManager;
	@Mock
	private TypedQuery<LpisLandParcelDBO> query;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		dao = new LpisLandParcelDaoImpl();
		dao.setEntityManager(entityManager);
	}
	@Test
	public void  testGetLandparcelsByLocationId()
	{
		Mockito.when(entityManager.createNamedQuery("LpisLandParcelDBO.findByLocationId", LpisLandParcelDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.getLandparcelsByLocationId(1l);
       

        Mockito.verify(entityManager).createNamedQuery("LpisLandParcelDBO.findByLocationId", LpisLandParcelDBO.class);
        Mockito.verify(query).setParameter("locationId", 1l);
        Mockito.verify(query).getResultList();
	}

}
