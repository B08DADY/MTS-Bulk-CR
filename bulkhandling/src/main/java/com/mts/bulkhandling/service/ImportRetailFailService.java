package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.ImportRetailFailBulkRequest;
import com.mts.bulkhandling.repository.WfWoBulkCloseQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportRetailFailService {
    @Autowired
    private WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;

    public void execute(List<ImportRetailFailBulkRequest> request) {
    }
}
