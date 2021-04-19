package br.com.zupacademy.mateus.mercadolivre.compra.gateway;

import java.net.URI;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;

/**
 * 
 * Implementação do {@link GatewayPaymentProcessor} para {@link Gateway} PAGSEGURO nos profiles dev e default.
 * 
 * @author Mateus Soares
 */
@Component
@Profile({"dev", "default"})
public class PagseguroGatewayPaymenteProcessorDevProfile extends GatewayPaymentProcessor {
	
	public PagseguroGatewayPaymenteProcessorDevProfile() {
		super(Gateway.PAGSEGURO);
	}

	@Override
	public URI getReturnAddres(Compra compra, String redirectUrl) {

		return uriBuilder
                .scheme("https").host("www.fakepagsegurourl.com.br")
                .queryParam("returnId", compra.getId())
                .queryParam("redirectUrl", redirectUrl)
                .build().encode().toUri();
	}
}
