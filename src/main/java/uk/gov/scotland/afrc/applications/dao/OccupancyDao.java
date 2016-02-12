package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.OccupancyDBO;

public interface OccupancyDao extends EntityManagerBase<OccupancyDBO, Long> {

    List<OccupancyDBO> findByLocation(long locationId);
    
    OccupancyDBO findByBrnAndLocationId (long locationId, long brn );
    
    Long findAllocationQueueIdForBrnMainLocation(long brn);
    
}
