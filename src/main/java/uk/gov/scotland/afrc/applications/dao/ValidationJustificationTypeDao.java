package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ValidationJustificationTypeDBO;
import uk.gov.scotland.afrc.applications.model.dto.ValidationJustificationDTO;

public interface ValidationJustificationTypeDao extends EntityManagerBase<ValidationJustificationTypeDBO, Long> {

    /**
     * This method returns List of all ValidationJustificationDTO that have the suppression_type_flag as true  
     * @param errorTypeCode 
     * @return List of ValidationJustificationDTO
     */
	List<ValidationJustificationDTO> getSupressJustificationReasons(long errorTypeCode);
	
	List<ValidationJustificationDTO> getAllJustificationReasons();

    ValidationJustificationTypeDBO findJustificationTypeWithName(String name);
}
