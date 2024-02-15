package ru.infotecs.cicd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.infotecs.cicd.ApiPropertyConfig;

/**
 * Конфигурация Spring Web MVC.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final String apiBasicPrefix;

	public WebMvcConfig(ApiPropertyConfig apiPropertyConfig) {
		this.apiBasicPrefix = apiPropertyConfig.prefix();
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix(apiBasicPrefix, HandlerTypePredicate.forAnnotation(RestController.class));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
	}

}