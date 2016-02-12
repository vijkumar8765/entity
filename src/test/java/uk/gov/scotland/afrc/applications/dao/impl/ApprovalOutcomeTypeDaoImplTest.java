package uk.gov.scotland.afrc.applications.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uk.gov.scotland.afrc.applications.model.domain.ApprovalOutcomeTypeDBO;

@RunWith(MockitoJUnitRunner.class)
public class ApprovalOutcomeTypeDaoImplTest {

	@InjectMocks
	private ApprovalOutcomeTypeDaoImpl approvalOutcomeTypeDao = new ApprovalOutcomeTypeDaoImpl();
	
	@Mock
	private EntityManager entityManager; 
	
	@Mock
	private TypedQuery<Long> queryLongType;
	
	@Mock
	private TypedQuery<ApprovalOutcomeTypeDBO> queryApprovalOutcomeType;
	
	@Before
	public void setupMocks() {
		// return Long type query 
		when(
				entityManager.createNamedQuery(
						"ApprovalOutcomeTypeDBO.findCodeByName", 
						Long.class)).thenReturn(queryLongType);
		// return ApprovalOutcomeTypeDBO type query
		when(
				entityManager.createNamedQuery(
						"ApprovalOutcomeTypeDBO.findApprovalOutcomeTypes",
                        ApprovalOutcomeTypeDBO.class)).thenReturn(queryApprovalOutcomeType);
	}
	
	/**
	 * Test for the method [ findCodeByName ]
	 */
	@Test
	public void findCodeByNameTest() {
		String name = "Approval Name";
		
		when(queryLongType.getSingleResult()).thenReturn(1l);
		long code = approvalOutcomeTypeDao.findCodeByName(name);
		assertEquals(1l, code);
	}
	
	/**
	 * Test for the method [ findApprovalOutcomeTypes ]
	 */
	@Test
	public void findApprovalOutcomeTypesTest() {
		
		when(queryApprovalOutcomeType.getResultList()).thenReturn(mockApprovalOutcomeTypeDBOList());
		
		List<ApprovalOutcomeTypeDBO> approvalOutcomeTypeDBOs = approvalOutcomeTypeDao.findApprovalOutcomeTypes();
		assertEquals(mockApprovalOutcomeTypeDBOList().size(), approvalOutcomeTypeDBOs.size());
		for(int i = 0; i < mockApprovalOutcomeTypeDBOList().size(); i++) {
			assertEquals((long) i, approvalOutcomeTypeDBOs.get(i).getCode().longValue());
		}
	}
	
	// mock -  List<ApprovalOutcomeTypeDBO>
	private List<ApprovalOutcomeTypeDBO> mockApprovalOutcomeTypeDBOList() {
		List<ApprovalOutcomeTypeDBO> approvalOutcomeTypeList = new ArrayList<ApprovalOutcomeTypeDBO>();
		for(int i = 0; i < 2 ; i++){
			approvalOutcomeTypeList.add(mockApprovalOutcomeTypeDBO(i));
		}
		return approvalOutcomeTypeList;
	}
	// mock - ApprovalOutcomeTypeDBO
	private ApprovalOutcomeTypeDBO mockApprovalOutcomeTypeDBO(long code) {
		ApprovalOutcomeTypeDBO approvalOutcomeTypeDBO = new ApprovalOutcomeTypeDBO();
		approvalOutcomeTypeDBO.setCode(code);
		approvalOutcomeTypeDBO.setName("Approval Oucome Type DBO Name : "+ code);
		approvalOutcomeTypeDBO.setDefaultChoice(0);
		approvalOutcomeTypeDBO.setDescription("Approval Outcome Type DBO Name : "+ code);
		approvalOutcomeTypeDBO.setLastUpdatedDate(new Date());
		
		return approvalOutcomeTypeDBO;
	}
	
}
