/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.LandUseAreaDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LandUseAreaDBO;

public class LandUseAreaDaoImpl extends EntityManagerBaseImpl<LandUseAreaDBO, Long> implements LandUseAreaDao {
    public LandUseAreaDaoImpl() {
        super(LandUseAreaDBO.class);
    }

    @Override
    public List<LandUseAreaDBO> getPermLandUseForLndPrcl(long appLndPrclId) {
        TypedQuery<LandUseAreaDBO> query =
                getEntityManager().createNamedQuery("LandUseAreaDBO.getPermLandUseForLndPrcl", LandUseAreaDBO.class);
        return query.setParameter("appLndPrclId", appLndPrclId).getResultList();
    }

    @Override
    public LandUseAreaDBO findByIdAndVersion(Long id, Long version) {
        TypedQuery<LandUseAreaDBO> query =
                getEntityManager().createNamedQuery("LandUseAreaDBO.findByIdAndVersion", LandUseAreaDBO.class);
        return query.setParameter("id", id).setParameter("version", version).getSingleResult();
    }

    @Override
    public List<LandUseAreaDBO> findByApplicationIdAndAppLndPrclId(long applicationId, long appLndPrclId) {
        TypedQuery<LandUseAreaDBO> query =
                getEntityManager().createNamedQuery("LandUseAreaDBO.findByApplicationIdAndAppLndPrclId",
                        LandUseAreaDBO.class);
        return query.setParameter("applicationId", applicationId).setParameter("lndPrclApplicationId", appLndPrclId)
                .getResultList();
    }

    public List<LandUseAreaDBO> findByApplicationId(long applicationId) {
        TypedQuery<LandUseAreaDBO> query =
                getEntityManager().createNamedQuery("LandUseAreaDBO.findByApplicationId", LandUseAreaDBO.class);
        return query.setParameter("applicationId", applicationId).getResultList();
    }

    @Override
    public List<LandUseAreaDBO> findByApplicationIdAndParcelType(long applicationId, boolean isPermanent) {
        TypedQuery<LandUseAreaDBO> query =
                getEntityManager().createNamedQuery("LandUseAreaDBO.findByApplicationIdAndParcelType",
                        LandUseAreaDBO.class);

        return query.setParameter("applicationId", applicationId).setParameter("isConfirmed", true)
                .setParameter("isPermanent", isPermanent).getResultList();
    }

}