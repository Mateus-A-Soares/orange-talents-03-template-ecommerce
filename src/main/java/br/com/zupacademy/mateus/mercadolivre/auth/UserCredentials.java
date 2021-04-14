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
	
	private Usuario usuario;
	
	/**
	 * Instância um objeto dessa classe através dos dados encapsulados em um {@link Usuario}.
	 * 
	 * @param usuario usuario contendo os dados.
	 */
	public UserCredentials(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Long getId() {
		return usuario.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getLogin();
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
	
	public Usuario toModel() {
		return usuario;
	}
}
