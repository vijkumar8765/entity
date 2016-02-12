/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.EligibleLandUseDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.EligibleLandUseDBO;

public class EligibleLandUseDaoImpl extends EntityManagerBaseImpl<EligibleLandUseDBO, Long> implements EligibleLandUseDao {
    public EligibleLandUseDaoImpl() {
        super(EligibleLandUseDBO.class);
    }

    @Override
    public boolean isLUTEligibleForBPS(long landUseTypeId) {
        TypedQuery<EligibleLandUseDBO> query =
                getEntityManager().createNamedQuery("EligibleLandUseDBO.findBPSEligibilityByIdLUT",
                        EligibleLandUseDBO.class);

        List<EligibleLandUseDBO> result = query.setParameter("landUseTypeId", landUseTypeId).getResultList();
        return (result != null && !result.isEmpty());
    }
}
