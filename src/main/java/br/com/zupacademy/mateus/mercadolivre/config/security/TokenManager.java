package br.com.zupacademy.mateus.mercadolivre.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

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

}
