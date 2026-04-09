package com.mts.bulkvalidation.repository;

import com.mts.bulkvalidation.model.WfWoAdditionalAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WfWoAdditionalAttributeRepository extends JpaRepository<WfWoAdditionalAttribute, Long> {

}
