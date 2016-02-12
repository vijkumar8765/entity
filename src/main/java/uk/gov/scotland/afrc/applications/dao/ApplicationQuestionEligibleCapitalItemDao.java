package uk.gov.scotland.afrc.applications.dao;

import java.util.List;

import uk.gov.scotland.afrc.applications.entity.base.EntityManagerBase;
import uk.gov.scotland.afrc.applications.model.domain.AppQuestEligibleCapitalItemDBO;

public interface ApplicationQuestionEligibleCapitalItemDao extends EntityManagerBase<AppQuestEligibleCapitalItemDBO, Long> {

    List<AppQuestEligibleCapitalItemDBO> getAppQuestionEligibleCapitalItems(Long choiceTypeCode);
}
