package br.com.zupacademy.mateus.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.mateus.mercadolivre.produto.imagem.ImagemUrl;
import br.com.zupacademy.mateus.mercadolivre.produto.imagem.ImagemUrlResponse;
import br.com.zupacademy.mateus.mercadolivre.produto.opiniao.Opiniao;
import br.com.zupacademy.mateus.mercadolivre.produto.opiniao.OpiniaoResponse;
import br.com.zupacademy.mateus.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.mateus.mercadolivre.produto.pergunta.PerguntaResponse;

/**
 * 
 * Classe modelo que encapsula os dados a serem enviados sobre os dados de um produto pela API na rota de detalhamento de um produto
 * 
 * @author Mateus Soares
 */
public class ProdutoDetailsResponse {

	private String nome;
	private BigDecimal valor;
	private String descricao;
	private Long quantidade;
	private List<OpiniaoResponse> opinioes;
	private List<PerguntaResponse> perguntas;
	private List<ImagemUrlResponse> imagens;

	/**
	 * Instância o objeto e popula com os dados encapsulados no produto recebido.
	 * 
	 * @param produto produto encapsulando os dados do registro.
	 */
	public ProdutoDetailsResponse(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.descricao = produto.getDescricao();
		this.quantidade = produto.getQuantidade();
		this.opinioes = opiniaoListToDto(produto.getOpinioes());
		this.perguntas = perguntaListToDto(produto.getPerguntas());
		this.imagens = imagemUrlListToModel(produto.getImagens());
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public Long getQuantidade() {
		return quantidade;
	}

	public Double getMediaNotas() {
		Integer somaNotas = opinioes.stream().map(opiniao -> opiniao.getNota()).reduce(0, Integer::sum);
		return ((double) somaNotas)/getQuantidadeNotas();
	}

	public Integer getQuantidadeNotas() {
		
		return opinioes.size();
	}

	public List<OpiniaoResponse> getOpinioes() {
		return opinioes;
	}

	public List<PerguntaResponse> getPergunta() {
		return perguntas;
	}

	public List<ImagemUrlResponse> getImagens() {
		return imagens;
	}

	private static List<PerguntaResponse> perguntaListToDto(List<Pergunta> perguntas) {
		return perguntas.stream().map(perguntaModel -> new PerguntaResponse(perguntaModel))
				.collect(Collectors.toList());
	}

	private static List<OpiniaoResponse> opiniaoListToDto(List<Opiniao> opinioes) {
		return opinioes.stream().map(opiniaoModel -> new OpiniaoResponse(opiniaoModel)).collect(Collectors.toList());
	}

	private static List<ImagemUrlResponse> imagemUrlListToModel(List<ImagemUrl> imagens) {
		return imagens.stream().map(imagemUrlModel -> new ImagemUrlResponse(imagemUrlModel)).collect(Collectors.toList());
	}
}