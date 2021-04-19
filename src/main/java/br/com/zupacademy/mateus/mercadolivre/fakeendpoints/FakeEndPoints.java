package br.com.zupacademy.mateus.mercadolivre.fakeendpoints;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * End points falsos utilizados apenas para simulação
 * 
 * @author Mateus Soares
 */
@RestController
public class FakeEndPoints {
	
	@PostMapping("/rankingvendedores")
	public void rankingVendedores(Long compraId, Long vendedorId) {
		System.out.println("ATUALIZANDO RANK DO VENDEDOR DE ID " + vendedorId);
	}
	
	@PostMapping("/notasfiscais")
	public void notasFiscais(Long compraId, Long usuarioId) {
		System.out.println("CRIANDO NOTA FISCAL PARA COMPRA DE ID " + compraId);
	}
}