/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AdditionalTaskInfoDBO;

public interface AdditionalTaskInfoDao extends EntityManagerBase<AdditionalTaskInfoDBO, Long> {

    AdditionalTaskInfoDBO findPaperSafTaskByBrnAndApplicationId(long brn, long applicationId);
	
	 AdditionalTaskInfoDBO findTaskByTaskTypeBrnAndApplicationId(long taskTypeId, long brn, long applicationId);

}