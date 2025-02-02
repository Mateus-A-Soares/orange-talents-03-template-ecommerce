package br.com.zupacademy.mateus.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import br.com.zupacademy.mateus.mercadolivre.categoria.Categoria;
import br.com.zupacademy.mateus.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.mateus.mercadolivre.produto.imagem.ImagemUrl;
import br.com.zupacademy.mateus.mercadolivre.produto.opiniao.Opiniao;
import br.com.zupacademy.mateus.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Classe modelo da entidade Produto.
 * 
 * @author Mateus Soares
 */
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@Positive
	@NotNull
	@Column(nullable = false)
	private BigDecimal valor;

	@PositiveOrZero
	@NotNull
	@Column(nullable = false)
	private Long quantidade;

	@Size(max = 1000)
	@NotBlank
	@Column(nullable = false)
	private String descricao;

	@NotNull
	@ManyToOne(optional = false)
	private Categoria categoria;

	@NotNull
	@ManyToOne(optional = false)
	private Usuario usuario;

	@NotNull
	@Size(min = 3)
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Caracteristica> caracteristicas;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<ImagemUrl> imagens;

	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<Opiniao> opinioes;

	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<Pergunta> perguntas;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime instanteCadastro;

	@Deprecated
	public Produto() {
	}

	/**
	 * Construtor que instancia um objeto {@link Produto}.
	 * 
	 * @param nome       nome do produto, obrigatório;
	 * @param valor      valor do produto, obrigatório e positivo;
	 * @param quantidade quantidade do produto em estoque, obrigatório e não pode
	 *                   ser negativa;
	 * @param descricao  descrição do produto, obrigatória e até 1000 caracteres;
	 * @param categoria  categoria do produto, obrigatória;
	 */
	public Produto(@NotBlank String nome, @Positive @NotNull BigDecimal valor, @PositiveOrZero @NotNull Long quantidade,
			@Size(max = 1000) @NotBlank String descricao, @NotNull Categoria categoria, @NotNull Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public List<ImagemUrl> getImagens() {
		return imagens;
	}

	public List<Opiniao> getOpinioes() {
		return opinioes;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
		Assert.isTrue(this.caracteristicas.size() >= 3, "É necessário ao menos três características para o cadastro");
	}

	public void linkImages(List<String> urls) {
		this.imagens
				.addAll(urls.stream().map(urlString -> new ImagemUrl(this, urlString)).collect(Collectors.toList()));
	}

	public boolean removeFromQuantidade(Integer quantidadeComprada) {
		if (quantidadeComprada <= quantidade) {
			this.quantidade -= quantidadeComprada;
			return true;
		}
		return false;
	}
}
