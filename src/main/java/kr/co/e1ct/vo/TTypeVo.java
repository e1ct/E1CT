package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TTypeVo extends VO {
	private String 	tyCd;
	private String 	tyNm;
	private String 	tySnm;
	private String 	tyGrpCd;
	private String 	tyGrpNm;
	private String 	tyCd1;
	private String 	useflag;
}
