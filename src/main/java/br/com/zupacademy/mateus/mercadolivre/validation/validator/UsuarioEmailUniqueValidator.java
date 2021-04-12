package br.com.zupacademy.mateus.mercadolivre.validation.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import br.com.zupacademy.mateus.mercadolivre.validation.constraints.UsuarioEmailUnique;

/**
 * Implementação do validator que executa a validação de unicidade de email da entidade usuário.
 * 
 * @author Mateus Soares
 */
public class UsuarioEmailUniqueValidator implements ConstraintValidator<UsuarioEmailUnique, String> {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public boolean isValid(String login, ConstraintValidatorContext context) {
		Query query = manager.createQuery("SELECT 1 FROM Usuario u WHERE u.login = :login")
							.setParameter("login", login);
		return query.getResultList().isEmpty();
	}
}
