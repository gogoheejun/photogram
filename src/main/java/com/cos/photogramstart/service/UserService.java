package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.subscibe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Value("${file.path}") // 여기다가 다 경로 안적고 yml파일꺼 가져오는건 그 경로를 다른곳에서도 쓸수도 있으니깐.
	private String uploadFolder;
	
	@Transactional
	public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile) {
		UUID uuid = UUID.randomUUID();// 랜덤으로 중복안되는 id만들어줌.(몇십억분의 1로 같은거 나올수 있지만 파일명이랑 합치면 몇천억분의 1됨)
		String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();

		Path imageFilePath = Paths.get(uploadFolder + imageFileName);

		// 통신,I/O ->예외가 발생할수있음.
		try {
			Files.write(imageFilePath, profileImageFile.getBytes());// 이미지를 바이트화해서 서버경로에다가 넣음
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User userEntity = userRepository.findById(principalId).orElseThrow(()->{
			throw new CustomApiException("유저를 찾을 수 없습니다.");
		});
		userEntity.setProfileImageUrl(imageFileName);
		return userEntity;
	}//더티체킹으로 업데이트 됨
	
	@Transactional(readOnly=true)
	public UserProfileDto 회원프로필(int pageUserId, int principalId) {
		UserProfileDto dto = new UserProfileDto();
		
		//원래 쿼리로 하면, SELECT * FROM image WHERE userId=:userId임
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId==principalId);//1은 페이지주인, -1은 주인이 아님
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		
		dto.setSubscribeState(subscribeState==1);
		dto.setSubscribeCount(subscribeCount);
		
		//좋아요카운트 추가하기
		userEntity.getImages().forEach(image->{
			image.setLikeCount(image.getLikes().size());
		});
		
		return dto;
	}
	
	@Transactional
	public User 회원수정(int id, User user) {
		//1.영속화
		//get쓴건 무조건 찾았다 걱정말라는거..만약 못찾으면 orElseThrow()
		User userEntity = userRepository.findById(id).orElseThrow(()-> {return new CustomValidationApiException("찾을 수 없는 id입니다");});
		
		//2.영속화된 오브젝트를 수정..수정끝나면 더티체킹(업데이트 완료)
		userEntity.setName(user.getName());
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		return userEntity;
	}//리턴할때 더테체킹이 일어나서 업데이트가 완료됨
}
