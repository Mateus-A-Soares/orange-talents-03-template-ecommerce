package br.com.zupacademy.mateus.mercadolivre.compra.gateway.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayPaymentProcessorsList;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayRetornoEvent;
import br.com.zupacademy.mateus.mercadolivre.shared.email.MailSender;

/**
 * 
 * Observer que realiza o envio de email após o retorno do gateway de pagamento.
 * 
 * @author Mateus Soares
 */
@Component
public class SendMailAfterGatewayReturnEvent extends GatewayRetornoEvent {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private GatewayPaymentProcessorsList gatewayPaymentProcessors;
	
	@Override
	protected void successful() {
		mailSender.execute(compra.getUsuario().getLogin(), "Compra efetuada com sucesso", "O pagamento do produto " + compra.getProduto().getNome() + " acaba de ser efetuado com sucesso.");
	}

	@Override
	protected void fail() { 
		String url = compra.getGateway().processesPayment(compra, gatewayPaymentProcessors).toString();
		mailSender.execute(compra.getUsuario().getLogin(), "Pagamento falhou", "O pagamento do produto " + compra.getProduto().getNome() + " falhou. Tente novamente em: " + url);
	}
}
