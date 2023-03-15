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
public class WCustHdVo extends VO {
	private Long 	custId;
	private String 	cargoNo;
	private String 	banipCd;
	private String 	cntrNo;
	private String 	operCd;
	private String 	iso;
	private String 	space;
	private String 	inspType;
	private String 	reqId;
	private String 	reqComp;
	private String 	reqEmp;
	private String 	reqTel;
	private String 	cargoType;
	private String 	noticeDt;
	private String 	admDt;
	private Date 	crtDt;
	private String 	inspStat;
	private String 	inspGubun;
	
	private String mrn;
	private String msn;
	private String hsn;
	
	private int itemCount;
}
