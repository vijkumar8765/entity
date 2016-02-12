package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ValidationErrorTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ValidationErrorTypeDBO;

public class ValidationErrorTypeDaoImpl extends EntityManagerBaseImpl<ValidationErrorTypeDBO, Long> implements ValidationErrorTypeDao {
    private final Logger logger = Logger.getLogger(ValidationErrorTypeDaoImpl.class);

    private static final String QUERY = "query = ";

    public ValidationErrorTypeDaoImpl() {
        super(ValidationErrorTypeDBO.class);
    }

    @Override
    public List<ValidationErrorTypeDBO> getAllErrorTypes() {
        TypedQuery<ValidationErrorTypeDBO> query =getEntityManager().createNamedQuery("ValidationErrorTypeDBO.getAll",ValidationErrorTypeDBO.class);
        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

}
