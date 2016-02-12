/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.LivestockDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public interface LivestockDao extends EntityManagerBase<LivestockDBO, Long> {

    List<LivestockDBO> retrieveLivestockByApplicationId(long applicationId);

    void removeAllLivestock(long applicationId) throws ConcurrentAccessException;

    void removeLivestock(Long listLivestockId) throws ConcurrentAccessException;

    void removeLivestocks(List<Long> listLivestockId) throws ConcurrentAccessException;

    List<Long> retrieveLivestockCatgIdByApplId(long applicationId);
}
