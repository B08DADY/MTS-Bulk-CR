package com.mts.bulkvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Bulk Validation Consumer application.
 *
 * <p>This application listens to the "bulk-queue-validation" Kafka topic,
 * fetches the corresponding WfWoBulkQueue record from the Oracle database,
 * and routes it to the correct validation logic.
 *
 * <p>For WebLogic deployment, extend SpringBootServletInitializer and
 * change pom.xml packaging to "war".
 */
@SpringBootApplication
public class BulkValidationConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BulkValidationConsumerApplication.class, args);
    }
}
