package br.com.zupacademy.mateus.mercadolivre.config.security;

import java.io.IOException;

import javax.persistence.EntityManager;
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

/**
 * 	
 *  Filtro utilizado para validar o token na requisição, construir um {@link UserCredentials}
 * através do id de usuário anexado a ele, e colocar essas credenciais no {@link SecurityContext} da aplicação. 
 *  
 * 	@author Mateus Soares
 */
public class TokenAuthenticationDevProfileFilter extends OncePerRequestFilter {

	private TokenManager tokenManager;
	private EntityManager entityManager;

	/**
	 * Construtor que instância um TokenAuthenticationFilter, que estende {@link OncePerRequestFilter}.
	 * 
	 * @param tokenManager tratará da leitura do token enviado pela requisição;
	 * @param entityManager tratará da pesquisa pelo usuário de id igual ao que deve estar dentro do token recebido.
	 */
	public TokenAuthenticationDevProfileFilter(TokenManager tokenManager, EntityManager entityManager) {
		this.tokenManager = tokenManager;
		this.entityManager = entityManager;
	}

	/**
	 * Método sobrescrito que é invocado uma vez por requisição.
	 * Se o token enviado for válido e formatado corretamente, autentica as credenciais do usuário no {@link SecurityContext},
	 * se não procura pelo usuário de id 1 e autentica as suas credenciais.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getTokenFromRequest(request);
		boolean isTokenValid = tokenManager.isTokenValid(token);
		if (isTokenValid) {
			authenticateClient(token);
		} else {
			Usuario defaultUser = entityManager.find(Usuario.class, 1l);
			String defaultUserToken = tokenManager.generateToken(new UserCredentials(defaultUser));
			authenticateClient(defaultUserToken);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void authenticateClient(String token) {
		Long usuarioId = tokenManager.getUsuarioId(token);
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);
		UserCredentials credentials = new UserCredentials(usuario);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(credentials, null, credentials.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
}
