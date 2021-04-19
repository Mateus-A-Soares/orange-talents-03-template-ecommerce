package br.com.zupacademy.mateus.mercadolivre.compra.gateway.event;

import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayRetornoEvent;

/**
 * 
 * Observer que realiza o envio de email após o retorno do gateway de pagamento.
 * 
 * @author Mateus Soares
 */
@Component
public class SendMailAfterGatewayReturnEvent extends GatewayRetornoEvent {
	
	@Override
	protected void successful() {
		System.out.println("EMAIL ENVIADO QUANDO A COMPRA FOI REALIZADA COM SUCESSO");
	}

	@Override
	protected void fail() { 
		System.out.println("EMAIL ENVIADO QUANDO A COMPRA NÃO FOI REALIZADA COM SUCESSO");
	}
}
