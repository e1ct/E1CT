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
public class TBsiotVo extends VO {

	private String cargoNo;
	private String banipCd;
	private String mstblNo;
	private String husblNo;
	private String hsnSuriNo;
	private String blType;
	private String pol;
	private String hsnJsCd;
	private String cargoCd;
	private String gwa;
	private String custCd;
	private String pumCd;
	private String pumNm;
	private String packUnit;
	private long packCnt;
	private double packWt;
	private double packCbm;
	private String iaccdCd;
	private String oaccdCd;
	private Date singoDt;
	private String outExpCd;
	private String jobCd;
	private String outExpNo;
	private String cstNo;
	private String tongNm;
	private String tongJuso;
	private String tongTel;
	private long cntrSu;
	private String insndCd;
	private String outsndCd;
	private Date updDt;
	private String shipCd;
	private String callNo;
	private String callYy;
	private long iacdCnt;
	private double iacdWt;
	private String backlogNo;
	private String allotCt;
	private String total;
	private String dsum;
	private String operCd;
	private String blNo;
	private String gubun;
}
