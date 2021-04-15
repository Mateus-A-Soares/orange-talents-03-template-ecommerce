package br.com.zupacademy.mateus.mercadolivre.produto.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Classe modelo da entidade Pergunta.
 * 
 * @author Mateus Soares
 */
@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String titulo;
	
	@CreationTimestamp
	private LocalDateTime instanteCriacao;

	@NotNull
	@ManyToOne(optional = false)
	private Usuario usuario;

	@NotNull
	@ManyToOne(optional = false)
	private Produto produto;
	
	@Deprecated
	public Pergunta() {
	}

	/**
	 * Construtor que instancia um objeto {@link Pergunta}
	 * 
	 * @param titulo	título da pergunta, obrigatório;
	 * @param usuario	usuário que fez a pergunta, obrigatório;
	 * @param produto	produto a qual a pergunta pertence, obrigatório.
	 */
	public Pergunta(@NotBlank String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getInstanteCriacao() {
		return instanteCriacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
}
