package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.DataChangeEventDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public interface DataChangeEventDao extends EntityManagerBase<DataChangeEventDBO, Long> {

    List<DataChangeEventDBO> findPendingDataChangeEvents();

    void removeAllDataChangeEvent(DataChangeEventDBO dataChangeEvent) throws ConcurrentAccessException;

    int updatelpisLndPrclId(String tableName, long oldLpisLndPrclId, long newLpisLndPrclId)
        throws ConcurrentAccessException;

    int updatelpisLndPrclId(String tableName, String columnName, long oldLpisLndPrclId, long newLpisLndPrclId)
        throws ConcurrentAccessException;
}
