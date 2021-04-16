package br.com.zupacademy.mateus.mercadolivre.produto.opiniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mateus.mercadolivre.auth.UserCredentials;
import br.com.zupacademy.mateus.mercadolivre.produto.Produto;

/**
 * 
 * Controller com os end-points relacionados ao CRUD das opinioes cadastradas para um produto.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/produtos/{id}/opiniao")
public class OpiniaoController {
	
	@PersistenceContext
	private EntityManager manager;

	/**
	 * End-point de URL /produtos/{id}/opiniao que realiza a validação e o
	 * cadastro do registro das opiniões relacionadas ao produto de Id passado na URL.
	 * 
	 * @param produtoId		Id do produto qual pertence a opinião a ser cadastrada;
	 * @param request		Opinião a ser cadastrada; 
	 * @param credentials	Credenciais do usuário logado que será anexado a opinião.
	 * @return ResponseEntity representando o status HTTP 200, 400 ou 500.
	 */
	@PatchMapping
	@Transactional
	public ResponseEntity<Void> cadastra(@PathVariable("id") Long produtoId, @Valid @RequestBody OpiniaoRequest request,
			@AuthenticationPrincipal UserCredentials credentials) {
		Produto produto = manager.find(Produto.class, produtoId);
		if(produto == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
		Opiniao opiniao = request.toModel(credentials.toModel(), produto);
		manager.persist(opiniao);
		return ResponseEntity.ok().build();
	}
}