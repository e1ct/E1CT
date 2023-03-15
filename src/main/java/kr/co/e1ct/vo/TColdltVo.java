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
public class TColdltVo extends VO {
	private @NonNull String shipCd;
	private int callYy;
	private int callNo;
	private String cntrNo;
	private String operCd;
	private String szCd;
	private String tyCd;
	private String fmCd;
	private String pod;
	private float weight;
	private String temp;
	private String tempId;
	private String imdg;
	private String unno;
	private String sealNo;
	private String tsId;
	private String ioChk;
	private String dest;
	private String opod1;
	private String hwlCd;
	private long ovH;
	private long ovWs;
	private long ovWp;
	private long ovLf;
	private long ovLb;
	private String socyn;
	private String socOper;
	private String bkNo;
	
	private int f20;
	private int f40;
	private int f45;
	private int m20;
	private int m40;
	private int m45;
	private int van;
	private int teu;
	
	private String ginDt;
	private String dirId;
	private String curStat;
	private String overdays;
	
	private String ibaseDay;
	private String iovDay;
}
