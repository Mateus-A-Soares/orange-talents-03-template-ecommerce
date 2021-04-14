package br.com.zupacademy.mateus.mercadolivre.shared.validation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;

public class FormErrorListMessageDto {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	private LocalDateTime timestamp;
	private Integer status;
	private String path;
	private List<FormErrorMessageDto> errors;
	
	public FormErrorListMessageDto(HttpStatus status, String path, List<FormErrorMessageDto> errors) {
		this.status = status.value();
		this.path = path;
		this.errors = errors;
		timestamp = LocalDateTime.now();
	}

	public static DateTimeFormatter getFormatter() {
		return formatter;
	}

	public String getTimestamp() {
		return timestamp.format(formatter);
	}

	public Integer getStatus() {
		return status;
	}
	
	public String getPath() {
		return path;
	}

	public List<FormErrorMessageDto> getErrors() {
		return errors;
	}
}
