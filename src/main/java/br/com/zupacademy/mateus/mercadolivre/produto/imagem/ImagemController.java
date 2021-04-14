package br.com.zupacademy.mateus.mercadolivre.produto.imagem;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * Controller com os end-points relacionados ao CRUD das imagens cadastradas para um produto.
 * 
 * @author Mateus Soares
 */
@Controller
@RequestMapping("/produtos/{id}/imagens")
public class ImagemController {

	@PostMapping
	public ResponseEntity<String> cadastra(@PathVariable("id") Long id, @Valid ImagemListRequest request) {
		return ResponseEntity.ok(id.toString() + request.toString());
	}
}