package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TRfcargoVo {
	private String 	cntrNo;
	private Long 	dupNo;
	private String 	shipCd;
	private Long 	callYy;
	private Long 	callNo;
	private String 	plinEmpno;
	private String 	ploutEmpno;
	private String 	plinDt;
	private String 	ploutDt;
	private String 	reqTemp;
	private String 	inTemp;
	private String 	inTempId;
	private String 	ptiYn;
	private String 	ovdema;
	private Long 	ptiCnt;
	private String 	repairingYn;
	private String 	repairingRegString;
	private String 	updEmpno;
	private String 	plinCheck;
	private String 	outTemp;
	private String 	outTempId;
	private String 	setTemp;
	private String 	setTempId;
}
