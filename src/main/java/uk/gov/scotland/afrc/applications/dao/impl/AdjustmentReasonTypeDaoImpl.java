package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AdjustmentReasonTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AdjustmentReasonTypeDBO;


public class AdjustmentReasonTypeDaoImpl extends EntityManagerBaseImpl<AdjustmentReasonTypeDBO, Long> implements AdjustmentReasonTypeDao  {

    private static Logger logger = Logger.getLogger(AdjustmentReasonTypeDaoImpl.class);

	public AdjustmentReasonTypeDaoImpl() {
		super(AdjustmentReasonTypeDBO.class);
	}
	
	@Override
	public List<AdjustmentReasonTypeDBO> findByName(String name) {
		TypedQuery<AdjustmentReasonTypeDBO> query = getEntityManager().createNamedQuery("AdjustmentReasonTypeDBO.findByName", AdjustmentReasonTypeDBO.class);
		query.setParameter("name", name);
        logger.debug("query = " + query.toString());
		return query.getResultList();
	}

}
