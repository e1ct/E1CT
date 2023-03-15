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
public class TCarbklstVo extends VO {

	private String 	truckerCd;
	private String 	carNo;
	private String 	passCd;
	private String 	penaCd;
	private Long 	penaTm;
	private String 	empNo;
	private String 	penaDes;
	private Date 	penaDt;
	private String 	forbidYn;
	private String 	passEmpNo;
	private Date 	passDt;
	private String 	passDes;
	private String 	penaScore;
}
