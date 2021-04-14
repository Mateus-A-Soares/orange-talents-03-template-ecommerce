package br.com.zupacademy.mateus.mercadolivre.produto.imagem;

import java.net.BindException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.mateus.mercadolivre.produto.Produto;
import br.com.zupacademy.mateus.mercadolivre.produto.ProdutoResponse;
import br.com.zupacademy.mateus.mercadolivre.shared.image.ImageUpload;

/**
 * 
 * Controller com os end-points relacionados ao CRUD das imagens cadastradas para um produto.
 * 
 * @author Mateus Soares
 */
@Controller
@RequestMapping("/produtos/{id}/imagens")
public class ImagemController {
	
	@Autowired
	private ImageUpload uploader;
	
	@PersistenceContext
	private EntityManager manager;

	@PatchMapping
	@Transactional
	public ResponseEntity<ProdutoResponse> cadastra(@PathVariable("id") Long produtoId, @Valid ImagemListRequest request) throws BindException {
		Produto produto = manager.find(Produto.class, produtoId);
		if(produto == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
		List<String> urls = uploader.execute(request.getImagens());
		produto.linkImages(urls);
		manager.merge(produto);
		return ResponseEntity.ok(new ProdutoResponse(produto));
	}
}