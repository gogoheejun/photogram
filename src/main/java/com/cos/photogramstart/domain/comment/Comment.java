package com.cos.photogramstart.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {
	@Id // 얘를 프라이머리키로 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 100, nullable = false)
	private String content;
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")
	@ManyToOne(fetch=FetchType.EAGER)//매니투원은 EAGER가 디폴트
	private User user;
	
	@JoinColumn(name="imageId")
	@ManyToOne(fetch=FetchType.EAGER)
	private Image image;
	

	private LocalDateTime createDate;

	@PrePersist // db에는 항상 시간이 필요해. 언제 그 데이터가 들어왔는지.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}














