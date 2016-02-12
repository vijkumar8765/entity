package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LMOContractDetailsDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LMOContractDetailsDBO;

public class LMOContractDetailsDaoImpl extends EntityManagerBaseImpl<LMOContractDetailsDBO, Long> implements
    LMOContractDetailsDao {

    private final Logger logger = Logger.getLogger(LMOContractDetailsDaoImpl.class);

    public LMOContractDetailsDaoImpl() {
        super(LMOContractDetailsDBO.class);
    }

    @Override
    public List<LMOContractDetailsDBO> getByBrn(long brn) {
        TypedQuery<LMOContractDetailsDBO> query =
                getEntityManager().createNamedQuery("LMOContractDetailsDBO.findByBrn", LMOContractDetailsDBO.class);
        query.setParameter("brn", brn);
        logger.debug("query  = " + query + " " + brn);
        return query.getResultList();
    }

}
