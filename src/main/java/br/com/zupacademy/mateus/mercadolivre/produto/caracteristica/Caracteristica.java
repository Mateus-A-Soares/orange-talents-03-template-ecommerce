package br.com.zupacademy.mateus.mercadolivre.produto.caracteristica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.mateus.mercadolivre.produto.Produto;

/**
 * 
 * Classe modelo da entidade Característica.
 * 
 * @author Mateus Soares
 */
@Entity
public class Caracteristica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false)
	private String valor;
	
	@NotNull
	@ManyToOne(optional = false)
	private Produto produto;

	@Deprecated
	public Caracteristica() {}
	
	/**
	 * Construtor que instancia um objeto {@link Caracteristica}
	 * 
	 * @param nome	nome da característica, obrigatório;
	 * @param valor	valor da característica, obrigatório.
	 */
	public Caracteristica(@NotBlank String nome, @NotBlank String valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getValor() {
		return valor;
	}
	
	public Produto getProduto() {
		return produto;
	}
}
