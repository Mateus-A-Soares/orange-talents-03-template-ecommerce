package br.com.zupacademy.mateus.mercadolivre.compra.gateway.pagseguro;

import java.net.URI;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.Gateway;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayPaymentProcessor;

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
	public URI getReturnAddres(Compra compra) {

		return UriComponentsBuilder.newInstance()
                .scheme("https").host("www.fakepagsegurourl.com.br")
                .queryParam("returnId", compra.getId())
                .queryParam("redirectUrl", gatewayType.getRedirectUrl())
                .build().encode().toUri();
	}
}
