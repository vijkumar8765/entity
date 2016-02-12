package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.CollaboratingDirectCustomerDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CollaboratingDirectCustomerDBO;

public class CollaboratingDirectCustomerDaoImpl extends EntityManagerBaseImpl<CollaboratingDirectCustomerDBO, Long>
    implements CollaboratingDirectCustomerDao {

    public CollaboratingDirectCustomerDaoImpl() {
        super(CollaboratingDirectCustomerDBO.class);
    }

    public void deleteAll(long applicationId) {
        TypedQuery<CollaboratingDirectCustomerDBO> query =
                getEntityManager().createNamedQuery("CollaboratingDirectCustomer.deleteAllByApplicationId",
                        CollaboratingDirectCustomerDBO.class);

        query.setParameter("applicationId", Long.valueOf(applicationId)).executeUpdate();

    }

    public List<CollaboratingDirectCustomerDBO> findAllByApplicationId(long applicationId) {
        TypedQuery<CollaboratingDirectCustomerDBO> query =
                getEntityManager().createNamedQuery("CollaboratingDirectCustomer.findAllByApplicationId",
                        CollaboratingDirectCustomerDBO.class);
        return query.setParameter("applicationId", Long.valueOf(applicationId)).getResultList();
    }

}
