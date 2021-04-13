package br.com.zupacademy.mateus.mercadolivre.usuario;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * End-point de URL /usuarios que realiza a validação e o cadastro do registro de um usuário.
	 * 
	 * @param request usuário a ser cadastrado.
	 * @return ResponseEntity representando o status HTTP 200, 400 ou 500.
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastra(@RequestBody @Valid UsuarioRequest request) {
		Usuario usuario = request.toModel(passwordEncoder);
		repository.save(usuario);
		return ResponseEntity.ok().build();
	}
}
