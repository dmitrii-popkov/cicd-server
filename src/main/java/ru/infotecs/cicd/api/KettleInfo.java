package ru.infotecs.cicd.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о чайнике")
public record KettleInfo(
		@Schema(description = "Уникальный идентификатор чайника в сети",
				requiredMode = Schema.RequiredMode.REQUIRED) String id) {
}
