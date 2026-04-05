package com.mts.bulkvalidation.service;


import com.mts.bulkvalidation.dto.BulkTerminateRequest;
import com.mts.bulkvalidation.repository.WfWorkOrderItemRepository;
import com.mts.bulkvalidation.repository.projection.WorkInstanceProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BulkTerminateAndGenerateService {


    private final EntityManager entityManager;

    public String execute(BulkTerminateRequest request) {



        // Step 2: Call the stored procedure via JPA StoredProcedureQuery
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("BULK_TERMINATE_AND_GENERATE");

        query.registerStoredProcedureParameter("P_work_order_id", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_NEW_STATUS",    String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_NEW_ACTIVITY",  String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_CATEGORY_ID",   String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_CLOSE_NAME",    String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_NOTES",         String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_REQ_TYPE",      String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_SEQ",           String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_UPDATE_BY",     String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("P_WORK_ID",       Long.class,   ParameterMode.IN);
        query.registerStoredProcedureParameter("P_INSTANCE_ID",   Long.class,   ParameterMode.IN);
        query.registerStoredProcedureParameter("o_gen_task_id",   String.class, ParameterMode.OUT);

        query.setParameter("P_work_order_id", request.getWorkOrderId());
        query.setParameter("P_CLOSE_NAME",    request.getCloseName());
        query.setParameter("P_NOTES",         request.getNotes());
        query.setParameter("P_REQ_TYPE",      request.getReqType());
        query.setParameter("P_WORK_ID",       request.getWorkId());      // fetched above
        query.setParameter("P_INSTANCE_ID",   request.getInstanceId());  // fetched above

        query.execute();

        return (String) query.getOutputParameterValue("o_gen_task_id");
    }
}