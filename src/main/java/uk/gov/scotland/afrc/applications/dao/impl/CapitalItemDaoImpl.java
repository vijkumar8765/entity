package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.CapitalItemDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CapitalItemDBO;
import uk.gov.scotland.afrc.applications.model.domain.ScheduleItemDBO;

public class CapitalItemDaoImpl extends EntityManagerBaseImpl<CapitalItemDBO, Long> implements CapitalItemDao {

    public CapitalItemDaoImpl() {
            super(CapitalItemDBO.class);
     }

    @Override
    public List<CapitalItemDBO> findCapitalItemByScheduleItem(ScheduleItemDBO scheduleItem) {
        TypedQuery<CapitalItemDBO> query =
                getEntityManager().createNamedQuery("CapitalItem.findByScheduleItem",CapitalItemDBO.class);
        return query.setParameter("scheduleItem", scheduleItem).getResultList();
    }

}
