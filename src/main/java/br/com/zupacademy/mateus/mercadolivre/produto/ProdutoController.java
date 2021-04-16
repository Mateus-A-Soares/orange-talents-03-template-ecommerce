package br.com.zupacademy.mateus.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	
	/**
	 * End-point de URL /produtos/{id} que retorna o dados do registro de um produto.
	 * 
	 * @param produtoId identificador do produto a ser detalhado;
	 * @return ResponseEntity representando o status HTTP 200, 400 ou 500.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDetailsResponse> detalhar(@PathVariable("id") Long produtoId) {
		Produto produto = manager.find(Produto.class, produtoId);
		if(produto == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
		return ResponseEntity.ok(new ProdutoDetailsResponse(produto));
	}
} 
