package br.com.zupacademy.mateus.mercadolivre.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

public class DefaultErrorMessageDto {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			
	private LocalDateTime timestamp;
	private Integer status;
	private String message;
	private String path;
	private String error;
	
	public DefaultErrorMessageDto(HttpStatus httpStatus, String message, String path) {
		this.status = httpStatus.value();
		this.message = httpStatus.name();
		this.path = path;
		this.error = message;
		timestamp = LocalDateTime.now();
	}

	public String getTimestamp() {
		return timestamp.format(formatter);
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public String getError() {
		return error;
	}
}
