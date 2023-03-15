package kr.co.e1ct.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TCntrVo extends VO {

	private String cntrNo;
	private String dupNo;
	private String shipCd;
	private Long callYy;
	private Long callNo;
	private String operCd;
	private String curStat;
	private String pod;
	private String dest;
	private String pol;
	private String fmCd;
	private String szCd;
	private String tyCd;
	private float weight;
	private String yTblock;
	private String[] yTblockArr;
	private Long yTbay;
	private Long yTrow;
	private Long yTtier;
	private String stucking;
	private String sTbay;
	private String sTrow;
	private String sTtier;
	private String eirNo;
	private String tsId;
	private String rtnCd;
	private String updDt;
	private String updEmpno;
	private String yStype;
	private String ySind;
	private String yFblock;
	private Long yFbay;
	private Long yFrow;
	private Long yFtier;
	private String sFbay;
	private Long sFrow;
	private Long sFtier;
	private String sType;
	private String sSind;
	private String viaLoc;
	private Long shiftTm;
	private String sealNo;
	private String tyGrpCd;
	private String dirId;
	private String bondId;
	private String bondDt;
	private String custDt;
	private String taShipCall;
	private String overlCd;
	private String overdCd;
	private String cashCd;
	private Long cashDay;
	private String ginDt;
	private String gcLdt;
	private String gcDdt;
	private String greoDt;
	private String goutDt;
	private String stckDt;
	private String cllDt;
	private String baplieChk;
	private String dmgCd;
	private String ptiYn; 
	private String precoolYn;
	private String commonId;
	private String ovdema;
	private String billCd;
	private String truckerNm;
	private String truckerCd;
	private String carNo;
	private String hdeckId;
	private Long gcBseq;
	private String eqPlno;
	private Long eqPlseq;
	private String delvTp;
	private String cargoTp;
	private String trnIn;
	private String trnOut;
	private String reefId;
	private String inspId;
	private String pickCd;
	private String holdCd;
	private Long updFlag;
	private String hndlInstru;
	private String absoCnstra;
	private String noChung;
	private String opercmd;
	private String socYn;
	private String onoffYn;
	private String temp;
	private String tempId;
	private String imdg;
	private String unno;
	private String dgYtype;
	private String dgFgrade;
	private String unnoCopi;
	private Long ovH;
	private Long ovWs;
	private Long ovWp;
	private Long ovLf;
	private Long ovLb;
	private String hwlCd;
	private Long ocH;
	private Long ocWs;
	private Long ocWp;
	private String exShipCall;
	private String exTsId;
	private String exPod;
	private String operCd1;
	private String opod1;
	private String opod2;
	private String opod3; 
	private String cashCd1;
	private String cashDay1;
	private String imdg1;
	private String sealNo1;
	private String repairCd;
	private String cleanCd;
	private String copinoYn;
	private String remark;
	// private String void; VOID	VARCHAR2(10 BYTE) // 예약어 케이스
	private String exOperCd;
	private String tsId2;
	private String reefChk;
	private String ownerNm;
	private String cyWkCd;
	private String sndIdIn;
	private String sndIdOut;
	private String truckerCdOut;
	private String carNoOut;
	private String rfRepairYn;
	private Long realWeight;
	private String remakrs;
	private String shipCdOper;
	private String operVoy;
	private String operCd2;
	private String cntrGrade;
	private String dgCtlYn;
	
	private String bkdoNo;
	private String bkNo;
	
	private int imp;
	private int exp;
	private int ondock;
	private int size20;
	private int sizef40;
	private int sizef45;
	private int van;
	private int teu;
	
	private String sztp;
	
	private String atbDt;
	private String fromDt;
	private int yardDay;
	private String taShipDt;
	
	private String shipNm;
	private String operKnm;
	private String cargoNo;
	private String sealNo2;
	
	private String orderNo;
	private String shipLoca;
	private String inDt;
	private String outDt;
	
	private String reqTemp;
	private String plinDt;
	private String ploutDt;
	
	private String ov1;
	private String ov2;
	private String ov3;
	
	private String f20;
	private String f40;
	private String f45;
	private String m20;
	private String m40;
	private String m45;
	private String t20;
	private String t40;
	private String t45;
	
	private String copinoSeal;
	
	private int cnt;
	
	private int ff20i;
	private int ff40i;
	private int ff40hqi;
	private int ff45i;
	private int tifful;
	private int ff20x;
	private int ff40x;
	private int ff40hqx;
	private int ff45x;
	private int txfful;
	private int ff20m;
	private int ff40m;
	private int ff40hqm;
	private int ff45m;
	private int tofful;
	private int mm20i;
	private int mm40i;
	private int mm40hqi;
	private int mm45i;
	private int timmul;
	private int mm20x;
	private int mm40x;
	private int mm40hqx;
	private int mm45x;
	private int txmmul;
	private int mm20m;
	private int mm40m;
	private int mm40hqm;
	private int mm45m;
	private int tmmmul;
	private int hm20ixm;
	private int hm40ixm;
	private int hm40hqixm;
	private int hm45ixm;
	private int htmmmul;
	
	private String repairDt;
	private String repairYn;
	private String dmgDt;
	private String dmgYn;

	private String dmgDesc;
	private String dmgDes2;
	private String dmgDes3;
	
	private String dmgChk1;
	private String dmgChk2;
	private String dmgChk3;
	
	private String overdays;
	private String status;
	
	private String ibaseDay;
	private String iovDay;
	
	private String cargoNo1;
	
	private String requestDt;
	private String odDt;
	private String acceptDt;
	
	private String greqDt;
	
	private String ilsu;
	
	private String iTruckerCd;
	private String iCarNo;
	private String iSndId;
	private String iCustomer;
	
	private String oTruckerCd;
	private String oCarNo;
	private String oSndId;
	private String oCustomer;
	
	private String carrier;
	private String truckerName;
	
	private String empNo;
	private String rejectOper;
	private String rejectIn;
	private String rejectFirst;
	private String rmk;
	private String fnBeforeOper;
	
	private String fnGetSztp;
	
	private String doNo;
	private String rlsDt;
	private String copYn;
	private String closingDt;
	
	private String rss1;
	
	private TCntrVo rs1;
	private String blNo;
	
	private List<String> cargoNoList;
	private List<String> cargoNo1List;
	
	private String gp20;
	private String gp40;
	private String hq40;
	private String gp45;
	private String tot;
}

