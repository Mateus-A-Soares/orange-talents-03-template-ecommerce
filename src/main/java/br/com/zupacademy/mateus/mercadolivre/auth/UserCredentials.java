package br.com.zupacademy.mateus.mercadolivre.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

/**
 * 
 * Classe representativa das credenciais do usuário logado.
 * Quando a requisição vem de um usuário logado, um objeto dessa classe é instanciado e 
 * anexado ao contexto da aplicação enquanto a construção da resposta para uma requisição estiver sendo construída.
 * 
 * @author Mateus Soares
 */
public class UserCredentials implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	
	/**
	 * Instância um objeto dessa classe através dos dados encapsulados em um {@link Usuario}.
	 * 
	 * @param usuario usuario contendo os dados.
	 */
	public UserCredentials(Usuario usuario) {
		this.login = usuario.getLogin();
		this.senha = usuario.getSenha();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
