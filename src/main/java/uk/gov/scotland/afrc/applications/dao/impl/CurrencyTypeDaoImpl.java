package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import uk.gov.scotland.afrc.applications.dao.CurrencyTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.CurrencyTypeDBO;

public class CurrencyTypeDaoImpl extends EntityManagerBaseImpl<CurrencyTypeDBO, Long> implements CurrencyTypeDao {

	public CurrencyTypeDaoImpl() {
		super(CurrencyTypeDBO.class);
	}

	@Override
	public List<CurrencyTypeDBO> getAllCurrency() {
		return queryAll("CurrencyTypeDBO.findAll");
	}

	@Override
	public CurrencyTypeDBO getCurrency(Long code) {
		return findById(code);
	}

}
