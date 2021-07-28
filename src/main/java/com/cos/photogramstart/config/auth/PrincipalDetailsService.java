package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service//ioc
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	//1. 패스워드는 알아서 체킹해줘서 신경쓸필요 노
	//2. 리턴이 잘되면 자동으로 UserDetails타입을 세션으로 만들어줌
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity==null) {
			return null;
		}else {
			return new PrincipalDetails(userEntity);
		}
		
	}	
}
