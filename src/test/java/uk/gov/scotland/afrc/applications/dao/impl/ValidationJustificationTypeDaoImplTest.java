package uk.gov.scotland.afrc.applications.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.dto.ValidationJustificationDTO;

public class ValidationJustificationTypeDaoImplTest {

    private ValidationJustificationTypeDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<ValidationJustificationDTO> justificationsQuery;
    @Mock    
	private Query queryObject;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new ValidationJustificationTypeDaoImpl();
        dao.setEntityManager(entityManager);
    }

	@Test
	public void getJustificationReasonsTest() {	
        List<Object[]> resultList = new ArrayList<Object[]>();
        Object[] obj1 = new Object[2];
        obj1[0] = BigDecimal.valueOf(1l);
        obj1[1] = "desc 1";
        Object[] obj2 = new Object[2];
        obj2[0] = BigDecimal.valueOf(2l);
        obj2[1] = "desc 2";
        resultList.add(obj1);
        resultList.add(obj2);

		when(entityManager.createNativeQuery(Mockito.anyString(), (Class) Mockito.anyObject())).thenReturn(queryObject);		
		when(queryObject.getResultList()).thenReturn(resultList);

		List<ValidationJustificationDTO> result = dao.getSupressJustificationReasons(1l);
		
		assertEquals(2, result.size());
		assertEquals(1l, result.get(0).getCode().longValue());
		assertEquals(2l, result.get(1).getCode().longValue());
		assertEquals("desc 1", result.get(0).getDescription());
		assertEquals("desc 2", result.get(1).getDescription());
	}  
	
}
