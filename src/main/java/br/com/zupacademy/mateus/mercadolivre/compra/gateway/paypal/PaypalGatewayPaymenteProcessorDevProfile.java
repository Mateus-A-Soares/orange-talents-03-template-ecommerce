package br.com.zupacademy.mateus.mercadolivre.compra.gateway.paypal;

import java.net.URI;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.Gateway;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayPaymentProcessor;

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
	public URI getReturnAddres(Compra compra) {

		return UriComponentsBuilder.newInstance()
		        .scheme("https").host("www.fakepaypalurl.com.br")
		        .queryParam("buyerId", compra.getId())
		        .queryParam("redirectUrl", gatewayType.getRedirectUrl())
		        .build().encode().toUri();
	}
}
