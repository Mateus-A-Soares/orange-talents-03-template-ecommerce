package br.com.zupacademy.mateus.mercadolivre.shared.validation.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints.ExistsOne;

/**
 * Implementação do validator que executa a validação de existência nos parâmetros que representam chaves estrangeiras de entidades JPA.
 * 
 * @author Mateus Soares
 */
public class ExistsOneValidator implements ConstraintValidator<ExistsOne, Object> {
	
	@PersistenceContext
	private EntityManager manager;
	private String fieldTargetName;
	private Class<?> entityTargetClass;

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String entityName = entityTargetClass.getSimpleName();
		Query query = manager.createQuery("SELECT 1 FROM " + entityName + " o WHERE o." + fieldTargetName + " = :value");
		query.setParameter("value", value);
		return !query.getResultList().isEmpty();
	}
	
	@Override
	public void initialize(ExistsOne annotation) {
		fieldTargetName = annotation.fieldTargetName();
		entityTargetClass = annotation.entityTargetClass();
	}
}
