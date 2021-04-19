package br.com.zupacademy.mateus.mercadolivre.compra.gateway.event;

import org.springframework.stereotype.Component;

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
		System.out.println("ACESSO AO SISTEMA DE NOTA FISCAL QUANDO A COMPRA FOI REALIZADA COM SUCESSO");
	}
}
