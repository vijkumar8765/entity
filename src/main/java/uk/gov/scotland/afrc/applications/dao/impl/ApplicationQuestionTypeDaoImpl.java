package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.ApplicationQuestionTypeDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.ApplicationQuestionTypeDBO;

public class ApplicationQuestionTypeDaoImpl extends EntityManagerBaseImpl<ApplicationQuestionTypeDBO, Long> implements
    ApplicationQuestionTypeDao {

    private static Logger logger = Logger.getLogger(ApplicationQuestionTypeDaoImpl.class);
    private static final String QUERY = "query = ";

    public ApplicationQuestionTypeDaoImpl() {
        super(ApplicationQuestionTypeDBO.class);
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationQuestionTypeDBO> getApplicationQuestions(long applicationTypeCode, String questionSetName,
                                                                    Date applicableYear) {
        TypedQuery<ApplicationQuestionTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationQuestionType.findByApplicationCode",
                        ApplicationQuestionTypeDBO.class);

        query.setParameter("applicationTypeCode", applicationTypeCode);
        query.setParameter("questionSetName", questionSetName);
        query.setParameter("applicableYear", applicableYear);

        logger.debug(QUERY + query.toString());

        return query.getResultList();
    }

    @Override
    public List<ApplicationQuestionTypeDBO> getApplicationQuestions(long applicationTypeCode, Date applicableYear) {
        TypedQuery<ApplicationQuestionTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationQuestionType.findByApplicationCodeAndYear",
                        ApplicationQuestionTypeDBO.class);

        query.setParameter("applicationTypeCode", applicationTypeCode);
        query.setParameter("applicableYear", applicableYear);

        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationQuestionTypeDBO> getAllApplicationQuestions(long applicationTypeCode,
                                                                       String questionSetName, Date applicableYear) {
        TypedQuery<ApplicationQuestionTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationQuestionType.findAllByApplicationCode",
                        ApplicationQuestionTypeDBO.class);

        query.setParameter("applicationTypeCode", applicationTypeCode);
        query.setParameter("questionSetName", questionSetName);
        query.setParameter("applicableYear", applicableYear);

        logger.debug(QUERY + query.toString());
        return query.getResultList();
    }

    @Override
    public List<ApplicationQuestionTypeDBO> getApplicationQuestionsByGivenType(long typeId, String type,
                                                                               String questionSetName,
                                                                               Date applicableYear) {
        Date currentDate = new Date();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<ApplicationQuestionTypeDBO> cq = cb.createQuery(ApplicationQuestionTypeDBO.class);
        Root<ApplicationQuestionTypeDBO> root = cq.from(ApplicationQuestionTypeDBO.class);

        Predicate p = cb.equal(root.get("questionSetName"), questionSetName);
        if (null != applicableYear) {
            p = cb.and(p, cb.lessThanOrEqualTo(root.<Date> get("startDate"), applicableYear));
            p =
                    cb.and(p,
                            cb.and(cb.isNull(root.get("endDate")),
                                    cb.lessThanOrEqualTo(root.<Date> get("startDate"), currentDate)));

            p =
                    cb.or(p,
                            cb.and(cb.greaterThanOrEqualTo(root.<Date> get("endDate"), currentDate),
                                    cb.lessThanOrEqualTo(root.<Date> get("startDate"), currentDate),
                                    cb.greaterThanOrEqualTo(root.<Date> get("endDate"), applicableYear)));
        }
        p = cb.and(p, cb.isNull(root.get("questionSet")));

        logger.debug("Predicate = " + p);

        String context = null;
        logger.debug("type = " + type);
        if ("ENTITLEMENT".equals(type)) {
            context = "entitlementCatgId";
        } else if ("SCHEME".equals(type)) {
            context = "schemeId";
        } else if ("SCHEME_OPTION".equals(type)) {
            context = "schemeOptionId";
        } else if ("LIVESTOCK".equals(type)) {
            context = "livestockCatgTypeCode";
        }
        p = cb.and(p, cb.equal(root.get(context), typeId));

        cq.where(p);
        cq.orderBy(cb.asc(root.get("businessPriority")));

        TypedQuery<ApplicationQuestionTypeDBO> query = getEntityManager().createQuery(cq);

        logger.debug(QUERY + query.toString());

        return query.getResultList();
    }

    @Override
    public ApplicationQuestionTypeDBO findByQuestionCode(String qCpde) {
        TypedQuery<ApplicationQuestionTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationQuestionType.findByQuestionCode",
                        ApplicationQuestionTypeDBO.class);

        query.setParameter("qCode", Long.parseLong(qCpde));
        return query.getSingleResult();
    }

    /** {@inheritDoc} */
    @Override
    public List<ApplicationQuestionTypeDBO> getAssessmentQuestions(long assessmentTypeCode) {
        TypedQuery<ApplicationQuestionTypeDBO> query =
                getEntityManager().createNamedQuery("ApplicationQuestionType.findByAssessmentTypeCode",
                        ApplicationQuestionTypeDBO.class);

        query.setParameter("assessmentTypeCode", assessmentTypeCode);

        return query.getResultList();
    }
}
