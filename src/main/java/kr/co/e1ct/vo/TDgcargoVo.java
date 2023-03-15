package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TDgcargoVo extends VO {
	private String 	cntrNo;
	private Long 	dupNo;
	private String 	shipCd;
	private Long 	callYy;
	private Long 	callNo;
	private String 	shipYymm;
	private String 	unno;
	private String 	imdg;
	private String 	imdg1;
	private String 	dgYtype;
	private String 	dgFgrade;
	private Long 	dgJisu;
	private String 	dgNm;
	private String 	unnoCopi;
	private Long 	packWt;
}
