package com.agrofarm.irrigationsystem.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "sensor")
public class AppProperties {
    private String url;

}
