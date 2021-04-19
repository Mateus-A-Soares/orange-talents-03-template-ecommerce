package br.com.zupacademy.mateus.mercadolivre.compra.gateway;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;
import br.com.zupacademy.mateus.mercadolivre.compra.pagamento.Pagamento;

/**
 * Interface que declara os métodos que a classe que representa o retorno de um gateway deve implementar.
 * 
 * @author Mateus Soares
 */
public interface GatewayRetornoRequest {
	
	/**
	 * Deve informar o status da transação.
	 * 
	 * @return Status da resposta informando se a transação foi sucedida no gateway escolhido.
	 */
	boolean isSucedida();
	
	/**
	 * Deve transformar a transação específica em um registro de pagamento.
	 * 
	 * @param compra compra a qual o pagamento pertence.
	 * @return pagamento representando os dados a serem persistidos.
	 */
	Pagamento toModel(Compra compra);
}
