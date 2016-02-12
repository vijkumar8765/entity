package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationCommonGrazingDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationCommonGrazingDBO;

public class ApplicationCommonGrazingDaoImpl extends EntityManagerBaseImpl<ApplicationCommonGrazingDBO, Long> implements
    ApplicationCommonGrazingDao {

    private static Logger logger = Logger.getLogger(ApplicationCommonGrazingDaoImpl.class);

    public ApplicationCommonGrazingDaoImpl() {
        super(ApplicationCommonGrazingDBO.class);
    }

    @Override
    public List<ApplicationCommonGrazingDBO> findByApplicationId(long applicationId) {
        TypedQuery<ApplicationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("ApplicationCommonGrazing.findByApplicationId",
                        ApplicationCommonGrazingDBO.class);

        query.setParameter("applicationId", applicationId);
        logger.debug("query = " + query.toString() + " " + applicationId);
        return query.getResultList();
    }

    @Override
    public ApplicationCommonGrazingDBO getByAppIdAndLocationId(long appId, long locationId) {
        ApplicationCommonGrazingDBO result = null;
        TypedQuery<ApplicationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("ApplicationCommonGrazing.getByAppIdAndLocationId",
                        ApplicationCommonGrazingDBO.class);
        query.setParameter("appId", appId).setParameter("locationId", locationId);

        logger.debug("query = " + query.toString() + " " + appId + " " + locationId);

        List<ApplicationCommonGrazingDBO> resultSet = query.getResultList();
        if ((null != resultSet) && (resultSet.size() != 0)) {
            result = resultSet.get(0);
        }
        logger.debug("result == null " + (result == null));
        return result;
    }

    @Override
    public List<ApplicationCommonGrazingDBO> findByLocationId(long locationId) {
        TypedQuery<ApplicationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("ApplicationCommonGrazing.findByLocationId",
                        ApplicationCommonGrazingDBO.class);

        query.setParameter("locationId", locationId);
        logger.debug("query " + query.toString() + " " + locationId);
        return query.getResultList();
    }
}
