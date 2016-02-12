package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationCommonGrazingShareDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationCommonGrazingDBO;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationCommonGrazingShareDBO;

public class ApplicationCommonGrazingShareDaoImpl extends EntityManagerBaseImpl<ApplicationCommonGrazingShareDBO, Long> implements ApplicationCommonGrazingShareDao {
	
    private static Logger logger = Logger.getLogger(ApplicationCommonGrazingShareDaoImpl.class);

    private static final String APP_CG = "appCg";

    public ApplicationCommonGrazingShareDaoImpl() {
		super(ApplicationCommonGrazingShareDBO.class);
	}

    @Override
    public List<ApplicationCommonGrazingShareDBO> getByAppCGId(ApplicationCommonGrazingDBO appCgDbo) {
        TypedQuery<ApplicationCommonGrazingShareDBO> query =
                getEntityManager()
                        .createNamedQuery("AppCgShareBDO.findByAppCG",
                        ApplicationCommonGrazingShareDBO.class);
        query.setParameter(APP_CG, appCgDbo);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }

    @Override
    public List<ApplicationCommonGrazingShareDBO> getActiveShareByAppCGId(ApplicationCommonGrazingDBO appCgDbo) {
        TypedQuery<ApplicationCommonGrazingShareDBO> query =
                getEntityManager().createNamedQuery("AppCgShareBDO.getActiveSharesByAppCG",
                        ApplicationCommonGrazingShareDBO.class);
        query.setParameter(APP_CG, appCgDbo);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }
    
    @Override
    public List<ApplicationCommonGrazingShareDBO> getApplicationShareByCGAndCroft(ApplicationCommonGrazingDBO applicationCommonGrazingDBO, long croftLocation) {
        TypedQuery<ApplicationCommonGrazingShareDBO> query =
                getEntityManager().createNamedQuery("AppCgShareBDO.getShareByCGAndCroft",
                        ApplicationCommonGrazingShareDBO.class);
        query.setParameter(APP_CG, applicationCommonGrazingDBO);
        query.setParameter("appCgCroftId", croftLocation);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }

    @Override
    public List<ApplicationCommonGrazingShareDBO>
        getApplicationShareByCGAndCroftAndName(ApplicationCommonGrazingDBO applicationCommonGrazingDBO,
                                               long croftLocation, String shareName) {
        TypedQuery<ApplicationCommonGrazingShareDBO> query =
                getEntityManager().createNamedQuery("AppCgShareBDO.getShareByCGAndCroftAndName",
                        ApplicationCommonGrazingShareDBO.class);
        query.setParameter(APP_CG, applicationCommonGrazingDBO);
        query.setParameter("appCgCroftId", croftLocation);
        query.setParameter("shareName", shareName);
        return query.getResultList();
    }

    @Override
    public List<ApplicationCommonGrazingShareDBO>
        getApplicationShareByCGAndCroftName(ApplicationCommonGrazingDBO appCG, String croftName) {
            TypedQuery<ApplicationCommonGrazingShareDBO> query =
            getEntityManager().createNamedQuery("AppCgShareBDO.getShareByCGAndCroftName",
            ApplicationCommonGrazingShareDBO.class);
            query.setParameter(APP_CG, appCG);
            query.setParameter("shareName", croftName);
            return query.getResultList();
    }
}
