package br.com.zupacademy.mateus.mercadolivre.produto.imagem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import br.com.zupacademy.mateus.mercadolivre.produto.Produto;

/**
 * 
 * Classe modelo para geração da tabela relaciona a url das imagens a um produto.
 * 
 * @author Mateus Soares
 */
@Entity
public class ImagemUrl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne(optional = false)
	private Produto produto;

	@URL
	@NotBlank
	@Column(nullable = false, unique = true)
	private String url;
	
	@Deprecated
	public ImagemUrl() {
	}

	/**
	 * Construtor que instancia um objeto {@link ImagemUrl}
	 * 
	 * @param produto produto ao qual a imagem pertence, obrigatório;
	 * @param url     url de acesso a imagem, deve estar formatado como url, ser
	 *                único e é obrigatório;
	 */
	public ImagemUrl(@NotNull Produto produto, @URL @NotBlank String url) {
		this.produto = produto;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public String getUrl() {
		return url;
	}
}
