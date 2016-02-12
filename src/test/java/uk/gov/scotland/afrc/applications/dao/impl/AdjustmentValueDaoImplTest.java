package uk.gov.scotland.afrc.applications.dao.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import uk.gov.scotland.afrc.applications.model.domain.AdjustmentValueDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class AdjustmentValueDaoImplTest {

	private AdjustmentValueDaoImpl dao = new AdjustmentValueDaoImpl();
	private EntityManager entityManager = mock(EntityManager.class);
	@SuppressWarnings("unchecked")
	private TypedQuery<AdjustmentValueDBO> query = mock(TypedQuery.class);
	@SuppressWarnings("unchecked")
	private TypedQuery<Long> adjustmentIdListQuery = mock(TypedQuery.class);
	private List<AdjustmentValueDBO> adjustmentValueDBOList = new ArrayList<AdjustmentValueDBO>();
	AdjustmentValueDBO mockAdjustmentValueDBOZero = mock(AdjustmentValueDBO.class);
	AdjustmentValueDBO mockAdjustmentValueDBOOne = mock(AdjustmentValueDBO.class);
	AdjustmentValueDBO mockAdjustmentValueDBOTwo = mock(AdjustmentValueDBO.class);
	AdjustmentValueDBO mockAdjustmentValueDBOThree = mock(AdjustmentValueDBO.class);
	AdjustmentValueDBO mockAdjustmentValueDBOFour = mock(AdjustmentValueDBO.class);

	@Before
	public void setUp() {
		dao.setEntityManager(entityManager);
		mockAdjustmentValueDBOZero = getMockedAdjustmentValueDBO(5001L, 101L, 5002L, 9500001L, "button");
		mockAdjustmentValueDBOZero = updateAdjustmentValues(mockAdjustmentValueDBOZero, 300L, 200L, 400L, "40", "30");
		mockAdjustmentValueDBOOne = getMockedAdjustmentValueDBO(5002L, 101L, 5003L, 9500001L, "button");
		mockAdjustmentValueDBOTwo = getMockedAdjustmentValueDBO(5003L, 101L, 5004L, 9500001L, "button");
		mockAdjustmentValueDBOThree = getMockedAdjustmentValueDBO(5004L, 101L, 5005L, 9500001L, "button");
		mockAdjustmentValueDBOFour = getMockedAdjustmentValueDBO(5005L, 101L, 5006L, 9500001L, "button");
	}

	@Test
	public void testfindByAdjustmentId() {
		adjustmentValueDBOList.add(mockAdjustmentValueDBOZero);
		when(entityManager.createNamedQuery("AdjustmentValueDBO.findByAdjustmentId", AdjustmentValueDBO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(adjustmentValueDBOList);
		List<AdjustmentValueDBO> result = dao.findByAdjustmentId(mockAdjustmentValueDBOZero.getAdjustmentId());
		assertTrue(result.size() == 1);
	}

	@Test
	public void testDeleteAdjusmentValueRows() throws ConcurrentAccessException {
		AdjustmentValueDaoImpl dao = mock(AdjustmentValueDaoImpl.class);
		when(entityManager.createNamedQuery("AdjustmentValueDBO.findById", AdjustmentValueDBO.class)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(mockAdjustmentValueDBOZero);
		dao.delete(mockAdjustmentValueDBOZero);
		verify(dao).delete(any(AdjustmentValueDBO.class));
	}

	@Test
	public void testfindLineFlagAdjustmentIdList() {
		when(entityManager.createNativeQuery("select adjt_id from (select distinct(adjustment.adjt_id), adjustment.last_updated_date from  adjt adjustment , adjt_val adjustmentvalue where adjustment.adjt_id = adjustmentvalue.adjt_id and adjustment.line_adjt_flag = 1 and adjustmentvalue.application_id=9500002 and adjustmentvalue.claim_id=1001 ORDER BY adjustment.last_updated_date)", Long.class)).thenReturn(adjustmentIdListQuery);
		when(adjustmentIdListQuery.getResultList()).thenReturn(new ArrayList<Long>(Arrays.asList(1L, 2L, 3L, 4L)));
		List<Long> result = dao.findLineFlagAdjustmentIdList(9500002L, null, 1001L, 0L, 0L);
		assertTrue(result.size() == 4);
	}
	
	
	@Test
	public void testfindLineFlagAdjustmentIdListProvidedLandDetails() {
		when(entityManager.createNativeQuery("select adjt_id from (select distinct(adjustment.adjt_id), adjustment.last_updated_date from  adjt adjustment , adjt_val adjustmentvalue where adjustment.adjt_id = adjustmentvalue.adjt_id and adjustment.line_adjt_flag = 1 and adjustmentvalue.application_id=9500002 and adjustmentvalue.claim_id=1001 ORDER BY adjustment.last_updated_date)", Long.class)).thenReturn(adjustmentIdListQuery);
		when(adjustmentIdListQuery.getResultList()).thenReturn(new ArrayList<Long>(Arrays.asList(1L, 2L, 3L, 4L)));
		List<Long> result = dao.findLineFlagAdjustmentIdListProvidedLandDetails(9500002L, null, 1001L, 0L, 0L,0L, 0L, 0L);
		assertTrue(result.size() == 4);
	}
	

	@Test
	public void testfindLoadAdjustmentValues() throws ConcurrentAccessException {
		adjustmentValueDBOList.add(mockAdjustmentValueDBOZero);
		when(entityManager.createNativeQuery("Select adjt_val_id, jpa_version_number , last_updated_date , last_updated_by, claim_id, livestock_id , lnd_use_area_id, adjt_id, data_element_code, old_val, new_val, cmt, adjt_reason_type_code, application_id , field_id , app_lnd_prcl_id, deleted_data_indicator, app_cg_id, cg_share_lnd_use_area_id  From adjt_val where application_id=9500002 and claim_id=1001 ORDER BY last_updated_date", AdjustmentValueDBO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(adjustmentValueDBOList);
		List<AdjustmentValueDBO> result = dao.findLoadAdjustmentValues(9500002L, null, 1001L, 0L, 0L);
		assertTrue(result.size() == 1);
	}
	
	
	@Test
	public void testfindLoadAdjustmentValuesProvidedLandDetails() throws ConcurrentAccessException {
		adjustmentValueDBOList.add(mockAdjustmentValueDBOZero);
		when(entityManager.createNativeQuery("Select adjt_val_id, jpa_version_number , last_updated_date , last_updated_by, claim_id, livestock_id , lnd_use_area_id, adjt_id, data_element_code, old_val, new_val, cmt, adjt_reason_type_code, application_id , field_id , app_lnd_prcl_id, deleted_data_indicator, app_cg_id, cg_share_lnd_use_area_id  From adjt_val where application_id=9500002 and claim_id=1001 ORDER BY last_updated_date", AdjustmentValueDBO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(adjustmentValueDBOList);
		List<AdjustmentValueDBO> result = dao.findLoadAdjustmentValuesProvidedLandDetails(9500002L, null, 1001L, 0L, 0L,0L, 0L, 0L);
		assertTrue(result.size() == 1);
	}
	
	

	@Test
	public void testfindLineAdjustmentAdjustmentValues() throws ConcurrentAccessException {
		adjustmentValueDBOList.add(mockAdjustmentValueDBOZero);
		when(entityManager.createNamedQuery("AdjustmentValueDBO.findByAdjustmentId", AdjustmentValueDBO.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(adjustmentValueDBOList);
		List<List<AdjustmentValueDBO>> result = dao.findLineAdjustmentAdjustmentValues(Arrays.asList(1L, 2L, 3L, 4L));
		assertTrue(result.size() == 4);
	}

	private AdjustmentValueDBO getMockedAdjustmentValueDBO(Long adjustmentId, Long reasonCode, Long adjustmentValueId, Long applicationId, String fieldId) {
		AdjustmentValueDBO mockAdjustmentValueDBO = mock(AdjustmentValueDBO.class);
		when(mockAdjustmentValueDBO.getAdjustmentId()).thenReturn(5000L);
		when(mockAdjustmentValueDBO.getAdjustmentReasonTypeCode()).thenReturn(101L);
		when(mockAdjustmentValueDBO.getAdjustmentValueID()).thenReturn(5001L);
		when(mockAdjustmentValueDBO.getApplicationId()).thenReturn(9500002L);
		when(mockAdjustmentValueDBO.getFieldId()).thenReturn("button");
		return mockAdjustmentValueDBO;
	}

	private AdjustmentValueDBO updateAdjustmentValues(AdjustmentValueDBO mockAdjustmentValueDBO, Long claimId, Long liveStockId, Long landUseAreaId, String newValue, String oldValue) {
		when(mockAdjustmentValueDBO.getClaimId()).thenReturn(claimId);
		when(mockAdjustmentValueDBO.getLivestockId()).thenReturn(landUseAreaId);
		when(mockAdjustmentValueDBO.getLndUseAreaId()).thenReturn(400L);
		when(mockAdjustmentValueDBO.getCmt()).thenReturn("comment");
		when(mockAdjustmentValueDBO.getNewValue()).thenReturn(String.valueOf(newValue));
		when(mockAdjustmentValueDBO.getOldValue()).thenReturn(String.valueOf(oldValue));
		return mockAdjustmentValueDBO;
	}

}
