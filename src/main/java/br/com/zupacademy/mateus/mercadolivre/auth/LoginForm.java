package br.com.zupacademy.mateus.mercadolivre.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	
	@Email
	@NotBlank
	private String login;
	@Size(min = 6)
	private String senha;
	
	public LoginForm(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken convert() {

		return new UsernamePasswordAuthenticationToken(login, senha);
	}
}
