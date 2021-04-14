package br.com.zupacademy.mateus.mercadolivre.shared.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.mateus.mercadolivre.auth.UserCredentials;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;
import br.com.zupacademy.mateus.mercadolivre.usuario.UsuarioRepository;

/**
 * 	
 *  Filtro utilizado para validar o token na requisição, construir um {@link UserCredentials}
 * através do id de usuário anexado a ele, e colocar essas credenciais no {@link SecurityContext} da aplicação. 
 *  
 * 	@author Mateus Soares
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private TokenManager tokenManager;
	private UsuarioRepository repository;

	/**
	 * Construtor que instância um TokenAuthenticationFilter, que estende {@link OncePerRequestFilter}.
	 * 
	 * @param tokenManager tratará da leitura do token enviado pela requisição;
	 * @param repository tratará da pesquisa pelo usuário de id igual ao que deve estar dentro do token recebido.
	 */
	public TokenAuthenticationFilter(TokenManager tokenManager, UsuarioRepository repository) {
		this.tokenManager = tokenManager;
		this.repository = repository;
	}

	/**
	 * Método sobrescrito que é invocado uma vez por requisição.
	 * Autentica o cliente durante o processo de criação da resposta de sua requisição, somente se o token enviado for válido e formatado corretamente.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getTokenFromRequest(request);
		boolean isTokenValid = tokenManager.isTokenValid(token);
		if (isTokenValid) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void authenticateClient(String token) {
		Long usuarioId = tokenManager.getUsuarioId(token);
		Optional<Usuario> usuario = repository.findById(usuarioId);
		if(usuario.isPresent()) {
			UserCredentials credentials = new UserCredentials(usuario.get());
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(credentials, null, credentials.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
}
