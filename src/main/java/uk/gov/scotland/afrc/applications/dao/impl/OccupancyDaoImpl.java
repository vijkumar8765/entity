package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.OccupancyDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.OccupancyDBO;

public class OccupancyDaoImpl extends EntityManagerBaseImpl<OccupancyDBO, Long> implements OccupancyDao {

    private final Logger logger = Logger.getLogger(OccupancyDaoImpl.class);
    public static final int START_POSITION = 2;
    public static final int END_POSITION = 5;

    public OccupancyDaoImpl() {
        super(OccupancyDBO.class);
    }

    @Override
    public List<OccupancyDBO> findByLocation(long locationId) {
        TypedQuery<OccupancyDBO> query =
                getEntityManager().createNamedQuery("OccupancyDBO.findByLocationId", OccupancyDBO.class);
        logger.debug("query = " + query.toString() + locationId);
        return query.setParameter("locationId", locationId).getResultList();
    }

    @Override
    public OccupancyDBO findByBrnAndLocationId(long locationId, long brn) {
        TypedQuery<OccupancyDBO> query =
                getEntityManager().createNamedQuery("OccupancyDBO.findByBrnAndLocationId", OccupancyDBO.class);
        logger.debug("query = " + query.toString() + locationId + " " + brn);
        return query.setParameter("locationId", locationId).setParameter("brn", brn).getSingleResult();

    }
    
    @Override
    public Long findAllocationQueueIdForBrnMainLocation(long brn) {
    	TypedQuery<String> query =
    			getEntityManager().createNamedQuery("OccupancyDBO.findMainLocationCodeForBrn", String.class);
        query.setParameter("brn", brn);
        List<String> list = query.getResultList();
        if(list.size() > 0){
	       	 String locationCode =  list.get(0);
	       	 TypedQuery<Long> allocationQuery =
	       			getEntityManager().createNamedQuery("AreaOfficeParishQueueAllocationDBO.findAllocationQueueIdByParishTypeCode", Long.class);
	       	 allocationQuery.setParameter("parishTypeCode", Long.valueOf(locationCode.substring(START_POSITION,END_POSITION)));
	       	 List<Long> result = allocationQuery.getResultList();
	       	 return (result.size() > 0 ? result.get(0) : null);
        }
        return null;
    }

}
