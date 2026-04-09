package com.mts.bulkvalidation.mapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SerialNumberIdMap {
    public static final Map<String, Long> SERIAL_IDS;

    static {
        Map<String, Long> map = new HashMap<>();
        map.put("FTTHReSurvey",           13868L);  // 1042
        map.put("FTTHUnRchReSurvey",      125641L); // 1959
        map.put("FTTHOntRep",             125642L); // 1047
        map.put("FTTHMigrationHV",        125632L); // 1051
        map.put("FTTHMigrationSurvey",    13869L);  // 1052
        map.put("FTTHNewSubV",            13870L);  // 1053
        map.put("FTTHNewSubVoiceData",    13871L);  // 1054
        map.put("FTTHNewSubHV",           13873L);  // 1068
        map.put("FTTHChPhNo",             125640L); // 1071
        map.put("FTTHMigrationVS",        125631L); // 1079
        map.put("FTTHNewSubVS",           125633L); // 1080
        map.put("FTTH Survey",            125634L); // 1085
        map.put("ChPhoneNoSurv",          125635L); // 1090
        map.put("FTTHMigrationNewSubV",   125636L); // 1097
        map.put("FTTHFVMigrationHV",      125637L); // 1664
        map.put("FTTHTDMMigrationVS",     125638L); // 1749
        map.put("FTTHTDMMigrationHV",     125639L); // 1754

        SERIAL_IDS = Collections.unmodifiableMap(map);
    }

}
