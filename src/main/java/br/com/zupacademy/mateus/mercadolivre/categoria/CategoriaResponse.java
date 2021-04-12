package br.com.zupacademy.mateus.mercadolivre.categoria;

/**
 * 
 * Classe modelo que encapsula os dados a serem enviados sobre categorias pela API
 * 
 * @author Mateus Soares
 */
public class CategoriaResponse {

	private Long id;
	
	private Long categoriaMaeId;

	private String nome;

	/**
	 * Instância o objeto e popula com os dados encapsulados na categoria recebida.
	 * 
	 * @param categoria categoria encapsulando os dados do registro.
	 */
	public CategoriaResponse(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		Categoria categoriaMae = categoria.getCategoriaMae();
		if(categoriaMae != null)
			this.categoriaMaeId = categoria.getCategoriaMae().getId();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}
}
