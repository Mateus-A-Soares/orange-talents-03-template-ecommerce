package br.com.zupacademy.mateus.mercadolivre.shared.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zupacademy.mateus.mercadolivre.usuario.UsuarioRepository;

/**
 *  Classe que define as configurações relacionadas ao processo de autenticação na aplicação 
 * enquanto o perfil ativo for "prod" ou "test".
 * 
 * @author Mateus Soares
 */
@EnableWebSecurity
@Configuration
@Profile(value = {"prod", "test"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UserDetailsServiceImp userDetailsService;
	
	@Autowired
	private TokenManager tokenManager;
	 
	/**
	 * 	Bean que popula um objeto PasswordEnconder utilizado para criptografar a senha do usuário.
	 * 
	 * @return BCryptPasswordEncoder que realizará a criptografia.
	 */	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	/**
	 *   Configura a implementação do UserDetailsService que carrega o usuário pelo login e o PasswordEncoder utilizado na criptografia da senha, 
	 *  invocado durante o processo de autenticação.
	 *  
	 *  @param auth builder que configurará o {@link AuthenticationManager}, responsável pelo processo de autenticação.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	
	/**
	 *  Configura as rotas que requerem autenticação, adicionando o filtro de autenticação {@link TokenAuthenticationFilter}
	 *  que realizará a autenticação das requisições que requerem usuário autenticado.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/produtos/").authenticated()
		.antMatchers(HttpMethod.PATCH, "/produtos/{id}/**").authenticated()
		.anyRequest().permitAll()
		.and().cors()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new TokenAuthenticationFilter(tokenManager, repository), UsernamePasswordAuthenticationFilter.class);
	}
}
