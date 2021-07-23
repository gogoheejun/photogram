package com.cos.photogramstart.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class ImageUploadDto {
	private MultipartFile file;
	private String caption;
	
	public Image toEntity(String postIamgeUrl, User user) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postIamgeUrl)
				.user(user) //Image객체에 user정보를 넣도록 모델짜놨으니까 필요함
				.build();
	}
}
