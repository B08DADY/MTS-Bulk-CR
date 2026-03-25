package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.HandledWoSearchResponse;
import com.mts.bulkhandling.dto.WoSearchRequest;
import com.mts.bulkhandling.mapper.Mapper;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import com.mts.bulkhandling.repository.WfWoBulkCloseQueueRepository;
import com.mts.bulkhandling.specification.WfWoBulkQueueSpecification;
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

    public Page<HandledWoSearchResponse> search(WoSearchRequest request, Pageable pageable) {
        Sort sort = buildSort(request);
        Pageable page = PageRequest.of(request.getPage(), request.getSize(), sort);

        Specification<WfWoBulkQueue> spec = WfWoBulkQueueSpecification.filter(request);

        Page<WfWoBulkQueue> BulkQueuePage = wfWoBulkCloseQueueRepository.findAll(spec, pageable);
        return BulkQueuePage.map(Mapper::toHandledWoSearchResponse);
    }

    private Sort buildSort(WoSearchRequest request) {
        Sort.Direction direction = "desc".equalsIgnoreCase(request.getSortDir())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return Sort.by(direction, request.getSortBy());
    }
}
