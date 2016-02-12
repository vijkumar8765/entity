package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.dto.LandUserDetailSummaryDTO;

public interface LandUseDetailsDao extends EntityManagerBase<LandUserDetailSummaryDTO, Object> {
		
	List<LandUserDetailSummaryDTO> findLandUseDetails(long applicationId,long parcelId);

    List<LandUserDetailSummaryDTO> findCGLandUseDetails(long applicationId, long shareId);
}
