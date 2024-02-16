package ru.infotecs.cicd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.infotecs.cicd.api.KettleInfo;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class KettleFacade {

	private final ReloadableKettleConnector kettleConnector;

	@EventListener(ApplicationReadyEvent.class)
	public void start() {
		kettleConnector.startListening();
	}

	public void reload() {
		kettleConnector.reload();
	}

	@EventListener(ContextClosedEvent.class)
	public void close() {
		kettleConnector.stopListening();
	}

	public Collection<KettleInfo> getAvailable() {
		return kettleConnector.getAvailable()
			.stream().map(state -> {
				return new KettleInfo(state.id(), state.switchMode(), state.temperature());
			}).collect(Collectors.toList());
	}

	public void turnOn(String id) {
		kettleConnector.turnOn(id);
	}

	public void turnOff(String id) {
		kettleConnector.turnOff(id);
	}
}
