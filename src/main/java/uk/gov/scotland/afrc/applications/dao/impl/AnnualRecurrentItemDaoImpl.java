package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AnnualRecurrentItemDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AnnualRecurrentItemDBO;

public class AnnualRecurrentItemDaoImpl extends EntityManagerBaseImpl<AnnualRecurrentItemDBO, Long> implements AnnualRecurrentItemDao {

    private static Logger logger = Logger.getLogger(AnnualRecurrentItemDaoImpl.class);

	public AnnualRecurrentItemDaoImpl() {
		super(AnnualRecurrentItemDBO.class);
	}

	@Override
	public List<AnnualRecurrentItemDBO> findAnnualRecurrentItemByScheduleItem(long scheduleItem) {
        TypedQuery<AnnualRecurrentItemDBO> query =
                getEntityManager().createNamedQuery("AnnualRecurrentItem.findByScheduleItem",AnnualRecurrentItemDBO.class);

        logger.debug("query =" + query.toString() + " " + scheduleItem);
        return query.setParameter("scheduleItemId", scheduleItem).getResultList();
	}

    @Override
    public List<AnnualRecurrentItemDBO> findDistinctMapLetterByScheduleItem(long scheduleItem) {
        TypedQuery<AnnualRecurrentItemDBO> query =
                getEntityManager().createNamedQuery("AnnualRecurrentItem.findDistinctMapLetterByScheduleItem",AnnualRecurrentItemDBO.class);
        logger.debug("query =" + query.toString() + " " + scheduleItem);
        return query.setParameter("scheduleItemId", scheduleItem).getResultList();
    }

}
