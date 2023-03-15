package kr.co.e1ct.vo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "T_VESSEL")
public class TVesselVo extends VO {
	@Id
	private String 	shipCd;
	private String 	shipNm;
	private String 	ctpCd;
	private String 	callSign;
	private Long 	grston;
	private Long 	netton;
	private Long 	loa;
	private Long 	lbp;
	private Long 	width;
	private Long 	depth;
	private Long 	draft;
	private Long 	sdraft;
	private Long 	dsdraft;
	private Long 	speed;
	private Long 	avgSpeed;
	private Long 	maxteu;
	private String 	operCd;
	private String 	statCd;
	private String 	owner;
	private String 	defberth;
	private String 	shipGrp;
	private String 	cellCd;
	private Long 	spCrane;
	private String 	inmarsat;
	private String 	lloyd;
	private Long 	tophgt;
	private Long 	anhgt;
	private Long 	nohatchs;
	private Long 	nobays;
	private Long 	dmaxrows;
	private Long 	hmaxrows;
	private Long 	dmaxtiers;
	private Long 	hmaxtiers;
	private Long 	bgfont;
	private Long 	bgback;
	private String 	bghatch;
	private Long 	gbrdfpos;
	private Long 	stemclr;
	private Long 	sternclr;
	private Long 	disp;
	private Long 	deadton;
	private Long 	lightwgt;
	private Long 	lightlcg;
	private Long 	lightvcg;
	private Long 	lightfsm;
	private Long 	constwgt;
	private Long 	constlcg;
	private Long 	constvcg;
	private Long 	constfsm;
	private String 	bnrule;
	private String 	stresscal;
	private Long 	dpsdraft;
	private Long 	notanks;
	private Long 	nohydro;
	private Long 	nolongit;
	private String	vslInfo;
	private Date 	updDt;
	private String 	lashingDeck;
	private String 	lashingHold;
	private String 	cyCd;
	private String 	shipCdOper;
	private String 	deck2040Yn;
	private String 	only20Bay;
	private String 	only40Bay;
	private String 	telOper;
	private String 	telChecker;
	private String 	noteShip;	
}
