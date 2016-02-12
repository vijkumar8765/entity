package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ValidationTypeCatgDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeCatgDBO;

public class ValidationTypeCatgDaoImpl extends EntityManagerBaseImpl<ValidationTypeCatgDBO, Long> implements ValidationTypeCatgDao {

	private static final String COLUMN_APPLICATION_ID = "applicationId";
	
    private final Logger logger = Logger.getLogger(ValidationTypeCatgDaoImpl.class);

	public ValidationTypeCatgDaoImpl() {
		super(ValidationTypeCatgDBO.class);
	}

	 @Override
		public List<Object> errorTypes(long applicationId) {
	        TypedQuery<Object> query = getEntityManager().createNamedQuery(
	        		"ValidationTypeCatgDBO.findByApplicationId", Object.class);
	        query.setParameter(COLUMN_APPLICATION_ID, applicationId);

        logger.debug("query = " + query.toString());

	        return query.getResultList();
		}
}
