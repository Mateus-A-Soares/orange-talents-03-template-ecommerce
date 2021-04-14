package br.com.zupacademy.mateus.mercadolivre.produto;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.com.zupacademy.mateus.mercadolivre.categoria.Categoria;
import br.com.zupacademy.mateus.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.mateus.mercadolivre.usuario.Usuario;

public class ProdutoTest {
	
	private static Produto produto;
	private Usuario usuario;

	@BeforeEach
	public void setup() {
		Categoria categoria = new Categoria("Categoria");
		usuario = new Usuario();
		produto = new Produto("nome", new BigDecimal(10.10), 10L, "descrição", categoria, usuario);
	}

	@MethodSource("getThreeOrMoreItemsList")
	@ParameterizedTest
	@DisplayName("Um produto deve aceitar uma lista com três ou mais características")
	public void shouldAcceptCaracteriscaListWithThreeOrMoreItems(List<Caracteristica> caracteristicas) {
		
		produto.setCaracteristicas(caracteristicas);
	}
	
	@MethodSource("getTwoOuLessItemsList")
	@ParameterizedTest
	@DisplayName("Um produto não deve aceitar uma lista com duas características ou menos")
	public void shouldNotAcceptCaracteriscaListWithTwoItemsOrLess(List<Caracteristica> caracteristicas) {
		
		assertThrows(IllegalArgumentException.class, () -> produto.setCaracteristicas(caracteristicas)); 
	}
	
	static Stream<Arguments> getThreeOrMoreItemsList() {
		return Stream.of(
				Arguments.of(
						Arrays.asList(
								new Caracteristica("Nome 1", "Valor 1", produto),
								new Caracteristica("Nome 2", "Valor 2", produto),
								new Caracteristica("Nome 3", "Valor 3", produto)
								)
						),
				Arguments.of(
						Arrays.asList(
						new Caracteristica("Nome 1", "Valor 1", produto),
						new Caracteristica("Nome 2", "Valor 2", produto),
						new Caracteristica("Nome 3", "Valor 3", produto),
						new Caracteristica("Nome 4", "Valor 4", produto)
						)
				));
	}
	
	static Stream<Arguments>  getTwoOuLessItemsList() {
		return Stream.of(
				Arguments.of(
						Arrays.asList(
						new Caracteristica("Nome 1", "Valor 1", produto),
						new Caracteristica("Nome 2", "Valor 2", produto)
						)),
				Arguments.of(
						Arrays.asList(
						new Caracteristica("Nome 1", "Valor 1", produto)
						)),
				Arguments.of(
						new ArrayList<Caracteristica>()
				));
	}
}