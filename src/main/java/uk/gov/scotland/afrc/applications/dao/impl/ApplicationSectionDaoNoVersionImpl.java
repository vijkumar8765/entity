/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationSectionDaoNoVersion;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSectionDBONoVersion;

public class ApplicationSectionDaoNoVersionImpl extends EntityManagerBaseImpl<ApplicationSectionDBONoVersion, Long>
    implements ApplicationSectionDaoNoVersion {

    private static Logger logger = Logger.getLogger(ApplicationSectionDaoNoVersionImpl.class);

    public ApplicationSectionDaoNoVersionImpl() {
        super(ApplicationSectionDBONoVersion.class);
        logger.debug("ApplicationSectionDaoNoVersionImpl");
    }

}
