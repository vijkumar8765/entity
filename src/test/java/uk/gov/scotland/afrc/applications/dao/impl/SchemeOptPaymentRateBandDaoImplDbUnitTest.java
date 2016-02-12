package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import uk.gov.scotland.afrc.applications.model.domain.PaymentRateBandDBO;

/**
 * Created by d608947 on 17/11/2014.
 */
@Ignore
public class SchemeOptPaymentRateBandDaoImplDbUnitTest extends DbUnitJpaTestBase {

    private SchemeOptPaymentRateBandDaoImpl dao;

    @Before
    public void before() throws Exception {
        super.cleanDB();
        this.dao = new SchemeOptPaymentRateBandDaoImpl();
        dao.setEntityManager(entityManager);
    }

    @Test
    public void testFindPaymentRateBandsBySchemeOptionIds() {
        List<Long> schemeIds = new ArrayList<Long>(1);
        schemeIds.add(15l);
        List<Object[]> results = dao.findPaymentRateBandsBySchemeIds(schemeIds);
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());

        Long schemeOptionId = (Long)results.get(0)[0];
        PaymentRateBandDBO rate = (PaymentRateBandDBO)results.get(0)[1];
        Assert.assertEquals(1, schemeOptionId.intValue());
        Assert.assertEquals(10, rate.getCap().intValue());

        schemeOptionId = (Long)results.get(1)[0];
        rate = (PaymentRateBandDBO)results.get(1)[1];
        Assert.assertEquals(2, schemeOptionId.intValue());
        Assert.assertEquals(20, rate.getCap().intValue());
    }

    @Override
    protected IDataSet getDataSet() throws DataSetException {
        return flatXmlDataSetBuilder.build(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("SchemeOptionPaymentBandDataSet.xml"));
    }
}
