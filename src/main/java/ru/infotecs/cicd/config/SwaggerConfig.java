package ru.infotecs.cicd.config;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.infotecs.cicd.ApiPropertyConfig;

import java.util.List;

/**
 * Конфигурация Swagger.
 */
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

	/**
	 * Тег раздела об аутентификации, отображаемый на сгенерированной Swagger
	 * странице.
	 */
	private static final String AUTHENTICATION_TAG_NAME = "Authentication";

	/**
	 * Путь к ресурсу аутентификации.
	 */
	private static final String AUTHENTICATION_RESOURCE_PATH = "/auth";

	private final ApiPropertyConfig apiPropertyConfig;

	@Bean
	public OpenApiCustomizer consumerTypeHeaderOpenAPICustomizer() {

		PathItem logoutPathItem = createLogoutPathItem();

		return openApi -> {
			Paths paths = openApi.getPaths();

			paths.addPathItem(apiPropertyConfig.prefix() + AUTHENTICATION_RESOURCE_PATH + "/logout", logoutPathItem);

			openApi.setPaths(paths);
		};
	}

	private PathItem createLogoutPathItem() {
		ApiResponse successResponse = new ApiResponse();
		successResponse.setDescription("Выход из учётной записи выполнен");

		ApiResponses responses = new ApiResponses();
		responses.addApiResponse("200", successResponse);

		Operation operation = new Operation();
		operation.setTags(List.of(AUTHENTICATION_TAG_NAME));
		operation.setSummary("Выход из учётной записи");
		operation.setDescription("Осуществляет выход из учётной записи пользователя");
		operation.setOperationId("logout");
		operation.setResponses(responses);

		PathItem pathItem = new PathItem();
		pathItem.setPost(operation);
		return pathItem;
	}
}
