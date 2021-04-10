package br.com.zupacademy.mateus.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 
 * Classe modelo que representa os dados nas requests de cadastro de usuários
 * 
 * @author Mateus Soares
 */
public class UsuarioRequest {
	
	@NotBlank
	@Email
	private String login;
	
	@NotBlank
	@Size(min = 6, message = "Tamanho mínimo de seis caracteres")
	private String senha;

	/**
	 * Construtor que instância um objeto UsuarioRequest com os dados representativos da entidade Usuario.
	 * 
	 * @param login login do usuário, deve estar formatada como email e não pode estar vazia.
	 * @param senha senha do usuário, não pode estar nula e tem tamanho mínimo de seis caracteres.
	 */
	public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
	
	/**
	 * Transforma o objeto UsuarioRequest em um objeto Usuario.
	 * @param passwordEncoder 
	 * 
	 * @return objeto Usuario populado com os dados desse objeto.
	 */
	public Usuario toModel(PasswordEncoder passwordEncoder) {
		return new Usuario(login, senha, passwordEncoder);
	}
}
