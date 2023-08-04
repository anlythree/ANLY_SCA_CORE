package com.anlythree.anlykafka.config;

import com.anlythree.common.factory.YamlPropertySourceFactory;
import org.springframework.context.annotation.PropertySource;

@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:anly-kafka.yml")
public class KafkaConfiguration {
}
