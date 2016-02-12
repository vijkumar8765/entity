package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AppValidationErrDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.base.BaseTable;
import uk.gov.scotland.afrc.applications.model.domain.AppValidationErrDBO;
import uk.gov.scotland.afrc.applications.model.domain.RelatedApplicationsDBO;
import uk.gov.scotland.afrc.applications.model.domain.ValidationErrorTypeDBO;
import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeCatgDBO;
import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeClassDBO;
import uk.gov.scotland.afrc.applications.model.domain.ValidationTypeDBO;
import uk.gov.scotland.afrc.applications.model.dto.ErrorContextDTO;
import uk.gov.scotland.afrc.applications.model.dto.ErrorCountDTO;
import uk.gov.scotland.afrc.applications.model.dto.FilterDTO;
import uk.gov.scotland.afrc.applications.model.dto.SIACSErrorsDTO;
import uk.gov.scotland.afrc.applications.model.dto.ValidationErrorAndSecDTO;
import uk.gov.scotland.afrc.applications.model.dto.ValidationErrorDTO;
import uk.gov.scotland.afrc.applications.model.dto.ValidationJustificationDTO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class AppValidationErrDaoImpl extends EntityManagerBaseImpl<AppValidationErrDBO, Long> implements
    AppValidationErrDao {

	private static Logger log = Logger.getLogger(AppValidationErrDaoImpl.class);
	
    public AppValidationErrDaoImpl() {
        super(AppValidationErrDBO.class);
    }

    private static final String APPLICATION_ID = "applicationId";    
    private static final String CLASS_NAME = "className";
    private static final String CATEGORY_NAME = "categoryName";
    private static final String APP_VALIDATION_ERROR_ID = "appValidationErrorId";
    private static final String APP_VALIDATION_ERR_ID = "appValidationErrId";
    
    private static final String VALIDATION_TYPE = "validationType";
    private static final String VALIDATION_TYPE_CATG = "validationTypeCatg";
    private static final String VALIDATION_TYPE_CLASS = "validationTypeClass";  
    private static final String VALIDATION_ERROR_TYPE = "validationErrorType";
    private static final String PASS_FLAG = "passFlag";
    private static final String SCHEME_ID = "schemeId";
    private static final String APP_LND_PRCL_ID = "appLndPrclId";
    private static final String APP_LND_USE = "lndUseAreaId";
	private static final String LPIS_LND_PRCL_ID = "lpisLndPrclId";    
	private static final String LOCATION_CG_ID = "locationCgId";
    private static final String APP_SECT_TYPE_CODE = "appSectTypeCode";
    private static final String CODE = "code";
    private static final String VALIDATION_RUN_DATETIME = "validationRunDatetime";
    private static final String VALIDATION_JUSTIFICATION_TYPE = "valJustificationTypeCode";
    private static final String VALIDATION_SUPPRESSION_TYPE = "valSuppressionTypeCode";    
    private static final String DESCRIPTION = "description"; 
    private static final String NAME = "name";
    private static final String CAN_SUPPRESS_FLAG = "canSuppressFlag";
    private static final String JPA_VERSION_NUMBER = "jpaVersionNumber";
    private static final String LAST_UPDATED_BY = "lastUpdatedBy";
    private static final String LAST_UPDATED_DATE = "lastUpdatedDate";
    private static final String SCHEME_OPTION_ID = "schemeOptionId";
    private static final String CLAIM_ID = "claimId";
    private static final String BASE_TABLE = "baseTable";
    private static final String SUPPRESSION_CMT_TXT = "suppressionCmntText";
    private static final String JUSTIFICATION_TEXT = "justificationText";
    private static final String ANIMAL_CLAIM_ID = "animalClaimId";
    private static final String IS_CLEARED_FLAG ="clearedFlag";
    private static final boolean IS_CLEARED = false;
    private static final String APP_CG_SHARE_ID = "appCgShareId";
    private static final String APP_QUESTION_TYPE_CODE = "appQuestionTypeCode";
    private static final String ASSESSMENT_ID = "assessmentId";
	public static final String RELATED_APPLICATIONS = "relatedApplications";
	public static final String SUB_APPLICATION_ID = "subApplicationId";
	private static final int ONE = 1;
	
    @Override
    public List<AppValidationErrDBO> findAppValidationErrByApplicationId(long applicationId) {
        TypedQuery<AppValidationErrDBO> query =
                getEntityManager().createNamedQuery("AppValidationErr.findByApplicationId", AppValidationErrDBO.class);
        query.setParameter(APPLICATION_ID, applicationId);

        return query.getResultList();
    }

    @Override
    public List<AppValidationErrDBO> findAppValidationErrByApplicationAndValidationTypeCatName(long applicationId, String catName) {
        TypedQuery<AppValidationErrDBO> query =
                getEntityManager().createNamedQuery("AppValidationErrDBO.findByApplicationAndValTypeCatName", AppValidationErrDBO.class);
        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter(CATEGORY_NAME, catName);

        return query.getResultList();
    }
    
    @Override
    public boolean createAppValidationErrors(List<AppValidationErrDBO> appValidationErrors) {
        boolean result = false;
        for (AppValidationErrDBO appValidationErrDBO : appValidationErrors) {
            super.create(appValidationErrDBO);
        }
        result = true;
        return result;
    }

    @Override
    public List<AppValidationErrDBO> findByApplicationAndSectionTypeCode(long applicationId, long sectionTypeCode) {
        TypedQuery<AppValidationErrDBO> query =
                getEntityManager().createNamedQuery("AppValidationErr.findByApplicationAndSectionTypeCode",
                        AppValidationErrDBO.class);
        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter("appSectTypeCode", sectionTypeCode);

        return query.getResultList();
    }

    @Override
    public List<AppValidationErrDBO> findUnresolvedErrorsByApplicationId(long applicationId) {
        TypedQuery<AppValidationErrDBO> query =
                getEntityManager().createNamedQuery("AppValidationErr.findUnresolvedErrorsByApplicationId",
                        AppValidationErrDBO.class);
        query.setParameter(APPLICATION_ID, applicationId);

        return query.getResultList();
    }
    
    @Override
	public long findUnresolvedErrorsCountByApplicationId(long applicationId) {
    	TypedQuery<Long> query =
                getEntityManager().createNamedQuery("AppValidationErr.findUnresolvedErrorsCountByApplicationId", Long.class);
        query.setParameter(APPLICATION_ID, applicationId);
        return query.getSingleResult().longValue();
	}

	@Override
    public List<Object> findCountByApplicationAndSectionTypeCode(long applicationId, long sectionTypeCode) {
        TypedQuery<Object> query =
                getEntityManager().createNamedQuery("AppValidationErr.findCountByApplicationAndSectionTypeCode",
                        Object.class);
        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter("appSectTypeCode", sectionTypeCode);

        return query.getResultList();
    }

    @Override
    public List<AppValidationErrDBO> findByApplicationIdAndValidationTypeCode(long applicationId,
                                                                              long validationTypeCode) {
        TypedQuery<AppValidationErrDBO> query =
                getEntityManager().createNamedQuery("AppValidationErrDBO.findByApplicationIdAndValidationTypeCode",
                        AppValidationErrDBO.class);

        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter("validationTypeCode", validationTypeCode);

        return query.getResultList();
    }

    @Override
    public long getOpenErrorCounts(long applicationId) {
        TypedQuery<Long> query =
                getEntityManager().createNamedQuery("AppValidationErrDBO.getOpenErrorsCount", Long.class);
        query.setParameter(APPLICATION_ID, applicationId);
        return query.getSingleResult().longValue();
    }

    @Override
    public long getOpenErrorCountsClass(long applicationId, String className) {
        TypedQuery<Long> query =
                getEntityManager().createNamedQuery("AppValidationErrDBO.getOpenErrorsCountByClass", Long.class);
        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter(CLASS_NAME, className);
        return query.getSingleResult().longValue();
    }

	@Override
	public void updateList(List<AppValidationErrDBO> appValidationErrDBOs) throws ConcurrentAccessException {
		log.debug("Start updating list of application validation errors");
		for (AppValidationErrDBO appValidationErrDBO : appValidationErrDBOs){
			update( appValidationErrDBO );
		}
		log.debug("End updating list of application validation errors");
	}

	@Override
	public List<AppValidationErrDBO> findAppValidationErrByApplicationIdAndByCriteria(long applicationId, Integer offset, Integer range) {
		
				CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		
				CriteriaQuery<AppValidationErrDBO> appValdnErrorCriteriaQuery = criteriaBuilder.createQuery(AppValidationErrDBO.class);
				Root<AppValidationErrDBO> appValidationErrDBORoot = appValdnErrorCriteriaQuery.from(AppValidationErrDBO.class);
				Join<AppValidationErrDBO, RelatedApplicationsDBO> appAssocJoin = appValidationErrDBORoot.join(RELATED_APPLICATIONS, JoinType.LEFT);
				
				List<Predicate> predicateList = new ArrayList<Predicate>();
				addApplicationIdOrRelatedApplicationIdPredicate(applicationId, criteriaBuilder, predicateList, appValidationErrDBORoot, appAssocJoin);
				predicateList.add(criteriaBuilder.isNull(appValidationErrDBORoot.get("valJustificationTypeCode")));
				
				appValdnErrorCriteriaQuery.where(getPredicateArray(predicateList));
				CriteriaQuery<AppValidationErrDBO> select = appValdnErrorCriteriaQuery.select(appValidationErrDBORoot);
				
				TypedQuery<AppValidationErrDBO> typedQuery = getEntityManager().createQuery(select);
				if(null!=offset&&null!=range){
					typedQuery.setFirstResult(offset);
					typedQuery.setMaxResults(range);
				}
				List<AppValidationErrDBO> resultList = typedQuery.getResultList();
				log.info(resultList);
				
				return resultList;
    }

	/**
	 * @param appValidationErrorId
	 * @return long
	 */
	@Override
    public long checkJPAVersionNumber(long appValidationErrorId) {
	TypedQuery<Integer> query = getEntityManager().createNamedQuery("AppValidationErrDBO.checkJPAVersionNumber", Integer.class);
    query.setParameter(APP_VALIDATION_ERROR_ID, appValidationErrorId);
    return query.getSingleResult().longValue();
	}

	/**
	 * @param appValidationErrDBO
	 * @return long
	 */
	@Override
    public boolean suppressError(AppValidationErrDBO appValidationErrDBO) {
	getEntityManager().merge(appValidationErrDBO);		
	getEntityManager().flush();
	return true;
	}

	@Override
	public ErrorCountDTO getAppValidationErrCount(long applicationId, Long clazz, Boolean passFlag, FilterDTO filter, Long suppressionType){
		ErrorCountDTO errorCount = new ErrorCountDTO();
		errorCount.setTotalOutstanding(getUnResolvedErrorsCountByCriteria(applicationId,clazz,false, passFlag,null,null));
		errorCount.setTotalSuppressed(getUnResolvedErrorsCountByCriteria(applicationId,clazz,true, passFlag,null,null));
		errorCount.setFilteredOutstanding(getUnResolvedErrorsCountByCriteria(applicationId,clazz,false, passFlag,filter,null));
		errorCount.setFilteredSuppressed(getUnResolvedErrorsCountByCriteria(applicationId,clazz,true, passFlag,filter,null));
		errorCount.setFilteredSuppressedForSuppressionType(getUnResolvedErrorsCountByCriteria(applicationId,clazz,true, passFlag,filter, suppressionType));
		return errorCount;
	}	
	
	/**
	 * @param  applicationId
	 * @return count
	 */
	public long getUnResolvedErrorsCountByCriteria(long applicationId, Long clazz, Boolean isSuppressed, Boolean passFlag, FilterDTO filterDTO, Long suppressionType) {
		
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
		Root<AppValidationErrDBO> appValidationErrDBORoot = cq.from(AppValidationErrDBO.class);
		Join<AppValidationErrDBO, RelatedApplicationsDBO> appAssocJoin = appValidationErrDBORoot.join(RELATED_APPLICATIONS, JoinType.LEFT);
		cq.select(criteriaBuilder.countDistinct(appValidationErrDBORoot.<Long>get(APP_VALIDATION_ERR_ID)));
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		addApplicationIdOrRelatedApplicationIdPredicate(applicationId, criteriaBuilder, predicates, appValidationErrDBORoot, appAssocJoin);
		
		predicates.add( criteriaBuilder.equal(appValidationErrDBORoot.<Boolean>get(PASS_FLAG), passFlag));
		predicates.add(criteriaBuilder.equal(appValidationErrDBORoot.<Boolean>get(IS_CLEARED_FLAG), IS_CLEARED));
		
		if (clazz != null) {
			Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<ValidationTypeClassDBO>get(VALIDATION_TYPE_CLASS).<Long>get(CODE), clazz);
			predicates.add(criteriaBuilder.and(p));
		}
		
		if (suppressionType != null) {
			Predicate suppressionTypePredicate = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(VALIDATION_SUPPRESSION_TYPE), suppressionType);
			predicates.add(criteriaBuilder.and(suppressionTypePredicate));
		}
		
		if (isSuppressed) {
			predicates.add(criteriaBuilder.isNotNull(appValidationErrDBORoot.<Long>get(VALIDATION_SUPPRESSION_TYPE)));
		}else{
			predicates.add(criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(VALIDATION_SUPPRESSION_TYPE)));
		}
		
		if(filterDTO != null){
			setFilterPredicates(filterDTO, criteriaBuilder, appValidationErrDBORoot, predicates);
		}
		
		cq.where(getPredicateArray(predicates));
		
		return getEntityManager().createQuery(cq).getSingleResult();
	}
	
	@Override
	public List<ValidationErrorDTO> getAppValidationErrByApplicationIdAndByCriteria(
			long applicationId, Integer offset, Integer range,
			Long clazz, FilterDTO filterDTO, boolean isSuppressed, boolean passFlag, Long suppressionType) {
		
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ValidationErrorDTO> appValdnErrorCriteriaQuery = criteriaBuilder.createQuery(ValidationErrorDTO.class);
		Root<AppValidationErrDBO> appValidationErrDBORoot = appValdnErrorCriteriaQuery.from(AppValidationErrDBO.class);
		Join<AppValidationErrDBO, RelatedApplicationsDBO> appAssocJoin = appValidationErrDBORoot.join(RELATED_APPLICATIONS, JoinType.LEFT);
		
		appValdnErrorCriteriaQuery.multiselect(
				appValidationErrDBORoot.<Long>get(APP_VALIDATION_ERR_ID), 
				appValidationErrDBORoot.<Long>get(APPLICATION_ID), 
				appValidationErrDBORoot.<Date>get(VALIDATION_RUN_DATETIME),
				appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<ValidationTypeClassDBO>get(VALIDATION_TYPE_CLASS).<Long>get(CODE),
				appValidationErrDBORoot.<String>get(DESCRIPTION),
				appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<Long>get(CODE),
				appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<String>get(NAME),
				appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<Boolean>get(CAN_SUPPRESS_FLAG),
				appValidationErrDBORoot.<Long>get(JPA_VERSION_NUMBER),
				appValidationErrDBORoot.<Long>get(APP_LND_PRCL_ID),
				appValidationErrDBORoot.<Long>get(APP_LND_USE),
				appValidationErrDBORoot.<Long>get(APP_CG_SHARE_ID),
				appValidationErrDBORoot.<BaseTable>get(BASE_TABLE).<String>get(LAST_UPDATED_BY),
				appValidationErrDBORoot.<BaseTable>get(BASE_TABLE).<String>get(LAST_UPDATED_DATE),
				appValidationErrDBORoot.<Long>get(SCHEME_ID),
				appValidationErrDBORoot.<String>get(JUSTIFICATION_TEXT),
				appValidationErrDBORoot.<Long>get(SCHEME_OPTION_ID),
				appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<Long>get(APP_SECT_TYPE_CODE),
				appValidationErrDBORoot.<Long>get(VALIDATION_JUSTIFICATION_TYPE),
				appValidationErrDBORoot.<Long>get(VALIDATION_SUPPRESSION_TYPE),
				appValidationErrDBORoot.<Date>get(VALIDATION_RUN_DATETIME),
				appValidationErrDBORoot.<Long>get(APP_QUESTION_TYPE_CODE),
				appValidationErrDBORoot.<String>get(SUPPRESSION_CMT_TXT)
				).distinct(true);

		List<Predicate> predicates = new ArrayList<Predicate>();

		addApplicationIdOrRelatedApplicationIdPredicate(applicationId, criteriaBuilder, predicates, appValidationErrDBORoot, appAssocJoin);
		
		predicates.add( criteriaBuilder.equal(appValidationErrDBORoot.<Boolean>get(PASS_FLAG), passFlag) );
		predicates.add(criteriaBuilder.equal(appValidationErrDBORoot.<Boolean>get(IS_CLEARED_FLAG), IS_CLEARED));
		
		if (clazz != null) {
			Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<ValidationTypeClassDBO>get(VALIDATION_TYPE_CLASS).<Long>get(CODE), clazz);
			predicates.add(criteriaBuilder.and(p));
		}
		
		if (suppressionType != null) {
			Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(VALIDATION_SUPPRESSION_TYPE), suppressionType);
			predicates.add(criteriaBuilder.and(p));
		}
		
		if (isSuppressed) {
			predicates.add(criteriaBuilder.isNotNull(appValidationErrDBORoot.<Long>get(VALIDATION_SUPPRESSION_TYPE)));
		}else{
			predicates.add(criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(VALIDATION_SUPPRESSION_TYPE)));
		}
		
		if(filterDTO != null){
			setFilterPredicates(filterDTO, criteriaBuilder, appValidationErrDBORoot, predicates);
		}
		
		appValdnErrorCriteriaQuery.where(getPredicateArray(predicates));
		appValdnErrorCriteriaQuery.orderBy(criteriaBuilder.asc(appValidationErrDBORoot.<Long>get(APP_VALIDATION_ERR_ID)));
		TypedQuery<ValidationErrorDTO> tQuery = getEntityManager().createQuery(appValdnErrorCriteriaQuery);
		
		if (offset != null && range != null){
			tQuery.setFirstResult(offset);
			tQuery.setMaxResults(range);
		}
		return tQuery.getResultList();
	}

	private void setFilterPredicates(FilterDTO filterDTO, CriteriaBuilder criteriaBuilder,
			Root<AppValidationErrDBO> appValidationErrDBORoot,
			List<Predicate> predicates) {
		if (filterDTO.getErrorTypeId() != null){
			Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<ValidationErrorTypeDBO>get(VALIDATION_ERROR_TYPE).<Long>get(CODE), filterDTO.getErrorTypeId());
			predicates.add(criteriaBuilder.and(p));
		}
		
		if (filterDTO.getSchemeId() != null) {
			Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(SCHEME_ID), filterDTO.getSchemeId());
			predicates.add(criteriaBuilder.and(p));
		}
		
		if (filterDTO.getLandPrclId() != null) {
			Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(APP_LND_PRCL_ID), filterDTO.getLandPrclId());
			predicates.add(criteriaBuilder.and(p));
		}
		
		if (filterDTO.getSectionId() != null) {
			Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<Long>get(APP_SECT_TYPE_CODE), filterDTO.getSectionId());
			predicates.add(criteriaBuilder.and(p));
		}
		
		if (filterDTO.getFromDate() != null) {
			Predicate p = criteriaBuilder.greaterThanOrEqualTo( appValidationErrDBORoot.<Date>get(VALIDATION_RUN_DATETIME), filterDTO.getFromDate() ) ;
			predicates.add(criteriaBuilder.and(p));
		}
		
		if (filterDTO.getToDate() != null) {
			Predicate p = criteriaBuilder.lessThanOrEqualTo( appValidationErrDBORoot.<Date>get(VALIDATION_RUN_DATETIME), addDays(filterDTO.getToDate(), ONE)  ) ;
			predicates.add(criteriaBuilder.and(p));
		}
		
		if (filterDTO.getQuestionTypeCode()!= null) {
            Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(APP_QUESTION_TYPE_CODE), filterDTO.getQuestionTypeCode());
            predicates.add(criteriaBuilder.and(p));
        }
	}
	
	@Override
    public List<AppValidationErrDBO> getAppValidationErrByApplicationIdAndSchemeOptionId(long applicationId, long schemeOptionId){
		TypedQuery<AppValidationErrDBO> query =
                getEntityManager().createNamedQuery("AppValidationErr.findByApplicationIdAndSchemeOptionId", AppValidationErrDBO.class);
        query.setParameter(APPLICATION_ID, applicationId);
        query.setParameter(SCHEME_OPTION_ID, schemeOptionId);

        return query.getResultList();
	}
	

	@Override
	public List<AppValidationErrDBO> getAppValidationErrByApplicationIdAndCriteria(long applicationId, ErrorContextDTO errorContextDTO) {
		
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<AppValidationErrDBO> appValdnErrorQuery = criteriaBuilder.createQuery(AppValidationErrDBO.class);
		Root<AppValidationErrDBO> appValidationErrDBORoot = appValdnErrorQuery.from(AppValidationErrDBO.class);
		Join<AppValidationErrDBO, RelatedApplicationsDBO> appAssocJoin = appValidationErrDBORoot.join(RELATED_APPLICATIONS, JoinType.LEFT);
		List<Predicate> errorContextPredicates = new ArrayList<Predicate>();
		
		appValdnErrorQuery.select(appValidationErrDBORoot);
		addApplicationIdOrRelatedApplicationIdPredicate(applicationId, criteriaBuilder, errorContextPredicates, appValidationErrDBORoot, appAssocJoin);
		addErrorContextPredicatesToList(criteriaBuilder, appValidationErrDBORoot, errorContextDTO, errorContextPredicates, false);
		
		if(errorContextPredicates.size() > 0 ){
			Predicate[] ps = new Predicate[errorContextPredicates.size()];
			errorContextPredicates.toArray(ps);
			appValdnErrorQuery.where(criteriaBuilder.and(ps));
		}
	
		TypedQuery<AppValidationErrDBO> tQuery = getEntityManager().createQuery(appValdnErrorQuery);
		
		return tQuery.getResultList();
	}

	@Override
	public List<ValidationJustificationDTO> getJustificationReasons(
			long applicationId, long classCode) {
		   TypedQuery<ValidationJustificationDTO> query =
	                getEntityManager().createNamedQuery("AppValidationErrDBO.getJustificationReasons",
	                        ValidationJustificationDTO.class);
	        query.setParameter(APPLICATION_ID, applicationId);
	        query.setParameter(VALIDATION_TYPE_CLASS, classCode);

	        return query.getResultList();
	}
	@Override
	public List<ValidationErrorAndSecDTO> findAppValidationErrAndSectionByApplicationId(long applicationId) {
        TypedQuery<ValidationErrorAndSecDTO> query =
                getEntityManager().createNamedQuery("AppValidationErrDBO.findWithSecByApplicationId", ValidationErrorAndSecDTO.class);
        query.setParameter(APPLICATION_ID, applicationId);

        return query.getResultList();
    }
	@Override
	public List<AppValidationErrDBO> getByAppValidationErrIds(List<Long> errorIds){
		 TypedQuery<AppValidationErrDBO> query =
	                getEntityManager().createNamedQuery("AppValidationErrDBO.getAppValidationErrsByIds",
	                		AppValidationErrDBO.class);
	        query.setParameter("errorIds", errorIds);
	        return query.getResultList();	        
		
	}	
	
	
	
	/** {@inheritDoc} */
	@Override
    public List<AppValidationErrDBO> getAppValidationErrsByApplicationCategoryAndContext(
	        Long applicationId, String categoryName, ErrorContextDTO errorContextDTO, boolean addNullErrCtxPredicates){
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<AppValidationErrDBO> appValdnErrorQuery = criteriaBuilder.createQuery(AppValidationErrDBO.class);
		Root<AppValidationErrDBO> appValidationErrDBORoot = appValdnErrorQuery.from(AppValidationErrDBO.class);
		Join<AppValidationErrDBO, RelatedApplicationsDBO> appAssocJoin = appValidationErrDBORoot.join(RELATED_APPLICATIONS, JoinType.LEFT);
		List<Predicate> errorContextPredicates = new ArrayList<Predicate>();
		
		appValdnErrorQuery.select(appValidationErrDBORoot);

		addApplicationIdOrRelatedApplicationIdPredicate(applicationId, criteriaBuilder, errorContextPredicates, appValidationErrDBORoot, appAssocJoin);

        if (categoryName != null) {
			errorContextPredicates.add(
					criteriaBuilder.equal(
							appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<ValidationTypeCatgDBO>get(VALIDATION_TYPE_CATG).<Long>get(NAME), categoryName));
		}
		addErrorContextPredicatesToList(criteriaBuilder, appValidationErrDBORoot, errorContextDTO, errorContextPredicates, addNullErrCtxPredicates);
		errorContextPredicates.add(criteriaBuilder.equal(appValidationErrDBORoot.<Boolean>get(IS_CLEARED_FLAG), IS_CLEARED));
		if(errorContextPredicates.size() > 0 ){
			Predicate[] ps = new Predicate[errorContextPredicates.size()];
			errorContextPredicates.toArray(ps);
			appValdnErrorQuery.where(criteriaBuilder.and(ps));
		}
	
		TypedQuery<AppValidationErrDBO> tQuery = getEntityManager().createQuery(appValdnErrorQuery);
		
		return tQuery.getResultList();
	}
	
	private void addApplicationIdOrRelatedApplicationIdPredicate(Long applicationId, CriteriaBuilder criteriaBuilder,
			List<Predicate> errorContextPredicates,Root<AppValidationErrDBO> appValidationErrDBORoot, Join<AppValidationErrDBO, RelatedApplicationsDBO> appAssocJoin) {
		EntityType<RelatedApplicationsDBO> relatedApplicationsDBO = getEntityManager().getMetamodel().entity(RelatedApplicationsDBO.class);
        @SuppressWarnings("rawtypes")
        SingularAttribute attribute = relatedApplicationsDBO.getSingularAttribute(APPLICATION_ID);
        errorContextPredicates.add( criteriaBuilder.or(criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(APPLICATION_ID), applicationId),
        											   criteriaBuilder.equal(appAssocJoin.<Long>get(attribute), applicationId) ));
	}

	/**
	 * This method adds all provided ErrorContext values to Predicates list.
	 * @param criteriaBuilder
	 * @param appValidationErrDBORoot
	 * @param errorContextDTO
	 * @param errorContextPredicates
	 */
	private void addErrorContextPredicatesToList(CriteriaBuilder criteriaBuilder, Root<AppValidationErrDBO> appValidationErrDBORoot, 
			ErrorContextDTO errorContextDTO, List<Predicate> errorContextPredicates, boolean addNullPrediicate ){
		if( errorContextDTO != null ){
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, APP_LND_USE,  errorContextDTO.getLandUseAreaId(), addNullPrediicate);
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, ANIMAL_CLAIM_ID,  errorContextDTO.getAnimalClaimId(), addNullPrediicate);
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, SCHEME_ID,  errorContextDTO.getSchemeId(), addNullPrediicate);
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, SCHEME_OPTION_ID,  errorContextDTO.getSchemeOptionId(), addNullPrediicate);
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, APP_LND_PRCL_ID,  errorContextDTO.getAppLandParcelId(), addNullPrediicate);
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, APP_CG_SHARE_ID,  errorContextDTO.getAppCgShareId(), addNullPrediicate);
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, LPIS_LND_PRCL_ID,  errorContextDTO.getLpisLandParcelId(), addNullPrediicate);
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, LOCATION_CG_ID,  errorContextDTO.getLocationCgId(), addNullPrediicate);			
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, APP_QUESTION_TYPE_CODE,  errorContextDTO.getAppQuestionTypeCode(), addNullPrediicate);
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, CLAIM_ID,  errorContextDTO.getClaimId(), addNullPrediicate);
			addPredicate(criteriaBuilder, appValidationErrDBORoot, errorContextPredicates, ASSESSMENT_ID,  errorContextDTO.getAssessmentId(), addNullPrediicate);
		}
	}
	
	
	/**
	 * 
	 * @param predicatesArrayList
	 * @return Predicate[]
	 */
	private Predicate[] getPredicateArray(List<Predicate> predicatesArrayList){
    	Predicate[] predicateArray = new Predicate[predicatesArrayList.size()];
    	predicatesArrayList.toArray(predicateArray);
    	return predicateArray;
    }
	
	
	/**
	 * This method adds predictions to the Query
	 * @param criteriaBuilder
	 * @param appValidationErrDBORoot
	 * @param predicates
	 * @param columnName
	 * @param value
	 */
	private void addPredicate(CriteriaBuilder criteriaBuilder, Root<AppValidationErrDBO> appValidationErrDBORoot, 
			List<Predicate> predicates, String columnName, Long value, boolean addNullPrediicate){
		if( value != null ){
			predicates.add( criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(columnName), value) );
		}else if (addNullPrediicate){
			predicates.add( criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(columnName)) );
		}
	}
	
	/**
     * Find unsuppressed and unjustified errors by application and section type code.
     *
     * @param applicationId the application id
     * @param sectionTypeName the sectionTypeName
     * @param errorContextDTO errorContextDTO
     * @param isAllApplicationSectionErrorsOnly
     * @return the List<AppValidationErrDBO>
     */
	@Override
    public List<AppValidationErrDBO> findUnsuppressedUnjustifiedErrorsByApplicationAndSectionTypeCode(long applicationId, long sectionTypeCode, ErrorContextDTO errorContextDTO, Boolean isAllApplicationSectionErrorsOnly) {
	    	
	    	CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

			CriteriaQuery<AppValidationErrDBO> appValdnErrorCriteriaQuery = criteriaBuilder.createQuery(AppValidationErrDBO.class);
			Root<AppValidationErrDBO> appValidationErrDBORoot = appValdnErrorCriteriaQuery.from(AppValidationErrDBO.class);
		
			List<Predicate> predicates = applyBasicUnsuppressedUnjustifiedPredicates(applicationId,sectionTypeCode, criteriaBuilder, appValidationErrDBORoot);
			
			if(errorContextDTO!=null||(isAllApplicationSectionErrorsOnly!=null&&!isAllApplicationSectionErrorsOnly)){
				applyErrorContextPredicates(errorContextDTO, criteriaBuilder,appValidationErrDBORoot, predicates);
			}
			
			if (isAllApplicationSectionErrorsOnly != null && isAllApplicationSectionErrorsOnly && isErrorContextAndContextsNull(errorContextDTO) ){
				applyErrorContextIfPredicatesNull(criteriaBuilder,appValidationErrDBORoot, predicates);
			}
			
			Predicate[] ps = new Predicate[predicates.size()];
			predicates.toArray(ps);
			appValdnErrorCriteriaQuery.where(ps);
			
			
			CriteriaQuery<AppValidationErrDBO> select = appValdnErrorCriteriaQuery.select(appValidationErrDBORoot);
			List<Order> orderList = new ArrayList();
			//According to Use case document validation Error are displayed in asc order by ref1,ref2,description.
			orderList.add(criteriaBuilder.asc(appValidationErrDBORoot.get("ref1")));
			orderList.add(criteriaBuilder.asc(appValidationErrDBORoot.get("ref2")));
			orderList.add(criteriaBuilder.asc(appValidationErrDBORoot.get("description")));
			select.orderBy(orderList);
			TypedQuery<AppValidationErrDBO> typedQuery = getEntityManager().createQuery(select);
			List<AppValidationErrDBO> resultList = typedQuery.getResultList();
			log.info(resultList);
			
			return resultList;
	    }
	
	private boolean isErrorContextAndContextsNull(ErrorContextDTO errorContextDTO){
		return errorContextDTO != null&&areErrorContextsNull(errorContextDTO);
	}

	private boolean areErrorContextsNull(ErrorContextDTO errorContextDTO){
		return isAnimalClaimIdSchemIdAndSchemeOptionIdNull(errorContextDTO)&&isAppLandParcelIdAndLandUseAreaId(errorContextDTO);
	}

	private boolean isAnimalClaimIdSchemIdAndSchemeOptionIdNull(ErrorContextDTO errorContextDTO) {
		return errorContextDTO.getAnimalClaimId()==null&&errorContextDTO.getSchemeId()==null&&errorContextDTO.getSchemeOptionId()==null;
	}
	
	private boolean isAppLandParcelIdAndLandUseAreaId(ErrorContextDTO errorContextDTO){
		return errorContextDTO.getAppLandParcelId()==null&&errorContextDTO.getLandUseAreaId()==null;
	}
	 
	 private List<Predicate> applyBasicUnsuppressedUnjustifiedPredicates(long applicationId,long sectionTypeCode, CriteriaBuilder criteriaBuilder,Root<AppValidationErrDBO> appValidationErrDBORoot) {

			List<Predicate> predicates = new ArrayList<Predicate>();
			Predicate equalsApplicationIdPredicate = criteriaBuilder.equal(appValidationErrDBORoot.get(APPLICATION_ID), applicationId);
			
			Predicate equalsSectTypeCodePredicate = criteriaBuilder.equal(appValidationErrDBORoot.<ValidationTypeDBO>get(VALIDATION_TYPE).<Long>get(APP_SECT_TYPE_CODE), sectionTypeCode );
			
			Predicate valJustificationTypeCodeEqualsNullPredicate = criteriaBuilder.isNull(appValidationErrDBORoot.get(VALIDATION_JUSTIFICATION_TYPE));
			Predicate valSuppressionTypeCodeEqualsNullPredicate = criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(VALIDATION_SUPPRESSION_TYPE));
			
			Predicate isClearedFlagPredicate = criteriaBuilder.equal(appValidationErrDBORoot.<Boolean>get(IS_CLEARED_FLAG), IS_CLEARED);
			
			predicates.add(equalsApplicationIdPredicate);
			predicates.add(equalsSectTypeCodePredicate);
			predicates.add(valJustificationTypeCodeEqualsNullPredicate);
			predicates.add(valSuppressionTypeCodeEqualsNullPredicate);
			predicates.add(isClearedFlagPredicate);
			return predicates;
		}
	 
	 private void applyErrorContextPredicates(ErrorContextDTO errorContextDTO, CriteriaBuilder criteriaBuilder,Root<AppValidationErrDBO> appValidationErrDBORoot,List<Predicate> predicates) {
			
		 
		 if(errorContextDTO!=null){
			 if(errorContextDTO.getAppLandParcelId()!=null){
					Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(APP_LND_PRCL_ID), errorContextDTO.getAppLandParcelId());
					predicates.add(criteriaBuilder.and(p));
				}
				
				if(errorContextDTO.getLandUseAreaId()!=null){
					Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(APP_LND_USE), errorContextDTO.getLandUseAreaId());
					predicates.add(criteriaBuilder.and(p));
				}
				
				if(errorContextDTO.getAnimalClaimId()!=null){
					Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(ANIMAL_CLAIM_ID), errorContextDTO.getAnimalClaimId());
					predicates.add(criteriaBuilder.and(p));
				}
				
				if(errorContextDTO.getSchemeId()!=null){
					Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(SCHEME_ID), errorContextDTO.getSchemeId());
					predicates.add(criteriaBuilder.and(p));
				}
				
				if(errorContextDTO.getSchemeOptionId()!=null){
					Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(SCHEME_OPTION_ID), errorContextDTO.getSchemeOptionId());
					predicates.add(criteriaBuilder.and(p));
				}
				
				if(errorContextDTO.getAppCgShareId()!=null){
					Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(APP_CG_SHARE_ID), errorContextDTO.getAppCgShareId());
					predicates.add(criteriaBuilder.and(p));
				}
				
				if(errorContextDTO.getAppQuestionTypeCode()!=null){
                    Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(APP_QUESTION_TYPE_CODE), errorContextDTO.getAppQuestionTypeCode());
                    predicates.add(criteriaBuilder.and(p));
                }
				
				if(errorContextDTO.getAssessmentId()!=null){
                    Predicate p = criteriaBuilder.equal(appValidationErrDBORoot.<Long>get(ASSESSMENT_ID), errorContextDTO.getAssessmentId());
                    predicates.add(criteriaBuilder.and(p));
                }
		 }
			
		}
	 
	 private void applyErrorContextIfPredicatesNull(CriteriaBuilder criteriaBuilder,Root<AppValidationErrDBO> appValidationErrDBORoot,List<Predicate> predicates) {
			Predicate predicateAppLandParcelNull = criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(APP_LND_PRCL_ID));
			predicates.add(criteriaBuilder.and(predicateAppLandParcelNull));
			
			Predicate predicateAppLandUseIdNull = criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(APP_LND_USE));
			predicates.add(criteriaBuilder.and(predicateAppLandUseIdNull));
			
			Predicate predicateAnimalClaimIdNull = criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(ANIMAL_CLAIM_ID));
			predicates.add(criteriaBuilder.and(predicateAnimalClaimIdNull));
			
			Predicate predicateSchemeIdNull = criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(SCHEME_ID));
			predicates.add(criteriaBuilder.and(predicateSchemeIdNull));
			
			Predicate predicateSchemeOptionIdNull = criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(SCHEME_OPTION_ID));
			predicates.add(criteriaBuilder.and(predicateSchemeOptionIdNull));
			
			Predicate predicateAppCgShareIdNull = criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(APP_CG_SHARE_ID));
			predicates.add(criteriaBuilder.and(predicateAppCgShareIdNull));
			
			Predicate predicateAppQuestionTypeNull = criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(APP_QUESTION_TYPE_CODE));
            predicates.add(criteriaBuilder.and(predicateAppQuestionTypeNull));
            
            Predicate predicateAssessmentIdNull = criteriaBuilder.isNull(appValidationErrDBORoot.<Long>get(ASSESSMENT_ID));
            predicates.add(criteriaBuilder.and(predicateAssessmentIdNull));
		}
	 
	 @Override
		public List<SIACSErrorsDTO> findSIACSErrorsByApplicationId(
				long applicationId) {
			TypedQuery<SIACSErrorsDTO> query =
	                getEntityManager().createNamedQuery("AppValidationErrDBO.findSIACSErrorsByApplicationId", SIACSErrorsDTO.class);
	        query.setParameter(APPLICATION_ID, applicationId);
	        
	        
	        return query.getResultList();
	        
		
		}
	 
	 
	 @Override
		public List<SIACSErrorsDTO> findNotClearedSIACSErrorsByApplicationId(
				long applicationId) {
			TypedQuery<SIACSErrorsDTO> query =
	                getEntityManager().createNamedQuery("AppValidationErrDBO.findNotClearedSIACSErrorsByApplicationId", SIACSErrorsDTO.class);
	        query.setParameter(APPLICATION_ID, applicationId);
	        return query.getResultList();
		}
	 

		@Override
		public List<SIACSErrorsDTO> findContractsByApplicationId(long applicationId) {
			TypedQuery<SIACSErrorsDTO> query =
	                getEntityManager().createNamedQuery("AppValidationErrDBO.findContractssByApplicationId", SIACSErrorsDTO.class);
	        query.setParameter(APPLICATION_ID, applicationId);
	        
	        return query.getResultList();
		}
		
		@Override
		public long getApplicationType(long appValidationErrId) {
			return getEntityManager().createNamedQuery("AppValidationErrDBO.getApplicationType", Long.class).setParameter("appValidationErrId", appValidationErrId).getSingleResult();
		}

	    @Override
	    public List<AppValidationErrDBO> findAppValidationErrByAppLandParcelId(long appLandParcelId) {
	        TypedQuery<AppValidationErrDBO> query =
	                getEntityManager().createNamedQuery("AppValidationErrDBO.findByAppLandParcel",
	                        AppValidationErrDBO.class);
	        query.setParameter(APP_LND_PRCL_ID, appLandParcelId);

	        return query.getResultList();
	    }

		@Override
		public AppValidationErrDBO findLatestSuppressedErrorByApplicationId(long applicationId) {
			TypedQuery<AppValidationErrDBO> query =
	                getEntityManager().createNamedQuery("AppValidationErrDBO.findLatestSuppressedErrorByApplicationId",
	                		AppValidationErrDBO.class);
	        query.setParameter(APPLICATION_ID, applicationId);
	        query.setParameter(SUB_APPLICATION_ID, applicationId);
	        List<AppValidationErrDBO> errors =  query.getResultList();
	        
			return (errors.size() > 0 )? errors.get(0) : null;
		}
		
		
		@Override
		public AppValidationErrDBO findLatestUpdatedErrorByApplicationId(long applicationId) {
			TypedQuery<AppValidationErrDBO> query =
	                getEntityManager().createNamedQuery("AppValidationErrDBO.findLatestUpdatedErrorByApplicationId",
	                		AppValidationErrDBO.class);
	        query.setParameter(APPLICATION_ID, applicationId);
	        query.setParameter(SUB_APPLICATION_ID, applicationId);
	        List<AppValidationErrDBO> errors =  query.getResultList();
	        
			return (errors.size() > 0 )? errors.get(0) : null;
		}
		
		
		public Date addDays(Date date, int days)
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, days);
	        return cal.getTime();
	    }
		
		
}
