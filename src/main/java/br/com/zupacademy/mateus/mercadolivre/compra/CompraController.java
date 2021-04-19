package br.com.zupacademy.mateus.mercadolivre.compra;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mateus.mercadolivre.auth.UserCredentials;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.GatewayPaymentProcessorsList;
import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.shared.email.MailSender;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Controller com os end-points relacionados ao CRUD de compras.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/produtos/{id}/comprar")
public class CompraController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private GatewayPaymentProcessorsList gatewayPaymentProcessors;

	/**
	 * End-point de URL /produtos/{id}/comprar que realiza a compra de um produto
	 * pelo usuário logado.
	 * 
	 * @param produtoId   id do produto a se comprado, presente na URL;
	 * @param request     quantidade do produto e gateway utilizado encapsulados em
	 *                    DTO;
	 * @param credentials credenciais do usuário logado, utilizado para registrar
	 *                    quem realizou a compra.
	 * @return ResponseEntity representando o status HTTP 200, 400 ou 500.
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<String> realizaCompra(@PathVariable("id") Long produtoId,
			@Valid @RequestBody CompraRequest request, @AuthenticationPrincipal UserCredentials credentials)
			throws BindException {
		Usuario usuario = credentials.toModel();
		Produto produto = manager.find(Produto.class, produtoId);
		if (produto == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
		Compra compra = request.toModel(usuario, produto, CompraStatus.INICIADA);
		boolean isRemoved = produto.removeFromQuantidade(compra.getQuantidade()); 
		if (!isRemoved) {
			BindException exception = new BindException(compra, "compra");
			exception.addError(new FieldError("compra", "quantidade", "Quantidade maior que o estoque"));
			throw exception;
		}
		manager.persist(compra);
		URI returnUri = compra.getGateway().processesPayment(compra, gatewayPaymentProcessors);
		mailSender.execute(produto.getUsuario().getLogin(), "COMPRA DE PRODUTO",
							usuario.getLogin() + " quer comprar " + compra.getQuantidade() + " unidades do produto " + produto.getNome());
		return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(returnUri.toString());
	}
}