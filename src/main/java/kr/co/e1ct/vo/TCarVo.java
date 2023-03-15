package kr.co.e1ct.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TCarVo extends VO {

	private @NonNull String truckerCd;
	private @NonNull String carNo;
	private String carNm;
	private String drvNm;
	private String carTel;
	private Date barissuDt;
	private String sndId;
	private String remark;
	private String noholdYn;
	private String shfYn;
	private String lodYn;
	private String disYn;
	private String disCarNo;
	private String lodGc;
	private String shfSictBlock;
	private String shfSlcBlock;
	private String comChk;
	private String sndIdRm;
	private String chassCd;
	private String smsYn;
}
