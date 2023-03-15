package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WCustItemVo extends VO {
	private Long 	itemId;
	private Long 	custId;
	private String 	itemNm;
	private String 	unit;
	private String 	qty;
	private String 	weight;
	private String 	modelNo;
	private String 	cartonNo;
	private String 	packageNo;
}
