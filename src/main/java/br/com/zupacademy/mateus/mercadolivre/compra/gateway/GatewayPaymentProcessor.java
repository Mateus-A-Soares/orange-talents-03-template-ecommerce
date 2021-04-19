package br.com.zupacademy.mateus.mercadolivre.compra.gateway;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;

/**
 * 
 * Classe abstrata que define como funcionará a geração de URI para cada gateway.
 * Deve ser estendida pelos geradores de URI de acesso a um gateway.
 * 
 * @author Mateus Soares
 */
public abstract class GatewayPaymentProcessor { 
	
	protected UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

	protected final Gateway gatewayType;

	public GatewayPaymentProcessor(Gateway gatewayType) {
		this.gatewayType = gatewayType;
	}

	public boolean isGateway(Gateway gateway) {
		if (gateway == null)
			return false;
		return gatewayType.equals(gateway);
	}

	/**
	 * Deve gerar uma URI para acesso ao gateway, passando os dados necessários da compra e indicando a rota de volta passada.
	 * 
	 * @param compra		compra com o identificador utilizado pelo gateway, que deve ser passado na URI;
	 * @param redirectUri	rota de retorno do gateway, deve ser passado na URI;
	 * @return {@link URI} de acesso ao gateway.
	 */
	public abstract URI getReturnAddres(Compra compra, String redirectUri);
}
