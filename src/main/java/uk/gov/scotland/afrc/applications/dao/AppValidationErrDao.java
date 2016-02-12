package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AppValidationErrDBO;
import uk.gov.scotland.afrc.applications.model.dto.ErrorContextDTO;
import uk.gov.scotland.afrc.applications.model.dto.ErrorCountDTO;
import uk.gov.scotland.afrc.applications.model.dto.FilterDTO;
import uk.gov.scotland.afrc.applications.model.dto.SIACSErrorsDTO;
import uk.gov.scotland.afrc.applications.model.dto.ValidationErrorAndSecDTO;
import uk.gov.scotland.afrc.applications.model.dto.ValidationErrorDTO;
import uk.gov.scotland.afrc.applications.model.dto.ValidationJustificationDTO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

/**
 * The Interface AppValidationErrDao.
 */
public interface AppValidationErrDao extends EntityManagerBase<AppValidationErrDBO, Long> {

    /**
     * Find app validation err by application id.
     *
     * @param applicationId the application id
     * @return the list
     */
    List<AppValidationErrDBO> findAppValidationErrByApplicationId(long applicationId);

    /**
     * Create new application validation error records
     * Update existing application validation
     * @param appValidationErrors
     * @return
     */
    boolean createAppValidationErrors(List<AppValidationErrDBO> appValidationErrors);

    /**
     * Find by application and section type code.
     *
     * @param applicationId the application id
     * @param sectionTypeCode the section type code
     * @return the list
     */
    List<AppValidationErrDBO> findByApplicationAndSectionTypeCode(long applicationId, long sectionTypeCode);

    /**
     * Find unresolved app validation err by application id.
     *
     * @param applicationId the application id
     * @return the list
     */
    List<AppValidationErrDBO> findUnresolvedErrorsByApplicationId(long applicationId);
    
    /**
     * This method returns number of Unresolved errors for the application
     * @param applicationId
     * @return number of unresolved errors
     */
    long findUnresolvedErrorsCountByApplicationId(long applicationId);

    /**
     * Find count by application and section type code.
     *
     * @param applicationId the application id
     * @param sectionTypeCode the section type code
     * @return the list
     */
     List<Object> findCountByApplicationAndSectionTypeCode(long applicationId, long sectionTypeCode);
    
    /**
     * Find by application id and validation type code.
     *
     * @param applicationId the application id
     * @param validationTypeCode the validation type code
     * @return the list
     */
    List<AppValidationErrDBO> findByApplicationIdAndValidationTypeCode(long applicationId, long validationTypeCode);

    /**
	 * get total number of open errors against a given application id 
	 * @param applicationId
	 * @return
	 */
	long getOpenErrorCounts(long applicationId);
	
	/**
	 * get total number of open errors against a given application id and class name
	 * @param applicationId
	 * @param className
	 * @return
	 */
	long getOpenErrorCountsClass(long applicationId, String className);
    
	
	void updateList(List<AppValidationErrDBO> appValidationErrDBOs) throws ConcurrentAccessException;
	
	
	 /**
     * Find app validation err by application id.
     *
     * @param applicationId the application id
     * @param offset
     * @param range
     * @return the list
     */
    List<AppValidationErrDBO> findAppValidationErrByApplicationIdAndByCriteria(long applicationId, Integer offset, Integer range);

	/** 
	 * @param appValidationErrorId
	 * @return hasChanged
	 */
	long checkJPAVersionNumber(long appValidationErrorId);
	
	/**
	 * 
	 * @param AppValidationErrDBO
	 * @return hasBeenSuppressed
	 */
	boolean suppressError(AppValidationErrDBO appValidationErrDBO);
	
	/**
	 * 
	 * @param applicationId
	 * @param offset
	 * @param range
	 * @param clazz
	 * @param landParclId
	 * @param schemeId
	 * @param errorType
	 * @param sectionTypeId
	 * @param fromDate
	 * @param toDate
	 * @param outstanding
	 * @param suppressionType
	 * @return List<ValidationErrorDTO>
	 */
	List<ValidationErrorDTO> getAppValidationErrByApplicationIdAndByCriteria(long applicationId, Integer offset, Integer range, Long clazz, FilterDTO filter, boolean isSuppressed, boolean passFlag, Long suppressiontype);
	
	/**
	 * @param applicationId
	 * @param clazz
	 * @param passFlag
	 * @param filter
	 * @param suppressionType
	 */
	ErrorCountDTO getAppValidationErrCount(long applicationId, Long clazz, Boolean passFlag, FilterDTO filter, Long suppressionType);
	
	List<AppValidationErrDBO> getAppValidationErrByApplicationIdAndSchemeOptionId(long applicationId, long schemeOptionId);
	
	/**
	 * This method returns AppValidationErrDBOs based on given Criteria
	 * @param applicationId
	 * @param errorContextDTO
	 * @return List of AppValidationErrDBOs
	 */
	List<AppValidationErrDBO> getAppValidationErrByApplicationIdAndCriteria(long applicationId, ErrorContextDTO errorContextDTO);
	
	
	
	/**
	 * This method returns list of ValidationJustificationDTOs
	 * @param applicationId
	 * @param classCode
	 * @return List of ValidationJustificationDTOs
	 */
	List<ValidationJustificationDTO> getJustificationReasons(long applicationId, long classCode);
	
    List<ValidationErrorAndSecDTO> findAppValidationErrAndSectionByApplicationId(long applicationId);
	
	List<AppValidationErrDBO> getByAppValidationErrIds(List<Long> errorIds);	
	/**
	 * This method returns list of SIACSErrorsDTO
	 * @param applicationId
	 * @param catgCode
	 * @return List of SIACSErrorsDTO
	 */
	
	
    List<SIACSErrorsDTO> findSIACSErrorsByApplicationId(long applicationId);
    
    /**
     * 
     * @param applicationId
     * @return
     */
    List<SIACSErrorsDTO> findNotClearedSIACSErrorsByApplicationId(long applicationId) ;
    
    /**
	 * This method returns list of SIACSErrorsDTO
	 * @param applicationId
	 * @return List of SIACSErrorsDTO for ContractId's
	 */
	
	
    List<SIACSErrorsDTO> findContractsByApplicationId(long applicationId);
	
	
	
	/**
     * Find by application and section type code.
     *
     * @param applicationId the application id
     * @param sectionTypeName the sectionTypeName
     * @param errorContextDTO
     * @param isAllApplicationSectionErrorsOnly
     * @return the list
     */
    List<AppValidationErrDBO> findUnsuppressedUnjustifiedErrorsByApplicationAndSectionTypeCode(long applicationId, long sectionTypeCode, ErrorContextDTO errorContextDTO, Boolean isAllApplicationSectionErrorsOnly);
    
    List<AppValidationErrDBO> getAppValidationErrsByApplicationCategoryAndContext(Long applicationId, String categoryName, ErrorContextDTO errorContextDTO, boolean addNullErrCtxPredicates);
    
    /**
     * Find app validation err by application id and val type cat name.
     *
     * @param applicationId the application id
     * @return the list
     */
    List<AppValidationErrDBO> findAppValidationErrByApplicationAndValidationTypeCatName(long applicationId, String valTypeCatName);
    

    /**
     * Get Application Type by Application Validation Error id
     * @param appValidationErrId the application validation error id
     * @return
     */
    long getApplicationType(long appValidationErrId);

    /**
     * Find app validation err by app land parcel id.
     *
     * @param appLandParcelId the appLandParcel id
     * @return the list
     */
    List<AppValidationErrDBO> findAppValidationErrByAppLandParcelId(long appLandParcelId);
    
   /**
    * This method returns latest suppressed error if available else returns null
    * @param applicationId
    * @return An instance of AppValidationErrDBO
    */
    AppValidationErrDBO  findLatestSuppressedErrorByApplicationId(long applicationId);
    
    /**
     * This method returns latest modified error if available else returns null
     * @param applicationId
     * @return An instance of AppValidationErrDBO
     */
    AppValidationErrDBO findLatestUpdatedErrorByApplicationId(long applicationId);

}

