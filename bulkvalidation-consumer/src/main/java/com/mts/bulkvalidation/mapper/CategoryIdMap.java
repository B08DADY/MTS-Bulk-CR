package com.mts.bulkvalidation.mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CategoryIdMap {

    public static final Map<String, Long> CATEGORY_IDS;
    // map each request with the att id of the queue variable
    static {
        Map<String, Long> map = new HashMap<>();
        map.put("FTTHReSurvey", 13822L);
        map.put("FTTHMigrationSurvBO", 13826L);
        map.put("FTTHMigrationSurvey", 13827L);
        map.put("FTTHNewSubSBO", 13828L	);
        map.put("FTTHMigrationVSBO", 13829L);
        map.put("FTTHReSurveyBO", 13840L);
        map.put("FTTHNewSubVSBO", 13841L);
        map.put("ChPhoneNoSurvBO", 13842L);
        map.put("FixPassiveCC", 13843L);
        map.put("FTTH Survey BO", 13844L);
        map.put("FTTHMigrationVS", 13845L);
        map.put("FTTHNewSubVS", 13846L);
        map.put("FTTH Survey", 13847L);
        map.put("ChPhoneNoSurv", 13848L);
        map.put("FTTHTDMMigrationVSBO", 13849L);
        map.put("FVChPhoneNoNewLoc", 13850L);
        map.put("FTTHTDMMigrationVS", 13851L);
        map.put("FTTHUnRchReSurvey", 13852L);
        map.put("FTTHUnRchReSurveyBO", 13853L);


        CATEGORY_IDS = Collections.unmodifiableMap(map);
    }
}
