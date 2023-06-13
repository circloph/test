package com.example.Currencyy;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Endpoint(id = "currency-service")
public class CurrencyServiceEndpoint {

	private final ConfigurableEnvironment  environment;

	@Autowired
	public CurrencyServiceEndpoint(ConfigurableEnvironment environment) {
		this.environment = environment;
	}

	// в актуатор вытащить список конфигов(без паролей)(отдельный эндпоинт)

	@ReadOperation
	public Collection<Object> getAllProperties() {
		Map<String, Object> properties = new ConcurrentHashMap<>();
		MutablePropertySources propertySources = environment.getPropertySources();
		propertySources.forEach(propertySource -> {
			if (propertySource.getName().startsWith("Config resource")) {
				properties.put(propertySource.getName(), propertySource.getSource().toString());
			}
		});
		return properties.values().stream().map(s -> s.toString().split(",")).collect(Collectors.toList());
	}

}
