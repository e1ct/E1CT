package kr.co.e1ct.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TCopinoRjctVo extends VO {
	private String 	cntrNo;
	private Long 	dupNo;
	private String 	shipCd;
	private Long 	callYy;
	private Long 	callNo;
	private String 	fmCd;
	private String 	szCd;
	private String 	tyCd;
	private String 	operCd;
	private String 	updDt;
	private String 	empNo;
	private String 	rmk;
	private String 	rejectOper;
	private String 	rejectFirst;
	private String 	rejectIn;
}
