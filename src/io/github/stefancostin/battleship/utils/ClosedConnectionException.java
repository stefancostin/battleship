package io.github.stefancostin.battleship.utils;

import java.io.IOException;

public class ClosedConnectionException extends IOException {
	
	public ClosedConnectionException() { }
	
	public ClosedConnectionException(String message) {
		super(message);
	}
	
}
