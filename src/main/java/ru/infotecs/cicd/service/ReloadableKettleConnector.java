package ru.infotecs.cicd.service;

import lombok.extern.slf4j.Slf4j;
import ru.infotecs.cicd.ConnectionNotClosedException;
import ru.infotecs.cicd.KettleConnector;
import ru.infotecs.cicd.KettleInternalException;
import ru.infotecs.cicd.KettleState;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@Slf4j
public class ReloadableKettleConnector implements KettleConnector {

	private final AtomicReference<KettleConnector> connector = new AtomicReference<>();
	private final Supplier<KettleConnector> supplier;

	public ReloadableKettleConnector(Supplier<KettleConnector> supplier) {
		this.supplier = supplier;

	}

	@Override
	public void startListening() throws KettleInternalException {
		try {
			KettleConnector kettleConnector = connector.get();
			if (kettleConnector == null) {
				reload();
			}
			connector.get().startListening();
		} catch (KettleInternalException e) {
			log.error("Could not reload connector, setting null", e);
			this.connector.set(NullKettleConnector.INSTANCE);
		}
	}

	public void reload() {
		KettleConnector oldConnector = this.connector.getAndSet(supplier.get());
		if (oldConnector != null && oldConnector != NullKettleConnector.INSTANCE) {
			try (KettleConnector connector = oldConnector) {
				log.info("Closing old kettle connection...");
			} catch (ConnectionNotClosedException e) {
				log.error("Could not close old connection", e);
				// TODO: 14.02.2024 handle dangling exception

			}
		}
	}

	@Override
	public void stopListening() throws KettleInternalException {
		connector.get().stopListening();
	}

	@Override
	public Collection<KettleState> getAvailable() {
		return connector.get().getAvailable();
	}

	@Override
	public void turnOn(String s) throws KettleInternalException {
		connector.get().turnOn(s);
	}

	@Override
	public void turnOff(String s) throws KettleInternalException {
		connector.get().turnOff(s);
	}

	@Override
	public void close() throws ConnectionNotClosedException {
		connector.get().close();
	}

	@Override
	public void heat(String s, int i) throws KettleInternalException {
		connector.get().heat(s, i);
	}
}
