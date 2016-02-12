package uk.gov.scotland.afrc.applications.dao.impl;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationStatusTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationStatusTypeDBO;

public class ApplicationStatusTypeDaoImpl extends
		EntityManagerBaseImpl<ApplicationStatusTypeDBO, Long> implements
		ApplicationStatusTypeDao {

    private static Logger logger = Logger.getLogger(ApplicationStatusTypeDaoImpl.class);
	
	public ApplicationStatusTypeDaoImpl() {
		super(ApplicationStatusTypeDBO.class);
        logger.debug("ApplicationStatusTypeDaoImpl");
	}



}
