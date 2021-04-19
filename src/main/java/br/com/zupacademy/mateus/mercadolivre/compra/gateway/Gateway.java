package br.com.zupacademy.mateus.mercadolivre.compra.gateway;

import java.net.URI;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;

/**
 * Enum representativa do gateway utilizado para realizar a compra.
 * 
 * @author MATEUS SOARES
 */
public enum Gateway {

	PAYPAL("www.paypal.com"), PAGSEGURO("www.pagseguro.com");

	private String baseUrl;

	private Gateway(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * Procura pelo primeiro processador de gateway relativo ao valor definido por esse enum e executa o procedimento de compra no gateway utilizado.
	 * 
	 * @param compra compra com o identificador que vai ser passado para o gateway;
	 * @param gatewayPaymenteProcessors objeto que gerencia a lista de processadores de gateway ativos;
	 * @param redirectUrl url passada como parâmetro no procedimento de compra, que será chamada no retorno.
	 * @return URI de acesso ao gateway de pagamento.
	 */
	public URI processesPayment(Compra compra, GatewayPaymentProcessorsList gatewayPaymenteProcessors,
			String redirectUrl) {
		return gatewayPaymenteProcessors
				.getGatewayPaymentProcessor(this)
				.getReturnAddres(compra, redirectUrl);
	}
}
