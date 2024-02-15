package ru.infotecs.cicd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import ru.infotecs.cicd.service.ReloadableKettleConnector;

import java.util.function.Supplier;

@Configuration
@Slf4j
@EnableConfigurationProperties({ ApiPropertyConfig.class })
public class Beans {
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public Supplier<KettleConnector> kettleConnectorSupplier(@Value("${mqtt.url}") String url) {
		return () -> KettleConnector.create(url);
	}

	@Bean
	public ReloadableKettleConnector reloadableKettleConnector(Supplier<KettleConnector> kettleConnectorSupplier) {
		return new ReloadableKettleConnector(kettleConnectorSupplier);
	}
}
