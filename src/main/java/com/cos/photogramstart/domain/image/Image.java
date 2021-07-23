package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.subscibe.Subscribe;
import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

	@Id//얘를 프라이머리키로 설정
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String caption;//캡션..등산가기 좋은 날~~ 이런거
	private String postImageUrl; //사진을 전송받아서 그 사진을 서버의 특정폴더에 저장. db에 그 저장된 경로를 insert
	
	@JoinColumn(name="userId")//User객체로 저장하면 포인트로 디비에 저장되기에, 포인트의 이름을 만들어줌
	@ManyToOne //한 유저는 여러개의 사진을 올리수있지만, 사진 하나는 하나의 유저만 올릴수있어. 이럴때의 user를 매니투원이라고 지정함.
	private User user;
	
	//이미지 좋아요
	
	//댓글
	
	private LocalDateTime createDate;
	
	@PrePersist//db에는 항상 시간이 필요해. 언제 그 데이터가 들어왔는지.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
