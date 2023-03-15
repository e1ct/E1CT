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
public class TEquiVo extends VO {
	private String 	eqNo;
	private String 	eqName;
	private String 	eqMod;
	private String 	eqMake;
	private String 	eqDate;
	private Long 	eqAmt;
	private Long 	eqLife;
	private Long 	eqHigh;
	private Long 	eqWide;
	private Long 	eqLeng;
	private Long 	eqWgt;
	private String 	manufacNo;
	private Date 	manufacDt;
	private String 	eqNewold;
	private String 	eqNation;
	private Date 	eqFitdt;
	private Date 	eqPchkdt;
	private Date 	eqChkdt;
	private Date 	eqTerdt;
	private String 	custYn;
}
