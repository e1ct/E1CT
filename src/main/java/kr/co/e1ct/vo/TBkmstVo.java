package kr.co.e1ct.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TBkmstVo extends VO {

	private String 	operCd;
	private String 	bkNo;
	private String 	bkDt;
	private String 	bkacptDt;
	private String 	empNo;
	private String 	ownerNm;
	private String 	telNo;
	private String 	forwdNm;
	private String 	corpnoFw;
	private String 	forwdPrsn;
	private String 	forwdTel;
	private String 	shipNm;
	private String 	corpcoOw;
	private String 	factorNm;
	private String 	factorLoc;
	private String 	contactNm;
	private String 	attnNm;
	private String 	zipCd;
	private String 	shipCd;
	private Long 	callYy;
	private Long 	callNo;
	private String 	voyageNo;
	private String 	callSign;
	private String 	pol;
	private String 	pod;
	private String 	dest;
	private String 	depotNm;
	private String 	termiNm;
	private String 	reqArrDt;
	private String 	transCd;
	private String 	carrierNm;
	private String 	dummyYn;
	private String 	carrierTel;
	private String 	flock;
	private String 	tlock = "";
	private String 	remark;
	private String 	shuttleYn;
	private String 	chargeYn;
	private String  bkEamt;
	private String  bkAamt;
	private String  tyGrpCd;
	private String  szCd;
	private String cyCd = "";
	private String handCd;
	private int cntrCount;
	private int cntrCount1;
	private String truckerNm;
	
	private String tyCd1;
	private String szCd1;
	private String cnt1;
	private String tyCd2;
	private String szCd2;
	private String cnt2;
	private String tyCd3;
	private String szCd3;
	private String cnt3;
	
	private String corpcoOw1;
	private String corpcoOw2;
	private String corpcoOw3;
	
	private List<String> cntrFixList;
	private List<String> cntrOutList;
}
