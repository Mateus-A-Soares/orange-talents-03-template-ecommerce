package br.com.zupacademy.mateus.mercadolivre.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8080/fake/rankingvendedores", name = "RankingVendedores")
public interface RankingVendedoresClient {

	@GetMapping
	public void rankingVendedores(@RequestParam("compraId") Long compraId, @RequestParam("vendedorId") Long vendedorId);
}