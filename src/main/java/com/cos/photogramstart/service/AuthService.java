package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //ioc등록, 트랜잭션 관리
public class AuthService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional//Write(insert,update,delete)할 때 얘를 붙임
	public User 회원가입(User user) {
		//회원가입 진행
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");//가입한애들한테 유저라는 권한줌. 관리자는 ROLE_ADMIN
		User userEntity = userRepository.save(user);//userEntity는 db에 있는 data를 user오브젝트에 담은거야
		return userEntity;
	}
}
