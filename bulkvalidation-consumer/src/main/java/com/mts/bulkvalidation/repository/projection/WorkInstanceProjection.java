package com.mts.bulkvalidation.repository.projection;

public interface WorkInstanceProjection {
    Long getWorkId();
    Long getInstanceId();
    String getStatus();
    Long getAcceptFlag();
}