package com.example.Currencyy;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.origin.OriginTrackedValue;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.*;
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
	public Map<Object, Object> getAllProperties() {
		Map<String, Object> map = new HashMap();
		for(Iterator it = ((AbstractEnvironment) environment).getPropertySources().iterator(); it.hasNext(); ) {
			PropertySource propertySource = (PropertySource) it.next();
			if (propertySource instanceof OriginTrackedMapPropertySource) {
				map.putAll(((MapPropertySource) propertySource).getSource());
			}
		}
		Map<Object, Object> mapa = map.entrySet().stream().collect(Collectors.toMap(e-> e.getKey(), e-> ((OriginTrackedValue) e.getValue()).getValue()));
		return mapa;
	}

}
