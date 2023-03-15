package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TEdicustomerVo extends VO {
	private @NonNull String cstId;
	private @NonNull String terminalId;
	private @NonNull String docu;
	private String cstNm;
	private String telNo;
	private String qualifier;
	private String clerkNm;
	private String cstNo;
	private String operCd;
	private String holdCd;
	private String breakChk;
	private String breakDesc;
	private String smsYn;
	private String handphone;
}
