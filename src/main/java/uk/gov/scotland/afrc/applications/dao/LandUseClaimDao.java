/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.LandUseAreaDBO;
import uk.gov.scotland.afrc.applications.model.domain.LandUseClaimDBO;
import uk.gov.scotland.afrc.applications.model.domain.SchemeDBO;

public interface LandUseClaimDao extends EntityManagerBase<LandUseClaimDBO, Long> {

    LandUseClaimDBO findByLandUseAreaAndScheme(LandUseAreaDBO luaDBO, SchemeDBO scheme);

    LandUseClaimDBO newClaim();

    void removeByLandUseArea(LandUseAreaDBO luaDbo);

    void removeByLandUseAreaAndScheme(LandUseAreaDBO luaDbo, SchemeDBO schemeDbo);

    List<LandUseClaimDBO> findBySchemeAndApplicationId(long schemeId, long applicationId);
    
    List<LandUseClaimDBO> findByLandUseAreaId(long landUseAreaId);
}
