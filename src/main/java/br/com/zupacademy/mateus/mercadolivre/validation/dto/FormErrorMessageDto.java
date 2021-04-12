package br.com.zupacademy.mateus.mercadolivre.validation.dto;

public class FormErrorMessageDto {
	
	private String field;
	private String message;
	
	public FormErrorMessageDto(String field, String message) {
		this.field = field;
		this.message = message;
	}
	
	public String getField() {
		return field;
	}
	public String getMessage() {
		return message;
	}
}
