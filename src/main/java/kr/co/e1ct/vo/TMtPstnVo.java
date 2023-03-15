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
public class TMtPstnVo extends VO {
	private String 	cntrNo;
	private Long 	dupNo;
	private String 	operCd;
	private String 	shipCd;
	private Long 	callNo;
	private Long 	callYy;
	private Date 	pstnDt;
	private String 	ibVoy;
	private String 	obVoy;
	private String 	empNo;
	private String 	pod;
}
