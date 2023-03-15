package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TbRvasMastVo {

	private String bankCd;
	private String orgCd;
	private String vacctNo;
	private String statCd;
	private String inGb;
	private Long payAmt;
	private String payFromDate;
	private String payToDate = "";
	private String custCd;
	private String custNm;
	private String entryDate;
	private String entryIdno;
	private String sendType;
	private String sendYn;
	private int vacctSeq;
}
