package br.com.zupacademy.mateus.mercadolivre.compra.gateway;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mateus.mercadolivre.compra.Compra;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.pagseguro.PagseguroRetornoRequest;
import br.com.zupacademy.mateus.mercadolivre.compra.gateway.paypal.PaypalRetornoRequest;

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
	 * @param request	dados enviados pelo gateway, informando a compra e o status do pagamento;
	 * @return ResponseEntity representando o status HTTP 200, 400 ou 500.
	 * @throws BindException caso ocorra algum erro de validação relacionado a lista de tentativas de pagamentos da compra.
	 */
	@PostMapping("/pagseguro/{id}")
	@Transactional
	public ResponseEntity<String> retornoPagseguro(@PathVariable("id") Long compraId, @Valid @RequestBody PagseguroRetornoRequest request) throws BindException{
		processGatewayReturn(compraId, request);
		return ResponseEntity.ok(request.getStatus().toString());
	}
	
	/**
	 * End-point de URL /retorno-gateway/paypal/{id} que só deve ser chamado após o gateway da Paypal efetuar o pagamento para uma compra.
	 * 
	 * @param compraId	id da compra realizada, presente na URL;
	 * @param request	dados enviados pelo gateway, informando a compra e o status do pagamento;
	 * @return ResponseEntity representando o status HTTP 200, 400 ou 500.
	 * @throws BindException caso ocorra algum erro de validação relacionado a lista de tentativas de pagamentos da compra. 
	 */
	@PostMapping("/paypal/{id}")
	@Transactional
	public ResponseEntity<String> retornoPaypal(@PathVariable("id") Long compraId, @Valid @RequestBody PaypalRetornoRequest request) throws BindException{
		processGatewayReturn(compraId, request);
		return ResponseEntity.ok(request.getStatus().toString());
	}
	
	
	private void processGatewayReturn(Long compraId, GatewayRetornoRequest request) throws BindException {
		Compra compra = manager.find(Compra.class, compraId);
		if (compra == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra não encontrada");		
		compra.addGatewayRetorno(request);
		manager.merge(compra);
	}
}
