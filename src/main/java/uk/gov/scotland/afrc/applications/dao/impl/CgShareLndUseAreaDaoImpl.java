/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.CgShareLndUseAreaDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CgShareLndUseAreaDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class CgShareLndUseAreaDaoImpl extends EntityManagerBaseImpl<CgShareLndUseAreaDBO, Long> implements
    CgShareLndUseAreaDao {
    public CgShareLndUseAreaDaoImpl() {
        super(CgShareLndUseAreaDBO.class);
    }

    @Override
    public List<CgShareLndUseAreaDBO> findByByApplicationId(long applicationId) {
        TypedQuery<CgShareLndUseAreaDBO> query =
                getEntityManager()
                        .createNamedQuery("CgShareLndUseArea.findByApplicationId", CgShareLndUseAreaDBO.class);

        return query.setParameter("applicationId", Long.valueOf(applicationId)).setParameter("isConfirmed", true)
                .getResultList();
    }

    @Override
    public List<CgShareLndUseAreaDBO> findByByAppCGShareId(long appCGShareId) {
        TypedQuery<CgShareLndUseAreaDBO> query =
                getEntityManager().createNamedQuery("CgShareLndUseArea.findByAppCGShareId", CgShareLndUseAreaDBO.class);

        return query.setParameter("appCGShareId", Long.valueOf(appCGShareId)).getResultList();
    }

    @Override
    public void removeCGLua(Long cgShareLuaId) throws ConcurrentAccessException {
        delete(this.findById(cgShareLuaId));
    }
}