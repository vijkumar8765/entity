package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.ContractDBO;

public class ContractDaoImplTest {

    private static final long BRN = 1;
    private static final String RP = "RP";
    private ContractDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ContractDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ContractDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void getContracts() {

    	// reinstate once IM fix startdaye issue
//        Mockito.when(entityManager.createNamedQuery("ContractDBO.findContractsByBrnAndScheme", ContractDBO.class)).thenReturn(
//                query);
//
//        Mockito.when(query.setParameter(anyString(), any(Date.class), any(TemporalType.class))).thenReturn(query);
//        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
//        Date startDate = new Date();
//        dao.getContracts(BRN, RP, startDate);
//
//        Mockito.verify(entityManager).createNamedQuery("ContractDBO.findContractsByBrnAndScheme", ContractDBO.class);
//        Mockito.verify(query).setParameter("brn", BRN);
//        Mockito.verify(query).setParameter("scheme", RP);
//        Mockito.verify(query).setParameter("startDate", startDate, TemporalType.DATE);
//        Mockito.verify(query).getResultList();
    }

}
