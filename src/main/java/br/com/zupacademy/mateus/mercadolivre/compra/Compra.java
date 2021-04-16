package br.com.zupacademy.mateus.mercadolivre.compra;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.mateus.mercadolivre.compra.gateway.Gateway;
import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Classe modelo da entidade Compra.
 * 
 * @author Mateus Soares
 */
@Entity
public class Compra {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
    @ManyToOne(optional = false)
    private Usuario usuario;

	@NotNull
    @ManyToOne(optional = false)
    private Produto produto;
	
	@NotNull @Positive
    @Column(nullable = false)
    private Integer quantidade;
	
	@NotNull @Positive
    @Column(nullable = false)
    private BigDecimal valorProduto;
    
	@NotNull
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gateway gateway;
	
	@NotNull
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompraStatus status;

    @Deprecated
    public Compra() {}

    /**
     * Construtor que instancia um objeto {@link Compra}
     * 
     * @param usuario		usuário que efetuou a compra, obrigatório;
     * @param produto 		produto comprado, obrigatório;
     * @param quantidade	quantidade que vai ser comprada e abatida do estoque de produtos, positiva e obrigatória;
     * @param valorProduto	valor do produto no momento da compra. positivo e obrigatório;
     * @param gateway		gateway usado para o pagamento, obrigatório;
     * @param status		status da compra, obrigatório.
     */
	public Compra(@NotNull Usuario usuario, @NotNull Produto produto, @NotNull Integer quantidade,
			@NotNull BigDecimal valorProduto, @NotNull Gateway gateway, @NotNull CompraStatus status) {
		this.usuario = usuario;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorProduto = valorProduto;
		this.gateway = gateway;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValorProduto() {
		return valorProduto;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public CompraStatus getStatus() {
		return status;
	}
}