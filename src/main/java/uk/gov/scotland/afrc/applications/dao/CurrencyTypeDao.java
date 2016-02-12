package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.CurrencyTypeDBO;

public interface CurrencyTypeDao extends EntityManagerBase<CurrencyTypeDBO, Long>{
	
	List<CurrencyTypeDBO> getAllCurrency();
	
	CurrencyTypeDBO getCurrency(Long code);

}
