/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.CgShareClaimDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CgShareClaimDBO;
import uk.gov.scotland.afrc.applications.model.domain.CgShareLndUseAreaDBO;

public class CgShareClaimDaoImpl extends EntityManagerBaseImpl<CgShareClaimDBO, Long> implements CgShareClaimDao {
    public CgShareClaimDaoImpl() {
        super(CgShareClaimDBO.class);
    }

    @Override
    public List<CgShareClaimDBO> findBySchemeAndApplicationId(long schemeId, long applicationId) {
        TypedQuery<CgShareClaimDBO> query =
                getEntityManager().createNamedQuery("CgShareClaim.findBySchemeAndApplicationId", CgShareClaimDBO.class);
        query.setParameter("schemeId", Long.valueOf(schemeId));
        query.setParameter("applicationId", Long.valueOf(applicationId));
        query.setParameter("isConfirmed", true);
        return query.getResultList();
    }

    @Override
    public void removeByCGLandUseArea(CgShareLndUseAreaDBO cgLuaDbo) {
        getEntityManager().createNamedQuery("CgShareClaimDBO.removeByCGLandUseArea")
                .setParameter("cgShareLandUseArea", cgLuaDbo)
                .executeUpdate();
    }
}