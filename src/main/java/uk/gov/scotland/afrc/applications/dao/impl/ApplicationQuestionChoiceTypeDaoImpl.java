package uk.gov.scotland.afrc.applications.dao.impl;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationQuestionChoiceTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationQuestionChoiceTypeDBO;

public class ApplicationQuestionChoiceTypeDaoImpl extends EntityManagerBaseImpl<ApplicationQuestionChoiceTypeDBO, Long>
    implements ApplicationQuestionChoiceTypeDao {

    private static Logger logger = Logger.getLogger(ApplicationQuestionChoiceTypeDaoImpl.class);

    public ApplicationQuestionChoiceTypeDaoImpl() {
        super(ApplicationQuestionChoiceTypeDBO.class);
        logger.debug("ApplicationQuestionChoiceTypeDaoImpl");
    }

}
