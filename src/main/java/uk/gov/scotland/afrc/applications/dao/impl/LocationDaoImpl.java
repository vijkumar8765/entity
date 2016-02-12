/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LocationDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class LocationDaoImpl extends EntityManagerBaseImpl<LocationDao.Location, Long> implements LocationDao {

    static final Integer CONSTANT_ZERO = 0;
    static final Integer CONSTANT_TWO = 2;
    static final Integer CONSTANT_THREE = 3;
    static final Integer CONSTANT_FOUR = 4;
    private static final String OPERATION_NOT_SUPPORTED = "operation not supported !";

    private final Logger logger = Logger.getLogger(LocationDaoImpl.class);

    public LocationDaoImpl() {
        super(LocationDao.Location.class);
    }

    @Override
    public LocationDao.Location findById(Long id) {
        final EntityManager em = getEntityManager();
        final Query q =
                em.createNativeQuery(
                        "select distinct l.location_name, o.main_location_flag,l.location_code from location l LEFT OUTER JOIN occupancy o on o.location_id=l.location_id where l.location_id=?1 and o.occupancy_end_date is null",
                        Object[].class);
        q.setParameter(1, id);

        LocationDao.Location result = null;
        logger.debug("query = " + q.toString() + id);

        try {
            final Object[] qResult = (Object[]) q.getSingleResult();

            boolean mlc = qResult[1] == null ? false : ((BigDecimal) qResult[1]).doubleValue() != 0;

            result = new LocationDao.Location((String) qResult[CONSTANT_ZERO], mlc, (String) qResult[CONSTANT_TWO]);
            logger.debug("result = " + result);
        } catch (NoResultException e) {
            logger.error(e);
        }

        return result;

    }

    @Override
    public LocationDao.Location getLocationByBrn(Long id, long brn) {
        final EntityManager em = getEntityManager();
        final Query q =
                em.createNativeQuery(
                        "select l.location_name, o.main_location_flag,l.location_code,o.occupancy_end_date,l.location_id  from location l LEFT OUTER JOIN occupancy o on o.location_id=l.location_id where l.location_id=?1 and o.brn=?2",
                        Object[].class);
        q.setParameter(1, id);
        q.setParameter(2, brn);
        logger.debug("query = " + q + " " + id + "  " + brn);
        LocationDao.Location result = null;
        try {
            final Object[] qResult = (Object[]) q.getSingleResult();

            boolean mlc = qResult[1] == null ? false : ((BigDecimal) qResult[1]).doubleValue() != 0;
            long locationId = (qResult[4] == null ? 0 : ((BigDecimal) qResult[4]).longValue());
            result =
                    new LocationDao.Location((String) qResult[CONSTANT_ZERO], mlc, (String) qResult[CONSTANT_TWO],
                            (Date) qResult[CONSTANT_THREE], locationId);

            logger.debug("result = " + result);
        } catch (NoResultException e) {
            logger.error(e);
        }

        return result;
    }

    @Override
    public LocationDao.Location findByCode(String locationCode) {

        final EntityManager em = getEntityManager();
        final Query q =
                em.createNativeQuery(
                        "select l.location_name,l.location_code from location l  where l.location_code like ?1",
                        Object[].class);
        q.setParameter(1, "%" +locationCode);
        logger.info("q = " + q);
        LocationDao.Location result = null;

        try {
            final Object[] qResult = (Object[]) q.getSingleResult();

            // Making the MLC is false by default because we are only searching for the location but not for Business
            result = new LocationDao.Location((String) qResult[0], false, (String) qResult[1]);
            logger.info("result = " + result);
        } catch (NoResultException e) {
            logger.error(e);
        }

        return result;
    }

    @Override
    public LocationDao.Location getLocation(Long id, long mainLocationFlag) {
        final EntityManager em = getEntityManager();
        final Query q =
                em.createNativeQuery(
                        "select l.location_name,l.location_code,l.location_id from location l where l.location_id=?1 ",
                        Object[].class);
        q.setParameter(1, id);
        LocationDao.Location result = null;
        try {
            final Object[] qResult = (Object[]) q.getSingleResult();

            boolean mlc = mainLocationFlag == 0l ? false : mainLocationFlag != 0;
            long locationId = (qResult[2] == null ? 0 : ((BigDecimal) qResult[2]).longValue());
            result =
                    new LocationDao.Location((String) qResult[CONSTANT_ZERO], mlc, (String) qResult[1], null,
                            locationId);
            logger.debug("result = " + result);
        } catch (NoResultException e) {
            logger.error(e);
        }

        return result;
    }

    @Override
    public Location create(Location entity) {
        logger.debug(OPERATION_NOT_SUPPORTED);
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Location entity) throws ConcurrentAccessException {
        logger.debug(OPERATION_NOT_SUPPORTED);
        throw new UnsupportedOperationException();
    }

    @Override
    public Location update(Location entity) throws ConcurrentAccessException {
        logger.debug(OPERATION_NOT_SUPPORTED);
        throw new UnsupportedOperationException();
    }

    @Override
    public void createList(List<Location> entities) {
        logger.debug(OPERATION_NOT_SUPPORTED);
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Location> queryAll(String namedQuery) {
        logger.debug(OPERATION_NOT_SUPPORTED);
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Location> queryByKey(String namedQuery, String keyName, Long key) {
        logger.debug(OPERATION_NOT_SUPPORTED);
        throw new UnsupportedOperationException();
    }

    @Override
    public Location queryByKey(String namedQuery, String keyName, String key) {
        logger.debug(OPERATION_NOT_SUPPORTED);
        throw new UnsupportedOperationException();
    }

}
