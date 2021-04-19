package br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.mateus.mercadolivre.shared.validation.validator.IsValidPagseguroStatusValidator;

/**
 * 
 *  Anotação utilizada para verificar a existência de um status da pagseguro relacionado ao valor da String anotada.
 * 
 * @author Mateus Soares
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsValidPagseguroStatusValidator.class)
public @interface IsValidPagseguroStatus {
	String message() default "Status de pagamento não permitido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
