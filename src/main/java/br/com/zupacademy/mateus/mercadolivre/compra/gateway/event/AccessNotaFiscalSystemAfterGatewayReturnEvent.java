package br.com.zupacademy.mateus.mercadolivre.compra.gateway.event;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayRetornoEvent;

/**
 * 
 * Observer que realiza o acesso ao sistema de geração de nota fiscal após o retorno do gateway de pagamento.
 * 
 * @author Mateus Soares
 */
@Component
public class AccessNotaFiscalSystemAfterGatewayReturnEvent extends GatewayRetornoEvent {

	@Override
	protected void successful() {
		RestTemplate restTemplate = new RestTemplate();
		String uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/fake/notasfiscais")
        .queryParam("compraId", compra.getId())
        .queryParam("usuarioId", compra.getUsuario().getId())
        .toUriString();
		restTemplate.getForEntity(uri, String.class);
	}
}
