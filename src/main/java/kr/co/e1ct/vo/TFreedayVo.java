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
public class TFreedayVo extends VO {
	private String 	operCd;
	private String 	ixCd;
	private String 	cargoCd;
	private String 	fmCd;
	private Date 	bgnDt;
	private Long 	freeDay;
	private String 	etc;
}
