package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WSajunVo extends VO {
	private Long 	sajunId;
	private String 	empComp;
	private String 	empNm;
	private String 	empTel;
	private String 	drvTel;
	private String 	ioStat;
	private String 	fmCd;
	private String 	cntrNo;
	private String 	estDay;
	private String 	estTime;
	private String 	estMin;
	private String 	ioChk;
	private String 	writeDt;
	private String  reqId;
	private String  comp;
	
}
