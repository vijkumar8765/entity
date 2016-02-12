package uk.gov.scotland.afrc.applications.dao.impl;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AnnualRecurrentRotationYrDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AnnualRecurrentRotationYrDBO;

public class AnnualRecurrentRotationYrDaoImpl extends EntityManagerBaseImpl<AnnualRecurrentRotationYrDBO, Long> implements  AnnualRecurrentRotationYrDao{

    private static Logger logger = Logger.getLogger(AnnualRecurrentRotationYrDaoImpl.class);
	
	public AnnualRecurrentRotationYrDaoImpl() {
		super(AnnualRecurrentRotationYrDBO.class);
        logger.debug("AnnualRecurrentRotationYrDaoImpl");
	}

}
