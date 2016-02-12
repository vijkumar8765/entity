package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AdjustmentDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AdjustmentDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class AdjustmentDaoImpl extends EntityManagerBaseImpl<AdjustmentDBO, Long> implements AdjustmentDao {

    private static Logger logger = Logger.getLogger(AdjustmentDaoImpl.class);

    public AdjustmentDaoImpl() {
        super(AdjustmentDBO.class);
    }

    @Override
    public List<AdjustmentDBO> findByUserId(Long userId) {
        TypedQuery<AdjustmentDBO> query =
                getEntityManager().createNamedQuery("AdjustmentDBO.findByUserId", AdjustmentDBO.class);
        query.setParameter("userIdentifier", userId);
        logger.debug("query = " + query.toString());
        return query.getResultList();

    }

    @Override
    public List<AdjustmentDBO> findByReasonTypeCode(Long code) {
        TypedQuery<AdjustmentDBO> query =
                getEntityManager().createNamedQuery("AdjustmentDBO.findByReasonTypeCode", AdjustmentDBO.class);
        query.setParameter("adjustmentReasonTypeCode", code);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }

    @Override
    public AdjustmentDBO create(AdjustmentDBO adjustmentDBO) {
        super.create(adjustmentDBO);
        logger.debug("create adjustment");
        getEntityManager().refresh(adjustmentDBO);
        return adjustmentDBO;
    }

    @Override
    public void delete(AdjustmentDBO adjustmentDBO) throws ConcurrentAccessException {
        AdjustmentDBO removableAdjustmentDBO = findById(adjustmentDBO.getAdjustmentId());
        logger.debug("delete adjustment");
        super.delete(removableAdjustmentDBO);

    }

}
