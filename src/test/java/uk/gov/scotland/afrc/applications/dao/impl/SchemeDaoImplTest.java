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

import uk.gov.scotland.afrc.applications.model.domain.SchemeDBO;

public class SchemeDaoImplTest {

    private SchemeDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<SchemeDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new SchemeDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void testGetSchemeByName() {

        Mockito.when(entityManager.createNamedQuery("SchemeDBO.findByName", SchemeDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.getSchemeByName("schemeNameString");

        Mockito.verify(entityManager).createNamedQuery("SchemeDBO.findByName", SchemeDBO.class);
        Mockito.verify(query).setParameter("schemeName", "schemeNameString");
    }
}
