package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.image.Image;
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
@Table(uniqueConstraints = { 
		@UniqueConstraint(
				name = "likes_uk", 
				columnNames = { "imageId", "userId" } //한명의 유저가 하나의 이미지를 여러번 좋아요 할수 없으니까, 요 두개의 칼럼에 똑같은개 두번못생긴다!. 유니크제약조건 걸음
				) 
		}
)
public class Likes {
	@Id // 얘를 프라이머리키로 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name="imageId")
	@ManyToOne  //N(likes) : 1(image) 관계니까...이미지 하나에 여러개좋아요.
	private Image image;
	
	@JoinColumn(name="userId")
	@ManyToOne  //N(likes) : 1(user) 관계니까...한명의 유저가 좋아요 여러개
	private User user;

	private LocalDateTime createDate;

	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
