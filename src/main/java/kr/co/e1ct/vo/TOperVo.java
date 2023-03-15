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
public class TOperVo extends VO {
	private @NonNull String operCd;
	private String countryCd;
	private String operKnm;
	private String operKsnm;
	private String operEnm;
	private String operEsnm;
	private String cstNo;
	private String portmisCd;
	private String vatCd;
	private String ondock;
	private String hyunsu;
	private String rfhyunsu;
	private long backcolor;
	private long forecolor;
	private String bkChk;
	private String mvChk;
	private String locChk;
	private String doChk;
	private String cnclCd;
	private String goutChk;
	private String fvpChk;
	private String operChk;
	private String mginChk;
	private String mginallChk;
	private String routeChk;
	private String demurChk;
	private String cyCd;
	private String slDemurChk;
	private String slFvpChk;
	private String slOperChk;
	private String slMginChk;
	private String slMginallChk;
	private String slOndock;
	private String slBkChk;
	private String slMvChk;
	private String slLocChk;
	private String slDoChk;
	private String slCnclCd;
	private String slGoutChk;
	private String opEmpNo;
	private Date opUpdDt;
	private String iginChk;
	private String dosndChk;
	private String repairGrp;
	private String webCd;
	private long iginIlsu;
	private String autoMail;
	private String mailType;
	private String mailAdr;
	private String mtPstnChk;
	private String liftType;
	private String rfTime;
	private String rfTimeUnit;
	private String autoGoutType;
	private String gradeChk;
	private String inspChk;
}
