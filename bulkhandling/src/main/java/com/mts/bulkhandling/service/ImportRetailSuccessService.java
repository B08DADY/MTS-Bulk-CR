package com.mts.bulkhandling.service;


import com.mts.bulkhandling.dto.ImportRetailSuccessBulkRequest;
import com.mts.bulkhandling.repository.WfWoBulkCloseQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportRetailSuccessService {
    @Autowired
    private WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;

    public void execute(List<ImportRetailSuccessBulkRequest> request) {
    }
}
