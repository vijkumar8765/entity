package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.GreeningStatusDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.GreeningStatusDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class GreeningStatusDaoImpl extends EntityManagerBaseImpl<GreeningStatusDBO, Long> implements GreeningStatusDao {

    public GreeningStatusDaoImpl() {
        super(GreeningStatusDBO.class);
    }

    @Override
    public GreeningStatusDBO getByApplicationId(Long applicationId) {
        TypedQuery<GreeningStatusDBO> query =
                getEntityManager().createNamedQuery("GreeningStatusDBO.findByApplicationId", GreeningStatusDBO.class);
        query.setParameter("appId", applicationId);
        return query.getSingleResult();
    }
    
    @Override
    public void deleteByApplicationId(Long applicationId) {
        TypedQuery<GreeningStatusDBO> query =
                getEntityManager().createNamedQuery("GreeningStatusDBO.deleteByApplicationId", GreeningStatusDBO.class);
        query.setParameter("appId", applicationId);
        query.executeUpdate();
    }

    @Override
    public GreeningStatusDBO createOrUpdateGreeningStatus(GreeningStatusDBO greeningStatusDBO)
        throws ConcurrentAccessException {
        return update(greeningStatusDBO);
    }

//    private LandUseTypeDBO getLandUseType(String name) {
//        try {
//            TypedQuery<LandUseTypeDBO> query =
//                    getEntityManager().createNamedQuery("LandUseTypeDBO.findByName", LandUseTypeDBO.class);
//            return query.setParameter("name", name).getSingleResult();
//        } catch (Exception e) {
//
//        }
//        return null;
//    }

        }
