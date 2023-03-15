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
public class TCashPlanVo extends VO {

	private String 	cntrNo;
	private Long 	dupNo;
	private String 	dealDd;
	private String 	szCd;
	private String 	tyCd;
	private String 	curStat = "";
	private String 	cstNo;
	private String 	vacctNo;
	private String 	closeDd;
	private String 	statCd;
	private Date 	inDt;
	private Date 	outDt;
	private String 	overYn;
	private Long 	overDay;
	private Long 	overAmt;
	private Long 	overCash;
	private String 	tempYn;
	private Long 	tempDay;
	private Long 	tempAmt;
	private Long 	tempCash;
	private String 	liftYn;
	private Long 	liftQty;
	private Long 	liftAmt;
	private Long 	liftCash;
	private Long 	chkAmt;
	private Long 	chkCash;
	private Long 	xrayAmt;
	private Long 	xrayCash;
	private Long 	carAmt;
	private Long 	carCash;
	private Long 	cfsCbm;
	private Long 	cfsAmt;
	private Long 	cfsCash;
	private Long 	etcCash;
	private String 	etcFlag;
	private String 	remark;
	private String 	taxYn;
	private Long 	saleAmt;
	private Long 	taxAmt;
	private String 	ioCd;
	private String 	returnYn;
	private Long 	returnAmt;
	private Long 	returnCash;
	private String 	cstNoSub;
	private String 	taxSeq;
}
