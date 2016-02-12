package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.CapitalItemDBO;
import uk.gov.scotland.afrc.applications.model.domain.ScheduleItemDBO;

public interface CapitalItemDao extends EntityManagerBase<CapitalItemDBO, Long>{
    
    List<CapitalItemDBO> findCapitalItemByScheduleItem(ScheduleItemDBO  scheduleItem);

}
