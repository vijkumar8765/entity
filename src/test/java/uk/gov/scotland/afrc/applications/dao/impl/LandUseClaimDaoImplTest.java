package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Ignore;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.gov.scotland.afrc.applications.model.domain.LandUseClaimDBO;

@Ignore
public class LandUseClaimDaoImplTest {

    private LandUseClaimDaoImpl dao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<LandUseClaimDBO> query;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dao = new LandUseClaimDaoImpl();
        dao.setEntityManager(entityManager);
    }

}
