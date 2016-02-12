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

import uk.gov.scotland.afrc.applications.model.domain.AnnualRecurrentItemDBO;

public class AnnualRecurrentItemDaoImplTest {
    private AnnualRecurrentItemDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<AnnualRecurrentItemDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new AnnualRecurrentItemDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void findAnnualRecurrentItemByScheduleItemTest()
    {
        long scheduleItem = 1l;
        Mockito.when(entityManager.createNamedQuery("AnnualRecurrentItem.findByScheduleItem",AnnualRecurrentItemDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findAnnualRecurrentItemByScheduleItem(scheduleItem);
       

        Mockito.verify(entityManager).createNamedQuery("AnnualRecurrentItem.findByScheduleItem",AnnualRecurrentItemDBO.class);
        Mockito.verify(query).setParameter("scheduleItemId", scheduleItem);
        Mockito.verify(query).getResultList();
    }

}
