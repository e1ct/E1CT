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
public class TDomstVo {
	private String 	doNo;
	private Date 	doDt;
	private String 	operCd;
	private String 	telNo;
	private String 	factorNm;
	private String 	factorLoc;
	private String 	shipCd;
	private Long 	callYy;
	private Long 	callNo;
	private String 	voyageNo;
	private String 	callSign;
	private String 	consgnNm;
	private String 	shipNm;
	private String 	depotNm;
	private String 	termiNm;
	private String 	empNo;
	private String 	operDo;
	private String 	edisndCd;
	private String 	selfTrans;
	private String 	remark;
	private String  closingDt;
	private String  carNm;
}
