package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TCodeVo extends VO {
	private String 	cdId;
	private String 	cdVal;
	private String 	cdNm;
	private Long 	sortCd;
	private String 	cdNm2;
	private String 	cdVal2;
	private String 	cdVal3;
}
