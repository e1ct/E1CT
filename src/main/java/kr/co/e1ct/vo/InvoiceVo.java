package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InvoiceVo {

	private String cntrNo;
	private String cstCd;
	private String vacctNo;
	private String closeDd;
	private String banCi;
	private Long totalAll;
	private Long dupNo;
	private Long dupNo2;
	private String shipCd;
	private Long callNo;
	private Long callYy;
	private String szCd;
	private String tyCd;
	private String overYn;
	private Long overDay;
	private Long overAmt;
	private Long overUt;
	private String tempYn;
	private Long tempDay;
	private Long tempAmt;
	private Long tempUt;
	private Long chkAmt;
	private Long xrayAmt;
	private String liftYn;
	private Long liftQty;
	private Long liftAmt;
	private Long liftUt;
	private String returnYn;
	private Long returnAmt;
	private Long dgAmt;
	private String ixCd;
	private Long totalInvoice;
	private Long totalTax;
	private Long saleAmt;
}
