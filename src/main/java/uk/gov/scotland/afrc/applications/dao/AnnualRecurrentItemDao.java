package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AnnualRecurrentItemDBO;

public interface AnnualRecurrentItemDao extends EntityManagerBase<AnnualRecurrentItemDBO, Long>{

	List<AnnualRecurrentItemDBO> findAnnualRecurrentItemByScheduleItem(long scheduleItem);
	List<AnnualRecurrentItemDBO> findDistinctMapLetterByScheduleItem(long scheduleItem);
}
