package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ApplicationSectionTypeDBO;

public class ApplicationSectionTypeDaoImplTest {

    private ApplicationSectionTypeDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ApplicationSectionTypeDBO> query;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dao = new ApplicationSectionTypeDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void testGetApplicationSectionTypeByName() {

        String name = "name";
        Mockito.when(
                entityManager.createNamedQuery("ApplicationSectionTypeDBO.findBySectionName",
                        ApplicationSectionTypeDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter("applicationSectionName", name)).thenReturn(query);
        Mockito.when(query.getSingleResult()).thenReturn(new ApplicationSectionTypeDBO());

        dao.getApplicationSectionTypeByName(name);

        Mockito.verify(query).setParameter("applicationSectionName", name);
    }
}
