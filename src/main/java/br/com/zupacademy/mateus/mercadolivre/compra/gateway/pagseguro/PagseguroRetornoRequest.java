package br.com.zupacademy.mateus.mercadolivre.compra.gateway.pagseguro;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayRetornoRequest;
import br.com.zupacademy.mateus.mercadolivre.compra.pagamento.Pagamento;
import br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints.IsValidPagseguroStatus;

/**
 * 
 * Classe modelo que representa os dados enviados pelo gateway da Pagseguro após a tentativa de um pagamento para uma compra.
 * 
 * @author Mateus Soares
 */
public class PagseguroRetornoRequest implements GatewayRetornoRequest {
	
	@NotBlank
	private String id;
	@NotBlank
	@IsValidPagseguroStatus
	private String status;
	
	/**
	 * Construtor que instância um objeto {@link PagseguroRetornoRequest},
	 * representando os dados enviados pelo gateway da Pagseguro após a tentativa de um pagamento para uma compra.
	 * 
	 * @param id		id da transação efetuada;
	 * @param status	status que informa se a transação foi sucedida.
	 */
	public PagseguroRetornoRequest(@NotBlank String id, @NotBlank String status) {
		this.id = id;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	
	public PagseguroRetornoStatus getStatus() {
		return PagseguroRetornoStatus.valueOf(status.toUpperCase());
	}
	
	@Override
	public boolean isSucedida() {
		
		return status.equalsIgnoreCase(PagseguroRetornoStatus.SUCESSO.name());
	}

	@Override
	public Pagamento toModel(Compra compra) {

		return new Pagamento(id, compra, isSucedida());
	}
}
