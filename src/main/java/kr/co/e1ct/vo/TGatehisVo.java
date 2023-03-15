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
public class TGatehisVo extends VO {
	private String truckerCd;
	private String carNo;
	private String cntrNo;
	private String sndId;
	private Date rcvDt;
	private Date sndDt;
	private String ioCd;
	private String shipCd;
	private int callYy;
	private int callNo;
	private String operCd;
	private Date ginoutDt;
	private String updCd;
	private String szCd;
	private String tyCd;
	private String fmCd;
	private float weight;
	private String socYn;
	private String dmgYn;
	private String telNo;
	private String temp;
	private String tempId;
	private String imdg;
	private String unno;
	private int ovH;
	private int ovWp;
	private int ovWs;
	private int ovLf;
	private int ovLb;
	private String pod;
	private String dest;
	private String pol;
	private String sealNo;
	private String flocation;
	private String ioUdest;
	private String doNo;
	private String bkNo;
	private String ioBaecha;
	private String tranTp;
	private String blNo;
	private String dupChk;
	private String dlvOk;
	private String onLine;
	private String errCd;
	private String tsId;
	private String opod1;
	private String opod2;
	private String opod3;
	private String bondId;
	private String holdCd;
	private String errDes;
	private String chassNo;
	private String mvCd;
	private String locCd;
	private String ownerNm;
	private Date estDt;
	private String ginOk;
	private String rmk;
	private Date updDt;
	private String empNo;
	private String rejectOper;
	private String rejectFirst;
	private String rejectIn;
	private String ixCd;
	private String wkCd;
	private String eqPlno;
	private String delvTp;
	private String cyCd;
	private Date smsDt;
	private String dmgLoc;
	private String shipCdOper;
	private String operVoy;
	private String orgSndId;
	private String exportNo;
	private String carNm;
	private String repoertCd;
	private String sndYn;
	private String dmgChk;
	private String inOutTimeLimit;
}
