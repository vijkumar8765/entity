package uk.gov.scotland.afrc.applications.dao;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSectionStatusTypeDBO;

public interface ApplicationSectionStatusTypeDao extends EntityManagerBase<ApplicationSectionStatusTypeDBO, Long> {

	ApplicationSectionStatusTypeDBO findByName(String string);

}
