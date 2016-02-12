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

import uk.gov.scotland.afrc.applications.model.domain.AnimalLocationDBO;

public class AnimalLocationDaoImplTest {

    private AnimalLocationDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<AnimalLocationDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new AnimalLocationDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void animalLocationListByApplicationIdTest() {

        Mockito.when(entityManager.createNamedQuery("AnimalLocationDBO.animalLocationList", AnimalLocationDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.animalLocationListByApplicationId(1L);

        Mockito.verify(entityManager).createNamedQuery("AnimalLocationDBO.animalLocationList", AnimalLocationDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
    }

    @Test
    public void createTest() {

        AnimalLocationDBO animalLocationDBO = new AnimalLocationDBO();

        dao.create(animalLocationDBO);

        Mockito.verify(entityManager).persist(animalLocationDBO);
        Mockito.verify(entityManager).flush();
        Mockito.verify(entityManager).refresh(animalLocationDBO);

    }

}
