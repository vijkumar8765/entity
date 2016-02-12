package uk.gov.scotland.afrc.applications.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeDBO;

public interface ValidationTypeDao extends EntityManagerBase<ValidationTypeDBO, Long> {
    /**
     * Find by name.
     *
     * @param name the name
     * @return the validation type dbo
     */
    ValidationTypeDBO findByName(String name);
    
    /**
     * 
     * @param codes
     * @return
     */
    Map<Long, ValidationTypeDBO> findByCodeList(List<Long> codes);

    List<Object[]> findErrorByApplicationId(long applicationId);

    ValidationTypeDBO findByCode(long code);
    
    /**
     * This method returns List of matching ValidationTypeDBOs for given names  
     * @param namesList
     * @param namesList
     * @return List of ValidationTypeDBOs
     */
    List<ValidationTypeDBO> findValidationTypesByCategoryName(String categoryName, Set<String> validationTypeNames);
}
