package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.ImportFoBulkRequest;
import com.mts.bulkhandling.repository.WfWoBulkCloseQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportFoService {

    @Autowired
    private WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;


    public void execute(List<ImportFoBulkRequest> request) {


        //wfWoBulkCloseQueueRepository.saveAll(request);

//        for(e:e) {
//
//        }
    }
}
