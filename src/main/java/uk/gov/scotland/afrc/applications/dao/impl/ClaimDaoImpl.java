package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.ClaimDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ClaimDBO;

public class ClaimDaoImpl extends EntityManagerBaseImpl<ClaimDBO, Long> implements ClaimDao {

    private static final String APPLICATION_ID = "applicationId";

    public ClaimDaoImpl() {
        super(ClaimDBO.class);
    }

    @Override
    public List<ClaimDBO> getByApplicationAndParcelId(long appId, long appLndPrclId) {
        TypedQuery<ClaimDBO> query =
                getEntityManager().createNamedQuery("ClaimDBO.findByApplicationAndParcelId", ClaimDBO.class);
        query.setParameter("appId", appId).setParameter("appLndPrclId", appLndPrclId);
        return query.getResultList();
    }

    @Override
    public List<Object[]> findByApplicationIdAndSchemeIds(final long applicationId, final List<Long> schemeIds) {
        TypedQuery<Object[]> query =
                getEntityManager().createNamedQuery("ClaimDBO.findByApplicationIdAndSchemeId", Object[].class);
        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter("schemeIds", schemeIds);
        return query.getResultList();
    }

    @Override
    public List<ClaimDBO> getByApplicationAndShareId(long appId, long appCgShareId) {
        TypedQuery<ClaimDBO> query =
                getEntityManager().createNamedQuery("ClaimDBO.findByApplicationAndShareId", ClaimDBO.class);
        query.setParameter("appId", appId).setParameter("appCgShareId", appCgShareId);
        return query.getResultList();
    }

    @Override
    public List<ClaimDBO> findByApplicationId(long applicationId) {
        TypedQuery<ClaimDBO> query =
                getEntityManager().createNamedQuery("ClaimDBO.findByApplicationId", ClaimDBO.class);
        query.setParameter(APPLICATION_ID, applicationId);

        return query.getResultList();
    }

    @Override
    public void removeByApplicationIdAndAppLndParcelId(long appId, long appLndPrclId) {
        TypedQuery<ClaimDBO> query =
                getEntityManager().createNamedQuery("ClaimDBO.removeByApplicationIdAndAppLndParcelId", ClaimDBO.class);
        query.setParameter(APPLICATION_ID, appId);
        query.setParameter("appLndPrclId", appLndPrclId);
        query.executeUpdate();
    }

    @Override
    public List<Object[]> getEFADetails(final long applicationId) {
        final String nsql =
                "SELECT so.SCHEME_OPTION_DESCRIPTION, " + " SUM(c.UNITS_UNDERTAKEN), " + " cft.ACTUAL_CNV_FACTOR "
                    + " FROM claim c, " + "  SCHEME_OPTION so, CNV_FACTOR_TYPE cft, "
                    + " SCHEME SCH,  APP_LND_PRCL ALP "
                    + " WHERE c.scheme_opt_id=so.SCHEME_OPT_ID AND cft.CODE =so.cnv_factor_type_code "
                    + " AND sch.SCHEME_ID = so.SCHEME_ID AND sch.SCHEME_NAME ='GREENING' AND c.APPLICATION_ID=?1 "
                    + " AND alp.APP_LND_PRCL_ID=c.APP_LND_PRCL_ID "
                    + " GROUP BY so.SCHEME_OPTION_DESCRIPTION,   cft.ACTUAL_CNV_FACTOR "
                    + " union all " + " SELECT so.SCHEME_OPTION_DESCRIPTION, " + " SUM(c.UNITS_UNDERTAKEN), "
                    + " cft.ACTUAL_CNV_FACTOR " + " FROM claim c, " + "  SCHEME_OPTION so, CNV_FACTOR_TYPE cft, "
                    + " SCHEME SCH,  APP_CG_SHARE ACG"
                    + " WHERE c.scheme_opt_id=so.SCHEME_OPT_ID AND cft.CODE =so.cnv_factor_type_code "
                    + " AND sch.SCHEME_ID = so.SCHEME_ID AND sch.SCHEME_NAME ='GREENING' AND c.APPLICATION_ID=?2 "
                    + " AND c.APP_CG_SHARE_ID    =acg.APP_CG_SHARE_ID "
                    + " GROUP BY so.SCHEME_OPTION_DESCRIPTION,   cft.ACTUAL_CNV_FACTOR ";
        Query query = getEntityManager().createNativeQuery(nsql);
        query.setParameter(1, applicationId);
        query.setParameter(2, applicationId);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getPreviousClaims(long applicationId, long lpisLndPrclId, String scheme, Date endDate) {
        TypedQuery<Object[]> query =
                getEntityManager().createNamedQuery("ClaimDBO.findPreviousYearClaimsByLandParcel", Object[].class);
        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter("lpisLndPrclId", lpisLndPrclId);
        query.setParameter("scheme", scheme);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getClaimsByAppIdAndBrn(long applicationId, long brn, String scheme, Date endDate) {
        TypedQuery<Object[]> query =
                getEntityManager().createNamedQuery("ClaimDBO.findPreviousYearClaims", Object[].class);
        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter("scheme", scheme);
        query.setParameter("brn", brn);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }


    @Override
    public List<Object[]> getPreviousClaims(long applicationId, String scheme, Date endDate) {
        TypedQuery<Object[]> query =
                getEntityManager().createNamedQuery("ClaimDBO.findPreviousYearClaims", Object[].class);
        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter("scheme", scheme);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
