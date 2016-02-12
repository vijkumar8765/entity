/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LandUseClaimDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LandUseAreaDBO;
import uk.gov.scotland.afrc.applications.model.domain.LandUseClaimDBO;
import uk.gov.scotland.afrc.applications.model.domain.SchemeDBO;

public class LandUseClaimDaoImpl extends EntityManagerBaseImpl<LandUseClaimDBO, Long> implements LandUseClaimDao {

    private final Logger logger = Logger.getLogger(LandUseClaimDaoImpl.class);

    public LandUseClaimDaoImpl() {
        super(LandUseClaimDBO.class);
    }

    @Override
    public LandUseClaimDBO findByLandUseAreaAndScheme(LandUseAreaDBO luaDbo, SchemeDBO schemeDbo) {
        TypedQuery<LandUseClaimDBO> query =
                getEntityManager().createNamedQuery("LandUseClaim.findByLandUseAreaAndScheme", LandUseClaimDBO.class);

        query.setParameter("landUseArea", luaDbo);
        query.setParameter("scheme", schemeDbo);

        LandUseClaimDBO dbo = null;

        logger.debug("query  = " + query);

        try {
            dbo = query.getSingleResult();
        } catch (NoResultException nre) {
            logger.error(nre);
            // NOSONAR Ignore. We return null by convention.
        }
        return dbo;
    }

    @Override
    public LandUseClaimDBO newClaim() {
        return new LandUseClaimDBO();
    }

    @Override
    public void removeByLandUseArea(LandUseAreaDBO luaDbo) {
        getEntityManager().createNamedQuery("LandUseClaim.removeByLandUseArea").setParameter("landUseArea", luaDbo)
                .executeUpdate();
    }

    @Override
    public void removeByLandUseAreaAndScheme(LandUseAreaDBO luaDbo, SchemeDBO schemeDbo) {
        TypedQuery<LandUseClaimDBO> query =
                getEntityManager().createNamedQuery("LandUseClaim.removeByLandUseAreaAndScheme", LandUseClaimDBO.class);
        query.setParameter("landUseArea", luaDbo);
        query.setParameter("scheme", schemeDbo);

        query.executeUpdate();

    }

    @Override
    public List<LandUseClaimDBO> findBySchemeAndApplicationId(long schemeId, long applicationId) {
        TypedQuery<LandUseClaimDBO> query =
                getEntityManager().createNamedQuery("LandUseClaim.findBySchemeAndApplicationId", LandUseClaimDBO.class);
        query.setParameter("schemeId", schemeId);
        query.setParameter("applicationId", Long.valueOf(applicationId));
        query.setParameter("isConfirmed", true);
        return query.getResultList();
    }
    
    @Override
    public List<LandUseClaimDBO> findByLandUseAreaId(long lndUseAreaId) {
        TypedQuery<LandUseClaimDBO> query =
                getEntityManager().createNamedQuery("LandUseClaim.findByLandUseAreaId", LandUseClaimDBO.class);

        query.setParameter("lndUseAreaId", lndUseAreaId);


        return query.getResultList();
    }
}
