package br.com.zupacademy.mateus.mercadolivre.shared.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.mateus.mercadolivre.compra.gateway.pagseguro.PagseguroRetornoStatus;
import br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints.IsValidPagseguroStatus;

/**
 * 
 * Implementação do validator que executa a validação da existência de um status da pagseguro ao valor da String anotada.
 * 
 * @author Mateus Soares
 */
public class IsValidPagseguroStatusValidator implements ConstraintValidator<IsValidPagseguroStatus, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		for (PagseguroRetornoStatus status  : PagseguroRetornoStatus.values()) {
			if(value.equalsIgnoreCase(status.name()))
				return true;
		}
		return false;
	}

}
