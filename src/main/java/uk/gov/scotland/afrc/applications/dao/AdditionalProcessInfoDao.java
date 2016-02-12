/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AdditionalProcessInfoDBO;

public interface AdditionalProcessInfoDao extends EntityManagerBase<AdditionalProcessInfoDBO, Long> {

	AdditionalProcessInfoDBO findByBrn(long brn);
	AdditionalProcessInfoDBO findByProcessAndAppId(long applicationId, String bpmnProcessId);
	AdditionalProcessInfoDBO findByBrnAndAppId(long brn, long applicationId);
	
}