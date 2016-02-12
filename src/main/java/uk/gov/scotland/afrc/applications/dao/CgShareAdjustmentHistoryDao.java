/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.CgShareAudjustmentHistoryDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

/**
 * 
 * Retrieve and return the requested page of common grazing share adjustment history records
 * 
 * @author d609030
 *
 */
public interface CgShareAdjustmentHistoryDao extends EntityManagerBase<CgShareAudjustmentHistoryDBO, Long> {

    /**
     * 
     * 
     * 
     * @param strLocationCode
     * @param paginationOffset
     * @return
     */
    List<CgShareAudjustmentHistoryDBO> loadCommonGrazingShareAdjustmentHistory(String strLocationCode,
                                                                               long paginationOffset);
    
    
    void saveCgShareHistory(CgShareAudjustmentHistoryDBO cgShareAudjustmentHistoryDBO) throws ConcurrentAccessException;

}
