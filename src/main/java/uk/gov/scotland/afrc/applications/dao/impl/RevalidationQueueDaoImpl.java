package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.RevalidationQueueDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.RevalidationQueueDBO;

public class RevalidationQueueDaoImpl extends EntityManagerBaseImpl<RevalidationQueueDBO, Long> implements
    RevalidationQueueDao {

    private final Logger logger = Logger.getLogger(RevalidationQueueDaoImpl.class);

    public RevalidationQueueDaoImpl() {
        super(RevalidationQueueDBO.class);
    }

    @Override
    public List<RevalidationQueueDBO> findPendingRevalidationEvents() {
        TypedQuery<RevalidationQueueDBO> query =
                getEntityManager().createNamedQuery("findPendingRevalidationEvents", RevalidationQueueDBO.class);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }

}
