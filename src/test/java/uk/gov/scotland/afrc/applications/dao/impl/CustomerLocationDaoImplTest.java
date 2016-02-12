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

import uk.gov.scotland.afrc.applications.model.domain.CustomerLocationDBO;

public class CustomerLocationDaoImplTest {

    private CustomerLocationDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<CustomerLocationDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new CustomerLocationDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void getLocationsByCodeTest() {

        Mockito.when(
                entityManager.createNamedQuery("CustomerLocation.retrieveByLocationCode", CustomerLocationDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.getLocationsByCode("locationCode");

        Mockito.verify(entityManager).createNamedQuery("CustomerLocation.retrieveByLocationCode",
                CustomerLocationDBO.class);
        Mockito.verify(query).setParameter("locationCode", "locationCode");
    }

    @Test
    public void getLocationByGrazing() {

        Mockito.when(
                entityManager.createNamedQuery("CustomerLocation.retrieveByLocationAndGrazing", CustomerLocationDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.getLocationByGrazing(1L);

        Mockito.verify(entityManager).createNamedQuery("CustomerLocation.retrieveByLocationAndGrazing",
                CustomerLocationDBO.class);
        Mockito.verify(query).setParameter("locationId", 1L);
    }
}
