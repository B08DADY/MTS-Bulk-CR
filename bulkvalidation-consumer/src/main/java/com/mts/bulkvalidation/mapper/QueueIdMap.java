package com.mts.bulkvalidation.mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QueueIdMap {
    public static final Map<String, Long> QUEUE_IDS;
    // map each request with the att id of the queue variable
    static {
        Map<String, Long> map = new HashMap<>();
        map.put("FTTHReSurvey",           13765L); // 1042
        map.put("FTTHMigrationSurvBO",    13805L); // 1046
        map.put("FTTHOntRep",             13779L); // 1047
        map.put("FTTHMigrationHV",        13780L); // 1051
        map.put("FTTHMigrationSurvey",    13806L); // 1052
        map.put("FTTHNewSubV",            13782L); // 1053
        map.put("FTTHNewSubVoiceData",    13807L); // 1054
        map.put("FTTHNewSubSBO",          13808L); // 1058
        map.put("FTTHMigrationVSBO",      13783L); // 1061
        map.put("FTTHReSurveyBO",         13809L); // 1062
        map.put("FTTHNewSubVSBO",         13810L); // 1067
        map.put("FTTHNewSubHV",           13781L); // 1068
        map.put("ChPhoneNoSurvBO",        13811L); // 1070
        map.put("FTTHChPhNo",             13784L); // 1071
        map.put("FixPassiveCC",           13812L); // 1072
        map.put("FTTH Survey BO",         13813L); // 1078
        map.put("FTTHMigrationVS",        13785L); // 1079
        map.put("FTTHNewSubVS",           13786L); // 1080
        map.put("FTTH Survey",            13795L); // 1085
        map.put("ChPhoneNoSurv",          13796L); // 1090
        map.put("FTTHMigrationNewSubV",   13797L); // 1097
        map.put("FVChPhoneNoNewLoc",      13798L); // 1424
        map.put("FTTHFVMigrationHV",      13799L); // 1664
        map.put("FTTHTDMMigrationVS",     13800L); // 1749
        map.put("FTTHTDMMigrationHV",     13801L); // 1754
        map.put("FTTHTDMMigrationVSBO",   13802L); // 1804
        map.put("FTTHUnRchReSurvey",      13803L); // 1959
        map.put("FTTHUnRchReSurveyBO",    13804L); // 1964

        QUEUE_IDS = Collections.unmodifiableMap(map);
    }
}
