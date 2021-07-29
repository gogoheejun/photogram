package com.cos.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

//NotNull=Null값체크
//NotEmpty=빈값("")|null|빈공백("  ") 체크
//NotBlacnk=빈값|null 체크
@Data
public class CommentDto {
	@NotBlank 
	private String content;
	@NotNull
	private Integer imageId;
	
	//toEntity가 필요없다
}
