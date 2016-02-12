package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSectionTypeDBO;

public interface ApplicationSectionTypeDao extends EntityManagerBase<ApplicationSectionTypeDBO, Long> {

    ApplicationSectionTypeDBO getApplicationSectionTypeByName(String name);

    List<ApplicationSectionTypeDBO> getApplicationSectionsByApplicatioTypeCode(long applicationTypeCode);
    
    /**
     * This method returns Application Section Types by given application id
     * @param applicationId
     * @return List of Application Section Types
     */
    List<Object> retrieveSections(long applicationId);
    
    List<Object[]> findErrorByApplicationId(long applicationId);
    
}
