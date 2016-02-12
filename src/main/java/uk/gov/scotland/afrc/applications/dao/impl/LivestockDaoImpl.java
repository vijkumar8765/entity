/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LivestockDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LivestockDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class LivestockDaoImpl extends EntityManagerBaseImpl<LivestockDBO, Long> implements LivestockDao {

    private final Logger logger = Logger.getLogger(LivestockDaoImpl.class);

    public LivestockDaoImpl() {
        super(LivestockDBO.class);
    }

    @Override
    public List<LivestockDBO> retrieveLivestockByApplicationId(long applicationId) {
        TypedQuery<LivestockDBO> query =
                getEntityManager().createNamedQuery("LivestockDBO.findByApplicationId", LivestockDBO.class);
        logger.debug("query  = " + query + " " + applicationId);
        return query.setParameter("applicationId", applicationId).getResultList();
    }

    @Override
    public List<Long> retrieveLivestockCatgIdByApplId(long applicationId) {
        TypedQuery<Long> query =
                getEntityManager().createNamedQuery("LivestockDBO.findLivestockCatgIdByAppId", Long.class);
        logger.debug("query  = " + query + " " + applicationId);
        return query.setParameter("applicationId", applicationId).getResultList();
    }

    @Override
    public void removeAllLivestock(long applicationId) throws ConcurrentAccessException {

        logger.debug("applicationId  = " + applicationId);
        List<LivestockDBO> dboList = retrieveLivestockByApplicationId(applicationId);
        for (LivestockDBO livestockDbo : dboList) {
            logger.debug("delete  = " + livestockDbo.getLivestockId());
            delete(livestockDbo);
        }
    }

    @Override
    public void removeLivestocks(List<Long> listLivestockId) throws ConcurrentAccessException {
        for (Long livestockId : listLivestockId) {
            logger.debug("removeLivestocks by id  = " + livestockId);
            delete(this.findById(livestockId));
        }
    }

    @Override
    public void removeLivestock(Long livestockId) throws ConcurrentAccessException {
        logger.debug("removeLivestock by id  = " + livestockId);
        delete(this.findById(livestockId));
    }

}
