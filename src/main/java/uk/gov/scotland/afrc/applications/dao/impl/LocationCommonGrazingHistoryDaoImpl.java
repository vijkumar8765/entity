/**
 * 
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LocationCommonGrazingHistoryDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LocationCommonGrazingHistoryDBO;

/**

 *
 */
public class LocationCommonGrazingHistoryDaoImpl extends EntityManagerBaseImpl<LocationCommonGrazingHistoryDBO, Long>
    implements LocationCommonGrazingHistoryDao {

    private final Logger logger = Logger.getLogger(LocationCommonGrazingHistoryDaoImpl.class);

    public LocationCommonGrazingHistoryDaoImpl() {
        super(LocationCommonGrazingHistoryDBO.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uk.gov.scotland.afrc.applications.dao.LocationCommonGrazingHistoryDao #getCGsByLocationCode(long)
     */
    @Override
    public List<LocationCommonGrazingHistoryDBO> getCGHistoryByLocationId(long locationCGId) {
        TypedQuery<LocationCommonGrazingHistoryDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingHistoryDBO.getHistoryForLocationCDId",
                        LocationCommonGrazingHistoryDBO.class);
        query.setParameter("locationId", locationCGId);
        logger.debug("query = " + query.toString() + locationCGId);
        return query.getResultList();
    }

    @Override
    public long insertLocationCGHistory(LocationCommonGrazingHistoryDBO locationCommonGrazingHistoryDBO) {
        logger.debug("insertLocationCGHistory not implemented ");
        return 0;
    }

}
