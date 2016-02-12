/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LpisLandParcelDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LpisLandParcelDBO;

public class LpisLandParcelDaoImpl extends EntityManagerBaseImpl<LpisLandParcelDBO, Long> implements LpisLandParcelDao {

    private final Logger logger = Logger.getLogger(LpisLandParcelDaoImpl.class);

    public LpisLandParcelDaoImpl() {
        super(LpisLandParcelDBO.class);
    }

    @Override
    public List<LpisLandParcelDBO> getLandparcelsByLocationId(long locationId) {

        TypedQuery<LpisLandParcelDBO> query =
                getEntityManager().createNamedQuery("LpisLandParcelDBO.findByLocationId", LpisLandParcelDBO.class);
        logger.debug("query = " + query.toString() + locationId);
        return query.setParameter("locationId", locationId).getResultList();
    }
    
	@Override
	public List<LpisLandParcelDBO> findByParcelId(String parcelId) {
		TypedQuery<LpisLandParcelDBO> query = getEntityManager().createNamedQuery("LpisLandParcel.findByParcelId", LpisLandParcelDBO.class);
		query.setParameter("parcelId", parcelId);
        logger.debug("query = " + query.toString() + parcelId);
		return query.getResultList();
	}

    @Override
    public LpisLandParcelDBO findById(Long lpisLndPrclId) {
        logger.debug("lpisLndPrclId = " + lpisLndPrclId);
        return getEntityManager().find(LpisLandParcelDBO.class, lpisLndPrclId);
    }
}
