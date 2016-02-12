/*
 * Project : AFRC Futures
 * Copyright (C) 2013 Scottish Government AFRC Programme
 * AFRC PROPRIETARY/CONFIDENTIAL
 */
package uk.gov.scotland.afrc.applications.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uk.gov.scotland.afrc.applications.dao.LndPrclClassificationDao;
import uk.gov.scotland.afrc.applications.entity.base.impl.EntityManagerBaseImpl;
import uk.gov.scotland.afrc.applications.model.domain.LndPrclClassificationDBO;
public class LndPrclClassificationDaoImpl extends EntityManagerBaseImpl<LndPrclClassificationDBO, Long> implements
    LndPrclClassificationDao {

    private final Logger logger = Logger.getLogger(LndPrclClassificationDaoImpl.class);

    public LndPrclClassificationDaoImpl() {
        super(LndPrclClassificationDBO.class);
    }

    @Override
    public boolean isLFAParcel(long lpisLndPrclId) {

        List<String> lfaNames = Arrays.asList("LFA(D)", "LFA(SD)");

        TypedQuery<LndPrclClassificationDBO> query =
                getEntityManager().createNamedQuery("LndPrclClassificationDBO.findByIdAndType",
                        LndPrclClassificationDBO.class);
        List<LndPrclClassificationDBO> result =
                query.setParameter("lpisLndPrclId", lpisLndPrclId).setParameter("typeList", lfaNames).getResultList();
        logger.debug("query  = " + query + " " + lpisLndPrclId);
        return (!(null == result || result.isEmpty()));
    }
}
