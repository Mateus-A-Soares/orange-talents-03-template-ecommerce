package br.com.zupacademy.mateus.mercadolivre.produto.pergunta;

import javax.validation.constraints.NotBlank;
import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
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

	public void setTitulo(@NotBlank String titulo) {
		this.titulo = titulo;
	}
	
	public Pergunta toModel( Usuario usuario, Produto produto) {
		return new Pergunta(titulo, usuario, produto);
	}
}
