package br.com.zupacademy.mateus.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.mateus.mercadolivre.categoria.Categoria;
import br.com.zupacademy.mateus.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.mateus.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints.ExistsOneCategoria;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Classe modelo que representa os dados nas requests de cadastro de produtos
 * 
 * @author Mateus Soares
 */
public class ProdutoRequest {
	
	@NotBlank
	private String nome;
	
	@Positive @NotNull
	private BigDecimal valor;
	
	@PositiveOrZero @NotNull
	private Long quantidade;
	
	@Size(max = 1000) @NotBlank
	private String descricao;
	
	@NotNull
	@ExistsOneCategoria
	private Long categoriaId;
	
	@NotNull
	@Size(min = 3)
	@Valid
	private List<CaracteristicaRequest> caracteristicas;

	/**
	 * Construtor que instância um objeto {@link ProdutoRequest} com os dados representativos da entidade Produto.
	 * 
	 * @param nome nome do produto, não pode esta vazio;
	 * @param valor valor do produto, deve ser um valo positivo;
	 * @param quantidade quantidade do produto em estoque, não pode ser negativo;
	 * @param descricao descrição do produto, obrigatório e até no máximo 1000 caracteres;
	 * @param categoriaId id da categoria a qual o produto pertence, obrigatório;
	 * @param caracteristicas lista de características que vão ser persistidas juntas a este produto, no mínimo três devem ser registradas.
	 */
	public ProdutoRequest(@NotBlank String nome, @Positive @NotNull BigDecimal valor,
			@PositiveOrZero @NotNull Long quantidade, @Size(max = 1000) @NotBlank String descricao,
			@NotNull Long categoriaId, @NotNull @Size(min = 3) List<CaracteristicaRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicas = caracteristicas;
	}
	
	/**
	 * Transforma o objeto {@link ProdutoRequest} em um objeto {@link Produto}.
	 * 
	 * @param manager EntityManager utilizado para recuperar a categoria a qual o produto pertence;
	 * @return objeto {@link Produto} populado a partir dos dados desse objeto.
	 */
	public Produto toModel(EntityManager manager, Usuario usuario){
		Categoria categoria = manager.find(Categoria.class, categoriaId);
		Produto produto = new Produto(nome, valor, quantidade, descricao, categoria, usuario);
		produto.setCaracteristicas(caracteriscaListToModel(produto));
		return produto;
	}

	/**
	 * Transforma a lista de CaracterísticaRequest em uma lista de {@link Caracteristica}.
	 * @return lista convertida.
	 */
	private List<Caracteristica> caracteriscaListToModel(Produto produto) {
		return caracteristicas.stream()
				.map(caracteristica -> caracteristica.toModel(produto))
				.collect(Collectors.toList());
	}
}