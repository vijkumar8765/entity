/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.CropFamilyTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CropFamilyTypeDBO;

public class CropFamilyTypeDaoImpl extends EntityManagerBaseImpl<CropFamilyTypeDBO, Long> implements CropFamilyTypeDao {

    public CropFamilyTypeDaoImpl() {
        super(CropFamilyTypeDBO.class);
    }

	@Override
	public CropFamilyTypeDBO findByName(String name) {
        TypedQuery<CropFamilyTypeDBO> query = getEntityManager().createNamedQuery("CropFamilyTypeDBO.findByName", CropFamilyTypeDBO.class);
        return query.setParameter("name", name).getSingleResult();
    }
	
	@Override
    public CropFamilyTypeDBO findByCode(String code) {
        TypedQuery<CropFamilyTypeDBO> query = getEntityManager().createNamedQuery("CropFamilyTypeDBO.findByCode", CropFamilyTypeDBO.class);
        return query.setParameter("code", code).getSingleResult();
    }
	
	@Override
	public List<CropFamilyTypeDBO> findAll() {
		TypedQuery<CropFamilyTypeDBO> query =
                getEntityManager().createNamedQuery("CropFamilyTypeDBO.findAll", CropFamilyTypeDBO.class);

        return query.getResultList();
	}
}
