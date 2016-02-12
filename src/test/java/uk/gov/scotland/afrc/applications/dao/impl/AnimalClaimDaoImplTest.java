package uk.gov.scotland.afrc.applications.dao.impl;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.AnimalClaimDBO;

public class AnimalClaimDaoImplTest {

    private AnimalClaimDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<AnimalClaimDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new AnimalClaimDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void animalClaimsListByApplicationIdTest() {

        Mockito.when(
                entityManager.createNamedQuery("AnimalClaimDBO.animalClaimsListByApplication", AnimalClaimDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.animalClaimsListByApplicationId(1L);

        Mockito.verify(entityManager).createNamedQuery("AnimalClaimDBO.animalClaimsListByApplication",
                AnimalClaimDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
    }

    @Test
    public void findAnimalClaimByEarTagAndApplicationIdTest() {

        Mockito.when(entityManager.createNamedQuery("AnimalClaimDBO.findByEarTagAndApplication", AnimalClaimDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        List<AnimalClaimDBO> earTags = new ArrayList<AnimalClaimDBO>();
        earTags.add(new AnimalClaimDBO());

        Mockito.when(query.getResultList()).thenReturn(earTags);

        dao.findAnimalClaimByEarTagAndApplicationId("earTag", 1L);

        Mockito.verify(entityManager).createNamedQuery("AnimalClaimDBO.findByEarTagAndApplication",
                AnimalClaimDBO.class);
        Mockito.verify(query).setParameter("earTag", "earTag");
        Mockito.verify(query).setParameter("applicationId", 1L);

    }

    @Test
    public void findAnimalClaimByEarTagAndApplicationIdTest_NullReturned() {

        Mockito.when(entityManager.createNamedQuery("AnimalClaimDBO.findByEarTagAndApplication", AnimalClaimDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        assertNull(dao.findAnimalClaimByEarTagAndApplicationId("earTag", 1L));

        Mockito.verify(entityManager).createNamedQuery("AnimalClaimDBO.findByEarTagAndApplication",
                AnimalClaimDBO.class);
        Mockito.verify(query).setParameter("earTag", "earTag");
        Mockito.verify(query).setParameter("applicationId", 1L);

    }

    @Test
    public void animalClaimsListByEarTag() {

        Mockito.when(entityManager.createNamedQuery("AnimalClaimDBO.animalClaimsListByEarTag", AnimalClaimDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.animalClaimsListByEarTag("earTag");

        Mockito.verify(entityManager).createNamedQuery("AnimalClaimDBO.animalClaimsListByEarTag", AnimalClaimDBO.class);
        Mockito.verify(query).setParameter("earTag", "earTag");

    }
}
