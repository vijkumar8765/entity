package uk.gov.scotland.afrc.applications.dao;

import java.util.Date;
import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ScheduleItemDBO;

public interface ScheduleItemDao extends EntityManagerBase<ScheduleItemDBO, Long> {

    List<ScheduleItemDBO> findScheduleItemsByApplicationId(long applicationId);

    ScheduleItemDBO findScheduleItemDetailsByApplicationIdAndScheduleItemId(long applicationId, long scheduleItemId);
    
    
    List<ScheduleItemDBO> findScheduleItemsByContractId(long brn, long contractId, Date startDate);

    List<ScheduleItemDBO> findScheduleItemsByContractIdAndEndDateAfter(long brn, long contractId, Date endDate);
    List<ScheduleItemDBO> findScheduleItemsByAppLndPrclId(long appLndPrclId);
    List<ScheduleItemDBO> findScheduleItemsByContractIdAndEndDate(long brn, long contractId, Date endDate);
    long findByDistictOfSchemeOptIdByApplicationId(long applicationId);
    List<ScheduleItemDBO> findScheduleItemsByApplicationIdAndAppLndPrclId(long applicationId,long appLndPrclId);
    List<ScheduleItemDBO> findScheduleItemsByApplicationIdAndSchemeOptionId(long applicationId, long schemeOptionId);

    List<ScheduleItemDBO> findScheduleItemsByLPISLandParcelId(long lpisLandParcelId);
}
