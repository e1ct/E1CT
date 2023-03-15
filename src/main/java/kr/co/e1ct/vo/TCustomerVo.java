package kr.co.e1ct.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TCustomerVo extends VO {
	private String 	cstNo;
	private String 	bkagCd;
	private String 	cstNm;
	private String 	cstType;
	private String 	cstTrad;
	private String 	operOwnerNm;
	private String 	zipCd;
	private String 	addr;
	private String 	addr1;
	private String 	addr2;
	private String 	hpNo;
	private String 	telNo;
	private String 	faxNo;
	private String 	cancelDt;
	private String 	vatCd;
	private String 	cstDam;
	private String 	cstEmpno;
	private String 	bizDt;
	private String 	wayCd;
	private String 	catalog;
	private Long 	schedule;
	private String 	receiver;
	private String 	jumCd;
	private String 	crtDt;
	private String 	crtEmp;
	private String 	badDt;
	private String 	badEmp;
	private String 	badNae;
	private String 	cstHdrNo;
	private String 	custId;
	private String 	email;
	private String 	deputyId;
	private String 	ediYn;
	private String 	ediCharge;
	private String 	ediChargeTel;
	private String 	smsYn;
	private String 	handphone;
	
	// for T_CUSTOMER_HDR
	private String corpFlag;
	
	// for T_CASH
	private String cntrNo;
	private String dealDd;
	private String overCash;
	private String tempCash;
	private String chkCash;
	private String xrayCash;
	private String liftCash;
	private String carCash;
}
