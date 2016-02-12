package uk.gov.scotland.afrc.applications.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LpisFeatureDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LpisFeatureDBO;

public class LpisFeatureDaoImpl extends EntityManagerBaseImpl<LpisFeatureDBO, Long> implements LpisFeatureDao {

    private static final BigDecimal HUNDRED = new BigDecimal(100);
    private static final String LPIS_LND_PRCL_ID = "lpisLndPrclId";
    private static final String QUERY = "query = ";

    private final Logger logger = Logger.getLogger(LpisFeatureDaoImpl.class);

    public LpisFeatureDaoImpl() {
        super(LpisFeatureDBO.class);

    }

    @Override
    public List<LpisFeatureDBO> getLpisFeatureByLandPrclId(long lpisLandPrclId) {
        TypedQuery<LpisFeatureDBO> query =
                getEntityManager().createNamedQuery("LpisFeatureDBO.findBylpisLandPrclId", LpisFeatureDBO.class);
        logger.debug(QUERY + query.toString() + lpisLandPrclId);

        return query.setParameter(LPIS_LND_PRCL_ID, Long.valueOf(lpisLandPrclId)).getResultList();
    }

    @Override
    public BigDecimal getFeatureAreaByLandPrclIdAndType(long lpisLandPrclId, long landUseType) {
        TypedQuery<BigDecimal> query =
                getEntityManager().createNamedQuery("LpisFeatureDBO.sumBylpisLandPrclIdAndType", BigDecimal.class);
        logger.debug(QUERY + query.toString() + lpisLandPrclId + " " + landUseType);
        return query.setParameter(LPIS_LND_PRCL_ID, lpisLandPrclId).setParameter("landUseType", landUseType)
                .getSingleResult();
    }

    @Override
    public BigDecimal getBPSIneligibleAreaByLandPrclIdAndType(long lpisLandPrclId, long landUseType) {
        TypedQuery<LpisFeatureDBO> query =
                getEntityManager().createNamedQuery("LpisFeatureDBO.findBylpisLandPrclIdAndType", LpisFeatureDBO.class);
        query.setParameter(LPIS_LND_PRCL_ID, lpisLandPrclId).setParameter("landUseType", landUseType);
        logger.debug(QUERY + query.toString() + lpisLandPrclId + " " + landUseType);
        return getBPSIneligibleArea(query.getResultList());
    }

    @Override
    public List<Long> getLUTypesByLpisFeature(long lpisLandPrclId) {
        TypedQuery<Long> query =
                getEntityManager().createNamedQuery("LpisFeatureDBO.findLUTypeBylpisLandPrclId", Long.class);
        logger.debug(QUERY + query.toString() + lpisLandPrclId);
        return query.setParameter(LPIS_LND_PRCL_ID, lpisLandPrclId).getResultList();
    }

    @Override
    public List<Long> getFeatureLUTypesByLocationId(long locationId) {
        TypedQuery<Long> query =
                getEntityManager().createNamedQuery("LpisFeatureDBO.findLUTypeByLocationId", Long.class);
        logger.debug(QUERY + query.toString() + locationId);
        return query.setParameter("locationId", locationId).getResultList();
    }

    @Override
    public List<Long> getBPSLUTypesByLpisFeature(long lpisLandPrclId) {
        TypedQuery<Long> query =
                getEntityManager().createNamedQuery("LpisFeatureDBO.findLUTypeBylpisLandPrclId", Long.class);
        logger.debug(QUERY + query.toString() + lpisLandPrclId);
        return query.setParameter(LPIS_LND_PRCL_ID, lpisLandPrclId).getResultList();
    }

    @Override
    public BigDecimal getFeatureAreaByLandPrclIdAndLocation(long locationId) {
        TypedQuery<BigDecimal> query =
                getEntityManager()
                        .createNamedQuery("LpisFeatureDBO.sumBylpisLandPrclIdAndLocationId", BigDecimal.class);
        logger.debug(QUERY + query.toString() + locationId);
        BigDecimal result = query.setParameter("locationId", locationId).getSingleResult();

        if (null == result) {
            logger.debug("result==null");
            result = BigDecimal.ZERO;
        }
        return result;
    }

    @Override
    public BigDecimal getBPSIneligibleAreaByLocationAndType(long locationId, long landUseType) {
        TypedQuery<LpisFeatureDBO> query =
                getEntityManager().createNamedQuery("LpisFeatureDBO.findByLocationIdAndType", LpisFeatureDBO.class);

        query.setParameter("locationId", locationId).setParameter("landUseType", landUseType);
        logger.debug(QUERY + query.toString() + locationId + " " + landUseType);
        return getBPSIneligibleArea(query.getResultList());
    }

    private BigDecimal getBPSIneligibleArea(List<LpisFeatureDBO> featureList) {
        BigDecimal result = BigDecimal.ZERO;
        for (LpisFeatureDBO feature : featureList) {
            logger.debug("featureList = " + featureList.size());
            BigDecimal featureArea = feature.getDigitisedArea();
            if (null != feature.getPercentageIneligible()) {
                logger.debug("null != feature.getPercentageIneligible()");
                result = result.add(featureArea.multiply(feature.getPercentageIneligible().divide(HUNDRED)));
            } else {
                result = result.add(featureArea);
            }
        }
        return result;
    }
}
