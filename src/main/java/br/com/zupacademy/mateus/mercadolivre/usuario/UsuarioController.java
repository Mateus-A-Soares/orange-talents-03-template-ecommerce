package br.com.zupacademy.mateus.mercadolivre.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * End-point de URL /usuarios que realiza o cadastro do registro de um usuário.
	 * 
	 * @param request usuário a ser cadastrado.
	 * @return ResponseEntity representando o status HTTP 200 ou 500.
	 */
	@PostMapping
	@Transactional
	public String cadastra(@RequestBody @Valid UsuarioRequest request) {
		Usuario usuario = request.toModel(passwordEncoder);
		manager.persist(usuario);
		return usuario.toString();
	}
}
