package io.egen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MetricException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MetricException() {
	}

	public MetricException(String message) {
		super(message);
	}

	public MetricException(String message, Throwable cause) {
		super(message, cause);
	}

}
