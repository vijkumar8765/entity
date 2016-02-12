/**
 * 
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.RelatedApplicationsDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationDBO;
import uk.gov.scotland.afrc.applications.model.domain.RelatedApplicationsDBO;

/**
 * @author d609094
 * 
 */
public class RelatedApplicationsDaoImpl extends
		EntityManagerBaseImpl<RelatedApplicationsDBO, Long> implements
		RelatedApplicationsDao {

	private static final String APP_VALIDATION_ERROR_ID = "appValidationErrorId";
	private static final String APPLICATION_ID = "applicationId";

    private final Logger logger = Logger.getLogger(RelatedApplicationsDaoImpl.class);


	public RelatedApplicationsDaoImpl(Class<RelatedApplicationsDBO> type) {
        super(type);
	}

	public RelatedApplicationsDaoImpl() {
		super(RelatedApplicationsDBO.class);
	}

	@Override
	public List<RelatedApplicationsDBO> getRelatedApplications(long appValidationErrorId) {
		TypedQuery<RelatedApplicationsDBO> query = getEntityManager()
				.createNamedQuery(
						"RelatedApplicationsDBO.findRelatedApplicationsByCrossErrorID",
						RelatedApplicationsDBO.class);

		List<RelatedApplicationsDBO> resultList = query.setParameter(
				APP_VALIDATION_ERROR_ID, Long.valueOf(appValidationErrorId))
				.getResultList();
        logger.debug("query = " + query.toString() + appValidationErrorId);
		return resultList;
	}
	
	@Override
	public long getBrnfromAssociatedApplication(long applicationId) {
		
		TypedQuery<ApplicationDBO> query = getEntityManager()
				.createNamedQuery("Application.findBrnByApplicationID", ApplicationDBO.class);
		
		ApplicationDBO appDbo = query.setParameter(APPLICATION_ID, Long.valueOf(applicationId)).getSingleResult();
        logger.debug("query = " + query.toString() + applicationId);
		return appDbo.getBrn();
	}
}
