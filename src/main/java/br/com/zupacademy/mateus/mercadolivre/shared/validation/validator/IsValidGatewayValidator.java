package br.com.zupacademy.mateus.mercadolivre.shared.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.mateus.mercadolivre.compra.gateway.Gateway;
import br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints.IsValidGateway;

/**
 * 
 * Implementação do validator que executa a validação da existência de um gateway relacionado ao valor da String anotada.
 * 
 * @author Mateus Soares
 */
public class IsValidGatewayValidator implements ConstraintValidator<IsValidGateway, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		for (Gateway gatewayType  : Gateway.values()) {
			if(value.equalsIgnoreCase(gatewayType.name()))
				return true;
		}
		return false;
	}

}
