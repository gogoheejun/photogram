package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	//나는 연관관계의 주인이 아니다. 주인은 Image의 user다 그러므로 테이블에 칼럼 만들지마
	//LAZY(디폴트) = User를 select할때 해당 User id로 등록된 image들을 다 가져와!-대신 getImages()함수의 image들이 호출될 때 가져와!
	//Eager = User를 셀렉할대 해당 User id로 등록된 image들을 전부 join해서 가져와!
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY) 
	@JsonIgnoreProperties({"user"})//Image내부에 있는 user를 무시하고 파싱함
	private List<Image> images;
	
	private LocalDateTime createDate;
	
	@PrePersist//디비에 Insert되기 직전에 실행되도록 하는 어노테이션
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", profileImageUrl=" + profileImageUrl + ", role=" + role + ", createDate="
				+ createDate + "]";
	}
	
	
}
