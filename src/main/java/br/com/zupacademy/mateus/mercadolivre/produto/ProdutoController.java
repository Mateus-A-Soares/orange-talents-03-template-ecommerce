package br.com.zupacademy.mateus.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.mateus.mercadolivre.auth.UserCredentials;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Controller com os end-points relacionados ao CRUD da entidade Produto.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * End-point de URL /produtos que realiza a validação e o cadastro do registro de um produto.
	 * 
	 * @param request produto a ser cadastrado.
	 * @return ResponseEntity representando o status HTTP 200, 400 ou 500.
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastra(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal UserCredentials credentials) {
		Usuario usuario = credentials.toModel();
		Produto produto = request.toModel(manager, usuario);
		manager.persist(produto);
		return ResponseEntity.ok().build();
	}
}
