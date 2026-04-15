package com.mts.bulkvalidation.mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SectionIdMap {

    public static final Map<String, Long> SECTION_IDS;

    static {
        Map<String, Long> map = new HashMap<>();
        map.put("FTTHReSurvey",           1042L);
        map.put("FTTHMigrationSurvBO",    1046L);
        map.put("FTTHOntRep",             581L);
        map.put("FTTHMigrationHV",        669L);
        map.put("FTTHMigrationSurvey",    1052L);
        map.put("FTTHNewSubV",            388L);
        map.put("FTTHNewSubVoiceData",    1007L);
        map.put("FTTHNewSubSBO",          1058L);
        map.put("FTTHMigrationVSBO",      1061L);
        map.put("FTTHReSurveyBO",         1062L);
        map.put("FTTHNewSubVSBO",         1067L);
        map.put("FTTHNewSubHV",           361L);
        map.put("ChPhoneNoSurvBO",        1070L);
        map.put("FTTHChPhNo",             666L);
        map.put("FixPassiveCC",           1072L);
        map.put("FTTH Survey BO",         1078L);
        map.put("FTTHMigrationVS",        1079L);
        map.put("FTTHNewSubVS",           1080L);
        map.put("FTTH Survey",            1085L);
        map.put("ChPhoneNoSurv",          1090L);
        map.put("FTTHMigrationNewSubV",   678L);
        map.put("FVChPhoneNoNewLoc",      1424L);
        map.put("FTTHFVMigrationHV",      1663L);
        map.put("FTTHTDMMigrationVS",     1749L);
        map.put("FTTHTDMMigrationHV",     1753L);
        map.put("FTTHTDMMigrationVSBO",   1804L);
        map.put("FTTHUnRchReSurvey",      1959L);
        map.put("FTTHUnRchReSurveyBO",    1964L);

        SECTION_IDS = Collections.unmodifiableMap(map);
    }
}