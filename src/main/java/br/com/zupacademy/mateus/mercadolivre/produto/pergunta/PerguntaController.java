package br.com.zupacademy.mateus.mercadolivre.produto.pergunta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mateus.mercadolivre.auth.UserCredentials;
import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.produto.ProdutoResponse;
import br.com.zupacademy.mateus.mercadolivre.shared.email.MailSender;

/**
 * 
 * Controller com os end-points relacionados ao CRUD das perguntas cadastradas para um produto.
 * 
 * @author Mateus Soares
 */
@RestController
@RequestMapping("/produtos/{id}/pergunta")
public class PerguntaController {
	
	@Autowired
	private MailSender mailSender;
	
	@PersistenceContext
	private EntityManager manager;

	/**
	 * End-point de URL /produtos/{id}/pergunta que realiza a validação e o
	 * cadastro do registro das perguntas relacionadas ao produto de Id passado na URL.
	 * 
	 * @param produtoId		Id do produto qual pertence a pergunta a ser cadastrada;
	 * @param request		Pergunta a ser cadastrada; 
	 * @param credentials	Credenciais do usuário logado que será anexado a pergunta.
	 * @return ResponseEntity representando o status HTTP 200, 400 ou 500.
	 */
	@PatchMapping
	@Transactional
	public ResponseEntity<ProdutoResponse> cadastra(@PathVariable("id") Long produtoId, @Valid @RequestBody PerguntaRequest request,
			@AuthenticationPrincipal UserCredentials credentials) {
		Produto produto = manager.find(Produto.class, produtoId);
		if(produto == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
		Pergunta pergunta = request.toModel(credentials.toModel(), produto);
		manager.persist(pergunta);
		String emailVendedor = produto.getUsuario().getLogin();
		mailSender.execute(emailVendedor, "Nova pergunta: " + pergunta.getTitulo(), "Você recebeu uma nova pergunta");
		return ResponseEntity.ok().build();
	}
}