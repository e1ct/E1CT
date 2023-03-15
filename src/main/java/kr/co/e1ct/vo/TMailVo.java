package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TMailVo extends VO {
	private String 	mailId;
	private String 	operCd;
	private String 	lineCd;
}
