package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.LivestockDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class LivestockDaoImplTest {

    private static final long APPLICATIONID = 0;
    private static final long LIVESTOCK_ID = 1;

    private LivestockDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<LivestockDBO> query;

    @SuppressWarnings("unchecked")
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new LivestockDaoImpl();
        dao.setEntityManager(entityManager);

        Mockito.when(entityManager.createNamedQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
    }

    @Test
    public void testRetrieveLivestockByApplicationId() {

        dao.retrieveLivestockByApplicationId(APPLICATIONID);

        Mockito.verify(entityManager).createNamedQuery("LivestockDBO.findByApplicationId", LivestockDBO.class);
        Mockito.verify(query).setParameter("applicationId", APPLICATIONID);
        Mockito.verify(query).getResultList();
    }

    @Test
    public void testRemoveAllLivestock() throws ConcurrentAccessException {
        LivestockDBO entity = new LivestockDBO();
        List<LivestockDBO> entityList = new ArrayList<LivestockDBO>();
        entityList.add(entity);

        Mockito.when(query.getResultList()).thenReturn(entityList);

        dao.removeAllLivestock(APPLICATIONID);
        Mockito.verify(entityManager).createNamedQuery("LivestockDBO.findByApplicationId", LivestockDBO.class);
        Mockito.verify(query).setParameter("applicationId", APPLICATIONID);
        Mockito.verify(query).getResultList();
        Mockito.verify(entityManager).remove(entity);
    }

    @Test
    public void testRetrieveLivestockCatgIdByApplId() throws ConcurrentAccessException {
        dao.retrieveLivestockCatgIdByApplId(APPLICATIONID);
        Mockito.verify(entityManager).createNamedQuery("LivestockDBO.findLivestockCatgIdByAppId", Long.class);
        Mockito.verify(query).setParameter("applicationId", APPLICATIONID);
        Mockito.verify(query).getResultList();
    }

    @Test
    public void testRemoveLivestock() throws ConcurrentAccessException {
        LivestockDBO entity = new LivestockDBO();
        Mockito.when(entityManager.find(LivestockDBO.class, LIVESTOCK_ID)).thenReturn(entity);

        dao.removeLivestock(LIVESTOCK_ID);

        Mockito.verify(entityManager).find(LivestockDBO.class, LIVESTOCK_ID);
        Mockito.verify(entityManager).remove(entity);
    }

    @Test
    public void testRemoveLivestocks() throws ConcurrentAccessException {
        List<Long> livestockIdList = new ArrayList<Long>();
        livestockIdList.add(LIVESTOCK_ID);

        LivestockDBO entity = new LivestockDBO();
        Mockito.when(entityManager.find(LivestockDBO.class, LIVESTOCK_ID)).thenReturn(entity);

        dao.removeLivestocks(livestockIdList);
        Mockito.verify(entityManager).find(LivestockDBO.class, LIVESTOCK_ID);
        Mockito.verify(entityManager).remove(entity);
    }
}
