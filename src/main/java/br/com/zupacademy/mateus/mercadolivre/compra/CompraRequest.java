package br.com.zupacademy.mateus.mercadolivre.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import br.com.zupacademy.mateus.mercadolivre.compra.gateway.Gateway;
import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Classe modelo que representa os dados de uma compra nas requests de compra de produtos
 * 
 * @author Mateus Soares
 */
public class CompraRequest {
	
	@NotNull @Positive
    private Integer quantidade;
    
	@NotNull
    private Gateway gateway;
	
	/**
	 * Construtor que instância um objeto {@link CompraRequest} com os dados representativos de uma compra.
	 * 
	 * @param quantidade	quantidade de itens que serão comprados, deve ser um valor positivo;
	 * @param gateway		gateway utilizado para o pagamento.
	 */
	public CompraRequest(@NotNull @Positive Integer quantidade, @NotNull Gateway gateway) {
		this.quantidade = quantidade;
		this.gateway = gateway;
	}
	
	public Compra toModel(@NotNull Usuario usuario, @NotNull Produto produto, @NotNull CompraStatus status) throws BindException {
		if(produto.getQuantidade() < quantidade) {
			BindException exception = new BindException(this, "compra");
			exception.addError(new FieldError("compra", "quantidade", "Quantidade maior que o estoque"));
			throw exception;
		}
		return new Compra(usuario, produto, quantidade, produto.getValor(), gateway, status);
	}
}
