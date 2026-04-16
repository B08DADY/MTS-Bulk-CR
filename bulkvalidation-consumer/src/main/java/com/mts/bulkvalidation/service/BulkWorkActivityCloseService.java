package com.mts.bulkvalidation.service;


import com.mts.bulkvalidation.dto.BulkTerminateRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
@RequiredArgsConstructor
public class BulkWorkActivityCloseService {

    private final EntityManager entityManager;

    public void closeWorkActivity(BulkTerminateRequest request) {

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("UPDATE_WF_WORK_ACTIVITY_CLOSE");

        query.registerStoredProcedureParameter("P_WORK_ID",          String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_NEW_STATUS",        String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_NEW_ACTIVITY",      String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_CATEGORY_ID",       String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_CLOSE_NAME",        String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_NOTES",             String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_UPDATE_BY",         String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_CLOSE_TEAM_LEADER", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_LINE_TYPE",         String.class, ParameterMode.IN);

        ProcedureCall procedureCall = query.unwrap(ProcedureCall.class);
        procedureCall.getParameterRegistration("P_LINE_TYPE").enablePassingNulls(true);
        procedureCall.getParameterRegistration("P_NEW_ACTIVITY").enablePassingNulls(true); // ← add this


        query.setParameter("P_WORK_ID",          String.valueOf(request.getWorkId()));
        query.setParameter("P_NEW_STATUS",        "Completed");
        query.setParameter("P_NEW_ACTIVITY",      null);
        query.setParameter("P_CATEGORY_ID",       "2");
        query.setParameter("P_CLOSE_NAME",        request.getCloseName());
        query.setParameter("P_NOTES",             request.getNotes());
        query.setParameter("P_UPDATE_BY",         "BULK");
        query.setParameter("P_CLOSE_TEAM_LEADER", "1");
        query.setParameter("P_LINE_TYPE",         null);

        query.execute();
    }
}
