package uk.gov.scotland.afrc.applications.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LocationCommonGrazingDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CustomerLocationDBO;
import uk.gov.scotland.afrc.applications.model.domain.LocationCommonGrazingDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class LocationCommonGrazingDaoImpl extends EntityManagerBaseImpl<LocationCommonGrazingDBO, Long> implements
    LocationCommonGrazingDao {

    private static final String LOC_ID_CG = "locationIdCommonGrazing";

    private static final String LOCATION_CODE = "locationCode";
    private static final String LOCATION_CG_ID = "locationCommonGrazingId";
    private static final String QUERY = "query = ";

    private final Logger logger = Logger.getLogger(LocationCommonGrazingDaoImpl.class);

    public LocationCommonGrazingDaoImpl() {
        super(LocationCommonGrazingDBO.class);

    }

    @Override
    public List<LocationCommonGrazingDBO> getCommonGrazingByLocationId(long locationId) {
        TypedQuery<LocationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingDBO.findByLocationId",
                        LocationCommonGrazingDBO.class);
        logger.debug(QUERY + query.toString() + locationId);
        return query.setParameter(LOC_ID_CG, new BigDecimal(locationId)).getResultList();
    }

    @Override
    public List<LocationCommonGrazingDBO> getCommonGrazingByLocationIdCroft(long locationId) {
        TypedQuery<LocationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingDBO.findByLocationIdCroft",
                        LocationCommonGrazingDBO.class);
        logger.debug(QUERY + query.toString() + locationId);
        return query.setParameter("locationIdCroft", new BigDecimal(locationId)).getResultList();
    }

    @Override
    public List<LocationCommonGrazingDBO> getCommonGrazingByLocationIdCommonGrazing(long locationId) {
        TypedQuery<LocationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingDBO.findByLocationIdCommonGrazing",
                        LocationCommonGrazingDBO.class);
        logger.debug(QUERY + query.toString() + locationId);
        return query.setParameter(LOC_ID_CG, new BigDecimal(locationId)).getResultList();
    }

    @Override
    public List<LocationCommonGrazingDBO> getLocationCommonGrazingByCGandCroft(long locationIdCG, long locationIdCroft) {
        TypedQuery<LocationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingDBO.findByCGandCroft",
                        LocationCommonGrazingDBO.class);
        logger.debug(QUERY + query.toString() + locationIdCG + " " + locationIdCroft);
        return query.setParameter(LOC_ID_CG, locationIdCG).setParameter("locationIdCroft", locationIdCroft)
                .getResultList();
    }

    @Override
    public LocationCommonGrazingDBO getLocationCommonGrazingByCGAndCroftAndName(long locationIdCG,
                                                                                long locationIdCroft, String shareName) {

        TypedQuery<LocationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingDBO.findByCGAndCroftAndName",
                        LocationCommonGrazingDBO.class);
        logger.debug(QUERY + query.toString() + locationIdCG + " " + shareName);
        return query.setParameter(LOC_ID_CG, locationIdCG).setParameter("locationIdCroft", locationIdCroft)
                .setParameter("shareName", shareName).getSingleResult();
    }

    @Override
    public LocationCommonGrazingDBO getLocationCommonGrazingByCGAndName(long locationId, String croftName) {
        TypedQuery<LocationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingDBO.findByCGAndCroftName",
                        LocationCommonGrazingDBO.class);
        logger.debug(QUERY + query.toString() + locationId + " " + croftName);
        return query.setParameter(LOC_ID_CG, locationId).setParameter("shareName", croftName).getSingleResult();

    }

    @Override
    public void saveLocationCommonGrazingDBO(LocationCommonGrazingDBO locationCommonGrazingDBO)
        throws ConcurrentAccessException {

        if (locationCommonGrazingDBO.getLocationCommonGrazingId() != 0) {
            logger.debug("locationCommonGrazingDBO.getLocationCommonGrazingId() != 0");
            update(locationCommonGrazingDBO);
        } else {
            logger.debug("locationCommonGrazingDBO.getLocationCommonGrazingId() == 0");
            create(locationCommonGrazingDBO);
        }
    }

    @Override
    public CustomerLocationDBO getCGsByLocationCode(String locationCode) {
        CustomerLocationDBO customerLocation = null;

        TypedQuery<CustomerLocationDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingDBO.retrieveCommonGrzingsByLocation",
                        CustomerLocationDBO.class);
        logger.info(QUERY + query.toString() + locationCode);

        List<CustomerLocationDBO> listOfLocations = query.setParameter(LOCATION_CODE, locationCode).getResultList();
        if (null != listOfLocations && listOfLocations.size()>0) {
            logger.info("Found common grazing locations  :" + listOfLocations.size());

            customerLocation = listOfLocations.get(0);
        }

        return customerLocation;

    }

    @Override
    public List<CustomerLocationDBO> getCommonGrazingsByBRN(long brn) {
        TypedQuery<CustomerLocationDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingDBO.getCommonGrazingsByBRN",
                        CustomerLocationDBO.class);
        logger.debug(QUERY + query.toString() + brn);
        return query.setParameter("brn", brn).getResultList();
    }

    @Override
    public LocationCommonGrazingDBO getLocationCommonGrazingByCGID(long locationIdCG) {
        TypedQuery<LocationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingDBO.findByLocationCommonGrazingId",
                        LocationCommonGrazingDBO.class);
        logger.debug(QUERY + query.toString() + locationIdCG);
        return query.setParameter(LOCATION_CG_ID, new BigDecimal(locationIdCG)).getSingleResult();
    }

    @Override
    public List<LocationCommonGrazingDBO> getLocationCommonGrazingByCG(long locationIdCG) {
        TypedQuery<LocationCommonGrazingDBO> query =
                getEntityManager().createNamedQuery("LocationCommonGrazingDBO.findByLocationId",
                        LocationCommonGrazingDBO.class);
        logger.debug(QUERY + query.toString() + locationIdCG);
        return query.setParameter(LOC_ID_CG, locationIdCG).getResultList();

    }

}