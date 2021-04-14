package br.com.zupacademy.mateus.mercadolivre.shared.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zupacademy.mateus.mercadolivre.auth.UserCredentials;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;
import br.com.zupacademy.mateus.mercadolivre.usuario.UsuarioRepository;

/**
 * 
 * Implementação do UserDetailsService necessário para o processo de autenticação.
 * 
 * @author Mateus Soares
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;

	/**
	 *  Implementação do método loadUserByUsername, invocado no processo de autenticação quando o usuário passa suas credenciais.
	 *  Procura pelo usuário de login igual ao passado e retorna um objeto {@link UserCredentials} instânciado com os dados do seu registro.
	 *  
	 *  @param login login do usuário utilizado para pesquisa na base de dados,
	 *  
	 *  @return {@link UserCredentials} populado com os dados do registro encontrado.
	 *  @throws UsernameNotFoundException lançada caso o usuário não seja encontrado.
	 */
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = repository.findByLogin(login);
		if (usuarioOptional.isPresent()) {
			return new UserCredentials(usuarioOptional.get());
		}

		throw new UsernameNotFoundException("Usuário não encontrado");
	}
}
