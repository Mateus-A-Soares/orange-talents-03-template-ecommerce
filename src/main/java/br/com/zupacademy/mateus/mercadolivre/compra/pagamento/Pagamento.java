package br.com.zupacademy.mateus.mercadolivre.compra.pagamento;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;

/**
 * 
 * Classe modelo da entidade Pagamento.
 * 
 * @author Mateus Soares
 */
@Entity
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime instante;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String idTransacao;
	
	@NotNull
	@ManyToOne(optional = false)
	private Compra compra;
	
	@NotNull
	private boolean sucedido;
	
	@Deprecated
	public Pagamento() {
	}

	/**
	 * Construtor que instância um objeto {@link Pagamento},
	 * representativo de uma tentativa de pagamento por um dos gateways suportados pela aplicação.
	 * 
	 * @param idTransacao	id da tentativa de pagamento no gateway escolhido;
	 * @param compra		compra para qual foi efetuada a tentativa de pagamento;
	 * @param sucedido		status do pagamento, verdadeiro caso ocorra com sucesso.
	 */
	public Pagamento(@NotBlank String idTransacao, @NotNull Compra compra, @NotNull boolean sucedido) {
		this.idTransacao = idTransacao;
		this.compra = compra;
		this.sucedido = sucedido;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public Compra getCompra() {
		return compra;
	}

	public boolean isSucedido() {
		return sucedido;
	}
}
