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
public class TInLiftCashCntrVo extends VO {
	private String 	cntrNo;
	private Long 	dupNo;
	private String 	liftType;
	private Date 	cashDate;
	private Date 	uptDt;
	private String 	uptEmpno;
	private String 	liftNm;
	private String 	sz;
	private String 	operCd;
	private String 	shipCd;
}
