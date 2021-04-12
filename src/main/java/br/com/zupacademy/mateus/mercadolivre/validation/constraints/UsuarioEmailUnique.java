package br.com.zupacademy.mateus.mercadolivre.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


import br.com.zupacademy.mateus.mercadolivre.validation.validator.UsuarioEmailUniqueValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsuarioEmailUniqueValidator.class)
public @interface UsuarioEmailUnique {
	String message() default "Email já cadastrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
