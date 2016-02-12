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

import uk.gov.scotland.afrc.applications.model.domain.EligibleCapitalItemTypeDBO;

public class EligibleCapitalItemDaoImplTest {

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<EligibleCapitalItemTypeDBO> mockQuery;

    private EligibleCapitalItemTypeDaoImpl dao;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new EligibleCapitalItemTypeDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void findEligibleCapitalItemTypesBySchemeOptionIdTest() {
        Mockito.when(
                entityManager.createNamedQuery(
                        "EligibleCapitalItemTypeDBO.findEligibleCapitalItemTypesBySchemeOptionId",
                        EligibleCapitalItemTypeDBO.class)).thenReturn(mockQuery);
        Mockito.when(mockQuery.setParameter(anyString(), anyObject())).thenReturn(mockQuery);
        dao.findEligibleCapitalItemTypesBySchemeOptionId(1L);
        Mockito.verify(entityManager).createNamedQuery(
                "EligibleCapitalItemTypeDBO.findEligibleCapitalItemTypesBySchemeOptionId",
                EligibleCapitalItemTypeDBO.class);
        Mockito.verify(mockQuery).setParameter("schemeOptionId", 1L);
    }

}
