package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LivestockCatgTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LivestockCatgTypeDBO;

public class LivestockCatgTypeDaoImpl extends EntityManagerBaseImpl<LivestockCatgTypeDBO, String> implements
    LivestockCatgTypeDao {

    private final Logger logger = Logger.getLogger(LivestockCatgTypeDaoImpl.class);

    public LivestockCatgTypeDaoImpl() {
        super(LivestockCatgTypeDBO.class);
    }

    @Override
    public List<LivestockCatgTypeDBO> findAll() {
        logger.debug("findAll called!");
        return getEntityManager().createNamedQuery("LivestockCatgType.findAll", LivestockCatgTypeDBO.class)
                .getResultList();
    }

}
