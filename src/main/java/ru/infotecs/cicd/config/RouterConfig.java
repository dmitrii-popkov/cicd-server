package ru.infotecs.cicd.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;
import ru.infotecs.cicd.ApiPropertyConfig;
import ru.infotecs.cicd.exception.FileSystemException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.springframework.web.servlet.function.RequestPredicates.GET;

/**
 * Конфигурация путей в приложении
 */
@Configuration
@RequiredArgsConstructor
public class RouterConfig {

	private final ApiPropertyConfig apiPropertyConfig;

	@Bean
	public RouterFunction<ServerResponse> mainRouter() {
		return RouterFunctions
				.route(GET("/**")
						.and(GET(apiPropertyConfig.prefix() + "/**").negate()).and(GET("/swagger").negate())
						.and(GET("/resources/**").negate()).and(GET("/favicon**").negate())
						.and(GET("/swagger-ui/**").negate()).and(GET("/").negate()), request -> getIndexHtml())
				.andRoute(GET("/swagger"),
						request -> ServerResponse.permanentRedirect(URI.create("/swagger-ui/index.html")).build());
	}

	private ServerResponse getIndexHtml() {
		try {
			Resource resource = new ClassPathResource("/static/index.html");
			InputStream inputStream = resource.getInputStream();
			byte[] htmlData = FileCopyUtils.copyToByteArray(inputStream);
			String html = new String(htmlData, StandardCharsets.UTF_8);
			return ServerResponse.ok().contentType(MediaType.TEXT_HTML).body(html);
		} catch (IOException e) {
			throw new FileSystemException(e);
		}
	}
}
