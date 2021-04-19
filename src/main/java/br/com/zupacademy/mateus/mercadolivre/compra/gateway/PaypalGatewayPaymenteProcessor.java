package br.com.zupacademy.mateus.mercadolivre.compra.gateway;

import java.net.URI;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;

/**
 * 
 * Implementação do {@link GatewayPaymentProcessor} para {@link Gateway} PAYPAL nos profiles de prod e test.
 * 
 * @author Mateus Soares
 */
@Component
@Profile({ "prod", "test" })
public class PaypalGatewayPaymenteProcessor extends GatewayPaymentProcessor {
	public PaypalGatewayPaymenteProcessor() {
		super(Gateway.PAYPAL);
	}

	@Override
	public URI getReturnAddres(Compra compra, String redirectUrl) {

		return uriBuilder.scheme("https").host(gatewayType.getBaseUrl()).queryParam("buyerId", compra.getId())
				.queryParam("redirectUrl", redirectUrl).build().encode().toUri();
	}
}
