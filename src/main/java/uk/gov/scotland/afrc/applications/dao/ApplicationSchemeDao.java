package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSchemeDBO;

public interface ApplicationSchemeDao extends EntityManagerBase<ApplicationSchemeDBO, Long> {

    List<ApplicationSchemeDBO> findSchemesByApplicationId(long applicationId);
    
    ApplicationSchemeDBO findByApplicationIdAndSchemeId(long applicationId, long schemeId);
    
    ApplicationSchemeDBO findByApplicationSchemeIdAndSchemeId(long applicationId, long applicationSchemeId);
}
