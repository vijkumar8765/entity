package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AdjustmentReasonTypeDBO;

public interface AdjustmentReasonTypeDao extends EntityManagerBase<AdjustmentReasonTypeDBO, Long> {	
	 List<AdjustmentReasonTypeDBO>  findByName(String name);

}
