package uk.gov.scotland.afrc.applications.dao.impl;

import uk.gov.scotland.afrc.applications.dao.FCSOfficeParishDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.FCSOfficeParishDBO;

public class FCSOfficeParishDaoImpl extends EntityManagerBaseImpl<FCSOfficeParishDBO, Long> implements FCSOfficeParishDao {

	public FCSOfficeParishDaoImpl() {
		super(FCSOfficeParishDBO.class);
	}
}
