package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LocationClassificationDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LocationClassificationDBO;

public class LocationClassificationDaoImpl extends
		EntityManagerBaseImpl<LocationClassificationDBO, Long> implements
		LocationClassificationDao {

    private final Logger logger = Logger.getLogger(LocationClassificationDaoImpl.class);

	public LocationClassificationDaoImpl() {
		super(LocationClassificationDBO.class);
	}

	@Override
	public LocationClassificationDBO getByLocationId(Long locationId) {
		List<LocationClassificationDBO> list = queryByKey(
				"LocationClassificationDBO.getByLocationId", "locationId",
				locationId);
        logger.debug("location id  = " + locationId);

		if (list == null || list.isEmpty()) {
            logger.debug("list == null || list.isEmpty()");
			return null;
		}	

		return list.iterator().next();
	}

	@Override
	public boolean isLFALocation(Long locationId) {
		List<String> lfaNames = Arrays.asList("LFA-D", "LFA-SD-A", "LFA-SD-B",
				"LFA-SD-C");

		TypedQuery<LocationClassificationDBO> query = getEntityManager()
				.createNamedQuery("LocationClassificationDBO.findLFAType",
						LocationClassificationDBO.class);
		List<LocationClassificationDBO> result = query
				.setParameter("locationId", locationId)
				.setParameter("typeList", lfaNames).getResultList();

        logger.debug("query = " + query + " " + locationId);

		return (!(null == result || result.isEmpty()));
	}

}
