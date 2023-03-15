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
public class TFreevanpoolVo extends VO {
	private String 	operCd;
	private Date 	bgnDt;
	private Long 	fullQt;
	private String 	fullUnit;
	private Long 	emptyQt;
	private String 	emptyUnit;
	private String 	alterCd;
	private Date 	updDt;
	private String 	empNo;
	private String 	adjFlg;
}
