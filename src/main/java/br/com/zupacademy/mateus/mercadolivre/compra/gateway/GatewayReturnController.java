package br.com.zupacademy.mateus.mercadolivre.compra.gateway;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;

/**
 * 
 * Controller com os end-points de resposta dos gateways, chamados após efetuar um pagamento.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/retorno-gateway")
public class GatewayReturnController {
	
	@PersistenceContext
	private EntityManager manager;

	/**
	 * End-point de URL /retorno-gateway/pagseguro/{id} que só deve ser chamado após o gateway da PagSeguro efetuar o pagamento para uma compra.
	 * 
	 * @param compraId	id da compra realizada, presente na URL;
	 * @return ResponseEntity representando o status HTTP 200, 400 ou 500.
	 */
	@PostMapping("/pagseguro/{id}")
	public ResponseEntity<String> retornoPagseguro(@PathVariable("id") Long compraId){
		Compra compra = manager.find(Compra.class, compraId);
		if (compra == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada");
		return ResponseEntity.ok(compra.toString());
	}
}
