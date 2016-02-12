package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.UnitOfMeasurementTypeDBO;

public interface UnitOfMeasurementTypeDao extends EntityManagerBase<UnitOfMeasurementTypeDBO, Long>{
    
    List<UnitOfMeasurementTypeDBO> getUnitOfMeasurementType();

}
