package br.com.zupacademy.mateus.mercadolivre.produto.caracteristica;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.mateus.mercadolivre.produto.Produto;

/**
 * 
 * Classe modelo que representa os dados para a entidade característica nas requests de cadastro de produtos
 * 
 * @author Mateus Soares
 */
public class CaracteristicaRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String valor;

	/**
	 * Construtor que instância um objeto {@link CaracteristicaRequest} com os dados representativos da entidade Característica.
	 * 
	 * @param nome nome da categoria, não pode estar vazio;
	 * @param valor valor da categoria, não pode estar vazio.
	 */
	public CaracteristicaRequest(@NotBlank String nome, @NotBlank String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	/**
	 * Transforma o objeto {@link CaracteristicaRequest} em um objeto {@link Caracteristica}.
	 * 
	 * @return objeto {@link Caracteristica} populado a partir dos dados desse objeto.
	 */
	public Caracteristica toModel(Produto produto) {
		return new Caracteristica(nome, valor, produto);
	}
}
