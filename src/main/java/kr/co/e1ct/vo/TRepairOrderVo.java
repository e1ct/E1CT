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
public class TRepairOrderVo extends VO {
	private String 	cntrNo;
	private Long 	dupNo;
	private Date 	requestDt;
	private String 	operCd;
	private String 	szCd;
	private String 	tyCd;
	private String 	fmCd;
	private String 	requestId;
	private String 	acceptEmpNo;
	private Date 	acceptDt;
	private String 	odEmpNo;
	private Date 	odDt;
	private Date 	repairDt;
	private Date 	inDt;
}
