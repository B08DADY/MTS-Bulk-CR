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
        map.put("FTTHOntRep",             1047L);
        map.put("FTTHMigrationHV",        1051L);
        map.put("FTTHMigrationSurvey",    1052L);
        map.put("FTTHNewSubV",            1053L);
        map.put("FTTHNewSubVoiceData",    1054L);
        map.put("FTTHNewSubSBO",          1058L);
        map.put("FTTHMigrationVSBO",      1061L);
        map.put("FTTHReSurveyBO",         1062L);
        map.put("FTTHNewSubVSBO",         1067L);
        map.put("FTTHNewSubHV",           1068L);
        map.put("ChPhoneNoSurvBO",        1070L);
        map.put("FTTHChPhNo",             1071L);
        map.put("FixPassiveCC",           1072L);
        map.put("FTTH Survey BO",         1078L);
        map.put("FTTHMigrationVS",        1079L);
        map.put("FTTHNewSubVS",           1080L);
        map.put("FTTH Survey",            1085L);
        map.put("ChPhoneNoSurv",          1090L);
        map.put("FTTHMigrationNewSubV",   1097L);
        map.put("FVChPhoneNoNewLoc",      1424L);
        map.put("FTTHFVMigrationHV",      1664L);
        map.put("FTTHTDMMigrationVS",     1749L);
        map.put("FTTHTDMMigrationHV",     1754L);
        map.put("FTTHTDMMigrationVSBO",   1804L);
        map.put("FTTHUnRchReSurvey",      1959L);
        map.put("FTTHUnRchReSurveyBO",    1964L);

        SECTION_IDS = Collections.unmodifiableMap(map);
    }
}

