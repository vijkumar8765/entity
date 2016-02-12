package uk.gov.scotland.afrc.applications.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uk.gov.scotland.afrc.applications.model.domain.ApprovalDBO;

@RunWith(MockitoJUnitRunner.class)
public class ApprovalDaoImplTest {

	private Date today = new Date();

	@InjectMocks
	private ApprovalDaoImpl approvalDao = new ApprovalDaoImpl();
	@Mock
	private EntityManager entityManager;
	@Mock
	private TypedQuery<ApprovalDBO> query;

	@Before
	public void setupMocks() {
		when(
				entityManager.createNamedQuery(
						"ApprovalDBO.findByAssessmentIdAndOutcomeType",
						ApprovalDBO.class)).thenReturn(query);
		when(
				entityManager.createNamedQuery(
						"ApprovalDBO.findByAssessmentId",
						ApprovalDBO.class)).thenReturn(query);

		when(
				entityManager.createNamedQuery("ApprovalDBO.findByApprovalId",
						ApprovalDBO.class)).thenReturn(query);

	}

	@Test
	public void findByAssessmentIdAndOutcomeTypeTest() {
		Long assessmentId = 1l;
		Long approvalOutcomeTypeCode = 1L;

		when(query.getResultList()).thenReturn(mockApprovalDBOList());

		List<ApprovalDBO> approvalDBOs = approvalDao
				.findByAssessmentIdAndOutcomeType(assessmentId,
						approvalOutcomeTypeCode);

		assertEquals(mockApprovalDBOList().size(), approvalDBOs.size());

		for (int i = 0; i < mockApprovalDBOList().size(); i++) {
			assertEquals((long) i, approvalDBOs.get(i).getApprovalId()
					.longValue());
		}
	}
	
	@Test
	public void findByAssessmentId() {
		
		ApprovalDBO approvalDBO = mockApprovalDBO(1L);
		when(query.getSingleResult()).thenReturn(mockApprovalDBO(1L));

		approvalDBO = approvalDao.findByAssessmentId(1L);

		assertEquals(3L, approvalDBO.getAssessmentId().longValue());
		
		}

	@Test
	public void findByAssessmentIdAndOutcomeTypeExceptionTest() {
		Long assessmentId = 1l;
		Long approvalOutcomeTypeCode = 1L;

		doThrow(new NoResultException()).when(query).getResultList();

		try {
			@SuppressWarnings("unused")
			List<ApprovalDBO> approvalDBOs = approvalDao
					.findByAssessmentIdAndOutcomeType(assessmentId,
							approvalOutcomeTypeCode);
		} catch (NoResultException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void findByApprovalIdTest() {
		ApprovalDBO approvalDBO = mockApprovalDBO(1L);

		when(query.getSingleResult()).thenReturn(mockApprovalDBO(1L));

		approvalDBO = approvalDao.findByApprovalId(1L);

		assertEquals(1L, approvalDBO.getApprovalId().longValue());
	}

	@Test
	public void findByApprovalIdExceptionTest() {
		@SuppressWarnings("unused")
		ApprovalDBO approvalDBO = mockApprovalDBO(1L);

		doThrow(new NoResultException()).when(query).getSingleResult();

		try {
			approvalDBO = approvalDao.findByApprovalId(1L);
		} catch (NoResultException e) {
			assertNotNull(e);
		}

	}

	@Test
	public void updateApprovalListTest() {
		List<ApprovalDBO> approvalList = mockApprovalDBOList();

		when(entityManager.merge(any(ApprovalDBO.class))).thenReturn(null);

		boolean status = approvalDao.updateApprovalList(approvalList);

		assertEquals(true, status);
		verify(entityManager, times(2)).merge(any(ApprovalDBO.class));

	}

	private ApprovalDBO mockApprovalDBO(long approvalId) {

		ApprovalDBO approvalDBO = new ApprovalDBO();
		approvalDBO.setApprovalId(approvalId);
		approvalDBO.setApprovalOutcomeTypeCode(approvalId + 1);
		approvalDBO.setAssessmentId(approvalId + 2);
		approvalDBO.setOpCustId(approvalId + 3);
		approvalDBO.setLastUpdatedDate(today);

		return approvalDBO;
	}

	private List<ApprovalDBO> mockApprovalDBOList() {

		List<ApprovalDBO> approvalList = new ArrayList<ApprovalDBO>();

		ApprovalDBO approval = null;

		for (long i = 0; i < 2; ++i) {
			approval = mockApprovalDBO(i);
			approvalList.add(approval);
		}

		return approvalList;
	}

}
