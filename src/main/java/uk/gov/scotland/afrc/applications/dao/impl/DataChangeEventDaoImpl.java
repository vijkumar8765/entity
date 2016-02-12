package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.DataChangeEventDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.DataChangeEventDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class DataChangeEventDaoImpl extends EntityManagerBaseImpl<DataChangeEventDBO, Long> implements
    DataChangeEventDao {

    public DataChangeEventDaoImpl() {
        super(DataChangeEventDBO.class);
    }

    @Override
    public List<DataChangeEventDBO> findPendingDataChangeEvents() {
        TypedQuery<DataChangeEventDBO> query =
                getEntityManager().createNamedQuery("DataChangeEventDBO.findPendingDataChangeEvents",
                        DataChangeEventDBO.class);
        return query.getResultList();
    }

    @Override
    public void removeAllDataChangeEvent(DataChangeEventDBO dataChangeEvent) throws ConcurrentAccessException {
        delete(dataChangeEvent);
    }

    @Override
    public int updatelpisLndPrclId(String tableName, long oldLpisLndPrclId, long newLpisLndPrclId)
        throws ConcurrentAccessException {
        try {
            return updatelpisLndPrclId(tableName, "lpis_lnd_prcl_id", oldLpisLndPrclId, newLpisLndPrclId);
        } catch (Exception ex) {
            throw new ConcurrentAccessException(ex);
        }

    }

    @Override
    public int updatelpisLndPrclId(String tableName, String columnName, long oldLpisLndPrclId, long newLpisLndPrclId)
        throws ConcurrentAccessException {
        try {
            int count = 0;
            String nsql = "update " + tableName + " set " + columnName + "=?1 where " + columnName + "=?2";
            Query query = getEntityManager().createNativeQuery(nsql);
            query.setParameter(1, newLpisLndPrclId);
            query.setParameter(2, oldLpisLndPrclId);
            count = query.executeUpdate();
            getEntityManager().flush();
            return count;
        } catch (Exception ex) {
            throw new ConcurrentAccessException(ex);
        }

    }

}
