package uk.gov.scotland.afrc.applications.dao;

import java.math.BigDecimal;
import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.SchemeOptionDBO;

public interface SchemeOptionDao extends EntityManagerBase<SchemeOptionDBO, Long> {

    /**
     * Returns the list of schemes for given application number.
     * 
     * @return  The list of all available scheme options.
     */
    List<SchemeOptionDBO> findAllSchemeOptions();

    /**
     * Returns the list of schemes for given application number.
     *  
     * @param   schemeId     The scheme Id.
     * 
     * @return  The list of all available scheme options.
     */
    List<SchemeOptionDBO> findAllSchemeOptionsBySchemeId(long schemeId);
    
    BigDecimal getConversionFactor(long schemeOptionId);

}
