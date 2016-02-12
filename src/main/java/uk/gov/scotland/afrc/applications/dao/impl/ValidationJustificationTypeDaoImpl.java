package uk.gov.scotland.afrc.applications.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ValidationJustificationTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ValidationJustificationTypeDBO;
import uk.gov.scotland.afrc.applications.model.dto.ValidationJustificationDTO;

public class ValidationJustificationTypeDaoImpl extends EntityManagerBaseImpl<ValidationJustificationTypeDBO, Long>
    implements ValidationJustificationTypeDao {

    private final Logger logger = Logger.getLogger(ValidationJustificationTypeDaoImpl.class);

    public ValidationJustificationTypeDaoImpl() {
        super(ValidationJustificationTypeDBO.class);
    }

	@Override
    @SuppressWarnings("unchecked")
    public List<ValidationJustificationDTO> getSupressJustificationReasons(long errorTypeCode) {
        String query = "SELECT DISTINCT v.code, v.description FROM validation_justification_type v WHERE v.suppression_Type_Flag = 1 " +
                		"and exists (select 1 from val_just_allowed_type vjat where vjat.val_just_type_code=v.code and vjat.validation_type_code= " + errorTypeCode + ")";
        List<ValidationJustificationDTO> supressJustificationReasons = new ArrayList<ValidationJustificationDTO>();
        try {
            logger.debug("getSupressJustificationReasons = " + query);
            List<Object[]> resultList = getEntityManager().createNativeQuery(query, Object[].class).getResultList();
            for (Object[] reason : resultList) {
	   			supressJustificationReasons.add(new ValidationJustificationDTO(((BigDecimal)reason[0]).longValue(), (String)reason[1])); // code, description
			}
        } catch (Exception e) {
            logger.error(e);
        }
        return supressJustificationReasons;
    }

    @Override
    public List<ValidationJustificationDTO> getAllJustificationReasons() {
        TypedQuery<ValidationJustificationDTO> query =
                getEntityManager().createNamedQuery("ValidationJustificationTypeDBO.getAllJustificationReasons",
                        ValidationJustificationDTO.class);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }

    @Override
    public ValidationJustificationTypeDBO findJustificationTypeWithName(String name) {
        TypedQuery<ValidationJustificationTypeDBO> query =
                getEntityManager().createNamedQuery("ValidationJustificationTypeDBO.findJustificationTypeWithName",
                        ValidationJustificationTypeDBO.class);
        query.setParameter("name", name);
        logger.debug("query = " + query.toString() + " " + name);
        return query.getSingleResult();
    }
}
