package kr.co.e1ct.vo;

import lombok.Setter;
import lombok.ToString;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchVo {

	private String search;
	private String search2;
	private String strYear;
	private String strMonth;
	private String shipNm;
	private String orderCd;
	private String cd;
	private String size;
	private String block;
	private String bay;
	private String ioCd;
	private String searchText;
	private String truckerCd; // cntrList/copino
	private String truckerCd1;
	private String carNo; // cntrList/copino
	private String carNo1;
	private String cntrNo; // cntrList/copino
	private String oper;
	private String shipName;
	private String shipCd;
	private String fmCd;
	private String szCd;
	private String operCd;
	private String callNo;
	private String callYy;
	private String logis;
	private String gubun;
	private String searchType;
	private String searchStartDt;
	private String transId;
	private String searchEndDt;
	private String sortBy;
	private String jo;
	private String cstNo;
	private String okCd;
	private String blNo;
	private String fmType;
	private String stvEndDt;
	private String bkNo;
	private String tyCd;
	private String sort;
	private String empNo;
	private String ixCd;
	private String dmgCd;
	private String pod;
	private String reqId;
	private String comp;
	private String custId;
	private String mrn;
	private String msn;
	private String hsn;
	private String item;
	private String unit;
	private String qty;
	private String weight;
	private String modelNo;
	private String itemId;
	private String itemNm;
	private String packNo;
	private String cartonNo;
	private String packageNo;
	private String noticeDt;
	private String nDate;
	private String chk;
	private String cargoNo;
	private String banipCd;
	private String iso;
	private String reqComp;
	private String reqTel;
	private String inspGubun;
	private String inspType;
	private String curStat;
	private String dupNo;
	private String user;
	private String eqNo;
	private String ship;
	private String shipLeft;
	private String shipRight;
	private String eqPlseq;
	private String bays;
	private String hdeckId;
	private String chkEq;
	private String side;
	private String[] baysList;
	private String yardDay;
	private String searchType2;
	private String portNm;
	private String code;
	private String tempId;
	private String tempCd;
	private String orderNo;
	private String podCd;
	private String dgCode;
	private String sealNo;
	private String cioCd;
	private String ctpCd;
	private String cmcNo;
	private String posCd;
	private String polCd;
	private String pcDupno;
	private String overW;
	private String overL;
	private String overH;
	private String sndId;
	private String cunBl;
	private String banCi;
	private String cSzCd;
	private String locCd = "";
	private String bondCd;
	private String customerNm;
	private String estDay;
	private String xCyCd;
	private String reject;
	private String cnt;
	private String corpcoOw = "";
	private String pol;
	private String dest = "";
	private String tlock;
	private String carrierNm;
	private Long bkEamt;
	private String contactNm;
	private String telNo;
	private String factorNm;
	private String factorLoc;
	private String corpCo1;
	private String corpCo2;
	private String corpCo3;
	private String remark;
	private String doNo;
	private String cargoType;
	private List<String> cntrList;
	private List<String> cargoNoList;
	private List<String> operList;
	private Pageable pageable;
	private int pageSize;
	private int pageNumber;
	private String url;
	
	
	public void setCustomPageable( Pageable pageable ) {
		setPageable( pageable );
		setPageNumber( pageable.getPageNumber() );
		setPageSize( pageable.getPageSize() );
	}
}
