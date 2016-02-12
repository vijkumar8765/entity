package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ValidationErrorTypeDBO;

public interface ValidationErrorTypeDao extends EntityManagerBase<ValidationErrorTypeDBO, Long> {
    /**
     * getAllErrorTypes.
     *
     * @return all types of errors
     */
	List<ValidationErrorTypeDBO> getAllErrorTypes();
}
