/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import uk.gov.scotland.afrc.applications.dao.EvidenceRequirementTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.EvidenceRequirementTypeDBO;

public class EvidenceRequirementTypeDaoImpl extends EntityManagerBaseImpl<EvidenceRequirementTypeDBO, Long> implements
    EvidenceRequirementTypeDao {
    public EvidenceRequirementTypeDaoImpl() {
        super(EvidenceRequirementTypeDBO.class);
    }

}