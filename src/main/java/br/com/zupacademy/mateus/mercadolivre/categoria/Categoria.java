package br.com.zupacademy.mateus.mercadolivre.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

/**
 * 
 * Classe modelo da entidade Categoria.
 * 
 * @author Mateus Soares
 */
@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String nome;
	
	@ManyToOne(optional = true)
	private Categoria categoriaMae;

	/**
	 * Construtor que instância um objeto Categoria
	 * 
	 * @param nome nome da categoria, único e obrigatório;
	 * @param categoriaMae, categoria mãe registrada no banco de dados (opcional).
	 */
	public Categoria(@NotBlank String nome, Categoria categoriaMae) {
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}
	
	/**
	 * Construtor que instância um objeto Categoria
	 * 
	 * @param nome nome da categoria, único e obrigatório;
	 */
	public Categoria(@NotBlank String nome) {
		this.nome = nome;
	}
	
	@Deprecated
	public Categoria() {
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}
}
