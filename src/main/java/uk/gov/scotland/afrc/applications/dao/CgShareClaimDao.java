/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.CgShareClaimDBO;
import uk.gov.scotland.afrc.applications.model.domain.CgShareLndUseAreaDBO;

/**
 * The Interface CgShareClaimDao.
 */
public interface CgShareClaimDao extends EntityManagerBase<CgShareClaimDBO, Long> {

    List<CgShareClaimDBO> findBySchemeAndApplicationId(long schemeId, long applicationId);

    void removeByCGLandUseArea(CgShareLndUseAreaDBO cgLuaDbo);
}
