package br.com.zupacademy.mateus.mercadolivre.compra.gateway;

import java.net.URI;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;

/**
 * 
 * Implementação do {@link GatewayPaymentProcessor} para {@link Gateway} PAYPAL nos profiles dev e default.
 * 
 * @author Mateus Soares
 */
@Component
@Profile({"dev", "default"})
public class PaypalGatewayPaymenteProcessorDevProfile extends GatewayPaymentProcessor {
	
	public PaypalGatewayPaymenteProcessorDevProfile() {
		super(Gateway.PAYPAL);
	}

	@Override
	public URI getReturnAddres(Compra compra, String redirectUrl) {

		return uriBuilder
		        .scheme("https").host("www.fakepaypalurl.com.br")
		        .queryParam("buyerId", compra.getId())
		        .queryParam("redirectUrl", redirectUrl)
		        .build().encode().toUri();
	}
}
