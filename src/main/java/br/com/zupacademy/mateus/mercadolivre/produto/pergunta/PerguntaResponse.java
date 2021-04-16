package br.com.zupacademy.mateus.mercadolivre.produto.pergunta;

import java.time.LocalDateTime;

import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Classe modelo que encapsula os dados a serem enviados sobre as perguntas de um produto pela API na rota de detalhamento de um produto
 * 
 * @author Mateus Soares
 */
public class PerguntaResponse {

	private Long id;
	private LocalDateTime instanteCriacao;
	private String titulo;
	private Usuario usuario;

	public PerguntaResponse(Pergunta pergunta) {
		id = pergunta.getId();
		instanteCriacao = pergunta.getInstanteCriacao();
		titulo = pergunta.getTitulo();
		usuario = pergunta.getUsuario();
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getInstanteCriacao() {
		return instanteCriacao;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getUsuarioLogin() {
		return usuario.getLogin();
	}
}
