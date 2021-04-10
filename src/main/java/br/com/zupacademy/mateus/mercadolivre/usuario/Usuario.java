package br.com.zupacademy.mateus.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * Classe modelo da entidade Usuario.
 * 
 * @author Mateus Soares
 */
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime cadastro;
	
	@Column(unique = true, nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String senha;
	
	@Deprecated
	public Usuario() {}

	/**
	 * Construtor que instância um objeto Usuário
	 * 
	 * @param login login do usuario, único e obrigatório.
	 * @param senha senha do usuario, obrigatória.
	 */
	public Usuario(String login, String senha, PasswordEncoder encoder) {
		this.login = login;
		this.senha = encoder.encode(senha);
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCadastro() {
		return cadastro;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", cadastro=" + cadastro + ", login=" + login + ", senha=" + senha + "]";
	}
}
