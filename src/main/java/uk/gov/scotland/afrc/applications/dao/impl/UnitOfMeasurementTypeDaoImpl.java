package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.UnitOfMeasurementTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.UnitOfMeasurementTypeDBO;

public class UnitOfMeasurementTypeDaoImpl extends EntityManagerBaseImpl<UnitOfMeasurementTypeDBO, Long> implements UnitOfMeasurementTypeDao {

    private final Logger logger = Logger.getLogger(ValidationJustificationTypeDaoImpl.class);
    
    public UnitOfMeasurementTypeDaoImpl() {
        super(UnitOfMeasurementTypeDBO.class);
    }

    @Override
    public List<UnitOfMeasurementTypeDBO> getUnitOfMeasurementType() {
        TypedQuery<UnitOfMeasurementTypeDBO> query = getEntityManager().createNamedQuery("UnitOfMeasurementTypeDBO.findAll", UnitOfMeasurementTypeDBO.class);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }

}
