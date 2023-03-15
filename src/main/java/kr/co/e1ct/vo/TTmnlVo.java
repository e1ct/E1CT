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
public class TTmnlVo extends VO {
	private String 	tmnlCd;
	private String 	tmnlNm;
	private String 	type;
	private String 	trad;
	private String 	telNo;
	private String 	faxNo;
	private String 	addr;
	private String 	zip;
	private String 	ownerNm;
	private String 	loc;
	private String 	busiidNo;
	private String 	product;
	private String 	seagan;
	private String 	kwa;
	private String 	state;
	private String 	city;
	private String 	bondAreaNo;
	private String 	portCd;
	private Long 	length;
	private Long 	height;
	private Long 	maxblock;
	private Long 	maxgc;
	private Long 	maxtc;
	private Long 	maxsc;
	private Long 	maxyt;
	private Long 	maxtl;
	private Long 	maxrs;
	private Long 	maxberth;
	private Long 	maxbitt;
	private String 	updEmpno;
	private Date 	updateTime;
}
