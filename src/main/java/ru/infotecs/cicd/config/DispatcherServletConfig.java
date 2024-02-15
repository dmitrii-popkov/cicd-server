package ru.infotecs.cicd.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Конфигурация диспетчера сервлетов.
 */
@Configuration
public class DispatcherServletConfig {

	@Value("${spring.servlet.multipart.file-size-threshold}")
	private int fileSizeThreshold;

	@Value("${spring.servlet.multipart.location}")
	private String tempDirPath;

	@Value("${spring.servlet.multipart.maxFileSize}")
	private Long maxFileSize;

	@Value("${spring.servlet.multipart.maxRequestSize}")
	private Long maxRequestSize;

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	@Bean
	public ServletRegistrationBean<?> dispatcherServletPath() {
		ServletRegistrationBean<?> registration = new ServletRegistrationBean<>(dispatcherServlet(), "/");
		registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);

		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(tempDirPath, maxFileSize,
				maxRequestSize, fileSizeThreshold);

		registration.setMultipartConfig(multipartConfigElement);

		return registration;
	}
}