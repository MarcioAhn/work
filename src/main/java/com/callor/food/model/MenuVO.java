package com.callor.food.model;



import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuVO {

	@JsonProperty("RCP_SEQ")
	private long RCP_SEQ;// 일련번호

	@JsonProperty("RCP_NM")
	private String RCP_NM;// 메뉴명
	
	@JsonProperty("ATT_FILE_NO_MK")
	private String ATT_FILE_NO_MK;// 이미지경로(대)
}
