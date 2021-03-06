package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.subscibe.Subscribe;
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
public class Image {

	@Id//얘를 프라이머리키로 설정
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String caption;//캡션..등산가기 좋은 날~~ 이런거
	private String postImageUrl; //사진을 전송받아서 그 사진을 서버의 특정폴더에 저장. db에 그 저장된 경로를 insert
	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name="userId")//User객체로 저장하면 포인트로 디비에 저장되기에, 포인트의 이름을 만들어줌
	@ManyToOne //한 유저는 여러개의 사진을 올리수있지만, 사진 하나는 하나의 유저만 올릴수있어. 이럴때의 user를 매니투원이라고 지정함.
	private User user;
	
	//이미지 좋아요
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy="image")
	private List<Likes> likes;
	
	@Transient //DB에 칼럼이 만들어지지 않는다.
	private boolean likeState;
	
	@Transient
	private int likeCount;
	
	
	//댓글
	@OrderBy("id DESC")
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy="image")//연관관계 주인이 Comment의 image다. 즉 FK가 이 comments가 아니라 Comment의 image에 생긴다
	private List<Comment> comments;
	
	private LocalDateTime createDate;
	
	@PrePersist//db에는 항상 시간이 필요해. 언제 그 데이터가 들어왔는지.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
