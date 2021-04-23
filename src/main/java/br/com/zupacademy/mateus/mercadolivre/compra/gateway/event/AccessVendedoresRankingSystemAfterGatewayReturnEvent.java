package br.com.zupacademy.mateus.mercadolivre.compra.gateway.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayRetornoEvent;
import br.com.zupacademy.mateus.mercadolivre.feignclient.RankingVendedoresClient;

/**
 * 
 * Observer que realiza o acesso ao sistema de rankings após o retorno do gateway de pagamento
 * 
 * @author Mateus Soares
 */
@Component
public class AccessVendedoresRankingSystemAfterGatewayReturnEvent extends GatewayRetornoEvent {

	@Autowired
	private RankingVendedoresClient RankingVendedoresClient;

	@Override
	protected void successful() {
		
		RankingVendedoresClient.rankingVendedores(compra.getId(), compra.getProduto().getUsuario().getId());
	}	
}