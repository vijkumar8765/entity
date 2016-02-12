package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.AdjustmentValueDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.AdjustmentValueDBO;
import uk.gov.scotland.afrc.util.jpahandler.ConcurrentAccessException;

public class AdjustmentValueDaoImpl extends EntityManagerBaseImpl<AdjustmentValueDBO, Long> implements
    AdjustmentValueDao {

    private static Logger logger = Logger.getLogger(AdjustmentValueDaoImpl.class);

    private static final String EMPTY_STRING = "";
    private static final String SPACE_STRING = " ";
    private static final String SQL = "sql = ";

    public AdjustmentValueDaoImpl() {
        super(AdjustmentValueDBO.class);
    }

    @Override
    public List<AdjustmentValueDBO> findByAdjustmentId(Long adjustmentId) {
        TypedQuery<AdjustmentValueDBO> query =
                getEntityManager().createNamedQuery("AdjustmentValueDBO.findByAdjustmentId", AdjustmentValueDBO.class);
        query.setParameter("adjustmentId", adjustmentId);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }

    @Override
    public int saveAdjustmentValueDBO(AdjustmentValueDBO adjustmentValueDBO) {
        int rowsInserted = 0;
        try {
            logger.debug("saveAdjustmentValueDBO " + adjustmentValueDBO.getAdjustmentId());
            create(adjustmentValueDBO);
            rowsInserted = (getEntityManager().contains(adjustmentValueDBO)) ? 1 : 0;
        } catch (Exception e) {        
            logger.error("error = " + e.toString());
            logger.error(e);
            rowsInserted = 0;
        }
        return rowsInserted;
    }

    @Override
    public AdjustmentValueDBO create(AdjustmentValueDBO adjustmentValueDBO) {
        super.create(adjustmentValueDBO);
        logger.debug("create " + adjustmentValueDBO.getAdjustmentId());
        getEntityManager().refresh(adjustmentValueDBO);
        return adjustmentValueDBO;
    }

    @Override
    public List<AdjustmentValueDBO> loadAdjustedFields(Long applicationId, List<String> uiFieldLabel) {
        TypedQuery<AdjustmentValueDBO> query =
                getEntityManager().createNamedQuery("AdjustmentValueDBO.loadAdjustedFields", AdjustmentValueDBO.class);
        query.setParameter("applicationId", applicationId);
        query.setParameter("uiFieldLabel", uiFieldLabel);
        logger.debug("query = " + query.toString());
        return query.getResultList();
    }

    @Override
    public void delete(AdjustmentValueDBO adjustmentValueDBO) throws ConcurrentAccessException {
        AdjustmentValueDBO removableAdjustmentValueDBO = findById(adjustmentValueDBO.getAdjustmentValueID());
        logger.debug("delete " + adjustmentValueDBO.getAdjustmentId());
        super.delete(removableAdjustmentValueDBO);
    }

    @Override
    public List<AdjustmentValueDBO> findLoadAdjustmentValues(Long applicationId, String fieldId, Long claimId,
                                                             Long livestockId, Long lndUseAreaId) {
        StringBuffer sql = buildLoadAdjusmentQueryParmeters(applicationId, fieldId, claimId, livestockId, lndUseAreaId);
        logger.debug(SQL + sql);
        return executeLoadAdjustmentQuery(sql.toString());
    }

    @Override
    public List<AdjustmentValueDBO> findLoadAdjustmentValuesProvidedLandDetails(Long applicationId, String fieldId,
                                                                                 Long claimId, Long livestockId,
                                                                                 Long lndUseAreaId, Long appLndPrclId, Long cgAppId, Long cgLndUseAreaId) {
        StringBuffer sql = buildLoadAdjusmentQueryParmeters(applicationId, fieldId, claimId, livestockId, lndUseAreaId);
        logger.debug(SQL + sql);
        
        if (appLndPrclId != null && appLndPrclId > 0) {
            appendAndIfRequired(sql);
            sql.append("app_lnd_prcl_id=");
            sql.append(appLndPrclId);
        }        
       
        if (cgAppId != null && cgAppId > 0) {
            appendAndIfRequired(sql);
            sql.append("app_cg_id=");
            sql.append(cgAppId);
        }
        
        if (cgLndUseAreaId != null && cgLndUseAreaId > 0) {
            appendAndIfRequired(sql);            
            sql.append("cg_share_lnd_use_area_id=");
            sql.append(cgLndUseAreaId);
        }        
        
        logger.debug(SQL + sql);
        return executeLoadAdjustmentQuery(sql.toString());
    }

    @Override
    public List<Long> findLineFlagAdjustmentIdList(Long applicationId, String fieldId, Long claimId, Long livestockId,
                                                   Long lndUseAreaId) {
        StringBuffer sql =
                buildLineAdjustmentListQueryParameter(applicationId, fieldId, claimId, livestockId, lndUseAreaId);
        logger.debug(SQL + sql);
        return executeLoadLineAdjustmentIdsQuery(sql.toString());
    }

    @Override
    public List<Long> findLineFlagAdjustmentIdListProvidedLandDetails(Long applicationId, String fieldId,
                                                                       Long claimId, Long livestockId,
                                                                       Long lndUseAreaId, Long appLndPrclId, Long cgAppId, Long cgLndUseAreaId) {
        StringBuffer sql =
                buildLineAdjustmentListQueryParameter(applicationId, fieldId, claimId, livestockId, lndUseAreaId);
       
        if (appLndPrclId != null && appLndPrclId > 0) {
            appendAndIfRequired(sql);
            sql.append("adjustmentvalue.app_lnd_prcl_id=");
            sql.append(appLndPrclId);
        }
        
        if (cgAppId != null && cgAppId > 0) {
            appendAndIfRequired(sql);
            sql.append("adjustmentvalue.app_cg_id=");
            sql.append(cgAppId);
        }
        
        if (cgLndUseAreaId != null && cgLndUseAreaId > 0) {
            appendAndIfRequired(sql);            
            sql.append("adjustmentvalue.cg_share_lnd_use_area_id=");
            sql.append(cgLndUseAreaId);
        }        
        
        logger.debug(SQL + sql);
        return executeLoadLineAdjustmentIdsQuery(sql.toString());
    }

    @Override
    public List<List<AdjustmentValueDBO>> findLineAdjustmentAdjustmentValues(List<Long> adjustmentIdList) {

        List<List<AdjustmentValueDBO>> lineAdjustmentList = new ArrayList<List<AdjustmentValueDBO>>();

        for (Long adjustmentId : adjustmentIdList) {

            List<AdjustmentValueDBO> adjustmentValues = findByAdjustmentId(adjustmentId);

            if (adjustmentValues != null) {
                lineAdjustmentList.add(adjustmentValues);
            } else {
                lineAdjustmentList.add(new ArrayList<AdjustmentValueDBO>());
            }
        }
        logger.debug("findLineAdjustmentAdjustmentValues = " + lineAdjustmentList.size());
        return lineAdjustmentList;
    }

    private void appendAndIfRequired(StringBuffer sql) {
        if (!sql.toString().isEmpty()) {
            sql.append(SPACE_STRING + "and" + SPACE_STRING);
        }
        logger.debug(SQL + sql);
    }

    @SuppressWarnings("unchecked")
    private List<AdjustmentValueDBO> executeLoadAdjustmentQuery(String sql) {
        String query =
                "Select adjt_val_id, jpa_version_number , last_updated_date , last_updated_by, claim_id, livestock_id , lnd_use_area_id, adjt_id, data_element_code, old_val, new_val, cmt, adjt_reason_type_code, application_id , field_id , app_lnd_prcl_id, deleted_data_indicator, app_cg_id, cg_share_lnd_use_area_id  From adjt_val where "
                    + sql + " ORDER BY last_updated_date";
        List<AdjustmentValueDBO> adjustmentValueList = null;
        try {
            logger.debug("executeLoadAdjustmentQuery = " + sql);
            adjustmentValueList = getEntityManager().createNativeQuery(query, AdjustmentValueDBO.class).getResultList();
        } catch (Exception e) {
            logger.error(e);
            adjustmentValueList.clear();
            return adjustmentValueList;
        }
        return adjustmentValueList;
    }

    @SuppressWarnings("unchecked")
    private List<Long> executeLoadLineAdjustmentIdsQuery(String sql) {
        String query =
                "select adjt_id from (select distinct(adjustment.adjt_id), adjustment.last_updated_date from  adjt adjustment , adjt_val adjustmentvalue where adjustment.adjt_id = adjustmentvalue.adjt_id and adjustment.line_adjt_flag = 1 and "
                    + sql + " ORDER BY adjustment.last_updated_date)";
        List<Long> adjustmentIdList = null;
        try {
            logger.debug("executeLoadLineAdjustmentIdsQuery = " + sql);
            adjustmentIdList = getEntityManager().createNativeQuery(query, Long.class).getResultList();
        } catch (Exception e) {
            logger.error(e);
            adjustmentIdList.clear();
            return adjustmentIdList;
        }

        return adjustmentIdList;
    }

    private StringBuffer buildLoadAdjusmentQueryParmeters(Long applicationId, String fieldId, Long claimId,
                                                          Long livestockId, Long lndUseAreaId) {
        StringBuffer sql = new StringBuffer(EMPTY_STRING);

        if (applicationId != null && applicationId > 0) {
            appendAndIfRequired(sql);
            sql.append("application_id=");
            sql.append(applicationId);
        }

        if (fieldId != null && !fieldId.isEmpty()) {
            appendAndIfRequired(sql);
            sql.append("field_id=" + "\'" + fieldId + "\'");
        }

        if (claimId != null && claimId > 0) {
            appendAndIfRequired(sql);
            sql.append("claim_id=");
            sql.append(claimId);
        }

        if (livestockId != null && livestockId > 0) {
            appendAndIfRequired(sql);
            sql.append("livestock_id=");
            sql.append(livestockId);
        }

        if (lndUseAreaId != null && lndUseAreaId > 0) {
            appendAndIfRequired(sql);
            sql.append("lnd_use_area_id=");
            sql.append(lndUseAreaId);
        }
        logger.debug(SQL + sql);
        return sql;
    }

    private StringBuffer buildLineAdjustmentListQueryParameter(Long applicationId, String fieldId, Long claimId,
                                                               Long livestockId, Long lndUseAreaId) {
        StringBuffer sql = new StringBuffer(EMPTY_STRING);

        if (applicationId != null && applicationId > 0) {
            appendAndIfRequired(sql);
            sql.append("adjustmentvalue.application_id=");
            sql.append(applicationId);
        }

        if (fieldId != null && !fieldId.isEmpty()) {
            appendAndIfRequired(sql);
            sql.append("adjustmentvalue.field_id=" + "\'" + fieldId + "\'");
        }

        if (claimId != null && claimId > 0) {
            appendAndIfRequired(sql);
            sql.append("adjustmentvalue.claim_id=");
            sql.append(claimId);
        }

        if (livestockId != null && livestockId > 0) {
            appendAndIfRequired(sql);
            sql.append("adjustmentvalue.livestock_id=");
            sql.append(livestockId);
        }

        if (lndUseAreaId != null && lndUseAreaId > 0) {
            appendAndIfRequired(sql);
            sql.append("adjustmentvalue.lnd_use_area_id=");
            sql.append(lndUseAreaId);
        }
        logger.debug(SQL + sql);
        return sql;
    }

}