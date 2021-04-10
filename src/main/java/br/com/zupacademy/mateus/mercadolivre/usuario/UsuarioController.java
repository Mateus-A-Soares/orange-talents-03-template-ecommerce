package br.com.zupacademy.mateus.mercadolivre.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Controller com os end-points relacionados ao CRUD da entidade Usuario.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * End-point de URL /usuarios que realiza o cadastro do registro de um usuário.
	 * 
	 * @param request usuário a ser cadastrado.
	 * @return ResponseEntity representando o status HTTP 200 ou 500.
	 */
	@PostMapping
	@Transactional
	public String cadastra(@RequestBody UsuarioRequest request) {
		Usuario usuario = request.toModel();
		manager.persist(usuario);
		return usuario.toString();
	}
}
