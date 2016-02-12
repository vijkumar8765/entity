package uk.gov.scotland.afrc.applications.dao.impl;

import uk.gov.scotland.afrc.applications.dao.RevalidationQueueArchiveDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.RevalidationQueueArchiveDBO;

public class RevalidationQueueArchiveDaoImpl extends EntityManagerBaseImpl<RevalidationQueueArchiveDBO, Long> implements
    RevalidationQueueArchiveDao {

    public RevalidationQueueArchiveDaoImpl() {
        super(RevalidationQueueArchiveDBO.class);
    }

}
