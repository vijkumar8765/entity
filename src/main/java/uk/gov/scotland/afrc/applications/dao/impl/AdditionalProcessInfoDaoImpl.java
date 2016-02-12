/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AdditionalProcessInfoDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AdditionalProcessInfoDBO;

public class AdditionalProcessInfoDaoImpl extends EntityManagerBaseImpl<AdditionalProcessInfoDBO, Long> implements
    AdditionalProcessInfoDao {

    private static Logger logger = Logger.getLogger(AdditionalProcessInfoDao.class);

    public AdditionalProcessInfoDaoImpl() {
        super(AdditionalProcessInfoDBO.class);
    }

    @Override
    public AdditionalProcessInfoDBO findByBrn(long brn) {
        TypedQuery<AdditionalProcessInfoDBO> query =
                getEntityManager().createNamedQuery("AdditionalProcessInfoDBO.findByBrn",
                        AdditionalProcessInfoDBO.class);
        query.setParameter("brn", brn);
        List<AdditionalProcessInfoDBO> additionalProcessInfoDBO = query.getResultList();

        if (additionalProcessInfoDBO != null && additionalProcessInfoDBO.isEmpty()) {
            return null;
        }
        if (additionalProcessInfoDBO != null) {
            logger.debug("additionalProcessInfoDBO.size()=" + additionalProcessInfoDBO.size());
        }
        return (additionalProcessInfoDBO != null) ? additionalProcessInfoDBO.get(0) : null;
    }

    @Override
    public AdditionalProcessInfoDBO findByBrnAndAppId(long brn, long applicationId) {
        TypedQuery<AdditionalProcessInfoDBO> query =
                getEntityManager().createNamedQuery("AdditionalProcessInfoDBO.findByBrnAndAppId",
                        AdditionalProcessInfoDBO.class);
        query.setParameter("brn", brn);
        query.setParameter("businessKey", "applicationId");
        query.setParameter("businessValue", String.valueOf(applicationId));
        List<AdditionalProcessInfoDBO> additionalProcessInfoDBO = query.getResultList();

        if (additionalProcessInfoDBO != null && additionalProcessInfoDBO.isEmpty()) {
            return null;
        }
        if (additionalProcessInfoDBO != null) {
            logger.debug("additionalProcessInfoDBO.size()=" + additionalProcessInfoDBO.size());
        }
        return (additionalProcessInfoDBO != null) ? additionalProcessInfoDBO.get(0) : null;
    }

    @Override
    public AdditionalProcessInfoDBO findByProcessAndAppId(long applicationId, String bpmnProcessId) {

        TypedQuery<AdditionalProcessInfoDBO> query =
                getEntityManager().createNamedQuery("AdditionalProcessInfoDBO.findByProcessAndAppId",
                        AdditionalProcessInfoDBO.class);

        query.setParameter("businessKey", "applicationId");
        query.setParameter("businessValue", String.valueOf(applicationId));
        query.setParameter("bpmnProcessId", bpmnProcessId);
        List<AdditionalProcessInfoDBO> additionalProcessInfoDBO = query.getResultList();
        if (additionalProcessInfoDBO != null && additionalProcessInfoDBO.isEmpty()) {
            return null;
        }
        if (additionalProcessInfoDBO != null) {
            logger.debug("additionalProcessInfoDBO.size()=" + additionalProcessInfoDBO.size());
        }
        return (additionalProcessInfoDBO != null) ? additionalProcessInfoDBO.get(0) : null;
    }

}
