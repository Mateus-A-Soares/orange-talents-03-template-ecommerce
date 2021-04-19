package br.com.zupacademy.mateus.mercadolivre.compra.gateway.paypal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayRetornoRequest;
import br.com.zupacademy.mateus.mercadolivre.compra.pagamento.Pagamento;
import br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints.Unique;

/**
 * 
 * Classe modelo que representa os dados enviados pelo gateway da Paypal após a tentativa de um pagamento para uma compra.
 * 
 * @author Mateus Soares
 */
public class PaypalRetornoRequest implements GatewayRetornoRequest {
	
	@NotBlank
	@Unique(entityClass = Pagamento.class, fieldName = "idTransacao")
	private String id;
	@NotNull
	private Boolean status;
	
	/**
	 * Construtor que instância um objeto {@link PaypalRetornoRequest},
	 * representando os dados enviados pelo gateway da Paypal após a tentativa de um pagamento para uma compra.
	 * 
	 * @param id		id da transação efetuada;
	 * @param status	status que informa se a transação foi sucedida.
	 */
	public PaypalRetornoRequest(@NotBlank String id, @NotNull Boolean status) {
		this.id = id;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	@Override
	public boolean isSucedida() {
		
		return getStatus();
	}

	@Override
	public Pagamento toModel(Compra compra) {

		return new Pagamento(id, compra, isSucedida());
	}
}
