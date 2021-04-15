package br.com.zupacademy.mateus.mercadolivre.produto.pergunta;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints.ExistsOne;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

public class PerguntaRequest {
	
	@NotBlank
	private String titulo;

	@NotNull
	private Usuario usuario;

	@NotNull
	@ExistsOne(entityTargetClass = Produto.class, fieldTargetName = "id")
	private Long produtoId;

	public PerguntaRequest(@NotBlank String titulo, @NotNull Long produtoId) {
		this.titulo = titulo;
		this.produtoId = produtoId;
	}
	
	public Pergunta toModel(EntityManager manager, Usuario usuario) {
		Produto produto = manager.find(Produto.class, produtoId);
		return new Pergunta(titulo, usuario, produto);
	}
}
