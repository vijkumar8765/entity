/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationSuspensionDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AppSuspensionDBO;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationDBO;

/**
 * @author d609030
 *
 */
public class ApplicationSuspensionDaoImpl extends EntityManagerBaseImpl<AppSuspensionDBO, Long> implements
    ApplicationSuspensionDao {

    private static Logger logger = Logger.getLogger(ApplicationStatusTypeDaoImpl.class);

    private static final String RESUME_OP_CUST_ID = "resumeOpCustId";
    private static final String HOLD_TYPE_ID = "holdTypeId";
    private static final String HOLD_LEVEL_ID = "holdLevelId";
    private static final String BRN = "brn";
    private static final String YEAR = "year";
	public static final String APPLICATION_ID = "applicationId";
	private static final String SCHEME_ID = "schemeId";
	private static final String QUERY = "query = ";

	public ApplicationSuspensionDaoImpl() {
        super(AppSuspensionDBO.class);
    }

	@Override
	public List<AppSuspensionDBO> findAllHoldsByBRN(Long brn) {
		TypedQuery<AppSuspensionDBO> query =
                getEntityManager().createNamedQuery("AppSuspensionDBO.findAllHoldsByBRN", AppSuspensionDBO.class);
		query.setParameter(BRN, brn);
        logger.debug(QUERY + query.toString() + " " + brn);
		return  new ArrayList<AppSuspensionDBO>(query.getResultList());
	}

	@Override
	public List<Object[]> findSchemeTypes(Long brn) {
		TypedQuery<Object[]> query=getEntityManager().createNamedQuery("AppSuspensionDBO.findSchemeTypes", Object[].class);
		query.setParameter(BRN, brn);

        logger.debug(QUERY + query.toString() + " " + brn);

		return new ArrayList<Object[]>(query.getResultList());
	}

	@Override
	public List<Object[]> findApplicationTypes(Long brn) {
		TypedQuery<Object[]> query=getEntityManager().createNamedQuery("AppSuspensionDBO.findApplicationTypes", Object[].class);
        String year = Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR)).toString();
        query.setParameter(BRN, brn);
        query.setParameter(YEAR, year);
        logger.debug(QUERY + query.toString() + " " + brn + " " + year);
		return new ArrayList<Object[]>(query.getResultList());
	}

	@Override
	public Long findApplicationHoldCount(Long brn, Long holdTypeId) {
		TypedQuery<AppSuspensionDBO> query =
                getEntityManager().createNamedQuery("AppSuspensionDBO.findApplicationHoldCount", AppSuspensionDBO.class);
        query.setParameter(BRN, brn);
        query.setParameter(HOLD_TYPE_ID, holdTypeId);
        query.setParameter(RESUME_OP_CUST_ID, null);
        logger.debug(QUERY + query.toString() + " " + brn);
        List<AppSuspensionDBO> result = query.getResultList();
        return (result!=null?result.size() : 0L);
	}
	
	@Override
	public List<AppSuspensionDBO> getApplicationHoldStatus(long applicationId) {
		 TypedQuery<AppSuspensionDBO> query =
	                getEntityManager().createNamedQuery("AppSuspensionDBO.findApplicationHoldStatus", AppSuspensionDBO.class);
	        query.setParameter(APPLICATION_ID, applicationId);
        logger.debug(QUERY + query.toString() + " " + applicationId);
	        return query.getResultList();
	}

    @Override
    public List<ApplicationDBO> findApplicationsBySchemeId(long brn, long schemeId) {
        TypedQuery<ApplicationDBO> query =
                getEntityManager().createNamedQuery("AppSuspensionDBO.findApplicationsBySchemeId", ApplicationDBO.class);
        query.setParameter(BRN, brn);
        query.setParameter(SCHEME_ID,schemeId);
        logger.debug(QUERY + query.toString() + " " + brn + " " + schemeId);
        return query.getResultList();
    }

    @Override
    public Boolean findCustomerHoldExists(Long brn, Long holdLevelId, Long holdTypeId) {
        TypedQuery<AppSuspensionDBO> query =
                getEntityManager().createNamedQuery("AppSuspensionDBO.findCustomerHoldExists", AppSuspensionDBO.class);
        query.setParameter(BRN, brn);
        query.setParameter(HOLD_LEVEL_ID, holdLevelId);
        query.setParameter(HOLD_TYPE_ID, holdTypeId);
        query.setParameter(RESUME_OP_CUST_ID, null);
        logger.debug(QUERY + query.toString() + " " + brn + " " + holdLevelId + " " + holdTypeId);
        List<AppSuspensionDBO> result = query.getResultList();
        return (result!=null&&result.size()>0);
    }

    @Override
    public Boolean findApplicationOrSchemeHoldExists(Long applicationId, Long holdLevelId, Long holdTypeId) {
        TypedQuery<AppSuspensionDBO> query =
                getEntityManager().createNamedQuery("AppSuspensionDBO.findApplicationOrSchemeHoldExists", AppSuspensionDBO.class);
        query.setParameter(HOLD_LEVEL_ID, holdLevelId);
        query.setParameter(HOLD_TYPE_ID, holdTypeId);
        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter(RESUME_OP_CUST_ID, null);
        logger.debug(QUERY + query.toString() + " " + applicationId + " " + holdLevelId + " " + holdTypeId);
        List<AppSuspensionDBO> result = query.getResultList();
        return (result!=null&&result.size()>0);
    }

}
