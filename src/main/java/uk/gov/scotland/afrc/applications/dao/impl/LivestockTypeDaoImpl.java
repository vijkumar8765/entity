package uk.gov.scotland.afrc.applications.dao.impl;

import uk.gov.scotland.afrc.applications.dao.LivestockTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LivestockTypeDBO;

public class LivestockTypeDaoImpl extends EntityManagerBaseImpl<LivestockTypeDBO, Long> implements LivestockTypeDao {

    public LivestockTypeDaoImpl() {
        super(LivestockTypeDBO.class);
    }

}
