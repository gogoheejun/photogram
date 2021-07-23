package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
 
	private final ImageRepository imageRepository;
	
	@Value("${file.path}")//여기다가 다 경로 안적고 yml파일꺼 가져오는건 그 경로를 다른곳에서도 쓸수도 있으니깐.
	private String uploadFolder;
	
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
		UUID uuid = UUID.randomUUID();//랜덤으로 중복안되는 id만들어줌.(몇십억분의 1로 같은거 나올수 있지만 파일명이랑 합치면 몇천억분의 1됨)
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
		System.out.println("이미지파일이름: "+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		//통신,I/O ->예외가 발생할수있음.
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());//이미지를 바이트화해서 서버경로에다가 넣음
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());//9d435608-6430-4574-9ac4-1353d57d51b9_내사진2.jpg
		Image imageEntity = imageRepository.save(image);
		
		System.out.println(imageEntity);
	}
}
