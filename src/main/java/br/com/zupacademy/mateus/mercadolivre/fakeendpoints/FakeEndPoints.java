package br.com.zupacademy.mateus.mercadolivre.fakeendpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * End points falsos utilizados apenas para simulação
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/fake")
public class FakeEndPoints {
	
	@GetMapping("/rankingvendedores")
	public void rankingVendedores(@RequestParam("compraId") Long compraId,@RequestParam("vendedorId") Long vendedorId) {
		System.out.println("ATUALIZANDO RANK DO VENDEDOR DE ID " + vendedorId);
	}
	
	@GetMapping("/notasfiscais")
	public void notasFiscais(@RequestParam("compraId") Long compraId,@RequestParam("usuarioId") Long usuarioId) {
		System.out.println("CRIANDO NOTA FISCAL PARA COMPRA DE ID " + compraId);
	}
}