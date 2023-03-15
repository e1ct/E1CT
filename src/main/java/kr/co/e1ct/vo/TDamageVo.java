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
public class TDamageVo extends VO {
	private String 	cntrNo;
	private Long 	dupNo;
	private Date 	dmgDt;
	private String 	dmgYn;
	private String 	dmgDesc;
	private Date 	creDt;
	private String 	empNo;
	private String 	holdCd;
	private String 	eirNo;
	private String 	repairYn;
	private String 	dmgChk1;
	private String 	dmgChk2;
	private String 	dmgChk3;
	private String 	dmgDes1;
	private String 	dmgDes2;
	private String 	dmgDes3;
	private Date 	repairDt;
	private String 	preRepair;
	private String 	cleanCd;
	private String 	dmgLoc;
	private Date 	mvntRqDt;
	private String 	fixComp;
	private String 	mvntChk;
	private String 	tpcYn;
	private Date 	prerepairDt;
}
