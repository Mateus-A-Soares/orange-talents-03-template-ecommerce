package br.com.zupacademy.mateus.mercadolivre.compra.gateway.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayRetornoEvent;
import br.com.zupacademy.mateus.mercadolivre.feignclient.NotaFiscalClient;

/**
 * 
 * Observer que realiza o acesso ao sistema de geração de nota fiscal após o retorno do gateway de pagamento.
 * 
 * @author Mateus Soares
 */
@Component
public class AccessNotaFiscalSystemAfterGatewayReturnEvent extends GatewayRetornoEvent {
	
	@Autowired
	private NotaFiscalClient notaFiscalClient;

	@Override
	protected void successful() {
		
		notaFiscalClient.notasFiscais(compra.getId(), compra.getUsuario().getId());
	}
}
