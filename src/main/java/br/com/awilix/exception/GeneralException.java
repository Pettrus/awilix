package br.com.awilix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.I_AM_A_TEAPOT)
public class GeneralException extends RuntimeException {
	
	private static final long serialVersionUID = -3393616274076168676L;

	public GeneralException(String mensagem) {
		super(mensagem);
	}
}
