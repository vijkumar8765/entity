/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.EligibleLandUseDBO;


public interface EligibleLandUseDao extends EntityManagerBase<EligibleLandUseDBO, Long> {
    boolean isLUTEligibleForBPS(long landUseTypeId);
}
