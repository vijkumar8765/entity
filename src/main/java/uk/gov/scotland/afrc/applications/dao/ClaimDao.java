package uk.gov.scotland.afrc.applications.dao;

import java.util.Date;
import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ClaimDBO;

public interface ClaimDao extends EntityManagerBase<ClaimDBO, Long> {

    List<ClaimDBO> getByApplicationAndParcelId(long appId, long appLndPrclId);

    List<ClaimDBO> findByApplicationId(long applicationId);

    void removeByApplicationIdAndAppLndParcelId(long appId, long appLndPrclId);

    List<ClaimDBO> getByApplicationAndShareId(long appId, long appCgShareId);

    List<Object[]> findByApplicationIdAndSchemeIds(final long applicationId, final List<Long> schemeIds);

    List<Object[]> getEFADetails(long applicationId);

    List<Object[]> getPreviousClaims(long applicationId, long appLndPrclId, String scheme, Date endDate);

    List<Object[]> getClaimsByAppIdAndBrn(long applicationId, long brn, String scheme, Date endDate);


    List<Object[]> getPreviousClaims(long applicationId, String scheme, Date endDate);
}
