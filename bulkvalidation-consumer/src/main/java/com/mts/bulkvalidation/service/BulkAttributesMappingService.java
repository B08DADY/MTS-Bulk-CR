package com.mts.bulkvalidation.service;


import com.mts.bulkvalidation.dto.BulkTerminateRequest;
import com.mts.bulkvalidation.model.WfWoBulkQueue;
import lombok.RequiredArgsConstructor;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
@RequiredArgsConstructor

public class BulkAttributesMappingService {

    private final EntityManager entityManager;

    public void execute(WfWoBulkQueue request) {

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("BULK_ATTRIBUTES_MAPPING");

        query.registerStoredProcedureParameter("P_QUEUE_ID",          Long.class, ParameterMode.IN);



        query.setParameter("P_QUEUE_ID",             request.getId());


        query.execute();
    }
}
