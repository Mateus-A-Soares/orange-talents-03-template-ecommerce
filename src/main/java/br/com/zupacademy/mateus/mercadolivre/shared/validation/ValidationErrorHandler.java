package br.com.zupacademy.mateus.mercadolivre.shared.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mateus.mercadolivre.shared.validation.dto.DefaultErrorMessageDto;
import br.com.zupacademy.mateus.mercadolivre.shared.validation.dto.FormErrorListMessageDto;
import br.com.zupacademy.mateus.mercadolivre.shared.validation.dto.FormErrorMessageDto;

/**
 * Handler que captura exceptions lançadas durante a execução dos end-points e modifica a resposta.
 * 
 * @author Mateus Soares
 */
@RestControllerAdvice
public class ValidationErrorHandler {
	
	@InitBinder
    private void activateDirectFieldAccess(DataBinder dataBinder) {
        dataBinder.initDirectFieldAccess();
    }
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<DefaultErrorMessageDto> handle(ResponseStatusException exception, WebRequest request) {
		String errorMessage = exception.getLocalizedMessage();
		String path = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		DefaultErrorMessageDto message = new DefaultErrorMessageDto(exception.getStatus(), errorMessage, path);
		return ResponseEntity.status(exception.getStatus()).body(message);
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AuthenticationException.class)
	public DefaultErrorMessageDto handle(AuthenticationException exception, WebRequest request) {
		String errorMessage = exception.getLocalizedMessage();
		String path = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		DefaultErrorMessageDto message = new DefaultErrorMessageDto(HttpStatus.BAD_REQUEST, errorMessage, path);
		return message;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public FormErrorListMessageDto handle(BindingResult br, WebRequest request) {
		List<FormErrorMessageDto> errors = new ArrayList<>();
		List<FieldError> bindingResultFieldErrors = br.getFieldErrors();
		bindingResultFieldErrors.forEach(error -> {
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			errors.add(new FormErrorMessageDto(error.getField(), message));
		});
		String path = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		FormErrorListMessageDto message = new FormErrorListMessageDto(HttpStatus.BAD_REQUEST, path, errors);
		return message;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public DefaultErrorMessageDto  handle(Exception exception, WebRequest request) {
		String errorMessage = messageSource.getMessage("InternalServerError", null, "500", null);
		String path = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
		DefaultErrorMessageDto message = new DefaultErrorMessageDto(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, path);
		return message;
	}
}
