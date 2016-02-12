/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.CgShareLndUseAreaDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

/**
 * The Interface CgShareLndUseAreaDao.
 */
public interface CgShareLndUseAreaDao extends EntityManagerBase<CgShareLndUseAreaDBO, Long> {

    List<CgShareLndUseAreaDBO> findByByApplicationId(long applicationId);

    List<CgShareLndUseAreaDBO> findByByAppCGShareId(long appCGShareId);

    void removeCGLua(Long cgShareLuaId) throws ConcurrentAccessException;
}
