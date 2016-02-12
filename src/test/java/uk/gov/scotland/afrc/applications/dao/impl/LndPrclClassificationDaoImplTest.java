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

import uk.gov.scotland.afrc.applications.model.domain.LndPrclClassificationDBO;

public class LndPrclClassificationDaoImplTest {
	
    private LndPrclClassificationDaoImpl lndPrclClassificationDaoImpl;
	
	@Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<LndPrclClassificationDBO> query;
    
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        lndPrclClassificationDaoImpl = new LndPrclClassificationDaoImpl();
        lndPrclClassificationDaoImpl.setEntityManager(entityManager);
    }
    
    @Test
    public void testIsLFAParcel()
    {
        Mockito.when(
                entityManager.createNamedQuery("LndPrclClassificationDBO.findByIdAndType",
                        LndPrclClassificationDBO.class)).thenReturn(query);

        Mockito.when(query.setParameter(anyString(), anyObject())).thenReturn(query);
        lndPrclClassificationDaoImpl.isLFAParcel(1l);

        Mockito.verify(entityManager).createNamedQuery("LndPrclClassificationDBO.findByIdAndType",
                LndPrclClassificationDBO.class);
        Mockito.verify(query).setParameter("lpisLndPrclId", 1l);
    }
}
