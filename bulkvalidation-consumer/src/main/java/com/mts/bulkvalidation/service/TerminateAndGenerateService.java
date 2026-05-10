package com.mts.bulkvalidation.service;

import com.mts.bulkvalidation.dto.TerminateAndGenerateRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
@RequiredArgsConstructor
public class TerminateAndGenerateService {

    private final EntityManager entityManager;

    public String execute(TerminateAndGenerateRequest request) {

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("TERMINATE_AND_GENERATE");

        // ── Required IN params you want to pass ──────────────────────────────
        query.registerStoredProcedureParameter("P_WORK_ID", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_NOTES", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_UPDATE_BY", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_CATEGORY_ID", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_CLOSE_NAME", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_NEW_STATUS", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_NEW_ACTIVITY", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_WO_ID", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_INSTANCE_ID", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_NOT_GENERATE_PROCESS", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("SAME_OWNER_FLAG", String.class, ParameterMode.IN);

        // ── Remaining required params — passed as null (procedure handles them) ──
        query.registerStoredProcedureParameter("P_PLAN_START_DATE", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_REQUEST_TYPE", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_PLAN_END_DATE", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("reservation_code", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_INITIATOR", String.class, ParameterMode.IN);

        // ── OUT param ────────────────────────────────────────────────────────
        query.registerStoredProcedureParameter("O_GEN_TASK_ID", String.class, ParameterMode.OUT);

        // ── Enable null passing for nullable params ───────────────────────────
        ProcedureCall procedureCall = query.unwrap(ProcedureCall.class);
        procedureCall.getParameterRegistration("P_INSTANCE_ID").enablePassingNulls(true);
        procedureCall.getParameterRegistration("P_PLAN_START_DATE").enablePassingNulls(true);
        procedureCall.getParameterRegistration("P_PLAN_END_DATE").enablePassingNulls(true);
        procedureCall.getParameterRegistration("P_REQUEST_TYPE").enablePassingNulls(true);
        procedureCall.getParameterRegistration("reservation_code").enablePassingNulls(true);
        procedureCall.getParameterRegistration("P_INITIATOR").enablePassingNulls(true);
        procedureCall.getParameterRegistration("P_NEW_ACTIVITY").enablePassingNulls(true);


        // ── Set values ───────────────────────────────────────────────────────
        query.setParameter("P_WORK_ID", request.getWorkId());
        query.setParameter("P_NOTES", request.getNotes());
        query.setParameter("P_UPDATE_BY", request.getUpdateBy());
        query.setParameter("P_CATEGORY_ID", request.getCategoryId());
        query.setParameter("P_CLOSE_NAME", request.getCloseName());
        query.setParameter("P_NEW_STATUS", request.getNewStatus());
        query.setParameter("P_NEW_ACTIVITY", request.getNewActivity());
        query.setParameter("P_WO_ID", request.getWoId());
        query.setParameter("P_INSTANCE_ID", request.getInstanceId());
        query.setParameter("P_NOT_GENERATE_PROCESS", request.getNotGenerateProcess());
        query.setParameter("SAME_OWNER_FLAG", request.getSameOwnerFlag());

        // Params not used — pass null so Oracle uses defaults/internal logic
        query.setParameter("P_PLAN_START_DATE", null);
        query.setParameter("P_REQUEST_TYPE", request.getRequestType()); // keep if needed
        query.setParameter("P_PLAN_END_DATE", null);
        query.setParameter("reservation_code", null);
        query.setParameter("P_INITIATOR", null);

        query.execute();

        return (String) query.getOutputParameterValue("O_GEN_TASK_ID");
    }
}
