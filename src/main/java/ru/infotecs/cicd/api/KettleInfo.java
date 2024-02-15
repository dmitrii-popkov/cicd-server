package ru.infotecs.cicd.api;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.infotecs.cicd.SwitchMode;

@Schema(description = "Информация о чайнике")
public record KettleInfo(
		@Schema(description = "Уникальный идентификатор чайника в сети",
				requiredMode = Schema.RequiredMode.REQUIRED) String id,
		@Schema(description = "Режим работы чайника",
				requiredMode = Schema.RequiredMode.REQUIRED) SwitchMode switchMode,
		@Schema(description = "Температура чайника",
				requiredMode = Schema.RequiredMode.REQUIRED) int temperature) {
}
