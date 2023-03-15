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
public class TMovelogVo extends VO {
	private String 	cntrNo;
	private Long 	dupNo;
	private String 	ixCd;
	private String 	shipCd;
	private Long 	callYy;
	private Long 	callNo;
	private Date 	ioDt;
	private String 	operCd;
	private String 	chassNo;
	private String 	ownerNm;
	private String 	ownerLoc;
	private String 	locCd;
	private String 	hndlCd;
	private String 	orderNo;
	private String 	exShipNm;
	private String 	exVoyage;
	private Date 	exAtaDt;
	private String 	tashipCd;
	private Date 	creDt;
	private String 	empNo;
	private String 	truckerCd;
	private String 	carNo;
	private String 	sndId;
	private String 	shipCdOper;
	private String 	operVoy;
	private String 	cyCd;
	private String doNo;
}
