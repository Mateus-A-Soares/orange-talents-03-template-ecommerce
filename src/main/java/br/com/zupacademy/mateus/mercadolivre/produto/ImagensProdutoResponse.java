package br.com.zupacademy.mateus.mercadolivre.produto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.mateus.mercadolivre.produto.imagem.ImagemUrl;
import br.com.zupacademy.mateus.mercadolivre.produto.imagem.ImagemUrlResponse;

/**
 * 
 * Classe modelo que encapsula os dados a serem enviados sobre as imagens de um produto pela API na rota de cadastro de imagens
 * 
 * @author Mateus Soares
 */
public class ImagensProdutoResponse {
	
	private Long id;

	private String nome;
	
	private List<ImagemUrlResponse> imagens;

	/**
	 * Instância o objeto e popula com os dados encapsulados no produto recebido.
	 * 
	 * @param produto produto encapsulando os dados do registro.
	 */
	public ImagensProdutoResponse(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.imagens = imagemUrlListToModel(produto.getImagens());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public List<ImagemUrlResponse> getImagens() {
		return imagens;
	}
	
	/**
	 * Transforma uma lista de {@link ImagemUrl} em uma de {@link ImagemUrlResponse} para serialização da resposta.
	 * 
	 * @param list lista de urls de imagem encapsuladas em um {@link ImagemUrl}
	 * @return lista de {@link ImagemUrlResponse} com os valores das urls da imagem do produto.
	 */
	private static List<ImagemUrlResponse> imagemUrlListToModel(List<ImagemUrl> list){
		return list.stream().map(imagemUrl -> new ImagemUrlResponse(imagemUrl)).collect(Collectors.toList());
	}
}
