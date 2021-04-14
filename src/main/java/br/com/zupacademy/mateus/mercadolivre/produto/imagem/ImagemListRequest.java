package br.com.zupacademy.mateus.mercadolivre.produto.imagem;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;


/**
 * 
 * Classe modelo que representa as imagens enviadas nas requests de cadastro de imagens para os produtos.
 * 
 * @author Mateus Soares
 */
public class ImagemListRequest {
	
	@NotNull
	@Size(min = 1)
	private List<MultipartFile> imagens;
	
	/**
	 * Construtor que instância um objeto {@link ImagemListRequest}
	 * 
	 * @param imagens lista de {@link MultipartFile} representando as imagens a serem cadastradas, não pode estar nula ou vazia.
	 */
	public ImagemListRequest(@NotNull @Size(min = 1) List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	@Override
	public String toString() {
		String s = "ImagemListRequest [imagens=";
		for (MultipartFile file : imagens) {
			s += file.getOriginalFilename() + ", ";
		}
		return s + "]";
	}
}
