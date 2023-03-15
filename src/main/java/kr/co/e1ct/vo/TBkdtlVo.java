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
public class TBkdtlVo extends VO {
	private String 	operCd;
	private String 	bkNo;
	private String 	szCd;
	private String 	tyGrpCd;
	private Long 	bkEamt;
	private Long 	bkAamt;
	private Date 	reqArrDt;
	private String 	cxlCd;
	private Long 	banchulAmt;
	private Long 	mibanchulAmt;
	private Long 	banipAmt;
	private Long 	mibanipAmt;
	private String 	cyCd = "";
	private String 	bkCd;
	private String 	handCd;
	
	private int cnt;
}
