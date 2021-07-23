package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//얘처럼 jpaRepository상속하면 어노테이션 없어도 ioc자동등록됨.
public interface UserRepository extends JpaRepository<User, Integer>{//오브젝트,PK타입
	//JPA query method
	User findByUsername(String username);
}
