package br.com.zupacademy.mateus.mercadolivre.produto.opiniao;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Classe modelo que representa as imagens enviadas nas requests de cadastro de opiniões para os produtos.
 * 
 * @author Mateus Soares
 */
public class OpiniaoRequest {

	@NotNull @Min(1) @Max(5)
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank @Size(max= 500)
	private String descricao;
	
	@NotNull
	private Long usuarioId;
	
	@NotNull
	private Long produtoId;

	/**
	 * Construtor que instância um objeto {@link OpiniaoRequest}
	 * 
	 * @param nota		nota dada por um usuário ao produto, obrigatória e valor entre um e cinco;
	 * @param titulo	título descritivo da opinião, obrigatório;
	 * @param descricao	descrição detalhada sobre a opinião, obrigatória e de no máximo 500 caracteres;
	 * @param usuario	usuário que cadastrou a opinião, obrigatório;
	 * @param produto	produto a qual a opinião pertence, obrigatório.
	 */
	public OpiniaoRequest(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Long usuarioId, @NotNull Long produtoId) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuarioId = usuarioId;
		this.produtoId = produtoId;
	}

	public Opiniao toModel(EntityManager manager) {
		Usuario usuario = manager.find(Usuario.class, manager);
		Produto produto = manager.find(Produto.class, manager);
		return new Opiniao(nota, titulo, descricao, usuario, produto);
	}
}