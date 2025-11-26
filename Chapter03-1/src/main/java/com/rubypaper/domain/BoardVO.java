package com.rubypaper.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder // setter대신 쓰임
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	@Builder.Default // builder한테 초기값을 설정했다고 알려주는 어노테이션
	private Date createDate = new Date();
	private int cnt;
}
