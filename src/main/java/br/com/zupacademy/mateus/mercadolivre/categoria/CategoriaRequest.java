package br.com.zupacademy.mateus.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;
import br.com.zupacademy.mateus.mercadolivre.validation.constraints.ExistsOneCategoria;
import br.com.zupacademy.mateus.mercadolivre.validation.constraints.Unique;

/**
 * 
 * Classe modelo que representa os dados nas requests de cadastro de categorias
 * 
 * @author Mateus Soares
 */
public class CategoriaRequest {
	
	@NotBlank
	@Unique(entityClass = Categoria.class, fieldName = "nome", message = "Nome de categoria já cadastrado")
	public String nome;
	@ExistsOneCategoria
	@Positive
	public Long categoriaMaeId;
	
	/**
	 * Construtor que instância um objeto CategoriaRequest com os dados representativos da entidade Categoria.
	 * 
	 * @param nome nome da categoria, deve ser único e não pode estar vazio;
	 * @param categoriaMaeId id da categoria mãe, deve ser de uma categoria existente na base de dados.
	 */
	public CategoriaRequest(String nome, Long categoriaMaeId) {
		this.nome = nome;
		this.categoriaMaeId = categoriaMaeId;
	}

	public String getNome() {
		return nome;
	}

	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}
	
	/**
	 * Transforma o objeto UsuarioRequest em um objeto Usuario.
	 * 
	 * @param manager EntityManager utilizado para recuperar a categoria mãe da base de dados, caso o parâmetro categoriaMaeId não esteja vazio.
	 * @return objeto Categoria populado a partir dos dados desse objeto.
	 */
	public Categoria toModel(EntityManager manager) {
		if(categoriaMaeId == null)
			return new Categoria(nome);
		Categoria categoria = manager.find(Categoria.class, categoriaMaeId);
		return new Categoria(nome, categoria);
	}
}
