package uk.gov.scotland.afrc.applications.dao;


import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.DataChangeEventArchiveDBO;

public interface DataChangeEventArchiveDao extends EntityManagerBase<DataChangeEventArchiveDBO, Long> {

    @Override
    DataChangeEventArchiveDBO create(DataChangeEventArchiveDBO dataChangeEventArchiveDBO);
}