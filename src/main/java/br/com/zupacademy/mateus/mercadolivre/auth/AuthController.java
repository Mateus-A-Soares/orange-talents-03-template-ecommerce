package br.com.zupacademy.mateus.mercadolivre.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenManager tokenManager;

	@PostMapping
	public Entry<String, String> authenticate(@RequestBody @Valid LoginForm form) {

		UsernamePasswordAuthenticationToken authenticatedToken = form.convert();
		Authentication authentication = authManager.authenticate(authenticatedToken);
		String token = tokenManager.generateToken(authentication);
		Entry<String, String> response = new SimpleEntry<String, String>("token", token);
		return response;
	}
}
