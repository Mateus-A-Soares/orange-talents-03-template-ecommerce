package br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.mateus.mercadolivre.shared.validation.validator.ExistsOneCategoriaValidator;

/**
 *  Anotação utilizada para verificar a existência da categoria relacionada ao valor do parâmetro anotado.
 * 
 * @author Mateus Soares
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsOneCategoriaValidator.class)
public @interface ExistsOneCategoria {
	String message() default "Id inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
