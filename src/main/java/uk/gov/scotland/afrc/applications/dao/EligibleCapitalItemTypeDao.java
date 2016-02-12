package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.EligibleCapitalItemTypeDBO;

public interface EligibleCapitalItemTypeDao extends EntityManagerBase<EligibleCapitalItemTypeDBO, Long> {

	List<EligibleCapitalItemTypeDBO> findEligibleCapitalItemTypesBySchemeOptionId(long schemeOptionId);
}
