package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ScheduleItemDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ScheduleItemDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class ScheduleItemDaoImpl extends EntityManagerBaseImpl<ScheduleItemDBO, Long> implements ScheduleItemDao {

    private static final String APPLICATION_ID = "applicationId";
    private final Logger logger = Logger.getLogger(ScheduleItemDaoImpl.class);
    private static final String QUERY = "query = ";

    public ScheduleItemDaoImpl() {
        super(ScheduleItemDBO.class);
    }

    @Override
    public List<ScheduleItemDBO> findScheduleItemsByApplicationId(long applicationId) {

        TypedQuery<ScheduleItemDBO> query =
                getEntityManager().createNamedQuery("ScheduleItemDBO.findByApplicationId", ScheduleItemDBO.class);
        logger.debug(QUERY + query.toString() + " " + applicationId);
        return query.setParameter(APPLICATION_ID, Long.valueOf(applicationId)).getResultList();
    }

    @Override
    public long findByDistictOfSchemeOptIdByApplicationId(long applicationId) {

        TypedQuery<ScheduleItemDBO> query =
                getEntityManager().createNamedQuery("ScheduleItemDBO.findByDistictSchemeOptIdByApplicationId",
                        ScheduleItemDBO.class);
        logger.debug(QUERY + query.toString() + " " + applicationId);
        return query.setParameter(APPLICATION_ID, Long.valueOf(applicationId)).getResultList().size();
    }

    @Override
    public ScheduleItemDBO findScheduleItemDetailsByApplicationIdAndScheduleItemId(long applicationId,
                                                                                   long scheduleItemId) {

        TypedQuery<ScheduleItemDBO> query =
                getEntityManager().createNamedQuery("ScheduleItemDBO.findByApplicationIdAndScheduleItemId",
                        ScheduleItemDBO.class);
        logger.debug(QUERY + query.toString() + " " + applicationId + " " + scheduleItemId);
        return query.setParameter(APPLICATION_ID, Long.valueOf(applicationId))
                .setParameter("scheduleItemId", Long.valueOf(scheduleItemId)).getSingleResult();
    }

    @Override
    public ScheduleItemDBO create(ScheduleItemDBO scheduleItemDBO) {
        super.create(scheduleItemDBO);

        getEntityManager().refresh(scheduleItemDBO);
        if (null != scheduleItemDBO) {
            logger.debug("scheduleItemDBO  = " + scheduleItemDBO.getScheduleItemId());
        }
        return scheduleItemDBO;
    }

    @Override
    public ScheduleItemDBO update(ScheduleItemDBO scheduleItemDBO) throws ConcurrentAccessException {
        super.update(scheduleItemDBO);

        getEntityManager().refresh(scheduleItemDBO);
        if (null != scheduleItemDBO) {
            logger.debug("scheduleItemDBO  = " + scheduleItemDBO.getScheduleItemId());
        }
        return scheduleItemDBO;
    }

    @Override
    public List<ScheduleItemDBO> findScheduleItemsByContractId(long brn, long contractId, Date startDate) {
        TypedQuery<ScheduleItemDBO> query =
                getEntityManager().createNamedQuery("ScheduleItemDBO.findByContractId", ScheduleItemDBO.class);
        query.setParameter("managedBrn", brn).setParameter("contractId", contractId)
                .setParameter("startDate", startDate);
        logger.debug(QUERY + query + " " + brn + " " + contractId + " " + startDate);
        return query.getResultList();
    }

    @Override
    public List<ScheduleItemDBO> findScheduleItemsByAppLndPrclId(long appLndPrclId) {

        TypedQuery<ScheduleItemDBO> query =
                getEntityManager().createNamedQuery("ScheduleItemDBO.findByAppLndPrclId", ScheduleItemDBO.class);
        logger.debug(QUERY + query + " " + appLndPrclId);
        return query.setParameter("appLndPrclId", Long.valueOf(appLndPrclId)).getResultList();
    }

    @Override
    public List<ScheduleItemDBO> findScheduleItemsByContractIdAndEndDateAfter(long brn, long contractId, Date endDate) {
        TypedQuery<ScheduleItemDBO> query =
                getEntityManager().createNamedQuery("ScheduleItemDBO.findByContractIdAndEndDateAfter",
                        ScheduleItemDBO.class);
        query.setParameter("managedBrn", brn).setParameter("contractId", contractId).setParameter("endDate", endDate);
        logger.debug(QUERY + query + " " + brn + " " + contractId + " " + endDate);
        return query.getResultList();
    }

    @Override
    public List<ScheduleItemDBO> findScheduleItemsByContractIdAndEndDate(long brn, long contractId, Date endDate) {
        TypedQuery<ScheduleItemDBO> query =
                getEntityManager()
                        .createNamedQuery("ScheduleItemDBO.findByContractIdAndEndDate", ScheduleItemDBO.class);
        query.setParameter("managedBrn", brn).setParameter("contractId", contractId).setParameter("endDate", endDate);
        logger.debug(QUERY + query + " " + brn + " " + contractId + " " + endDate);
        return query.getResultList();
    }

    @Override
    public List<ScheduleItemDBO> findScheduleItemsByApplicationIdAndAppLndPrclId(long applicationId, long appLndPrclId) {
        TypedQuery<ScheduleItemDBO> query =
                getEntityManager().createNamedQuery("ScheduleItemDBO.findScheduleItemsByApplicationIdAndAppLndPrclId",
                        ScheduleItemDBO.class);
        query.setParameter("appLndPrclId", Long.valueOf(appLndPrclId)).setParameter(APPLICATION_ID, applicationId);
        logger.debug(QUERY + query + " " + applicationId + " " + appLndPrclId);
        return query.getResultList();
    }

    @Override
    public List<ScheduleItemDBO> findScheduleItemsByApplicationIdAndSchemeOptionId(long applicationId,
                                                                                   long schemeOptionId) {
        TypedQuery<ScheduleItemDBO> query =
                getEntityManager().createNamedQuery(
                        "ScheduleItemDBO.findScheduleItemsByApplicationIdAndSchemeOptionId", ScheduleItemDBO.class);
        query.setParameter("applicationId", Long.valueOf(applicationId)).setParameter("schemeOptionId", schemeOptionId);
        logger.debug(QUERY + query + " " + applicationId + " " + schemeOptionId);
        return query.getResultList();
    }

    @Override
    public List<ScheduleItemDBO> findScheduleItemsByLPISLandParcelId(long lpisLandParcelId) {
        TypedQuery<ScheduleItemDBO> query =
                getEntityManager().createNamedQuery("ScheduleItemDBO.findScheduleItemsByLPISLandParcelId",
                        ScheduleItemDBO.class);
        query.setParameter("lpisLndPrclId", Long.valueOf(lpisLandParcelId));
        logger.debug(QUERY + query + " " + lpisLandParcelId);
        return query.getResultList();
    }
}
