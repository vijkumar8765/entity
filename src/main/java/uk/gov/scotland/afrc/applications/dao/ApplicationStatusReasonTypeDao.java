package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationStatusReasonTypeDBO;

public interface ApplicationStatusReasonTypeDao extends EntityManagerBase<ApplicationStatusReasonTypeDBO, Long>{
    List<ApplicationStatusReasonTypeDBO> findAll();
}
