# Paginated Dynamic Search API — Implementation Plan

Implements a `/search` POST endpoint that dynamically filters across `WF_WORK_ORDER` and `WF_WO_BULK_CLOSE_QUEUE` using the JPA Criteria API (Specifications). All code is Java 8-compatible with Lombok.

## Proposed Changes

### Entities (new)

#### [NEW] WfWorkOrder.java
`com.mts.bulkhandling.model.WfWorkOrder`  
JPA entity for `WF_WORK_ORDER` table (schema `MTS_WFM_2017`). Maps all relevant columns with correct Oracle column names. `WORK_ORDER_ID` is the `@Id`.

#### [NEW] WfWoBulkCloseQueue.java
`com.mts.bulkhandling.model.WfWoBulkCloseQueue`  
JPA entity for `WF_WO_BULK_CLOSE_QUEUE` table (schema `MTS_WFM_2017`). `ID` is the `@Id` (auto-generated). Has a `@ManyToOne` join to `WfWorkOrder` via `WORK_ORDER_ID`.

---

### DTOs (new)

#### [NEW] WorkOrderSearchRequest.java
`com.mts.bulkhandling.dto.WorkOrderSearchRequest`  
Request DTO accepting optional filter fields:
- `workOrderId`, `organization`, `place`, `serviceId`, `referenceId`, `requestType` (from `wf_work_order`)
- `fileId` (from `wf_wo_bulk_close_queue`)
- `recordStatus` (user-supplied, must still exclude `'pending validation'`)
- `page` (default 0), `size` (default 10), `sortBy` (default `workOrderId`), `sortDir` (default `asc`)

#### [NEW] WorkOrderSearchResponse.java
`com.mts.bulkhandling.dto.WorkOrderSearchResponse`  
Response DTO (flat projection) with fields from both tables:
`workOrderId`, `orgRoleName`, `placeId`, `serviceId`, `referenceId`, `requestType`, `woStage`, `woStatus`, `fileId`, `recordStatus`, `orderStatus`, `actionDate`.

---

### Repositories (new)

#### [NEW] WfWorkOrderRepository.java
`com.mts.bulkhandling.repository.WfWorkOrderRepository`  
Extends `JpaRepository<WfWorkOrder, String>` and `JpaSpecificationExecutor<WfWorkOrder>`.

#### [NEW] WfWoBulkCloseQueueRepository.java
`com.mts.bulkhandling.repository.WfWoBulkCloseQueueRepository`  
Extends `JpaRepository<WfWoBulkCloseQueue, Long>`.

---

### Specification (new)

#### [NEW] WorkOrderSpecification.java
`com.mts.bulkhandling.specification.WorkOrderSpecification`  
Static factory method `buildSearchSpec(WorkOrderSearchRequest)` returns a `Specification<WfWorkOrder>`.

**Strategy:** Because we need to filter on the `WF_WO_BULK_CLOSE_QUEUE` table, the spec performs an INNER JOIN from `WF_WORK_ORDER` to its `bulkCloseQueues` collection using the Criteria API (`Root.join()`).

**Predicates applied:**
- **Hardcoded always-on:**
  - `wo.woStage IN ('Assign')`
  - `queue.recordStatus <> 'pending validation'`
- **Dynamic (null-safe):**
  - `workOrderId` → `wo.workOrderId = :val`
  - `organization` → `wo.orgRoleName = :val`
  - `place` → `wo.placeId = :val`
  - `serviceId` → `wo.serviceId = :val`
  - `referenceId` → `wo.referenceId = :val`
  - `requestType` → `wo.requestType = :val`
  - `fileId` → `queue.fileId = :val`
  - `recordStatus` → `queue.recordStatus = :val` (applied on top of the hardcoded NOT 'pending validation')

---

### Service (new)

#### [NEW] WorkOrderService.java
`com.mts.bulkhandling.service.WorkOrderService`  
- Builds `Pageable` from request (page, size, sortBy, sortDir).
- Calls `WfWorkOrderRepository.findAll(spec, pageable)`.
- Maps each `WfWorkOrder` result to `WorkOrderSearchResponse` (pulling queue fields from the first matching queue entry).
- Returns `Page<WorkOrderSearchResponse>`.

---

### Controller (new)

#### [NEW] WorkOrderController.java
`com.mts.bulkhandling.controller.WorkOrderController`  
- `POST /search`
- Accepts `@RequestBody WorkOrderSearchRequest`
- Returns `ResponseEntity<Page<WorkOrderSearchResponse>>`

---

### Config update

#### [MODIFY] application.properties
Add Oracle dialect and Hibernate settings:
```properties
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
```

---

## Verification Plan

### Automated Tests
No existing tests found in the project. The project currently has an empty test folder.

### Manual Verification (Compile)
After all files are created, run:
```
cd "d:\MTS\BULK CR\bulkhandling"
mvnw.cmd compile
```
A successful `BUILD SUCCESS` output confirms the code compiles without errors.

### Manual Verification (Runtime/API)
1. Start the application: `mvnw.cmd spring-boot:run`
2. Send a POST request to `http://localhost:8081/search` with a JSON body (example):
```json
{
  "organization": "SomeOrg",
  "requestType": "INSTALLATION",
  "page": 0,
  "size": 10,
  "sortBy": "workOrderId",
  "sortDir": "asc"
}
```
3. Confirm a valid paginated JSON response is returned.
4. Confirm that results have `woStage = 'Assign'` and `recordStatus != 'pending validation'`.
