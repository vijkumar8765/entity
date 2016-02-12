package uk.gov.scotland.afrc.applications.dao;

import java.util.Date;
import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.ContractDBO;

public interface ContractDao extends EntityManagerBase<ContractDBO, Long> {

    List<ContractDBO> getContracts(long brn, String scheme, Date startDate);

    List<ContractDBO> getContractsWithBrnAndEndDateAfter(long brn, String scheme, Date endDate);
    
    List<ContractDBO> getContracts(long brn, String scheme,  Date startDate, Date endDate);
    
    List<ContractDBO> getContractsByYear(long brn, String scheme,  Date startDate, Date endDate);
}
