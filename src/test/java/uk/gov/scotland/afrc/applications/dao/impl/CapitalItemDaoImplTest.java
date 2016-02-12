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

import uk.gov.scotland.afrc.applications.model.domain.CapitalItemDBO;
import uk.gov.scotland.afrc.applications.model.domain.ScheduleItemDBO;

public class CapitalItemDaoImplTest {
    
    private CapitalItemDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<CapitalItemDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new CapitalItemDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void findCapitalItemByScheduleItemTest()
    {
        Mockito.when(entityManager.createNamedQuery("CapitalItem.findByScheduleItem",CapitalItemDBO.class)).thenReturn(query);
        ScheduleItemDBO scheduleItem = new ScheduleItemDBO();
        scheduleItem.setScheduleItemId(1l);
        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findCapitalItemByScheduleItem(scheduleItem);
       

        Mockito.verify(entityManager).createNamedQuery("CapitalItem.findByScheduleItem",CapitalItemDBO.class);
        Mockito.verify(query).setParameter("scheduleItem", scheduleItem);
        Mockito.verify(query).getResultList();
    }

}
