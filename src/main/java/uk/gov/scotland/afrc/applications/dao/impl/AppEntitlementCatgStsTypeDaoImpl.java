package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AppEntitlementCatgStsTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AppEntitlementCatgStsTypeDBO;

public class AppEntitlementCatgStsTypeDaoImpl extends EntityManagerBaseImpl<AppEntitlementCatgStsTypeDBO, Long>
    implements AppEntitlementCatgStsTypeDao {

    private static Logger logger = Logger.getLogger(AppEntitlementCatgStsTypeDaoImpl.class);

    public AppEntitlementCatgStsTypeDaoImpl() {
        super(AppEntitlementCatgStsTypeDBO.class);
    }

    @Override
    public AppEntitlementCatgStsTypeDBO findByName(String name) {
        try {
            TypedQuery<AppEntitlementCatgStsTypeDBO> query =
                    getEntityManager().createNamedQuery("AppEntitlementCatgStsType.findByName",
                            AppEntitlementCatgStsTypeDBO.class);
            query.setParameter("name", name);
            logger.debug("query = " + query.toString());
            return query.getSingleResult();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
}
