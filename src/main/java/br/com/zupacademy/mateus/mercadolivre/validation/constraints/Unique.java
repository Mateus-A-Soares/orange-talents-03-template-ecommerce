package br.com.zupacademy.mateus.mercadolivre.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.mateus.mercadolivre.validation.UniqueValidator;

/**
 *  Anotação utilizada nas classes que representam entidades, nos parâmetros em que deve ocorrer validação de unicidade 
 *  
 * @author Mateus Soares
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface Unique {
	
	String fieldName();
	Class<?> entityClass();
    String message() default "Valor já cadastrado para este campo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}