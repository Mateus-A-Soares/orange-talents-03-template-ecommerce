package br.com.zupacademy.mateus.mercadolivre.compra.gateway.pagseguro;

import java.net.URI;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.Gateway;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayPaymentProcessor;

/**
 * 
 * Implementação do {@link GatewayPaymentProcessor} para {@link Gateway} PAGSEGURO nos profiles prod e test.
 * 
 * @author Mateus Soares
 */
@Component
@Profile({"prod", "test"})
public class PagseguroGatewayPaymenteProcessor extends GatewayPaymentProcessor {
	
	public PagseguroGatewayPaymenteProcessor() {
		super(Gateway.PAGSEGURO);
	}

	@Override
	public URI getReturnAddres(Compra compra, String redirectUrl) {

		return uriBuilder
                .scheme("https").host(gatewayType.getBaseUrl())
                .queryParam("returnId", compra.getId())
                .queryParam("redirectUrl", redirectUrl)
                .build().encode().toUri();
	}
}
