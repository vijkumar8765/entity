package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationStatusReasonTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationStatusReasonTypeDBO;

public class ApplicationStatusReasonTypeDaoImpl extends EntityManagerBaseImpl<ApplicationStatusReasonTypeDBO, Long> implements ApplicationStatusReasonTypeDao {

    private static Logger logger = Logger.getLogger(ApplicationStatusReasonTypeDaoImpl.class);

    public ApplicationStatusReasonTypeDaoImpl() {
        super(ApplicationStatusReasonTypeDBO.class);
    }

    @Override
    public List<ApplicationStatusReasonTypeDBO> findAll() {
        logger.debug("findAll");
        return getEntityManager().createNamedQuery("ApplicationStatusReasonType.findAll", ApplicationStatusReasonTypeDBO.class).getResultList();
    }

}
