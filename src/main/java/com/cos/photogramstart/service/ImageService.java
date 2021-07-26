package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;

	@Transactional(readOnly = true)
	public Page<Image> 이미지스토리(int principalId, Pageable pageable) {
		Page<Image> images = imageRepository.mStory(principalId, pageable);

		// images에 좋아요 상태 담기
		images.forEach((image) -> {
			
			//좋아요개수
			image.setLikeCount(image.getLikes().size());
			
			//내가 좋아요눌렀는지
			image.getLikes().forEach((like) -> {
				if (like.getUser().getId() == principalId) {// 해당이미지에 좋아요한사람들 찾아서 현재 로그인한 사람이 좋아요 한건지 비교
					image.setLikeState(true);
				}
			});
		});

		return images;
	}

	@Value("${file.path}") // 여기다가 다 경로 안적고 yml파일꺼 가져오는건 그 경로를 다른곳에서도 쓸수도 있으니깐.
	private String uploadFolder;

	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();// 랜덤으로 중복안되는 id만들어줌.(몇십억분의 1로 같은거 나올수 있지만 파일명이랑 합치면 몇천억분의 1됨)
		String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
		System.out.println("이미지파일이름: " + imageFileName);

		Path imageFilePath = Paths.get(uploadFolder + imageFileName);

		// 통신,I/O ->예외가 발생할수있음.
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());// 이미지를 바이트화해서 서버경로에다가 넣음
		} catch (Exception e) {
			e.printStackTrace();
		}

		// image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());// 9d435608-6430-4574-9ac4-1353d57d51b9_내사진2.jpg
		imageRepository.save(image);

//		System.out.println(imageEntity);
	}
}
