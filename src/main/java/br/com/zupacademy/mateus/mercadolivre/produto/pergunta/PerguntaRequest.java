package br.com.zupacademy.mateus.mercadolivre.produto.pergunta;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints.ExistsOne;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Classe modelo que representa as imagens enviadas nas requests de cadastro de perguntas para os produtos.
 * 
 * @author Mateus Soares
 */
public class PerguntaRequest {
	
	@NotBlank
	private String titulo;

	@NotNull
	@ExistsOne(entityTargetClass = Produto.class, fieldTargetName = "id")
	private Long produtoId;

	/**
	 * Construtor que instância um objeto {@link PerguntaRequest}
	 * 
	 * @param titulo	título da pergunta, obrigatório;
	 * @param produtoId	id do produto a qual a pergunta pertence, obrigatório e existente.
	 */
	public PerguntaRequest(@NotBlank String titulo, @NotNull Long produtoId) {
		this.titulo = titulo;
		this.produtoId = produtoId;
	}
	
	public Pergunta toModel(EntityManager manager, Usuario usuario) {
		Produto produto = manager.find(Produto.class, produtoId);
		return new Pergunta(titulo, usuario, produto);
	}
}
