package ru.infotecs.cicd.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.infotecs.cicd.api.KettleInfo;
import ru.infotecs.cicd.service.KettleFacade;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Kettle", description = "Методы работы с умными чайниками")
public class KettleController {

	private final KettleFacade kettleFacade;

	@Operation(summary = "Получение списка доступных в сети чайников")
	@GetMapping(value = "/kettle")
	public Collection<KettleInfo> getAvailable() {
		return kettleFacade.getAvailable();
	}

	@Operation(summary = "Включение чайника")
	@PostMapping(value = "/kettle/{id}/on")
	public void turnOn(@Parameter(description = "Идентификатор чайника", required = true) @PathVariable String id) {
		kettleFacade.turnOn(id);
	}

	@Operation(summary = "Выключение чайника")
	@PostMapping(value = "/kettle/{id}/off")
	public void turnOff(@Parameter(description = "Идентификатор чайника", required = true) @PathVariable String id) {
		kettleFacade.turnOff(id);
	}

	@Operation(summary = "Перезагрузить брокер")
	@PostMapping(value = "/kettle/reset")
	public void reset() {
		kettleFacade.reload();
	}


}