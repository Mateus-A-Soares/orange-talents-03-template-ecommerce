package br.com.zupacademy.mateus.mercadolivre.validation.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.mateus.mercadolivre.categoria.Categoria;
import br.com.zupacademy.mateus.mercadolivre.validation.constraints.ExistsOneCategoria;

/**
 * Implementação do validator que executa a validação de existência de uma categoria.
 * 
 * @author Mateus Soares
 */
public class ExistsOneCategoriaValidator implements ConstraintValidator<ExistsOneCategoria, Long> {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean isValid(Long categoriaId, ConstraintValidatorContext context) {
		if(categoriaId == null)
			return true;
		Categoria categoria = manager.find(Categoria.class, categoriaId);
		return categoria != null;
	}
}