package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TYpmasterVo extends VO {
	private String 	shipCd;
	private Long 	callYy;
	private Long 	callNo;
	private String 	pod;
	private String 	szCd;
	private String 	fmCd;
	private String 	hiClass;
	private Long 	planAmt;
	private Long 	stackAmt;
	private Long 	copinoAmt;
	private String 	acntDt;
	private String 	blCd;
	private Long 	billNo;
}
