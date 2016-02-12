package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import uk.gov.scotland.afrc.applications.dao.LandUseActivityTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LandUseActivityTypeDBO;

public class LandUseActivityTypeDaoImpl extends EntityManagerBaseImpl<LandUseActivityTypeDBO, Long> implements LandUseActivityTypeDao {

    public LandUseActivityTypeDaoImpl() {
        super(LandUseActivityTypeDBO.class);
    }
    
    @Override
    public List<LandUseActivityTypeDBO> getAllActivity() {
    	return super.queryAll("LandUseActivityTypeDBO.findAll");
    }
    
    @Override
    public List<LandUseActivityTypeDBO> getActivity(long code) {
    	return super.queryByKey("LandUseActivityTypeDBO.findByCode", "code", code);
     }
}
