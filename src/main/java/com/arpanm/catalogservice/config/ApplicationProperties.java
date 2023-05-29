package com.arpanm.catalogservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationProperties {

    /**
     * A message to welcome users
     */
    private String greeting;

    /**
     * Indicate where to load test data
     * in-memory, db
     */
    private String loadTestData;
}
