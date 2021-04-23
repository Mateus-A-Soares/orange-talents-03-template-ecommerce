package br.com.zupacademy.mateus.mercadolivre.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8080/fake/notasfiscais", name = "NotasFiscais")
public interface NotaFiscalClient {

	@GetMapping
	public void notasFiscais(@RequestParam("compraId") Long compraId, @RequestParam("usuarioId") Long usuarioId);
}