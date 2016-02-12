package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AdjustmentDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public interface AdjustmentDao extends EntityManagerBase<AdjustmentDBO, Long> {	
	
	
	
	List<AdjustmentDBO> findByUserId(Long userId);	
	
	List<AdjustmentDBO>  findByReasonTypeCode(Long code);	
	
	void delete(AdjustmentDBO adjustmentDBO)throws ConcurrentAccessException;

}
