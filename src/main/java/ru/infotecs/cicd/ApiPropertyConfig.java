package ru.infotecs.cicd;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Конфиг API
 *
 * @param prefix         Базовый путь API
 */
@ConfigurationProperties(prefix = "api")
public record ApiPropertyConfig(String prefix) {
}
