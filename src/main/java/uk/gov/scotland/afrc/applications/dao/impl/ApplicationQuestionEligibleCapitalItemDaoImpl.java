package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationQuestionEligibleCapitalItemDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AppQuestEligibleCapitalItemDBO;

public class ApplicationQuestionEligibleCapitalItemDaoImpl extends EntityManagerBaseImpl<AppQuestEligibleCapitalItemDBO, Long> implements ApplicationQuestionEligibleCapitalItemDao{

    private static Logger logger = Logger.getLogger(ApplicationQuestionEligibleCapitalItemDaoImpl.class);

    public ApplicationQuestionEligibleCapitalItemDaoImpl() {
        super(AppQuestEligibleCapitalItemDBO.class);
    }

    @Override
    public List<AppQuestEligibleCapitalItemDBO> getAppQuestionEligibleCapitalItems(Long choiceTypeCode) {
        TypedQuery<AppQuestEligibleCapitalItemDBO> query =
                getEntityManager().createNamedQuery("AppQuestEligibleCapitalItemDBO.findByChoicType",
                        AppQuestEligibleCapitalItemDBO.class);
        query.setParameter("choiceTypeCode", choiceTypeCode);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }
    
}
