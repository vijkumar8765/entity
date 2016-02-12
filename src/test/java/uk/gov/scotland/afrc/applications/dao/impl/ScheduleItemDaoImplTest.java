package uk.gov.scotland.afrc.applications.dao.impl;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ScheduleItemDBO;

public class ScheduleItemDaoImplTest {

	private ScheduleItemDaoImpl dao;

	@Mock
	private EntityManager entityManager;
	@Mock
	private TypedQuery<ScheduleItemDBO> query;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		dao = new ScheduleItemDaoImpl();
		dao.setEntityManager(entityManager);
	}
	
	@Test
	public void testFindScheduleItemsByApplicationId()
	{
		long applicationId = 1l;
		Mockito.when(entityManager.createNamedQuery("ScheduleItemDBO.findByApplicationId",ScheduleItemDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findScheduleItemsByApplicationId(1l);
       

        Mockito.verify(entityManager).createNamedQuery("ScheduleItemDBO.findByApplicationId",ScheduleItemDBO.class);
        Mockito.verify(query).setParameter("applicationId", Long.valueOf(applicationId));
        Mockito.verify(query).getResultList();
	}
	
	@Test
	public void findScheduleItemDetailsByApplicationIdAndScheduleItemId()
	{
	    long applicationId = 1l;
	    long scheduleItemId = 2l;
        Mockito.when(entityManager.createNamedQuery("ScheduleItemDBO.findByApplicationIdAndScheduleItemId",ScheduleItemDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findScheduleItemDetailsByApplicationIdAndScheduleItemId(applicationId,scheduleItemId);
       

        Mockito.verify(entityManager).createNamedQuery("ScheduleItemDBO.findByApplicationIdAndScheduleItemId",ScheduleItemDBO.class);
        Mockito.verify(query).setParameter("applicationId", Long.valueOf(applicationId));
        Mockito.verify(query).setParameter("scheduleItemId", Long.valueOf(scheduleItemId));
        Mockito.verify(query).getSingleResult();
	}
	
	@Test
	public void findScheduleItemsByContractIdTest()
	{
        Date date = new Date();
        Mockito.when(entityManager.createNamedQuery("ScheduleItemDBO.findByContractId",ScheduleItemDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findScheduleItemsByContractId(1l,1l,date);
       

        Mockito.verify(entityManager).createNamedQuery("ScheduleItemDBO.findByContractId",ScheduleItemDBO.class);
        Mockito.verify(query).setParameter("managedBrn",1l);
        Mockito.verify(query).setParameter("contractId",1l);
        Mockito.verify(query).setParameter("startDate",date);
        Mockito.verify(query).getResultList(); 
	}
	
	@Test
	public void testFindScheduleItemsByContractIdAndEndDateTest1() {
		Date lmoendDate = new Date(); // should be ending by the 15th of March 2015 in the application.
		final long brn = 300011l;
		final long contractId = 1l;
		
		Mockito.when(entityManager.createNamedQuery("ScheduleItemDBO.findByContractIdAndEndDate",ScheduleItemDBO.class)).thenReturn(query);
        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findScheduleItemsByContractIdAndEndDate(brn, contractId, lmoendDate);
        
        // verify the createQuery is invoked.
        Mockito.verify(entityManager).createNamedQuery("ScheduleItemDBO.findByContractIdAndEndDate",ScheduleItemDBO.class);
        // verify the input parameters.
        Mockito.verify(query).setParameter("managedBrn", brn);
        Mockito.verify(query).setParameter("contractId", contractId);
        Mockito.verify(query).setParameter("endDate", lmoendDate);
        // verify the result list is invoked.
        Mockito.verify(query).getResultList(); 
        
	}

	@Test
	public void testFindScheduleItemsByContractIdAndEndDateTest2() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date lmoendDate = dateFormat.parse("15/05/2016");
		 
		final long brn = 300011l;
		final long contractId = 1l;
		
		Mockito.when(entityManager.createNamedQuery("ScheduleItemDBO.findByContractIdAndEndDate",ScheduleItemDBO.class)).thenReturn(query);
        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        dao.findScheduleItemsByContractIdAndEndDate(brn, contractId, lmoendDate);
        
        // verify the createQuery is invoked.
        Mockito.verify(entityManager).createNamedQuery("ScheduleItemDBO.findByContractIdAndEndDate",ScheduleItemDBO.class);
        // verify the input parameters.
        Mockito.verify(query).setParameter("managedBrn", brn);
        Mockito.verify(query).setParameter("contractId", contractId);
        Mockito.verify(query).setParameter("endDate", lmoendDate);
        // verify the result list is invoked.
        Mockito.verify(query).getResultList(); 
        
	}
}
