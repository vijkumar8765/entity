package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationCommonGrazingDBO;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationCommonGrazingShareDBO;

public interface ApplicationCommonGrazingShareDao extends EntityManagerBase<ApplicationCommonGrazingShareDBO, Long>{
    List<ApplicationCommonGrazingShareDBO> getByAppCGId(ApplicationCommonGrazingDBO appCgDbo);

    List<ApplicationCommonGrazingShareDBO> getActiveShareByAppCGId(ApplicationCommonGrazingDBO appCgDbo);
    
    List<ApplicationCommonGrazingShareDBO> getApplicationShareByCGAndCroft(ApplicationCommonGrazingDBO applicationCommonGrazingDBO,long croftLocation);

    List<ApplicationCommonGrazingShareDBO>
        getApplicationShareByCGAndCroftAndName(ApplicationCommonGrazingDBO applicationCommonGrazingDBO,
                                               long croftLocation, String shareName);

    List<ApplicationCommonGrazingShareDBO> getApplicationShareByCGAndCroftName(ApplicationCommonGrazingDBO appCG,
                                                                               String croftName);

}
