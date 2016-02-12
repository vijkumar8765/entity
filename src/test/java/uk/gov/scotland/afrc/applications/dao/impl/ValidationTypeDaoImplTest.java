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

import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeDBO;

public class ValidationTypeDaoImplTest {

    private ValidationTypeDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ValidationTypeDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ValidationTypeDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void findByNameTest() {

        Mockito.when(entityManager.createNamedQuery("ValidationTypeDBO.findByName", ValidationTypeDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.findByName("name");

        Mockito.verify(entityManager).createNamedQuery("ValidationTypeDBO.findByName", ValidationTypeDBO.class);

        Mockito.verify(query).setParameter("name", "name");
    }

}
