/**
 * 
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import uk.gov.scotland.afrc.applications.dao.CgShareReasonTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CgShareReasonTypeDBO;

public class CgShareReasonTypeDaoImpl extends
		EntityManagerBaseImpl<CgShareReasonTypeDBO, Long> implements
		CgShareReasonTypeDao {

	public CgShareReasonTypeDaoImpl() {
		super(CgShareReasonTypeDBO.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CgShareReasonTypeDBO> getShareReasonByLocationId() {

		TypedQuery<CgShareReasonTypeDBO> query = getEntityManager()
				.createNamedQuery("CgShareReasonType.getAllCgShareReason",
						CgShareReasonTypeDBO.class);
		return query.getResultList();
	}

}
