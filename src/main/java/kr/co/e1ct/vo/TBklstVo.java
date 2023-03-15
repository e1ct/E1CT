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
public class TBklstVo extends VO {
	private String operCd;
	private String bkNo;
	private String cntrNo;
	private String dupNo;
	private long seqNo;
	private Date updDt;
	private String empNo;
	private String copYn;
	private String carNm;
	private String carNo1;
	private String carNo2;
	private String carPrsn;
	private String carTel;
	private String yTblock;
	private long yTbay;
	private long yTrow;
	private long yTtier;
	private String shipCd;
	private long callYy;
	private long callNo;
	private String pod;
	private String dest;
	private String szCd;
	private String tyGrpCd;
	private String sealNo;
	private Date reqArrDt;
	private String fixCd;
	private String chassNo;
	private String fixedCd;
	private String truckerCd;
	
	private int cnt;
}
