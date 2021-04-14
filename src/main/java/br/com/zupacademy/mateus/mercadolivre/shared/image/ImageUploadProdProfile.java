package br.com.zupacademy.mateus.mercadolivre.shared.image;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Profile({"prod", "test"})
public class ImageUploadProdProfile implements ImageUpload {

	@Override
	public List<String> execute(List<MultipartFile> images) {
		
		return images.stream()
					.map(image -> "https://fakeurl.io/" + image.getSize() + "-" + UUID.randomUUID().toString())
					.collect(Collectors.toList());
	}
}
