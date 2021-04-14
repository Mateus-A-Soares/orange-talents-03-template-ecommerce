package br.com.zupacademy.mateus.mercadolivre.shared.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.mateus.mercadolivre.shared.validation.constraints.Unique;

/**
 * Implementação do validator que executa a validação de unicidade para campos de entidades JPA.
 * 
 * @author Mateus Soares
 */
public class UniqueValidator implements ConstraintValidator<Unique, Object> {
	
	@PersistenceContext
	private EntityManager manager;
	private String fieldName;
	private Class<?> entityClass;

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String entityName = entityClass.getSimpleName();
		Query query = manager.createQuery("SELECT 1 FROM " + entityName + " o WHERE o." + fieldName + " = :value");
		query.setParameter("value", value);
		return query.getResultList().isEmpty();
	}
	
	@Override
	public void initialize(Unique annotation) {
		fieldName = annotation.fieldName();
		entityClass = annotation.entityClass();
	}
}
