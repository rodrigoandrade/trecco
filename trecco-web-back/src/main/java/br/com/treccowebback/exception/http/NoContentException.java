package br.com.treccowebback.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author rodrigo.andrade
 *
 */

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NoContentException extends Exception {

	private static final long serialVersionUID = 8725313819338561908L;

	public NoContentException(String message) {
		super(message);
	}

	public NoContentException(Throwable cause) {
		super(cause);
	}

	public NoContentException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoContentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
