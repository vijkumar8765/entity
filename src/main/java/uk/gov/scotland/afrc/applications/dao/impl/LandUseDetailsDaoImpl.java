package uk.gov.scotland.afrc.applications.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LandUseDetailsDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.dto.LandUserDetailSummaryDTO;

public class LandUseDetailsDaoImpl extends EntityManagerBaseImpl<LandUserDetailSummaryDTO, Object> implements
    LandUseDetailsDao {

    private static final int INDEX0 = 0;
    private static final int INDEX1 = 1;
    private static final int INDEX2 = 2;
    private static final int INDEX3 = 3;
    private static final int INDEX4 = 4;
    private static final int INDEX5 = 5;
    private static final int INDEX6 = 6;
    private static final int INDEX7 = 7;
    private static final String BPS = "BPS";
    private static final String LFASS = "LFASS";
    private static final String QUERY = "query = ";

    private final Logger logger = Logger.getLogger(LandUseDetailsDaoImpl.class);

    public LandUseDetailsDaoImpl() {
        super(LandUserDetailSummaryDTO.class);
    }

    /**
     * This need to be rewritten to select based on land use/scheme eligibility
     * and to return a 1:n landUse to Scheme construct which includes any land use claim info
     *  
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LandUserDetailSummaryDTO> findLandUseDetails(long applicationId, long parcelId) {

        final String nsql =
                "select  lut.code, lut.description, lua.area_amt, s.scheme_name, luc.area_amt, lua.lnd_use_area_id, lua.is_mapped_flag, lut.name"
                    + " from app_lnd_prcl alp"
                    + " inner join land_use_area lua on alp.app_lnd_prcl_id = lua.app_lnd_prcl_id"
                    + " inner join land_use_type lut on lut.code = lua.lnd_use_type_code"
                    + " left outer join land_use_claim luc on lua.lnd_use_area_id = luc.lnd_use_area_id"
                    + " left outer join scheme s on luc.scheme_id=s.scheme_id "
                    + " where alp.application_id = ?1 and alp.app_lnd_prcl_id = ?2 order by lua.lnd_use_area_id";

        Query query = getEntityManager().createNativeQuery(nsql);
        query.setParameter(1, applicationId);
        query.setParameter(2, parcelId);
        List<Object[]> rowList = query.getResultList();
        List<LandUserDetailSummaryDTO> listDTO = mapResult(rowList);
        List<LandUserDetailSummaryDTO> landUseList = new ArrayList<LandUserDetailSummaryDTO>();
        for (LandUserDetailSummaryDTO dto : listDTO) {
            String name = dto.getName();
            BigDecimal area = dto.getLandUseArea();
            if(area != null){
                area = area.setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            boolean mappedFlag = dto.isFeature();
            if (!(name.equals("EXCL") && BigDecimal.ZERO.compareTo(area) == 0 && mappedFlag)) {
                landUseList.add(dto);
            }

        }
        return landUseList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LandUserDetailSummaryDTO> findCGLandUseDetails(long applicationId, long shareId) {
        final String nsql =
                "select  lut.code, lut.description, lua.area_amt, s.scheme_name, luc.claimed_area, lua.cg_share_lnd_use_area_id,0,lut.name"
                    + " from app_cg_share acs"
                    + " inner join cg_share_lnd_use_area lua on acs.app_cg_share_id = lua.app_cg_share_id"
                    + " inner join land_use_type lut on lut.code = lua.lnd_use_type_code"
                    + " left outer join cg_share_claim luc on lua.cg_share_lnd_use_area_id = luc.cg_share_lnd_use_area_id"
                    + " left outer join scheme s on luc.scheme_id=s.scheme_id "
                    + " where acs.app_cg_share_id = ?1"
                    + " order by lua.cg_share_lnd_use_area_id";

        Query query = getEntityManager().createNativeQuery(nsql);
        query.setParameter(1, shareId);
        logger.debug(QUERY + query + " " + shareId + " " + applicationId);
        List<Object[]> rowList = query.getResultList();
        if (null != rowList && !rowList.isEmpty()) {
            logger.debug(QUERY + query + " " + shareId + " " + applicationId);
        }
        return rowList != null ? mapResult(rowList) : mapResult(null);
    }

    @SuppressWarnings("serial")
    private List<LandUserDetailSummaryDTO> mapResult(List<Object[]> rowList) {
        final Map<Long, LandUserDetailSummaryDTO> map = new HashMap<Long, LandUserDetailSummaryDTO>();

        if (null != rowList && !rowList.isEmpty()) {
            logger.debug("rowList  = " + rowList.size());
        }

        // we always get at least one row even if no land_use_claims
        // but we might get up to two if LUA is associated with both schemes
        if (null != rowList) {
            for (Object[] row : rowList) {
                final Long luaPk = ((BigDecimal) row[INDEX5]).longValue();

                LandUserDetailSummaryDTO dto;

                // we're dealing with lots of LUAs!
                dto = map.get(luaPk);

                if (dto == null) {
                    logger.debug("dto==null ");
                    dto = new LandUserDetailSummaryDTO();
                    dto.setLandUseTypeCode(((BigDecimal) row[INDEX0]).longValue());
                    dto.setLandUseDescription((String) row[INDEX1]);
                    dto.setLandUseArea((BigDecimal) row[INDEX2]);
                    dto.setLandUseAreaId(luaPk);
                    dto.setFeature(!((BigDecimal) row[INDEX6]).equals(BigDecimal.ZERO));
                    dto.setName((String) row[INDEX7]);
                    // this is the trick. we set a place holder for every possible scheme
                    // with a flag to say whether or not the association actually exists
                    // this is strictly beyond what's required here but it makes the front end
                    // work so much simpler
                    dto.setSchemes(new ArrayList<LandUserDetailSummaryDTO.SchemeSummary>() {
                        {
                            // TODO enumerate schemes and add one entry per scheme
                            add(new LandUserDetailSummaryDTO.SchemeSummary(BPS, false, null));
                            add(new LandUserDetailSummaryDTO.SchemeSummary(LFASS, false, null));
                        }
                    });
                    logger.debug("luaPk = " + luaPk);
                    map.put(luaPk, dto);
                }

                // finally if we actually have a scheme/area we set the flag to true and maybe fill in an area amount

                final String scheme = (String) row[INDEX3];

                if (scheme != null) {
                    logger.debug("scheme != null ");
                    final LandUserDetailSummaryDTO.SchemeSummary ss = dto.getSchemeSummary(scheme);
                    ss.setAssociated(true);
                    ss.setArea((BigDecimal) row[INDEX4]);
                }
            }
        }

        return new ArrayList<LandUserDetailSummaryDTO>() {
            {
                addAll(map.values());
            }
        };
    }
}
