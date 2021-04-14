package br.com.zupacademy.mateus.mercadolivre.produto.imagem;

/**
 * 
 * Classe modelo que encapsula os dados a serem enviados sobre as imagens dos produtos pela API
 * 
 * @author Mateus Soares
 */
public class ImagemUrlResponse {
	
	private Long id;
	
	private Long produtoId;

	private String url;

	/**
	 * Instância o objeto e popula com os dados encapsulados na ImagemUrl recebida.
	 * 
	 * @param ImagemUrl url da imagem populada em um objeto {@link ImagemUrl}.
	 */
	public ImagemUrlResponse(ImagemUrl imagemUrl) {
		this.id = imagemUrl.getId();
		this.produtoId = imagemUrl.getProduto().getId();
		this.url = imagemUrl.getUrl();
	}

	public Long getId() {
		return id;
	}

	public Long getProdutoId() {
		return produtoId;
	}
 
	public String getUrl() {
		return url;
	}
}
