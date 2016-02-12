/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.CgShareAdjustmentHistoryDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CgShareAudjustmentHistoryDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

/**
 * @author d609030
 *
 */
public class CgShareAdjustmentHistoryDaoImpl extends EntityManagerBaseImpl<CgShareAudjustmentHistoryDBO, Long>
    implements CgShareAdjustmentHistoryDao {

    public CgShareAdjustmentHistoryDaoImpl() {
        super(CgShareAudjustmentHistoryDBO.class);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase#create(java.lang.Object)
     */
   /* @Override
    public CgShareAudjustmentHistoryDBO create(CgShareAudjustmentHistoryDBO entity) {
        // TODO Auto-generated method stub
        return null;
    }*/

    /*
     * (non-Javadoc)
     * 
     * @see uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase#delete(java.lang.Object)
     */
   /* @Override
    public void delete(CgShareAudjustmentHistoryDBO entity) throws ConcurrentAccessException {
        // TODO Auto-generated method stub

    }*/

    /*
     * (non-Javadoc)
     * 
     * @see uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase#update(java.lang.Object)
     */
   /* @Override
    public CgShareAudjustmentHistoryDBO update(CgShareAudjustmentHistoryDBO entity) throws ConcurrentAccessException {
        // TODO Auto-generated method stub
        return null;
    }*/

    /*
     * (non-Javadoc)
     * 
     * @see uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase#createList(java.util.List)
     */
    @Override
    public void createList(List<CgShareAudjustmentHistoryDBO> entities) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase#findById(java.lang.Object)
     */
    @Override
    public CgShareAudjustmentHistoryDBO findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase#findByIdAndVersion(java.lang.Object,
     * java.lang.Long)
     */
    @Override
    public CgShareAudjustmentHistoryDBO findByIdAndVersion(Long id, Long version) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase#queryAll(java.lang.String)
     */
    @Override
    public List<CgShareAudjustmentHistoryDBO> queryAll(String namedQuery) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase#queryByKey(java.lang.String,
     * java.lang.String, java.lang.Long)
     */
    @Override
    public List<CgShareAudjustmentHistoryDBO> queryByKey(String namedQuery, String keyName, Long key) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase#queryByKey(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public CgShareAudjustmentHistoryDBO queryByKey(String namedQuery, String keyName, String key) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * uk.gov.scotland.afrc.applications.dao.CGShareAdjustmentHistoryDao#loadCommonGrazingShareAdjustmentHistory(java
     * .lang.String, int)
     */
    @Override
    public List<CgShareAudjustmentHistoryDBO> loadCommonGrazingShareAdjustmentHistory(String strLocationCode,
                                                                                      long paginationOffset) {
        // TODO Auto-generated method stub
        
        TypedQuery<CgShareAudjustmentHistoryDBO> query =
                getEntityManager().createNamedQuery("CgShareAudjustmentHistoryDBO.loadCommonGrazingShareAdjustmentHistory",
                        CgShareAudjustmentHistoryDBO.class);
        return query.setParameter("locationCode", strLocationCode).getResultList();
    }

	@Override
	public void saveCgShareHistory(
			CgShareAudjustmentHistoryDBO cgShareAudjustmentHistoryDBO)
			throws ConcurrentAccessException {
        Date effectiveDate = cgShareAudjustmentHistoryDBO.getEffectiveDate();
        String locationCode = cgShareAudjustmentHistoryDBO.getLocationCode();

        TypedQuery<CgShareAudjustmentHistoryDBO> query =
                getEntityManager().createNamedQuery("CgShareAudjustmentHistoryDBO.updateDeleteFlag",
                        CgShareAudjustmentHistoryDBO.class);
        query.setParameter("locationCode", locationCode);
        query.setParameter("effectiveDateVal", effectiveDate);
        query.executeUpdate();
        create(cgShareAudjustmentHistoryDBO);
		
	}

}
