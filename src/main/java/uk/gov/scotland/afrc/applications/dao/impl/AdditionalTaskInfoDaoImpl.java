/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AdditionalTaskInfoDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AdditionalTaskInfoDBO;

public class AdditionalTaskInfoDaoImpl extends EntityManagerBaseImpl<AdditionalTaskInfoDBO, Long> implements
    AdditionalTaskInfoDao {

    private static Logger logger = Logger.getLogger(AdditionalTaskInfoDao.class);

    private static final long TASK_TYPE = 115L;

    public AdditionalTaskInfoDaoImpl() {
        super(AdditionalTaskInfoDBO.class);
    }

    @Override
    public AdditionalTaskInfoDBO findPaperSafTaskByBrnAndApplicationId(long brn, long applicationId) {
        TypedQuery<AdditionalTaskInfoDBO> query =
                getEntityManager().createNamedQuery("AdditionalTaskInfo.findByTaskTypeBrnAndBusinessKey",
                        AdditionalTaskInfoDBO.class);
        query.setParameter("taskType", TASK_TYPE);
        query.setParameter("brn", brn);
        query.setParameter("businessKey", "applicationId");
        query.setParameter("businessValue", String.valueOf(applicationId));
        List<AdditionalTaskInfoDBO> tasks = query.getResultList();

        if (tasks != null && tasks.isEmpty()) {
            return null;
        }
        if (tasks != null) {
            logger.debug("tasks.size()=" + tasks.size());
        }
        return (tasks != null) ? tasks.get(0) : null;
    }

    @Override
    public AdditionalTaskInfoDBO findTaskByTaskTypeBrnAndApplicationId(long taskTypeId, long brn, long applicationId) {
        TypedQuery<AdditionalTaskInfoDBO> query =
                getEntityManager().createNamedQuery("AdditionalTaskInfo.findByTaskTypeBrnAndBusinessKey",
                        AdditionalTaskInfoDBO.class);
        query.setParameter("taskType", taskTypeId);
        query.setParameter("brn", brn);
        query.setParameter("businessKey", "applicationId");
        query.setParameter("businessValue", String.valueOf(applicationId));
        List<AdditionalTaskInfoDBO> tasks = query.getResultList();

        if (tasks != null && tasks.isEmpty()) {
            return null;
        }
        if (tasks != null) {
            logger.debug("tasks.size()=" + tasks.size());
        }
        return (tasks != null) ? tasks.get(0) : null;
    }
}
