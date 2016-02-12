package uk.gov.scotland.afrc.applications.dao;

import java.math.BigDecimal;
import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.LpisFeatureDBO;

public interface LpisFeatureDao extends EntityManagerBase<LpisFeatureDBO, Long>{
	
	List<LpisFeatureDBO> getLpisFeatureByLandPrclId(long lpisLandPrclId);

    List<Long> getLUTypesByLpisFeature(long lpisLandPrclId);

    List<Long> getBPSLUTypesByLpisFeature(long lpisLandPrclId);

    BigDecimal getFeatureAreaByLandPrclIdAndType(long lpisLandPrclId, long landUseType);

    BigDecimal getBPSIneligibleAreaByLandPrclIdAndType(long lpisLandPrclId, long landUseType);

    BigDecimal getFeatureAreaByLandPrclIdAndLocation(long locationId);

    BigDecimal getBPSIneligibleAreaByLocationAndType(long locationId, long landUseType);

    List<Long> getFeatureLUTypesByLocationId(long locationId);
}
