package br.com.zupacademy.mateus.mercadolivre.produto.opiniao;

import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;


/**
 * 
 * Classe modelo que encapsula os dados a serem enviados sobre as opiniões de um produto pela API na rota de detalhamento de um produto
 * 
 * @author Mateus Soares
 */
public class OpiniaoResponse {

	private Long id;
	private Integer nota;
	private String descricao;
	private String titulo;
	private Usuario usuario;

	public OpiniaoResponse(Opiniao opiniao) {
		id = opiniao.getId();
		nota = opiniao.getNota();
		descricao = opiniao.getDescricao();
		titulo = opiniao.getTitulo();
		usuario = opiniao.getUsuario();
	}

	public Long getId() {
		return id;
	}

	public Integer getNota() {
		return nota;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getUsuarioLogin() {
		return usuario.getLogin();
	}
}