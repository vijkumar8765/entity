package uk.gov.scotland.afrc.applications.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationSectionStatusTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationSectionStatusTypeDBO;

public class ApplicationSectionStatusTypeDaoImpl extends EntityManagerBaseImpl<ApplicationSectionStatusTypeDBO, Long>
    implements ApplicationSectionStatusTypeDao {

    private static Logger logger = Logger.getLogger(ApplicationSectionStatusTypeDaoImpl.class);

    public ApplicationSectionStatusTypeDaoImpl() {
        super(ApplicationSectionStatusTypeDBO.class);
    }

    @Override
    public ApplicationSectionStatusTypeDBO findByName(String name) {
        TypedQuery<ApplicationSectionStatusTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationSectionStatusTypeDBO.findByName",
                        ApplicationSectionStatusTypeDBO.class);

        ApplicationSectionStatusTypeDBO result;

        try {
            query.setParameter("name", name);
            logger.debug("query = " + query.toString());
            result = query.getSingleResult();
        } catch (NoResultException e) {
            logger.error(e);
            result = null;
        }

        return result;
    }
}
