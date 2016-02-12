/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao;

import java.util.List;
import java.util.Set;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationDBO;
import uk.gov.scotland.afrc.applications.model.domain.LandUseTypeDBO;
import uk.gov.scotland.afrc.applications.model.dto.CodeNameDTO;

/**
 * ApplicationDao provides finder queries on the {@link ApplicationDBO} entity.
 * 
 * <pre>
 * <p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities </th></tr>
 * <tr><td> Query draft applications by customer, year and status. </td></tr>
 * <tr><td> Query draft applications by customer, type and status. </td></tr>
 * </table>
 * </pre>
 */
public interface ApplicationDao extends EntityManagerBase<ApplicationDBO, Long> {
	/**
	 * Lists any draft applications by customer and status.
	 * 
	 * @param brn
	 *            The BRN of the customer.
	 * @param status
	 *            The application status.
	 * 
	 * @return A list of any matching draft applications, or empty list if none.
	 */
	List<ApplicationDBO> getApplicationsByCustomerAndStatus(long brn,
			long status);

	/**
	 * Lists any draft applications by customer and application type.
	 * 
	 * @param brn
	 *            The BRN of the customer.
	 * @param type
	 *            The application type to get drafts for.
	 * @param status
	 *            The application status.
	 * 
	 * @return A list of any matching draft applications, or empty list if none.
	 */
	List<ApplicationDBO> getApplicationsByCustomerTypeAndStatus(long brn,
			long type, long status);

	/**
	 * Lists any draft applications by customer and type.
	 * 
	 * @param brn
	 *            The BRN of the customer.
	 * @param type
	 *            The application type code.
	 * 
	 * @return A list of any matching applications, or empty list if none.
	 */
	List<ApplicationDBO> getApplicationsByCustomerAndType(long brn, long type);
	
	 /**
     * Lists any draft applications by customer and type.
     * 
     * @param brn
     *            The BRN of the customer.
     * @param type
     *            The application type code.
     * 
     * @return A list of any matching applications, or empty list if none.
     */
    List<ApplicationDBO> findByBrnAndTypeAndRef(long brn, long type);

	/**
	 * To find the currency type code for requested brn
	 * 
	 * @param brn
	 * @return Application
	 */
	ApplicationDBO getApplicationByBRNAndAppId(long appId, long brn);

	/**
	 * Distinct Land Use Type codes associated with application by dint of
	 * applied for schemes
	 * 
	 * @param appId
	 *            application PK
	 * @return List of land use type codes
	 */
	Set<LandUseTypeDBO> eligibleLandUseTypes(long appId);

	/**
	 * Gets the application reference number.
	 * 
	 * @param applicationTypeName
	 *            the application type name
	 * @return the application reference number
	 */
	long getApplicationReferenceNumber(String applicationTypeName);

	/**
	 * To find the applications for requested brn and year
	 * 
	 * @param brn
	 * @param year
	 * @return Application
	 */
	List<ApplicationDBO> getApplicationByBRNAndYear(long brn, String year);

	/**
	 * To find the applications for requested brn
	 * 
	 * @param brn
	 * @return Application
	 */
	List<ApplicationDBO> getApplicationByBRN(long brn);

	ApplicationDBO getApplicationByID(long applicationID);

	/**
	 * 
	 * @param applicationId
	 * @return
	 */
	CodeNameDTO getApplicationTypeByApplicationId(long applicationId);

	/**
	 * This finds the Application based on the UniqueReference Number which will
	 * be generated during submission
	 * 
	 * @param appSubmissionReferenceNumber
	 * @return
	 */

	ApplicationDBO getApplicationBySubmissionReferenceNumber(
			String appSubmissionReferenceNumber);

	/**
	 * To find the Recent applications for requested brn based on lastupdated
	 * date
	 * 
	 * @param brn
	 * @return Application
	 */
	List<ApplicationDBO> getLastUpdatedApplicationsByBRN(long brn);
	
	/** Get SubmittedRef value by passing ApplicationId.
	 * @param applicationId
	 * @return SubmittedRef
	 */
	String getSubmittedRefByApplicationId(long applicationId);
	
	List<ApplicationDBO> getApplicationsByTypeAndStatus(Long type, Long status);

}
