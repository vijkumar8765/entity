package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.CollaboratingDirectCustomerDBO;

public interface CollaboratingDirectCustomerDao extends EntityManagerBase<CollaboratingDirectCustomerDBO, Long> {

    /**
     * Deletes all by application id.
     *
     * @param   applicationId     The application Id.
     *
     * @return  The list of collaborating businesses.
     */
    void deleteAll(long applicationId);

    /**
     * Finds all by application id.
     *
     * @param   applicationId     The application Id.
     *
     * @return  The list of collaborating businesses.
     */
    List<CollaboratingDirectCustomerDBO> findAllByApplicationId(long applicationId);

}
