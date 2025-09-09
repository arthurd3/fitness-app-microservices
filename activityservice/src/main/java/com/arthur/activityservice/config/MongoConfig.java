package com.arthur.activityservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing //To Activity MongoDB annotations.
public class MongoConfig {
}
