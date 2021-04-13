package br.com.zupacademy.mateus.mercadolivre.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.zupacademy.mateus.mercadolivre.auth.UserCredentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * Manager utilizado para os processos relativos a leitura dos tokens de autenticação criados pela aplicação.
 * 
 * @author Mateus Soares
 */
@Component
public class TokenManager {
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	@Value("${forum.jwt.expiration}")
	private String expiration;

	/**
	 * Verifica se o parâmetro token é válido de acordo com a chave utilizada pela aplicação.
	 * 
	 * @param token token verificado
	 */
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Recebe um token e lê o parâmetro Id anexado ao corpo.
	 * 
	 * @param token token com o corpo contendo o parâmetro id.
	 * @return id do usuário.
	 */
	public Long getUsuarioId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	/**
	 * Transforma o objeto {@link UserCredentials} anexado ao {@link Authentication}
	 * em um token de acesso a aplicação,
	 * 
	 * @param authentication objeto representando a autenticação do usuário logado;
	 * @return token de acesso a aplicação.
	 */
	public String generateToken(Authentication authentication) {
		UserCredentials loggedUser = (UserCredentials) authentication.getPrincipal();
		Date today = new Date();
		Date expirationTime = new Date(today.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API Mercado Livre")
				.setSubject(loggedUser.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expirationTime)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
