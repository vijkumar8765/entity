package uk.gov.scotland.afrc.applications.dao.impl;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ApplicationSectionDBO;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSectionTypeDBO;

public class ApplicationSectionDaoImplTest {

    private ApplicationSectionDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ApplicationSectionDBO> query;
    @Mock
    private ApplicationSectionTypeDaoImpl sectionType;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ApplicationSectionDaoImpl();
        dao.setEntityManager(entityManager);
        dao.setApplicationSectionTypeDao(sectionType);
    }

    @Test
    public void getApplicationSectionByApplicationIdTest() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationSection.findBySectionName", ApplicationSectionDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.getApplicationSectionByApplicationId(1L, "section");

        Mockito.verify(entityManager).createNamedQuery("ApplicationSection.findBySectionName",
                ApplicationSectionDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).setParameter("applicationSectionName", "section");
    }

    @Test
    public void getAllApplicationSectionsTest() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationSection.findByApplicationId", ApplicationSectionDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.getAllApplicationSections(1L);

        Mockito.verify(entityManager).createNamedQuery("ApplicationSection.findByApplicationId",
                ApplicationSectionDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
    }

    @Test
    public void getAllApplicationSections_MultipleSectionsTest() {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationSection.findByApplicationId", ApplicationSectionDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        List<ApplicationSectionDBO> sections = new ArrayList<ApplicationSectionDBO>();
        ApplicationSectionDBO section1 = new ApplicationSectionDBO();
        section1.setApplicationSectionType(createApplicationSectionType(1L));
        sections.add(section1);
        ApplicationSectionDBO section2 = new ApplicationSectionDBO();
        section2.setApplicationSectionType(createApplicationSectionType(2L));
        sections.add(section2);

        Mockito.when(query.getResultList()).thenReturn(sections);

        ApplicationSectionTypeDBO type = new ApplicationSectionTypeDBO();
        type.setName("type1");
        Mockito.when(sectionType.findById(1L)).thenReturn(type);

        dao.getAllApplicationSections(1L);

        Mockito.verify(entityManager).createNamedQuery("ApplicationSection.findByApplicationId",
                ApplicationSectionDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).getResultList();
        // Mockito.verify(sectionType).findById(1L);
    }

    @Test
    public void createOrUpdateApplicationSectionTest() throws Exception {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationSection.findBySectionName", ApplicationSectionDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        List<ApplicationSectionDBO> sections = new ArrayList<ApplicationSectionDBO>();
        sections.add(new ApplicationSectionDBO());

        Mockito.when(query.getResultList()).thenReturn(sections);

        dao.createOrUpdateApplicationSection(1L, "sectionName", 2L);

        Mockito.verify(entityManager).createNamedQuery("ApplicationSection.findBySectionName",
                ApplicationSectionDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).setParameter("applicationSectionName", "sectionName");
        Mockito.verify(entityManager).merge(any(ApplicationSectionDBO.class));
        Mockito.verify(entityManager).flush();
    }

    @Test
    public void createOrUpdateApplicationSectionTest_NullSections() throws Exception {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationSection.findBySectionName", ApplicationSectionDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.createOrUpdateApplicationSection(1L, "sectionName", 2L);

        Mockito.verify(entityManager).createNamedQuery("ApplicationSection.findBySectionName",
                ApplicationSectionDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).setParameter("applicationSectionName", "sectionName");
        Mockito.verify(entityManager).persist(any(ApplicationSectionDBO.class));
        Mockito.verify(entityManager).flush();
    }

    @Test
    public void createApplicationSectionIfNotExistTest() throws Exception {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationSection.findBySectionName", ApplicationSectionDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        ApplicationSectionTypeDBO type = new ApplicationSectionTypeDBO();
        type.setCode(1L);
        Mockito.when(sectionType.getApplicationSectionTypeByName("sectionName")).thenReturn(type);

        dao.createApplicationSectionIfNotExist(1L, "sectionName");

        Mockito.verify(entityManager).createNamedQuery("ApplicationSection.findBySectionName",
                ApplicationSectionDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).setParameter("applicationSectionName", "sectionName");
        Mockito.verify(entityManager).persist(any(ApplicationSectionDBO.class));
        Mockito.verify(entityManager).flush();
    }

    @Test
    public void createApplicationSectionIfNotExist_sectionTypeNotFoundTest() throws Exception {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationSection.findBySectionName", ApplicationSectionDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        dao.createApplicationSectionIfNotExist(1L, "sectionName");

        Mockito.verify(entityManager).createNamedQuery("ApplicationSection.findBySectionName",
                ApplicationSectionDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).setParameter("applicationSectionName", "sectionName");
        Mockito.verify(entityManager).persist(any(ApplicationSectionDBO.class));
        Mockito.verify(entityManager).flush();
    }

    @Test
    public void createApplicationSectionIfNotExistTest_SectionExists() throws Exception {

        Mockito.when(
                entityManager.createNamedQuery("ApplicationSection.findBySectionName", ApplicationSectionDBO.class))
                .thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);

        List<ApplicationSectionDBO> sections = new ArrayList<ApplicationSectionDBO>();
        sections.add(new ApplicationSectionDBO());

        Mockito.when(query.getResultList()).thenReturn(sections);

        dao.createApplicationSectionIfNotExist(1L, "sectionName");

        Mockito.verify(entityManager).createNamedQuery("ApplicationSection.findBySectionName",
                ApplicationSectionDBO.class);
        Mockito.verify(query).setParameter("applicationId", 1L);
        Mockito.verify(query).setParameter("applicationSectionName", "sectionName");
        Mockito.verify(entityManager, never()).persist(any(ApplicationSectionDBO.class));
        Mockito.verify(entityManager, never()).flush();
    }

    @SuppressWarnings("deprecation")
    @Test
    public void getApplicationSectionTypeDaoTest() {

        assertEquals(sectionType, dao.getApplicationSectionTypeDao());
    }

    private ApplicationSectionTypeDBO createApplicationSectionType(Long code) {
        ApplicationSectionTypeDBO result = new ApplicationSectionTypeDBO();
        result.setCode(code);
        return result;
    }
}
