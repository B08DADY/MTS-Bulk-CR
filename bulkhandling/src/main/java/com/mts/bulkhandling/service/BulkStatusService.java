package com.mts.bulkhandling.service;

import com.mts.bulkhandling.model.LkpBulkStatus;
import com.mts.bulkhandling.repository.LkpBulkStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BulkStatusService {
    @Autowired
    private LkpBulkStatusRepository repository;

    public List<String> getAllStatuses() {
        try {
            return repository.findAll()
                    .stream()
                    .map(LkpBulkStatus::getStatus)
                    .collect(Collectors.toList());
        }catch (org.springframework.dao.DataAccessException e) {
            throw new RuntimeException("Failed to retrieve bulk statuses due to a database error", e);

        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while retrieving bulk statuses", e);
        }
    }
}
