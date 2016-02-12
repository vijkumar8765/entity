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
import org.mockito.internal.verification.Times;

import uk.gov.scotland.afrc.applications.model.domain.SchemeOptionDBO;

public class SchemeOptionDaoImplTest {
	

	private SchemeOptionDaoImpl dao;

	@Mock
	private EntityManager entityManager;
	@Mock
	private TypedQuery<SchemeOptionDBO> query;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		dao = new SchemeOptionDaoImpl();
		dao.setEntityManager(entityManager);
	}
	@Test
	public void  testFindById()
	{
		Mockito.when(entityManager.createNamedQuery("SchemeOptionDBO.findAll", SchemeOptionDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findAllSchemeOptions();
       

        Mockito.verify(entityManager).createNamedQuery("SchemeOptionDBO.findAll", SchemeOptionDBO.class);
       
        Mockito.verify(query).getResultList();
	}
	
	@Test
	public void testFindAllSchemeOptionsBySchemeId()
	{
		Mockito.when(entityManager.createNamedQuery("SchemeOptionDBO.findByScheme", SchemeOptionDBO.class)).thenReturn(query);
		Mockito.when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findAllSchemeOptionsBySchemeId(1l);
       

        Mockito.verify(entityManager).createNamedQuery("SchemeOptionDBO.findByScheme", SchemeOptionDBO.class);
        Mockito.verify(query).setParameter("schemeId", Long.valueOf(1l));
        Mockito.verify(query,new Times(2)).getResultList();
	}

}
