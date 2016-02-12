package uk.gov.scotland.afrc.applications.dao.impl;

import uk.gov.scotland.afrc.applications.dao.CapitalItemTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CapitalItemTypeDBO;

public class CapitalItemTypeDaoImpl extends EntityManagerBaseImpl<CapitalItemTypeDBO, Long> implements CapitalItemTypeDao {

    public CapitalItemTypeDaoImpl() {
            super(CapitalItemTypeDBO.class);
     }
}
