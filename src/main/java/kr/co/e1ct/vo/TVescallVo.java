package kr.co.e1ct.vo;

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
public class TVescallVo {
	private String 	shipCd;
	private Long 	callYy;
	private String 	callNo;
	private String 	ctpCd;
	private String 	ibVoy;
	private String 	obVoy;
	private String 	shipYymm;
	private Long 	callYseq;
	private Long 	callMseq;
	private String 	operCd;
	private String 	plBerthNo;
	private String 	plPsId;
	private String 	berthNo;
	private String 	psId;
	private String 	yardOpenDt;
	private String 	closingDt;
	private String 	longEtaDt;
	private String 	longEtbDt;
	private String 	etaDt;
	private String 	etbDt;
	private String 	etdDt;
	private String 	etstvBeginDt;
	private String 	etstvEndDt;
	private String 	atbDt;
	private String 	ataDt;
	private String 	atdDt;
	private String 	disBeginDt;
	private String 	disEndDt;
	private String 	lodBeginDt;
	private String 	lodEndDt;
	private String 	stvBeginDt;
	private String 	stvEndDt;
	private String 	pilotOnDt;
	private String 	pilotOffDt;
	private String 	ancInDt;
	private String 	ancOutDt;
	private Long 	dftHr;
	private String 	delayCd;
	private Long 	lodCnt;
	private Long 	lodCntteu;
	private Long 	disCnt;
	private Long 	disCntteu;
	private Long 	sftCnt;
	private Long 	sftCntteu;
	private Long 	tsCnt;
	private Long 	tsCntteu;
	private Long 	hthCnt;
	private Long 	bulkCnt;
	private Long 	pusCall;
	private String 	jointCd;
	private String 	operVoy;
	private String 	inRoute;
	private String 	outRoute;
	private Long 	deckPos;
	private String 	bitPos;
	private Long 	berthIdx;
	private String 	planId;
	private String 	deungCd;
	private String 	jaeDeungCd;
	private String 	deungDt;
	private String 	remark;
	private Long 	nougcs;
	private Long 	sbitt;
	private Long 	ebitt;
	private String 	state;
	private Long 	bbit;
	private Long 	bbitt;
	private String 	cancelYn;
	private String 	exTmnl;
	private String 	exShipCd;
	private Long 	exCallYy;
	private Long 	exCallNo;
	private String 	catb;
	private String 	catd;
	private Long 	cdisCnt;
	private Long 	clodCnt;
	private Long 	csftCnt;
	private String 	remark2;
	private String 	nxeta;
	private String 	nxport;
	private String 	mrn;
	private String 	hOperCd;
	private String 	hthCd;
	private Long 	overtime1;
	private Long 	overtime2;
	private Long 	overtime3;
	private Long 	overtime4;
	private String 	cyCd;
	private Long 	actTeu;
	private String 	cxlYn;
	private String 	socIbVoy;
	private String 	socObVoy;
	private String 	socIbVoy2;
	private String 	socObVoy2;
	private String 	maeShip;
	
	// TVesselVo
	private String shipNm;
	
	// for berthText
	private long iv;
	private long xv;
	private long s2v;
	private String depDt;
	
	// for vesselWork
	private long disRest;
	private long disFin;
	private long lodRest;
	private long lodFin;
	private long eqPlno;
	
	// for vesselWorkDetail
	private long gcBay;
	private String isArrived;
	private int vesselWorkCount;
	
	private long rnum;
	
	private String backcolor;
	private String forecolor;
	
	private String rtnDt;
}
