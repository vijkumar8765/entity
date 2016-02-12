package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.LMOContractDetailsDBO;

public interface LMOContractDetailsDao extends EntityManagerBase<LMOContractDetailsDBO, Long> {

    List<LMOContractDetailsDBO> getByBrn(long brn);
    
}
