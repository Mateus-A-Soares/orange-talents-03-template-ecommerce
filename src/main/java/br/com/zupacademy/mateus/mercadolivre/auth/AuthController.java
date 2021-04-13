package br.com.zupacademy.mateus.mercadolivre.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import br.com.zupacademy.mateus.mercadolivre.config.security.TokenManager;

/**
 * 
 * Controller com o end point de autenticação de usuário.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenManager tokenManager;

	/**
	 * End-point de URL /auth que realiza a autenticação do usuário. recebe as credenciais de um usuário no corpo da requisição,
	 * se forem válidas gera um token e o retorna no corpo da resposta com status 200
	 * @param form credenciais de um usuário presentes no corpo da requisição,
	 * @return ResponseEntity representando o status HTTP 200 e token de acesso em seu corpo caso as credenciais estejam válidas, 400 quando não estão
	 * ou 500 caso ocorra algo inesperado na aplicação.
	 */ 
	@PostMapping
	public ResponseEntity<Entry<String, String>> authenticate(@RequestBody @Valid LoginForm form) {

		UsernamePasswordAuthenticationToken authenticatedToken = form.convert();
		Authentication authentication = authManager.authenticate(authenticatedToken);
		String token = tokenManager.generateToken((UserCredentials) authentication.getPrincipal());
		Entry<String, String> response = new SimpleEntry<String, String>("token", token);
		return ResponseEntity.ok(response);
	}
}
