package ru.infotecs.cicd.service;

import ru.infotecs.cicd.ConnectionNotClosedException;
import ru.infotecs.cicd.KettleConnector;
import ru.infotecs.cicd.KettleInternalException;
import ru.infotecs.cicd.KettleState;

import java.util.Collection;

public class NullKettleConnector implements KettleConnector {

	public static final NullKettleConnector INSTANCE = new NullKettleConnector();

	@Override
	public void startListening() throws KettleInternalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stopListening() throws KettleInternalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<KettleState> getAvailable() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void turnOn(String s) throws KettleInternalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void turnOff(String s) throws KettleInternalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void heat(String s, int i) throws KettleInternalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void close() throws ConnectionNotClosedException {
		throw new UnsupportedOperationException();
	}
}
