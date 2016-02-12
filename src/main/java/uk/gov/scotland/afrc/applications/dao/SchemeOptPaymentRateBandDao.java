package uk.gov.scotland.afrc.applications.dao;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SchemeOptPaymentRateBandDBO;

import java.util.List;

/**
 * Created by d608947 on 17/11/2014.
 */
public interface SchemeOptPaymentRateBandDao extends EntityManagerBase<SchemeOptPaymentRateBandDBO, Long> {

    List<Object[]> findPaymentRateBandsBySchemeIds(final List<Long> schemeIds);
}
