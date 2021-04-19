package br.com.zupacademy.mateus.mercadolivre.compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.validation.BindException;

import br.com.zupacademy.mateus.mercadolivre.compra.gateway.Gateway;
import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints.IsValidGateway;
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
    
	@NotBlank
	@IsValidGateway
    private String gateway;
	
	/**
	 * Construtor que instância um objeto {@link CompraRequest} com os dados representativos de uma compra.
	 * 
	 * @param quantidade	quantidade de itens que serão comprados, deve ser um valor positivo;
	 * @param gateway		nome do gateway utilizado para o pagamento.
	 */
	public CompraRequest(@NotNull @Positive Integer quantidade, @NotNull String gateway) {
		this.quantidade = quantidade;
		this.gateway = gateway;
	}
	
	public Compra toModel(@NotNull Usuario usuario, @NotNull Produto produto, @NotBlank CompraStatus status) throws BindException {
		
		return new Compra(usuario, produto, quantidade, produto.getValor(), Gateway.valueOf(gateway.toUpperCase()), status);
	}
}
