package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.LivestockCatgTypeDBO;

public interface LivestockCatgTypeDao extends EntityManagerBase<LivestockCatgTypeDBO, String> {

    List<LivestockCatgTypeDBO> findAll();

}
