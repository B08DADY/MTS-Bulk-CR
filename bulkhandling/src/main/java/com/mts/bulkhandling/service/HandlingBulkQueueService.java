package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.HandledWoSearchResponse;
import com.mts.bulkhandling.dto.WoSearchRequest;
import com.mts.bulkhandling.mapper.Mapper;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import com.mts.bulkhandling.repository.WfWoBulkCloseQueueRepository;
import com.mts.bulkhandling.specification.WfWoBulkQueueSpecification;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)

public class HandlingBulkQueueService {

    @Autowired
    private WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;

    public Page<HandledWoSearchResponse> search(WoSearchRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Search request must not be null");
        }
        if (request.getPage() < 0 || request.getSize() <= 0) {
            throw new IllegalArgumentException("Invalid pagination parameters: page=" + request.getPage() + ", size=" + request.getSize());
        }

        try {
            Sort sort = buildSort(request);
            Pageable page = PageRequest.of(request.getPage(), request.getSize(), sort);

            Specification<WfWoBulkQueue> spec = WfWoBulkQueueSpecification.filter(request);

            Page<WfWoBulkQueue> BulkQueuePage = wfWoBulkCloseQueueRepository.findAll(spec, page);
            return BulkQueuePage.map(Mapper::toHandledWoSearchResponse);
        }catch (IllegalArgumentException e) {
            throw e;
        }
        catch (org.springframework.dao.DataAccessException e) {
            throw new RuntimeException("Failed to retrieve bulk queue data due to a database error", e);
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while processing the search request", e);
        }
    }

    private Sort buildSort(WoSearchRequest request) {
        Sort.Direction direction = "desc".equalsIgnoreCase(request.getSortDir())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return Sort.by(direction, request.getSortBy());
    }
}
