/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.CustomerLocationDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CustomerLocationDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

/**
 * @author d607693
 *
 */
public class CustomerLocationDaoImpl extends EntityManagerBaseImpl<CustomerLocationDBO, Long> implements
    CustomerLocationDao {
    public CustomerLocationDaoImpl() {
        super(CustomerLocationDBO.class);
    }

    public List<CustomerLocationDBO> getLocationsByCode(String locationCode) {
        TypedQuery<CustomerLocationDBO> query =
                getEntityManager().createNamedQuery("CustomerLocation.retrieveByLocationCode",
                        CustomerLocationDBO.class);

        return query.setParameter("locationCode", locationCode).getResultList();
    }
    
    @Override
    public CustomerLocationDBO getLocationByGrazing(long locationId) {
        TypedQuery<CustomerLocationDBO> query =
                getEntityManager().createNamedQuery("CustomerLocation.retrieveByLocationAndGrazing",
                        CustomerLocationDBO.class);

        return query.setParameter("locationId", locationId).getSingleResult();
    }

    @Override
    public CustomerLocationDBO getLocationByLocationId(long locationId) {
        TypedQuery<CustomerLocationDBO> query =
                getEntityManager().createNamedQuery("CustomerLocation.retrieveByLocationId",
                        CustomerLocationDBO.class);

        return query.setParameter("locationId", locationId).getSingleResult();
    }

	@Override
	public void updateLocationDBO(CustomerLocationDBO customerLocationDBO)
			throws ConcurrentAccessException {
		update(customerLocationDBO);
		
	}

}
