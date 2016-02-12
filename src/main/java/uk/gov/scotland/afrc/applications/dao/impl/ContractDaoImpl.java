package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.ContractDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ContractDBO;

public class ContractDaoImpl extends EntityManagerBaseImpl<ContractDBO, Long> implements ContractDao {

	private static final String schemeParam = "scheme";
    private static final String brnParam = "brn";
    public ContractDaoImpl() {
        super(ContractDBO.class);
    }

    @Override
    public List<ContractDBO> getContracts(long brn, String scheme, Date startDate) {
        TypedQuery<ContractDBO> query =
                getEntityManager().createNamedQuery("ContractDBO.findContractsByBrn", ContractDBO.class);
        query.setParameter(brnParam, brn).setParameter(schemeParam, scheme);
        
        query.setParameter("startDate", startDate, TemporalType.DATE);
        query = getRequiredQuery(brn, scheme, startDate, null,query);
        return query.getResultList();
    }

    @Override
    public List<ContractDBO> getContractsWithBrnAndEndDateAfter(long brn, String scheme, Date endDate) {
        TypedQuery<ContractDBO> query =
                getEntityManager().createNamedQuery("ContractDBO.findContractsByBrnAndEndDateLaterThan",
                        ContractDBO.class);
        query.setParameter(brnParam, brn).setParameter(schemeParam, scheme);

        query.setParameter("endDate", endDate, TemporalType.DATE);
        return query.getResultList();
    }
    
    @Override
    public List<ContractDBO> getContracts(long brn, String scheme, Date startDate, Date endDate) {
        TypedQuery<ContractDBO> query =
                getEntityManager().createNamedQuery("ContractDBO.findContractsBetweenDates",
                        ContractDBO.class);
        query.setParameter(brnParam, brn).setParameter(schemeParam, scheme);
        query.setParameter("startDate", startDate, TemporalType.DATE);
        query.setParameter("endDate", endDate, TemporalType.DATE);
        return query.getResultList();
    }

    @Override
    public List<ContractDBO> getContractsByYear(long brn, String scheme, Date startDate, Date endDate) {
        TypedQuery<ContractDBO> query =
                getEntityManager().createNamedQuery("ContractDBO.findContractsByYear",
                        ContractDBO.class);
        query.setParameter(brnParam, brn).setParameter(schemeParam, scheme);
        query = getRequiredQuery(brn, scheme, startDate, endDate,query);
        return query.getResultList();
    }
    
      //this method has been refactored for avoiding duplicate code on DAO API's.
      //now sonar violations have been fixed.
    private TypedQuery<ContractDBO> getRequiredQuery(long brn, String scheme, Date startDate, Date endDate,TypedQuery<ContractDBO> query){
        query.setParameter("brn", brn).setParameter("scheme", scheme);
        if(startDate!=null){
            query.setParameter("startDate", startDate, TemporalType.DATE);
        }
        if(endDate!=null){
            query.setParameter("endDate", endDate, TemporalType.DATE);
        }
        return query;
    }
}
