package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TTcorderVo extends VO {
	private String 	cntrNo;
	private Long 	dupNo;
	private String 	wkCd;
	private String 	odDt;
	private String 	odBlock;
	private Long 	odBay;
	private String 	shipCd;
	private Long 	callYy;
	private Long 	callNo;
	private String 	curStat;
	private String 	szCd;
	private String 	tyCd;
	private String 	fmCd;
	private String 	pod;
	private String 	ySind;
	private String 	operCd;
	private Long 	weight;
	private String 	frLoca;
	private String 	toLoca;
	private String 	tcOk;
	private String 	okDt;
	private String 	eqNo;
	private String 	gcNo;
	private String 	gcBay;
	private String 	hdeckId;
	private Long 	gcBseq;
	private String 	gateNo;
	private String 	goutDt;
	private String 	cancelDt;
	private String 	imdg;
	private String 	dgYtype;
	private String 	hwlCd;
	private Long 	ovH;
	private Long 	ovWs;
	private Long 	ovWp;
	private Long 	ovLf;
	private Long 	ovLb;
	private String 	ovPsfb;
	private String 	comChk;
	private String 	ytno;
	private String 	dirId;
	private String 	truckerCd;
	private String 	trnCd;
	private String 	sndId;
	private String 	controlYn;
	private String 	ytOdDt;
	private int cnt;
	
	private int truckCnt;
	private int inCnt;
	private int outCnt;
}
