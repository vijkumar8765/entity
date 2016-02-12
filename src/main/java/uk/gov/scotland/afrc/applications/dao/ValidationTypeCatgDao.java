package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeCatgDBO;

public interface ValidationTypeCatgDao extends EntityManagerBase<ValidationTypeCatgDBO, Long> {
	
	/**
     * This method returns errorTypes based on application Id
     * @param applicationId
     * @return List of errorTypes
     */
	List<Object> errorTypes(long applicationId);
}
