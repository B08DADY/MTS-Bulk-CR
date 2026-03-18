package com.mts.bulkhandling.controller;


import com.mts.bulkhandling.dto.*;
import com.mts.bulkhandling.service.ImportFoService;
import com.mts.bulkhandling.service.ImportRetailFailService;
import com.mts.bulkhandling.service.ImportRetailSuccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/import")
public class ImportBulkController {

    @Autowired
    private ImportFoService importFoService;
    @Autowired
    private ImportRetailSuccessService importRetailSuccessService;
    @Autowired
    private ImportRetailFailService importRetailFailService;

    @PostMapping("/fo")
    public ResponseEntity<?> importFo(
            @RequestBody List<ImportFoBulkRequest> request
    ) {
        try {

            importFoService.execute(request);

            return ResponseEntity.ok("Imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while importing: " + e.getMessage());
        }
    }

    @PostMapping("/retail/success")
    public ResponseEntity<?> importRetailSuccess(
            @RequestBody List<ImportRetailSuccessBulkRequest> request
    ) {
        try {

            importRetailSuccessService.execute(request);

            return ResponseEntity.ok("Imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while importing: " + e.getMessage());
        }
    }


    @PostMapping("/retail/fail")
    public ResponseEntity<?> importRetailFail(
            @RequestBody List<ImportRetailFailBulkRequest> request
    ) {
        try {

            importRetailFailService.execute(request);

            return ResponseEntity.ok("Imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while importing: " + e.getMessage());
        }
    }

}
