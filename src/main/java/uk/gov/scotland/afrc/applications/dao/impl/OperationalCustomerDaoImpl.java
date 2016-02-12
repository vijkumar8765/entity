package uk.gov.scotland.afrc.applications.dao.impl;

import uk.gov.scotland.afrc.applications.dao.OperationalCustomerDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.OperationalCustomerDBO;

public class OperationalCustomerDaoImpl extends EntityManagerBaseImpl<OperationalCustomerDBO, Long> implements OperationalCustomerDao{

	public OperationalCustomerDaoImpl() {
		super(OperationalCustomerDBO.class);
	}
	
}
