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
public class TCustomerHdrVo extends VO {
	private String 	cstHdrNo;
	private String 	cstNm;
	private String 	cstSnm;
	private String 	cstKind;
	private String 	corpFlag;
	private String 	custHdrId;
	private Date 	crtDt;
	private String 	crtEmp;
}
