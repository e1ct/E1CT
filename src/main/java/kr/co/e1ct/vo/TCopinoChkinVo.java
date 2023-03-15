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
public class TCopinoChkinVo extends VO {
	private String 	cntrNo;
	private String 	operCd;
	private String 	flagCd;
	private Date 	creDt;
	private String 	empNo;
	private String 	fileNm;
	private String 	rmk;
}
