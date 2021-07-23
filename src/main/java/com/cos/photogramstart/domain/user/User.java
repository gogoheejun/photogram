package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//JPA- Java Persistance API(자바를 데이터로 영구적으로 저장(DB에다가)할수있는 api제공)

@Builder //빌더패턴..dto에서 toEntity()함수쓰기위해
@AllArgsConstructor //전체생성자 생성
@NoArgsConstructor//빈생성자 생성
@Data //롬복
@Entity//디비에 테이블을 생성해줌
public class User {

	@Id//얘를 프라이머리키로 설정
	@GeneratedValue(strategy=GenerationType.IDENTITY)//번호증가전략이 데이터베이스를 따라간다(mysql의 경우 오토인크리먼트)
	private int id;
	
	@Column(length=20, unique = true)
	private String username;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String name;
	private String website;//웹사이트
	private String bio;//자기소개
	@Column(nullable=false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl;//사진
	private String role;//권한
	
	private LocalDateTime createDate;
	
	@PrePersist//디비에 Insert되기 직전에 실행되도록 하는 어노테이션
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
