package br.com.zupacademy.mateus.mercadolivre.produto.opiniao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Classe modelo da entidade Opinião.
 * 
 * @author Mateus Soares
 */
@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @Min(1) @Max(5)
	private Integer nota;
	
	@NotBlank
	@Column(nullable = false)
	private String titulo;
	
	@NotBlank @Size(max= 500)
	@Column(nullable = false)
	private String descricao;
	
	@NotNull
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	@NotNull
	@ManyToOne(optional = false)
	private Produto produto;

	/**
	 * Construtor que instancia um objeto {@link Opiniao}
	 * 
	 * @param nota		nota dada por um usuário ao produto, obrigatória e valor entre um e cinco;
	 * @param titulo	título descritivo da opinião, obrigatório;
	 * @param descricao	descrição detalhada sobre a opinião, obrigatória e de no máximo 500 caracteres;
	 * @param usuario	usuário que cadastrou a opinião, obrigatório;
	 * @param produto	produto a qual a opinião pertence, obrigatório.
	 */
	public Opiniao(@Min(1) @Max(5) Integer nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao,
			@NotNull Usuario usuario, @NotNull Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	@Deprecated
	public Opiniao() {
	}

	public Long getId() {
		return id;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
}
