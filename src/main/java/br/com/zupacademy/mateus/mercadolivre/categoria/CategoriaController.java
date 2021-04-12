package br.com.zupacademy.mateus.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Controller com os end-points relacionados ao CRUD da entidade Categoria.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * End-point de URL /categorias que realiza a validação e o cadastro do registro de uma categoria.
	 * 
	 * @param request categoria a ser cadastrada.
	 * @return ResponseEntity representando o status HTTP 200, 400 ou 500.
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaResponse> cadastra(@RequestBody @Valid CategoriaRequest request) {
		Categoria categoria = request.toModel(manager);
		manager.persist(categoria);
		return ResponseEntity.ok(new CategoriaResponse(categoria));
	}
}
