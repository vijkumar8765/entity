package uk.gov.scotland.afrc.applications.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import uk.gov.scotland.afrc.applications.model.domain.AppLndPrclDBO;
import uk.gov.scotland.afrc.applications.model.domain.ClaimDBO;
import uk.gov.scotland.afrc.applications.model.domain.SchemeOptionDBO;

/**
 * Created by d608947 on 14/11/2014.
 */
@Ignore
public class ClaimDaoImplDbUnitTest extends DbUnitJpaTestBase {

    private ClaimDaoImpl dao;

    @Before
    public void before() throws Exception {
        super.cleanDB();
        dao = new ClaimDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void testFindByApplicationId() throws Exception {
        List<ClaimDBO> results = dao.findByApplicationId(1l);
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
    }

    @Test
     public void testFindByApplicationIdAndSchemeIdWithMatchingLandParcel() {
        List<Long> schemeIds = new ArrayList<Long>(1);
        schemeIds.add(15l);
        List<Object[]> results = dao.findByApplicationIdAndSchemeIds(1, schemeIds);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(3, results.get(0).length);

        ClaimDBO claimDbo = (ClaimDBO)results.get(0)[0];
        AppLndPrclDBO landPrclDbo = (AppLndPrclDBO)results.get(0)[1];
        SchemeOptionDBO schemeOptionDbo = (SchemeOptionDBO)results.get(0)[2];

        Assert.assertNotNull(claimDbo);
        Assert.assertNotNull(landPrclDbo);
        Assert.assertNotNull(schemeOptionDbo);
        Assert.assertNotNull(schemeOptionDbo.getSchemeOptionType());
        Assert.assertEquals(new BigDecimal(10), claimDbo.getUnitsAgreed());
        Assert.assertEquals(new BigDecimal(10), claimDbo.getUnitsUndertaken());
        Assert.assertEquals(0, schemeOptionDbo.getSchemeOptionType().getDefaultChoice().intValue());
        Assert.assertEquals("ll/bbb/ccccc", landPrclDbo.getPrclId());
    }

    @Test
    public void testFindByApplicationIdAndSchemeIdWithEmptyLandParcel() {
        List<Long> schemeIds = new ArrayList<Long>(1);
        schemeIds.add(14l);
        List<Object[]> results = dao.findByApplicationIdAndSchemeIds(1, schemeIds);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals(3, results.get(0).length);

        ClaimDBO claimDbo = (ClaimDBO)results.get(0)[0];
        AppLndPrclDBO landPrclDbo = (AppLndPrclDBO)results.get(0)[1];
        SchemeOptionDBO schemeOptionDbo = (SchemeOptionDBO)results.get(0)[2];

        Assert.assertNotNull(claimDbo);
        Assert.assertNull(landPrclDbo);
        Assert.assertNotNull(schemeOptionDbo);
        Assert.assertNotNull(schemeOptionDbo.getSchemeOptionType());
        Assert.assertEquals(new BigDecimal(10), claimDbo.getUnitsAgreed());
        Assert.assertEquals(new BigDecimal(10), claimDbo.getUnitsUndertaken());
        Assert.assertEquals(0, schemeOptionDbo.getSchemeOptionType().getDefaultChoice().intValue());
    }

    @Override
    protected IDataSet getDataSet() throws DataSetException {
        return flatXmlDataSetBuilder.build(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("ClaimOptionsDataSet.xml"));
    }
}
