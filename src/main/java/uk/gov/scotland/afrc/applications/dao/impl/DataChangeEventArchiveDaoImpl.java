package uk.gov.scotland.afrc.applications.dao.impl;

import uk.gov.scotland.afrc.applications.dao.DataChangeEventArchiveDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.DataChangeEventArchiveDBO;

public class DataChangeEventArchiveDaoImpl extends EntityManagerBaseImpl<DataChangeEventArchiveDBO, Long> implements
    DataChangeEventArchiveDao {

    public DataChangeEventArchiveDaoImpl() {
        super(DataChangeEventArchiveDBO.class);
    }
        
    @Override    
    public DataChangeEventArchiveDBO create(DataChangeEventArchiveDBO dataChangeEventArchiveDBO) {
        super.create(dataChangeEventArchiveDBO);

        getEntityManager().refresh(dataChangeEventArchiveDBO);
        return dataChangeEventArchiveDBO;
    }

}