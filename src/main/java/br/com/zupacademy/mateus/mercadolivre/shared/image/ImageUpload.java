package br.com.zupacademy.mateus.mercadolivre.shared.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * Deve tratar o procedimento de upload de uma lista de arquivos contidos em objetos do tipo {@link MultipartFile}.
 *  
 * @author Mateus Soares
 */
public interface ImageUpload {
	
	/**
	 * Deve realizar o upload dessa lista de arquivos.
	 * 
	 * @param images lista de arquivos contidos em um {@link MultipartFile}.
	 * @return lista das URLs indicando o caminho dos arquivos salvos.
	 */
	List<String> execute(List<MultipartFile> images);
}