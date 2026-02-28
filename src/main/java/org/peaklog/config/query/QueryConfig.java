package org.peaklog.config.query;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:queries/user-queries.properties")
public class QueryConfig {}
