package com.server.aiservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing //To Activity MongoDB annotations.
public class MongoConfig {
}

