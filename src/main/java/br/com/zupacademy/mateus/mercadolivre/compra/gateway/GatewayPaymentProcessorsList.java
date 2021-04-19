package br.com.zupacademy.mateus.mercadolivre.compra.gateway;
 
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 
 * Gerencia os objetos que estendem {@link GatewayPaymentProcessor}.
 * 
 * @author Mateus Soares
 */
@Component
public class GatewayPaymentProcessorsList {

	@Autowired
	private Collection<GatewayPaymentProcessor> paymenteProcessors;
	
	/**
	 * Procura pelo primeiro {@link GatewayPaymentProcessor} que processe o gateway passado.
	 * 
	 * @param gateway gateway usado na procura por um {@link GatewayPaymentProcessor} que possa gerar uma URI para ele;
	 * @return o primeiro {@link GatewayPaymentProcessor} que consiga processar o gateway passado.
	 */
	public GatewayPaymentProcessor getGatewayPaymentProcessor(Gateway gateway) {
		for (GatewayPaymentProcessor processor : paymenteProcessors) {
			if (processor.isGateway(gateway))
				return processor;
		}
		Assert.isTrue(false, "Deveria existir gateway de pagamento viável");
		return null;
	}
}
