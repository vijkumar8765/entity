package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AdjustmentValueDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public interface AdjustmentValueDao extends EntityManagerBase<AdjustmentValueDBO, Long> {
	List<AdjustmentValueDBO> findByAdjustmentId(Long adjustmentId);		
	int saveAdjustmentValueDBO(AdjustmentValueDBO adjustmentValueDBO);
	List<AdjustmentValueDBO> loadAdjustedFields(Long applicationId, List<String> uiFieldLabel);	
	void delete(AdjustmentValueDBO adjustmentValueDBO)throws ConcurrentAccessException;
	List<List<AdjustmentValueDBO>> findLineAdjustmentAdjustmentValues(List<Long> adjustmentIdList);
    //LoadAdjustments
	List<AdjustmentValueDBO> findLoadAdjustmentValues(Long applicationId,String fieldId,Long claimId,Long livestockId, Long lndUseAreaId) ;
    List<AdjustmentValueDBO> findLoadAdjustmentValuesProvidedLandDetails(Long applicationId,String fieldId,Long claimId,Long livestockId, Long lndUseAreaId, Long appLndPrclId, Long cgAppId, Long cgLndUseAreaId);
    List<Long>  findLineFlagAdjustmentIdList(Long applicationId,String fieldId,Long claimId,Long livestockId, Long lndUseAreaId);
    List<Long>  findLineFlagAdjustmentIdListProvidedLandDetails(Long applicationId,String fieldId,Long claimId,Long livestockId, Long lndUseAreaId, Long appLndPrclId, Long cgAppId, Long cgLndUseAreaId);
    
}