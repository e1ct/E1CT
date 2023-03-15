package kr.co.e1ct.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.co.e1ct.constant.ExcelConstant;
import kr.co.e1ct.mappers.CommonRepository;
import kr.co.e1ct.mappers.TBklstRepository;
import kr.co.e1ct.mappers.TBsiotRepository;
import kr.co.e1ct.mappers.TBsiotSubRepository;
import kr.co.e1ct.mappers.TCntrRepository;
import kr.co.e1ct.mappers.TCopinoRjctRepository;
import kr.co.e1ct.mappers.TDodtlRepository;
import kr.co.e1ct.mappers.TEdicustomerRepository;
import kr.co.e1ct.mappers.TGateRepository;
import kr.co.e1ct.mappers.TGatehisRepository;
import kr.co.e1ct.mappers.THoldngRepository;
import kr.co.e1ct.mappers.TRfcargoRepository;
import kr.co.e1ct.mappers.TSecretRepository;
import kr.co.e1ct.mappers.TVescallRepository;
import kr.co.e1ct.util.StringUtils;
import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBklstVo;
import kr.co.e1ct.vo.TBsiotSubVo;
import kr.co.e1ct.vo.TBsiotVo;
import kr.co.e1ct.vo.TCntrVo;
import kr.co.e1ct.vo.TCopinoRjctVo;
import kr.co.e1ct.vo.TDodtlVo;
import kr.co.e1ct.vo.TEdicustomerVo;
import kr.co.e1ct.vo.TGateVo;
import kr.co.e1ct.vo.TGatehisVo;
import kr.co.e1ct.vo.THoldngVo;
import kr.co.e1ct.vo.TRfcargoVo;
import kr.co.e1ct.vo.TSecretVo;
import kr.co.e1ct.vo.TVescallVo;

@Service
public class ExcelService {
	
	@Autowired
	private TCopinoRjctRepository tCopinoRjctRepository;
	
	@Autowired
	private TVescallRepository tVescallRepository;
	
	@Autowired
	private TGateRepository tGateRepository;
	
	@Autowired
	private TGatehisRepository tGatehisRepository;
	
	@Autowired
	private TCntrRepository tCntrRepository;
	
	@Autowired
	private TEdicustomerRepository tEdicustomerRepository;
	
	@Autowired
	private TBklstRepository tBklstRepository;
	
	@Autowired
	private TDodtlRepository tDodtlRepository;
	
	@Autowired
	private TBsiotRepository tBsiotRepository;
	
	@Autowired
	private TBsiotSubRepository tBsiotSubRepository;
	
	@Autowired
	private TRfcargoRepository tRfcargoRepository;
	
	@Autowired
	private TSecretRepository tSecretRepository;
	
	@Autowired
	private CommonRepository commonRepository;
	
	@Autowired
	private THoldngRepository tHoldngRepository;
	
	public SearchVo setOperList( SearchVo searchVo ) {
		SearchVo returnVo = searchVo;
		
		TSecretVo tSecretVo = new TSecretVo();
		tSecretVo.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
		tSecretVo = tSecretRepository.findByEmpNo( tSecretVo );
		
		List<HashMap<String, Object>> sessionOperList = commonRepository.findOperCdListFromSession(tSecretVo);
		List<String> operList = new ArrayList<String>();
		for( int i=0; i<sessionOperList.size(); i++ ) {
			operList.add( sessionOperList.get(i).get("OPER_CD").toString() );
		}
		returnVo.setOperList( operList );
		
		return returnVo;
	}
	
	public Map<String, ?> berthTextExcel(SearchVo searchVo) throws ParseException {
		List<TVescallVo> tempList = tVescallRepository.berthTextPrint( searchVo );
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		for( int i=0; i<tempList.size(); i++ ) {
			/*
			 * 15 : disEndDt
			 * 16 : lodEndDt
			 * 8 : closingDt
			 */
			
			if( tempList.get(i).getDisEndDt() == "" && tempList.get(i).getLodEndDt() == "" ) {
				tempList.get(i).setDepDt( tempList.get(i).getClosingDt() );
			} else {
				if( tempList.get(i).getDisEndDt() != "" && tempList.get(i).getDisEndDt() != null && tempList.get(i).getLodEndDt() != "" && tempList.get(i).getLodEndDt() != null ) {
					SimpleDateFormat disEndDt = new SimpleDateFormat("yyyyMMddHHmmss");
					SimpleDateFormat lodEndDt = new SimpleDateFormat("yyyyMMddHHmmss");
					
					if( disEndDt.parse( tempList.get(i).getDisEndDt() ).getTime() > lodEndDt.parse( tempList.get(i).getLodEndDt() ).getTime() ) {
						tempList.get(i).setDepDt( "(" + tempList.get(i).getDisEndDt() + ")" );
					} else {
						tempList.get(i).setDepDt( "(" + tempList.get(i).getLodEndDt() + ")" );
					}
				} else {
					if( tempList.get(i).getDisEndDt() == null || tempList.get(i).getDisEndDt() == "" ) {
						tempList.get(i).setDepDt( "(" + tempList.get(i).getLodEndDt() + ")" );
					} else {
						tempList.get(i).setDepDt( "(" + tempList.get(i).getDisEndDt() + ")" );
					}
				}
			}
			
			if( tempList.get(i).getAtdDt() == null ) {
				TVescallVo tempVo = tVescallRepository.berthTextSum( tempList.get(i) );
				
				if( tempVo == null ) {
					tempList.get(i).setIv( 0 );
					tempList.get(i).setXv( 0 );
					tempList.get(i).setS2v( 0 );
				} else {
					tempList.get(i).setIv( tempVo.getIv() );
					tempList.get(i).setXv( tempVo.getXv() );
					tempList.get(i).setS2v( tempVo.getS2v() );
				}
			} else {
				tempList.get(i).setIv( tempList.get(i).getDisCnt() - tempList.get(i).getCdisCnt() );
				tempList.get(i).setXv( tempList.get(i).getLodCnt() );
				tempList.get(i).setS2v( tempList.get(i).getSftCnt() + tempList.get(i).getTsCnt() );
			}
			
			List<String> insertList = new ArrayList<String>();
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( tempList.get(i).getShipCd() + "-" + tempList.get(i).getCallNo() );
			insertList.add( String.valueOf( tempList.get(i).getCallYy() ) );
			insertList.add( tempList.get(i).getBerthNo() + "(" + tempList.get(i).getPsId() + ")" );
			insertList.add( tempList.get(i).getBitPos() + " (+ " + tempList.get(i).getDeckPos() + ")" );
			insertList.add( tempList.get(i).getShipNm() );
			insertList.add( tempList.get(i).getAtbDt() != null ? tempList.get(i).getAtbDt() : tempList.get(i).getEtbDt() );
			insertList.add( tempList.get(i).getDepDt() );
			if( tempList.get(i).getAtdDt() != null ) {
				insertList.add( tempList.get(i).getAtdDt() );
			} else {
				insertList.add( "(" + tempList.get(i).getEtdDt() + ")" );
			}
			insertList.add( tempList.get(i).getOperCd() );
			insertList.add( String.valueOf( tempList.get(i).getIv() ) );
			insertList.add( String.valueOf( tempList.get(i).getXv() ) );
			insertList.add( String.valueOf( tempList.get(i).getS2v() ) );
			
			rowList.add( insertList );
		}
		resultList.add( rowList );
		

		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_berth.xls" );
		returnMap.put( ExcelConstant.TITLE, "선적 배정현황" );
		// filter
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "선석", "Bitt", "모선항차", "년 도", "선박명", "접안(예정)일시", "반입마감시간(종료)", "출항(예정)일시", "선 사", "양하수량", "적하수량", "shift" ) );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.TEMPLATE, ExcelConstant.TEMPLATE_NORMAL );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelCntrListCopino(SearchVo searchVo) {
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		
		List<TGateVo> copinoList = tGateRepository.cntrListCopino( searchVo );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		for( int i=0; i<copinoList.size(); i++ ) {
			TEdicustomerVo tEdicustomerVo = tEdicustomerRepository.cntrListCopinoGetSender( copinoList.get(i) );
			
			if( tEdicustomerVo != null ) {
				copinoList.get(i).setSendName( tEdicustomerVo.getCstNm() );
			}
			
			List<String> insertList = new ArrayList<String>();
			
			if( copinoList.get(i).getIoCd().toUpperCase().equals( "I" ) ) {
				insertList.add( "반입" );
			} else {
				insertList.add( "반출" );
			}
			
			insertList.add( copinoList.get(i).getTruckerCd() + "-" + copinoList.get(i).getCarNo() );
			insertList.add( copinoList.get(i).getCntrNo() );
			insertList.add( copinoList.get(i).getSzCd() + "/" + copinoList.get(i).getTyCd() );
			insertList.add( StringUtils.removeSpace( copinoList.get(i).getShipCd() + "-" + StringUtils.lpad( copinoList.get(i).getCallNo(), 3, "0" ) + "-" + copinoList.get(i).getCallYy() ) );
			insertList.add( copinoList.get(i).getOperCd() );
			insertList.add( copinoList.get(i).getFmCd() );
			insertList.add( String.valueOf( copinoList.get(i).getWeight() ) );
			insertList.add( copinoList.get(i).getPod() );
			insertList.add( copinoList.get(i).getSendName() );
			insertList.add( copinoList.get(i).getRcvDt() );
			
			rowList.add( insertList );
			
		}
		resultList.add( rowList );
		
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_copino.xls" );
		returnMap.put( ExcelConstant.TITLE, "반출입 예정 목록" );
		returnMap.put( ExcelConstant.FILTER, "조회일자 : " + searchVo.getSearchStartDt() + "  차량번호 : " + searchVo.getTruckerCd() + "-" + searchVo.getCarNo() + "  컨테이너 번호 : " + searchVo.getCntrNo() );
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "구분", "차량번호", "컨테이너번호", "Sz/Tp", "모선항차", "선사", "F/M", "무게", "양하항", "송신처", "수신일자" ) );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.TEMPLATE, ExcelConstant.TEMPLATE_NORMAL );
		returnMap.put( ExcelConstant.BODY, resultList );

		return returnMap;
	}

	public Map<String, ?> excelCntrListGateInOut(SearchVo searchVo) {
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		
		List<TCntrVo> list = tCntrRepository.cntrListGateInOut( searchVo );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		for( int i=0; i<list.size(); i++ ) {
			if( searchVo.getGubun().equals( "out" ) ) {
				if( list.get(i).getFmCd().equals("M") ) {
					TBklstVo paramVo = new TBklstVo();
					paramVo.setCntrNo( list.get(i).getCntrNo() );
					paramVo.setDupNo( list.get(i).getDupNo() );
					paramVo.setOperCd( list.get(i).getOperCd() );
					TBklstVo tBklstVo = tBklstRepository.findBkNoByCntrNoAndDupNoAndOperCd( paramVo );
					
					list.get(i).setBkdoNo( tBklstVo.getBkNo() );
				} else if( list.get(i).getFmCd().equals( "F" ) ) {
					TDodtlVo paramVo = new TDodtlVo();
					paramVo.setCntrNo( list.get(i).getCntrNo() );
					paramVo.setDupNo( list.get(i).getDupNo() );
					TDodtlVo tDodtlVo = tDodtlRepository.findDoNoByCntrNoAndDupNo( paramVo );
					
					list.get(i).setBkdoNo( tDodtlVo.getDoNo() );
				}
			} else if( searchVo.getGubun().equals( "in" ) ) {
				TCntrVo paramVo = new TCntrVo();
				paramVo.setCntrNo( list.get(i).getCntrNo() );
				paramVo.setDupNo( list.get(i).getDupNo() );
				TCntrVo tCntrVo = tCntrRepository.findFnGetBknoByCntrNoAndDupNo( paramVo );
				
				list.get(i).setBkdoNo( tCntrVo.getBkNo() );
				
				// copino-seal
				if( list.get(i).getFmCd().toUpperCase().equals("F") ) {
					List<TGatehisVo> tGatehisList = tGatehisRepository.findSealNoFromCntrListGateInOut( list.get(i) ) ;
					
					if( tGatehisList.size() > 0 ) {
						list.get(i).setCopinoSeal( tGatehisList.get(0).getSealNo() );
					}
				}
			}
			
			if( list.get(i).getSealNo() == null || list.get(i).getSealNo().equals("") ) {
				TBsiotSubVo paramVo = new TBsiotSubVo();
				paramVo.setShipCd( list.get(i).getShipCd() );
				paramVo.setCallNo( list.get(i).getCallNo() );
				paramVo.setCallYy( list.get(i).getCallYy() );
				paramVo.setCntrNo( list.get(i).getCntrNo() );
				paramVo.setDupNo( list.get(i).getDupNo() );
				
				TBsiotSubVo tBsiotSubVo = tBsiotSubRepository.findSealNoByShipCdAndCallNoAndCallYyAndCntrNoAndDupNo( paramVo );
				
				list.get(i).setSealNo( tBsiotSubVo.getSealNo() );
			} else {
				if( !StringUtils.isEmpty( list.get(i).getSealNo1() ) ) {
					list.get(i).setSealNo( list.get(i).getSealNo1() );
				}
			}
			
			List<String> insertList = new ArrayList<String>();
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( searchVo.getGubun().toUpperCase().equals("IN") ? "반입" : "반출" );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getShipCd() + "-" + StringUtils.lpad( String.valueOf( list.get(i).getCallNo() ), 3, "0") + "-" + list.get(i).getCallYy() );
			insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			insertList.add( list.get(i).getFmCd() );
			insertList.add( list.get(i).getPod() );
			insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
			
			rowList.add( insertList );
			insertList = new ArrayList<String>();
			insertList.add( ExcelConstant.ROW_SPAN );
			if( !list.get(i).getGinDt().equals("-") ) {
				insertList.add( list.get(i).getGinDt() );
			} else if( !list.get(i).getGcDdt().equals("-") ) {
				insertList.add( list.get(i).getGcDdt() );
			} else {
				insertList.add( "-" );
			}
			
			if( !list.get(i).getGoutDt().equals("-") ) {
				insertList.add( list.get(i).getGoutDt() );
			} else if( !list.get(i).getGcLdt().equals("-") ) {
				insertList.add( list.get(i).getGcLdt() );
			} else {
				insertList.add( "-" );
			}
			
			insertList.add( list.get(i).getCarNo() );
			insertList.add( list.get(i).getTruckerNm() );
			insertList.add( list.get(i).getBkdoNo() );
			insertList.add( list.get(i).getSealNo() );
			if( list.get(i).getCopinoSeal() != null && !StringUtils.isEmpty( list.get(i).getCopinoSeal() ) ) {
				insertList.add( list.get(i).getCopinoSeal() );
			} else {
				insertList.add( "" );
			}
			rowList.add( insertList );
			
		}
		resultList.add( rowList );
		
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_gate_in_out.xls" );
		returnMap.put( ExcelConstant.TITLE, "반출입 목록" );
		returnMap.put( ExcelConstant.FILTER, "조회일자 : " + searchVo.getSearchStartDt() + "  차량번호 : " + searchVo.getTruckerCd() + "-" + searchVo.getCarNo() + "  컨테이너 번호 : " + searchVo.getCntrNo() );
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "번호", "구분", "컨테이너번호", "모선항차", "Sz/Tp", "F/M", "양하항", "야드위치" ) );
		headList.add( Arrays.asList( ExcelConstant.ROW_SPAN, "반입일시", searchVo.getGubun().toUpperCase().equals("IN") ? "반출(적하)일시" : "반입(적하)일시", "차량번호", "운송사", searchVo.getGubun().toUpperCase().equals("IN") ? "Order No." : "B/O or D/O", "Seal", "Copino Seal" ) );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.TEMPLATE, ExcelConstant.TEMPLATE_CNTRLIST_GATEINOUT );
		returnMap.put( ExcelConstant.BODY, resultList );

		return returnMap;
	}

	public Map<String, ?> excelCntrListNotGateInOut(SearchVo searchVo) {

		Map<String, Object> returnMap = new HashMap<>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		
		List<List<String>> headList = new ArrayList<List<String>>();
		if( searchVo.getGubun().toUpperCase().equals("OUT") ) {
			headList.add( Arrays.asList( "번호", "컨테이너번호", "선사", "Sz/Tp", "F/M", "DMG", "장척", "야드위치", "양하(반입)일시" ) );
			
			List<TCntrVo> list = tCntrRepository.cntrListNotGateOut( searchVo );
			List<List<String>> rowList = new ArrayList<List<String>>();
			for( int i=0; i<list.size(); i++ ) {
				List<String> insertList = new ArrayList<String>();
				
				insertList.add( String.valueOf( i + 1 ) );
				insertList.add( list.get(i).getCntrNo() );
				insertList.add( list.get(i).getOperCd() );
				insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
				insertList.add( list.get(i).getFmCd() );
				insertList.add( list.get(i).getDmgCd() );
				insertList.add( list.get(i).getHwlCd() );
				insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
				if( list.get(i).getGcDdt().equals("-") ) {
					insertList.add( "(" + list.get(i).getGinDt() + ")" );
				} else {
					insertList.add( list.get(i).getGcDdt() );
				}
				
				rowList.add( insertList );
			}
			
			resultList.add( rowList );
			
		} else {
			headList.add( Arrays.asList( "번호", "컨테이너번호", "선사", "Sz/Tp", "F/M", "장척", "양하항", "무게", "온도", "IMDG" ) );
			
			List<TCntrVo> list = tCntrRepository.cntrListNotGateIn( searchVo );
			List<List<String>> rowList = new ArrayList<List<String>>();
			
			for( int i=0; i<list.size(); i++ ) {
				List<String> insertList = new ArrayList<String>();
				
				insertList.add( String.valueOf( i + 1 ) );
				insertList.add( list.get(i).getCntrNo() );
				insertList.add( list.get(i).getOperCd() );
				insertList.add( list.get(i).getSztp() );
				insertList.add( list.get(i).getFmCd() );
				insertList.add( list.get(i).getHwlCd() );
				insertList.add( list.get(i).getPod() );
				insertList.add( String.valueOf( list.get(i).getWeight() ) );
				insertList.add( list.get(i).getTemp() );
				insertList.add( list.get(i).getImdg() );
				
				rowList.add( insertList );
			}
			resultList.add( rowList );
		}
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_not_gate_in_out.xls" );
		returnMap.put( ExcelConstant.TITLE, "미반출입 목록" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelCntrListByVslDisLod(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "Cntr", "SZ/TP", "상태", "F/M", "TS", "무게", "냉동", "IMDG", "선내위치", "장치위치", "선사", "반입(양하)", "반출(적하)" ) );
		
		List<TCntrVo> list = tCntrRepository.byVslDisLod( searchVo );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			insertList.add( list.get(i).getCurStat() );
			insertList.add( list.get(i).getFmCd() );
			insertList.add( list.get(i).getTsId() );
			insertList.add( String.valueOf( list.get(i).getWeight() ) );
			insertList.add( list.get(i).getReefId() );
			insertList.add( list.get(i).getImdg() );
			if( StringUtils.isEmpty( list.get(i).getSTbay() ) ) {
				insertList.add( "" );
			} else {
				insertList.add( list.get(i).getSTbay() + "-" + list.get(i).getSTrow() + "-" + list.get(i).getSTtier() );
			}
			
			if( StringUtils.isEmpty( list.get(i).getYTblock() ) ) {
				insertList.add( "" );
			} else {
				insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
			}
			
			insertList.add( list.get(i).getOperCd() );
			
			if( searchVo.getGubun().toUpperCase().equals( "DOWN" ) ) {
				insertList.add( "(" + list.get(i).getGcDdt() + ")" );
				if( list.get(i).getCurStat().toUpperCase().substring(0, 1).equals( "S" ) ) {
					insertList.add( "(" + list.get(i).getGcLdt() + ")" );
				} else {
					insertList.add( "(" + list.get(i).getGoutDt() + ")" );
				}
			} else {
				if( list.get(i).getCurStat().toUpperCase().substring(0, 1).equals( "S" ) ) {
					insertList.add( "(" + list.get(i).getGcDdt() + ")" );
				} else {
					insertList.add( "(" + list.get(i).getGinDt() + ")" );
				}
			}
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_by_vsl_dis_lod.xls" );
		returnMap.put( ExcelConstant.TITLE, "모선별 양하 컨테이너 목록" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelCntrListCllOverStorage(SearchVo searchVo) throws ParseException {
		
		Map<String, Object> returnMap = new HashMap<>();
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "Cntr", "SZ/TP", "상태", "F/M", "POD", "무게", "반입일시", "기준일시", "경과일수" ) );
		
		List<TCntrVo> list = null;

		if( searchVo.getShipCd() == null || searchVo.getShipCd().equals("") || 
				searchVo.getCallNo() == null || searchVo.getCallNo().equals("") ||
				searchVo.getCallYy() == null || searchVo.getCallYy().equals("") ) {
			list = new ArrayList<TCntrVo>();
		} else {
			list = tCntrRepository.cllOverStorage( searchVo );
			for( int i=0; i<list.size(); i++ ) {
				String[] rtnVar = list.get(i).getOverdays().split(" ");
				String iovDay = rtnVar[0];
				int iovTrm = Integer.valueOf( rtnVar[1] ) - 1;
				Date todate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( list.get(i).getGinDt() );
				Calendar c = Calendar.getInstance();
				c.setTime( todate );
				c.add( Calendar.DATE, iovTrm );
				String ibaseDay = new SimpleDateFormat("yyyy-MM-dd").format( c.getTime() ) + " 24:00:00";
				
				list.get(i).setIbaseDay( ibaseDay );
				list.get(i).setIovDay( iovDay );
				
			}
		}
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			insertList.add( list.get(i).getStatus() );
			insertList.add( list.get(i).getFmCd() );
			insertList.add( list.get(i).getPod() );
			insertList.add( String.valueOf( list.get(i).getWeight() ) );
			insertList.add( list.get(i).getGinDt() );
			insertList.add( list.get(i).getIbaseDay() );
			insertList.add( list.get(i).getIovDay() );
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_cll_over_storage.xls" );
		returnMap.put( ExcelConstant.TITLE, "CLL경과 목록" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelCntrListCntrCancel(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "모선항차", "컨테이너번호", "Sz/Tp", "F/M", "POD", "야드위치", "장척", "냉동온도", "위험물", "손상여부", "보세", "반입일시" ) );
		
		List<TCntrVo> list = tCntrRepository.cntrListCntrCancel( searchVo );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		for( int i=0; i<list.size(); i++ ) {
			
			List<String> insertList = new ArrayList<String>();
			
			if( list.get(i).getOvH() == 0 ) {
				list.get(i).setOv1( "" );
			} else {
				list.get(i).setOv1( "1" );
			}
			
			if( list.get(i).getOvWp() == 0 && list.get(i).getOvWs() == 0 ) {
				list.get(i).setOv2( "" );
			} else if( list.get(i).getOvWp() != 0 && list.get(i).getOvWs() == 0 ) {
				list.get(i).setOv2( "1" );
			} else if( list.get(i).getOvWp() == 0 && list.get(i).getOvWs() != 0 ) {
				list.get(i).setOv2( "2" );
			} else {
				list.get(i).setOv3( "3" );
			}
			
			if( list.get(i).getOvLf() == 0 && list.get(i).getOvLb() == 0 ) {
				list.get(i).setOv3( "" );
			} else if( list.get(i).getOvLf() != 0 && list.get(i).getOvLb() == 0 ) {
				list.get(i).setOv3( "1" );
			} else if( list.get(i).getOvLf() == 0 && list.get(i).getOvLb() != 0 ) {
				list.get(i).setOv3( "2" );
			} else {
				list.get(i).setOv3( "3" );
			}
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getShipCd() + "-" + StringUtils.lpad( String.valueOf( list.get(i).getCallNo() ), 3, "0" ) + "-" + String.valueOf( list.get(i).getCallYy() ) );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			insertList.add( list.get(i).getFmCd() );
			insertList.add( list.get(i).getPod() );
			insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
			if( StringUtils.isEmpty( list.get(i).getOv1() ) ) {
				insertList.add( "" );
			} else {
				insertList.add( list.get(i).getOv1() + "-" + list.get(i).getOv2() + "-" + list.get(i).getOv3() );
			}
			insertList.add( list.get(i).getTemp() + list.get(i).getTempId().substring(0, 1) );
			insertList.add( list.get(i).getImdg() );
			insertList.add( list.get(i).getDmgCd() );
			insertList.add( list.get(i).getBondId() );
			insertList.add( list.get(i).getGinDt() );
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_cntr_cancel.xls" );
		returnMap.put( ExcelConstant.TITLE, "선적취소 목록" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelCntrListReeferCargo(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "모선항차", "선사", "컨테이너번호", "Sz/Tp", "상태코드", "기준온도", "야드위치", "접속일시", "단전일시" ) );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		List<TCntrVo> list = tCntrRepository.cntrListReeferCargo( searchVo );
		
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			if( searchVo.getGubun().toLowerCase().equals( "dry" ) ) {
				TRfcargoVo cargoVo = tRfcargoRepository.findReqTempAndPlinDtByShipCdAndCallYyAndCallNoAndCntrNo( list.get(i) );
				
				if( cargoVo != null ) {
					list.get(i).setReqTemp( cargoVo.getReqTemp() );
					list.get(i).setPlinDt( cargoVo.getPlinDt() );
					list.get(i).setPloutDt( cargoVo.getPloutDt() );
				}
			}
			
			list.get(i).setTempId( list.get(i).getTempId().substring(0, 1) );
			
			if( StringUtils.isEmpty( list.get(i).getTsId() ) ) {
				list.get(i).setTsId("");
			}
			if( StringUtils.isEmpty( list.get(i).getRtnCd() ) ) {
				list.get(i).setRtnCd( "" );
			}
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getShipCd() + "-" + StringUtils.lpad( String.valueOf( list.get(i).getCallNo() ) , 3, "0" ) + "-" + list.get(i).getCallYy() );
			insertList.add( list.get(i).getOperCd() );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			insertList.add( list.get(i).getCurStat() + list.get(i).getFmCd() + "-" + list.get(i).getTsId() + list.get(i).getRtnCd() );
			if( StringUtils.isEmpty( list.get(i).getReqTemp() ) ) {
				insertList.add( "" );
			} else if( searchVo.getGubun().toLowerCase().equals( "icd" ) ) {
				insertList.add( list.get(i).getReqTemp() + "˚" + list.get(i).getTempId() );
			} else {
				insertList.add( list.get(i).getReqTemp() + list.get(i).getTempId() );
			}
			insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
			insertList.add( list.get(i).getPlinDt() );
			insertList.add( list.get(i).getPloutDt() );
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_reefer_cargo.xls" );
		returnMap.put( ExcelConstant.TITLE, "냉동 목록" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelCntrListCntrDanger(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "모선항차", "Cntr No.", "Sz/Tp", "상태", "직송", "중량", "IMGD", "UNNO", "DG type", "Fire Class", "선내위치", "야드위치", "할증", "보세", "Shift Time", "반입(양하)일시" ) );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		List<TCntrVo> list = tCntrRepository.cntrListCntrDanger( searchVo );
		
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			if( list.get(i).getTsId() == null ) {
				list.get(i).setTsId("");
			}
			if( list.get(i).getRtnCd() == null ) {
				list.get(i).setRtnCd( "" );
			}
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getShipCd() + "-" + StringUtils.lpad( String.valueOf( list.get(i).getCallNo() ) , 3, "0" ) + "-" + list.get(i).getCallYy() );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			insertList.add( list.get(i).getCurStat() + list.get(i).getFmCd() + "-" + list.get(i).getTsId() + list.get(i).getRtnCd() );
			insertList.add( list.get(i).getDirId() );
			insertList.add( String.valueOf( list.get(i).getWeight() ) );
			insertList.add( list.get(i).getImdg() );
			insertList.add( list.get(i).getUnno() );
			insertList.add( list.get(i).getDgYtype() );
			insertList.add( list.get(i).getDgFgrade() );
			insertList.add( list.get(i).getSTbay() + "-" + list.get(i).getSTrow() + "-" + list.get(i).getSTtier() );
			insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
			if( list.get(i).getCurStat().substring(0, 1).toUpperCase().equals( "I" ) ) {
				insertList.add( list.get(i).getOverdCd() );
				insertList.add( list.get(i).getBondId() );
				
				if( list.get(i).getSSind().toUpperCase().equals( "H" ) ) {
					insertList.add( "1Time" );
				} else if( list.get(i).getSSind().toUpperCase().equals( "Y" ) ) {
					insertList.add( "2Time" );
				} else {
					insertList.add( "" );
				}
				
				if( StringUtils.isEmpty( list.get(i).getGcDdt() ) ) {
					insertList.add( list.get(i).getGinDt() );
				} else {
					insertList.add( list.get(i).getGcDdt() );
				}
			} else if( list.get(i).getCurStat().substring(0, 1).toUpperCase().equals( "X" ) ) {
				insertList.add( list.get(i).getOverlCd() );
				insertList.add( list.get(i).getBondId() );
				
				if( list.get(i).getSSind().toUpperCase().equals( "H" ) ) {
					insertList.add( "1Time" );
				} else if( list.get(i).getSSind().toUpperCase().equals( "Y" ) ) {
					insertList.add( "2Time" );
				} else {
					insertList.add( "" );
				}
				
				insertList.add( list.get(i).getGinDt() );
			} else if( list.get(i).getCurStat().substring(0, 1).toUpperCase().equals( "S" ) ) {
				insertList.add( list.get(i).getOverlCd() );
				insertList.add( list.get(i).getBondId() );
				
				if( list.get(i).getSSind().toUpperCase().equals( "H" ) ) {
					insertList.add( "1Time" );
				} else if( list.get(i).getSSind().toUpperCase().equals( "Y" ) ) {
					insertList.add( "2Time" );
				} else {
					insertList.add( "" );
				}
				
				if( StringUtils.isEmpty( list.get(i).getGcDdt() ) ) {
					insertList.add( list.get(i).getGinDt() );
				} else {
					insertList.add( list.get(i).getGcDdt() );
				}
			} else {
				insertList.add( "" );
				insertList.add( "" );
				insertList.add( "" );
				insertList.add( "" );
			}
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_cntr_danger.xls" );
		returnMap.put( ExcelConstant.TITLE, "위험물 목록" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelCntrInfoStock(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "모선항차", "FULL", ExcelConstant.COL_SPAN, ExcelConstant.COL_SPAN, "EMPTY", ExcelConstant.COL_SPAN, ExcelConstant.COL_SPAN, "TOTAL", ExcelConstant.COL_SPAN, ExcelConstant.COL_SPAN, "TEU" ) );
		headList.add( Arrays.asList( ExcelConstant.ROW_SPAN, "20'", "40'", "45'", "20'", "40'", "45'", "20'", "40'", "45'", ExcelConstant.ROW_SPAN ) );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		List<TCntrVo> list = tCntrRepository.cntrInfoStock( searchVo );
		
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			insertList.add( list.get(i).getShipCd() + "-" + StringUtils.lpad( String.valueOf( list.get(i).getCallNo() ) , 3, "0" ) + "-" + list.get(i).getCallYy() );
			insertList.add( list.get(i).getF20() );
			insertList.add( list.get(i).getF40() );
			insertList.add( list.get(i).getF45() );
			
			insertList.add( list.get(i).getM20() );
			insertList.add( list.get(i).getM40() );
			insertList.add( list.get(i).getM45() );
			
			insertList.add( list.get(i).getT20() );
			insertList.add( list.get(i).getT40() );
			insertList.add( list.get(i).getT45() );
			
			insertList.add( String.valueOf( list.get(i).getTeu() ) );
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_stock.xls" );
		returnMap.put( ExcelConstant.TITLE, "컨테이너 재고현황" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelOnDockOverStock(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "컨테이너번호", "모선항차", "Sz/Tp", "F/M", "반입(양하)일시", "수입/수출", "야드위치", "경과보관일수" ) );
		
		SearchVo operListAdded = setOperList( searchVo );
		List<TCntrVo> list = tCntrRepository.onDockOverStock( operListAdded );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getShipCd() + "-" + StringUtils.lpad( String.valueOf( list.get(i).getCallNo() ) , 3, "0" ) + "-" + list.get(i).getCallYy() );
			insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			insertList.add( list.get(i).getFmCd() );
			
			String curStat = list.get(i).getCurStat().substring(0, 1).toUpperCase();
			if( curStat.equals( "I" ) ) {
				insertList.add( "(" + list.get(i).getGcDdt() + ")" );
			} else if( curStat.equals( "X" ) || curStat.equals( "M" ) ) {
				insertList.add( list.get(i).getGinDt() );
			} else {
				insertList.add( "" );
			}
			
			if( curStat.equals( "I" ) ) {
				insertList.add( "수입" );
			} else if( curStat.equals( "X" ) || curStat.equals( "M" ) ) {
				insertList.add( "수출" );
			} else {
				insertList.add( "" );
			}
			
			insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
			insertList.add( list.get(i).getOverdays() );
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_overstock.xls" );
		returnMap.put( ExcelConstant.TITLE, "경과보관 컨테이너 조회" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelOnDockStock(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "모선항차", "컨테이너번호", "상태코드", "Sz/Tp", "Weight", "야드위치", "DMG", "양하(반입)일시", "장치일수" ) );
		
		SearchVo operListAdded = setOperList( searchVo );
		
		List<TCntrVo> list = tCntrRepository.onDockStock( operListAdded );
		
		boolean searchDiv = ( !StringUtils.isEmpty( searchVo.getSearchStartDt() ) && !StringUtils.isEmpty( searchVo.getSearchEndDt() ) ) ? true : false;
		int index = 0;
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			Calendar c = Calendar.getInstance();
			Date aStartDt = null;
			String cClass = list.get(i).getCurStat().substring(0, 1).toUpperCase();
			
			if( cClass.equals( "I" ) ) {
				if( !StringUtils.isEmpty( list.get(i).getGcDdt() ) ) {
					try {
						aStartDt = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse( list.get(i).getGcDdt() );
					} catch (ParseException e) {
						aStartDt = null;
					}
				}
			} else {
				if( !StringUtils.isEmpty( list.get(i).getGinDt() ) ) {
					try {
						aStartDt = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse( list.get(i).getGinDt() );
					} catch (ParseException e) {
						aStartDt = null;
					}
				}
			}
			
			if( aStartDt != null ) {
				if( cClass.equals( "I" ) ) {
					if( Integer.valueOf( new SimpleDateFormat("HHmm").format( aStartDt ) ) >= 8000 ) {
						c.setTime( aStartDt );
						c.add( Calendar.DATE, 1 );
						c.set( Calendar.HOUR , 8 );
						c.set( Calendar.MINUTE, 0 );
						c.set( Calendar.SECOND, 0 );
						aStartDt = c.getTime();
					}
				}
				
				int datediff = (int) Math.abs( ( new Date().getTime() - aStartDt.getTime() ) / ( 24*60*60*1000 ) );
				list.get(i).setYardDay( datediff );
			}
			
			if( searchDiv ) {
				if( list.get(i).getYardDay() >= Integer.valueOf( searchVo.getSearchStartDt() ) && list.get(i).getYardDay() <= Integer.valueOf( searchVo.getSearchEndDt() ) ) {
					insertList.add( String.valueOf( ++index ) );
					insertList.add( list.get(i).getShipCd() + "-" + StringUtils.lpad( String.valueOf( list.get(i).getCallNo() ), 3, "0" ) );
					insertList.add( list.get(i).getCntrNo() );
					insertList.add( list.get(i).getCurStat() + "-" + list.get(i).getFmCd() );
					insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
					insertList.add( list.get(i).getTyGrpCd() );
					insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
					if( list.get(i).getDmgCd().toUpperCase().equals( "Y" ) ) {
						insertList.add( "D" );
					} else {
						insertList.add( "" );
					}
					if( list.get(i).getCurStat().substring(0, 1).toUpperCase().equals( "I" ) ) {
						insertList.add( list.get(i).getGcDdt() );
					} else if( list.get(i).getCurStat().substring(0, 1).toUpperCase().equals( "X" ) || list.get(i).getCurStat().substring(0, 1).toUpperCase().equals( "M" ) ) {
						insertList.add( list.get(i).getGinDt() );
					}
					insertList.add( String.valueOf( list.get(i).getYardDay() ) );
				}
			} else {
				insertList.add( String.valueOf( ++index ) );
				insertList.add( list.get(i).getShipCd() + "-" + StringUtils.lpad( String.valueOf( list.get(i).getCallNo() ), 3, "0" ) );
				insertList.add( list.get(i).getCntrNo() );
				insertList.add( list.get(i).getCurStat() + "-" + list.get(i).getFmCd() );
				insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
				insertList.add( list.get(i).getTyGrpCd() );
				insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
				if( list.get(i).getDmgCd().toUpperCase().equals( "Y" ) ) {
					insertList.add( "D" );
				} else {
					insertList.add( "" );
				}
				if( list.get(i).getCurStat().substring(0, 1).toUpperCase().equals( "I" ) ) {
					insertList.add( list.get(i).getGcDdt() );
				} else if( list.get(i).getCurStat().substring(0, 1).toUpperCase().equals( "X" ) || list.get(i).getCurStat().substring(0, 1).toUpperCase().equals( "M" ) ) {
					insertList.add( list.get(i).getGinDt() );
				}
				insertList.add( String.valueOf( list.get(i).getYardDay() ) );
			}
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_ondock_stock.xls" );
		returnMap.put( ExcelConstant.TITLE, "Stock 현황" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelOnDockDamage(SearchVo searchVo) {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "모선항차", "컨테이너번호", "Sz/Tp", "F/M", "손상상태", "Hold유무", "수리여부", "반입(양하)일시", "발생일시" ) );

		SearchVo operListAdded = setOperList( searchVo );
		List<TCntrVo> list = tCntrRepository.onDockDamage( operListAdded );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getShipCd() + "-" + StringUtils.lpad( String.valueOf( list.get(i).getCallNo() ), 3, "0") + "-" + list.get(i).getCallYy() );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			insertList.add( list.get(i).getFmCd() );
			insertList.add( list.get(i).getDmgYn() );
			insertList.add( StringUtils.isEmpty( list.get(i).getHoldCd() ) ? "" : "HOLD" );
			insertList.add( list.get(i).getRepairYn().toUpperCase().equals( "Y" ) ? "수리" : "미수리" );
			insertList.add( list.get(i).getCurStat().substring(0, 1).toUpperCase().equals( "I" ) ? ( "(" + list.get(i).getGcDdt() + ")" ) : list.get(i).getGinDt() );
			insertList.add( list.get(i).getDmgDt() );
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_ondock_damage.xls" );
		returnMap.put( ExcelConstant.TITLE, "컨테이너 Damage 현황" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelOnDockHolding(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "컨테이너번호", "주기", "선사", "Sz/Tp", "F/M", "야드위치", "Holding 일시", "Holding 사유", "Remark" ) );

		SearchVo operListAdded = setOperList( searchVo );
		List<THoldngVo> list = tHoldngRepository.onDockHoldingEntire( operListAdded );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( String.valueOf( list.get(i).getDupNo() ) );
			insertList.add( list.get(i).getOperCd() );
			if( !StringUtils.isEmpty( list.get(i).getSzCd() ) && !StringUtils.isEmpty( list.get(i).getTyCd() ) ) {
				insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			} else {
				insertList.add( StringUtils.EMPTY );
			}
			insertList.add( list.get(i).getFmCd() );
			
			if( !StringUtils.isEmpty( list.get(i).getYTblock() ) && !StringUtils.isEmpty( list.get(i).getYTbay() ) && !StringUtils.isEmpty( list.get(i).getYTrow() ) && !StringUtils.isEmpty( list.get(i).getYTtier()) ) {
				insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
			} else {
				insertList.add( StringUtils.EMPTY );
			}
			
			insertList.add( list.get(i).getHoldDt() );
			insertList.add( list.get(i).getCdNm() );
			insertList.add( list.get(i).getRemark() );
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_ondock_holding.xls" );
		returnMap.put( ExcelConstant.TITLE, "Holding 목록" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelIntraIntrDamage(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "선사", "Cntr No.", "Sz/Tp", "S/D", "데미지발생일시", "이적일시", "수리일시", "Grade" ) );

		List<TCntrVo> list = tCntrRepository.intraIntrDamage( searchVo );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getOperCd() );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			if( StringUtils.isEmpty( list.get(i).getDmgCd() ) ) {
				insertList.add( "S" );
			} else {
				insertList.add( "D (" + list.get(i).getDmgCd() + ")" );
			}
			insertList.add( list.get(i).getDmgDt() );
			insertList.add( list.get(i).getOdDt() );
			insertList.add( list.get(i).getRepairDt() );
			insertList.add( StringUtils.isEmpty( list.get(i).getCntrGrade() ) ? "-" : list.get(i).getCntrGrade() );
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_intr_damage.xls" );
		returnMap.put( ExcelConstant.TITLE, "수리완료 컨테이너" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelCntrListLongStack(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "컨테이너번호", "Sz/Tp", "상태코드", "장치위치", "선사", "모선항차", "반입,양하일시", "경과일수", "장치일수", "Booking No", "M. B/L No" ) );

		List<TCntrVo> list = tCntrRepository.cntrListLongStack( searchVo );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		Calendar calendar = Calendar.getInstance();
		
		Date queryDate = new Date();
		Date queryDate2 = new Date();
		if( StringUtils.isEmpty( searchVo.getSearchStartDt() ) ) {
			calendar.setTime( new Date() );
			calendar.set( Calendar.HOUR, 0 );
			calendar.set( Calendar.MINUTE, 0 );
			calendar.set( Calendar.SECOND, 0 );
			
			queryDate = calendar.getTime();
			
			calendar.add( Calendar.HOUR, 8 );
			queryDate2 = calendar.getTime();
		} else {
			calendar.set( Calendar.YEAR, Integer.valueOf( searchVo.getSearchStartDt().substring(0,4) ) );
			calendar.set( Calendar.MONTH, Integer.valueOf( searchVo.getSearchStartDt().substring(5,6) ) );
			calendar.set( Calendar.DATE, Integer.valueOf( searchVo.getSearchStartDt().substring(7,8) ) );
			calendar.set( Calendar.HOUR, 0 );
			calendar.set( Calendar.MINUTE, 0 );
			calendar.set( Calendar.SECOND, 0 );
			
			queryDate = calendar.getTime();
			
			calendar.add( Calendar.HOUR, 8 );
			queryDate2 = calendar.getTime();
		}
		
		
		for( int i=0; i<list.size(); i++ ) {
			
			List<String> insertList = new ArrayList<String>();
			
			TCntrVo data = list.get(i);
			
			if( !StringUtils.isEmpty( list.get(i).getFromDt() ) ) {
				String replacedFromDt = StringUtils.replaceAll( StringUtils.replaceAll( list.get(i).getFromDt(), "-", StringUtils.EMPTY), ":", StringUtils.EMPTY );
				Calendar fromDtCalendar = Calendar.getInstance();
				fromDtCalendar.set( Calendar.YEAR, Integer.valueOf( replacedFromDt.substring(0, 4) ) );
				fromDtCalendar.set( Calendar.MONTH, Integer.valueOf( replacedFromDt.substring(5, 6) ) );
				fromDtCalendar.set( Calendar.DATE, Integer.valueOf( replacedFromDt.substring(7,8) ) );
				fromDtCalendar.set( Calendar.HOUR, Integer.valueOf( replacedFromDt.substring(9,10) ) );
				fromDtCalendar.set( Calendar.MINUTE, Integer.valueOf( replacedFromDt.substring(11,12) ) );
				fromDtCalendar.set( Calendar.SECOND, Integer.valueOf( replacedFromDt.substring(13,14) ) );
				
				if( data.getCurStat().substring(0, 1).toUpperCase().equals("X") || data.getCurStat().substring(0, 1).toUpperCase().equals("M") ) {
					int diffDay = (int) (Math.abs( ( queryDate.getTime() - fromDtCalendar.getTime().getTime() ) / (24 * 60 * 60 * 1000) ) + 1);
					list.get(i).setYardDay( diffDay );
				} else {
					int diffDay = (int) Math.abs( ( queryDate2.getTime() - fromDtCalendar.getTime().getTime() ) / (24 * 60 * 60 * 1000) ) + 1;
					list.get(i).setYardDay( diffDay );
				}
			} else {
				list.get(i).setYardDay( 0 );
			}
			
			List<String> cargoNoList = new ArrayList<String>();
			List<String> cargoNo1List = new ArrayList<String>();
			
			
			list.get(i).setCargoNoList( cargoNoList );
			list.get(i).setCargoNo1List( cargoNo1List );
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getSzCd() + "/" + list.get(i).getTyCd() );
			insertList.add( list.get(i).getCurStat() + "" + ( list.get(i).getFmCd() == null ? "" : list.get(i).getFmCd() ) + "-" + ( list.get(i).getTsId() == null ? "" : list.get(i).getTsId() ) + "" + ( list.get(i).getRtnCd() == null ? "" : list.get(i).getRtnCd() ) );
			insertList.add( list.get(i).getYTblock() + "-" + list.get(i).getYTbay() + "-" + list.get(i).getYTrow() + "-" + list.get(i).getYTtier() );
			insertList.add( list.get(i).getOperCd() );
			insertList.add( list.get(i).getShipCd() + "-" + StringUtils.lpad( String.valueOf( list.get(i).getCallNo() ), 3, "0" ) + "-" + list.get(i).getCallYy() );
			insertList.add( list.get(i).getGinDt() );
			insertList.add( list.get(i).getOverdays() );
			insertList.add( String.valueOf( list.get(i).getYardDay() ) );
			insertList.add( list.get(i).getBkNo() );
			String cargoNo = "";

			List<TBsiotVo> mstblNoList = tBsiotRepository.cntrListLongStackMstBlNoList( list.get(i) );
			
			for( int j=0; j<mstblNoList.size(); j++ ) {
				cargoNo += mstblNoList.get(j).getMstblNo() + "\n";
			}
			
			List<TBsiotSubVo> cargoNoDataList = tBsiotSubRepository.cntrListLongStackCargoNoList( list.get(i) );
			
			for( int j=0; j<cargoNoDataList.size(); j++ ) {
				cargoNo += cargoNoDataList.get(j).getCargoNo() + "\n";
			}
			
			insertList.add( cargoNo );
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_longstack.xls" );
		returnMap.put( ExcelConstant.TITLE, "장기체화목록" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelOnDockGatePass(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "No", "컨테이너번호", "등록일시", "담당자", "해제사유" ) );
		
		TSecretVo tSecretVo = new TSecretVo();
		
		tSecretVo.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
		
		tSecretVo = tSecretRepository.findByEmpNo( tSecretVo );
		
		if( tSecretVo != null ) {
			searchVo.setEmpNo( tSecretVo.getEmpNo() );
			searchVo.setJo( tSecretVo.getJo() );
		}
		
		List<HashMap<String, Object>> sessionOperList = commonRepository.findOperCdListFromSession( tSecretVo );
		
		List<String> operList = new ArrayList<String>();
		
		for( int i=0; i<sessionOperList.size(); i++ ) {
			operList.add( sessionOperList.get(i).get( "OPER_CD" ).toString() );
		}
		
		searchVo.setOperList( operList );

		List<TCopinoRjctVo> list = tCopinoRjctRepository.onDockGatePass( searchVo );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			insertList.add( String.valueOf( i+1 ) );
			insertList.add( list.get(i).getCntrNo() );
			insertList.add( list.get(i).getUpdDt() );
			if( list.get(i).getEmpNo().substring(0,2).equals("20") || list.get(i).getEmpNo().substring(0,2).equals("77") ) {
				insertList.add( "E1CT" );
			} else {
				insertList.add( list.get(i).getEmpNo() );
			}
			insertList.add( list.get(i).getRmk() );
			
			rowList.add( insertList );
		}
		
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_gatepass.xls" );
		returnMap.put( ExcelConstant.TITLE, "반입허용 컨테이너 관리" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}

	public Map<String, ?> excelEmptyContainer(SearchVo searchVo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		List<List<List<String>>> resultList = new ArrayList<List<List<String>>>();
		List<List<String>> headList = new ArrayList<List<String>>();
		headList.add( Arrays.asList( "선사", "20GP", "40GP", "40HQ", "45GP", "합계" ) );
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		List<TCntrVo> list = tCntrRepository.terminalEmptyContainer(searchVo);
		
		for( int i=0; i<list.size(); i++ ) {
			List<String> insertList = new ArrayList<String>();
			
			insertList.add( list.get(i).getOperCd() );
			insertList.add( list.get(i).getGp20() );
			insertList.add( list.get(i).getGp40() );
			insertList.add( list.get(i).getHq40() );
			insertList.add( list.get(i).getGp45() );
			insertList.add( list.get(i).getTot() );
			
			rowList.add( insertList );
		}
		
		resultList.add( rowList );
		
		returnMap.put( ExcelConstant.FILE_NAME, new SimpleDateFormat("yyyyMMdd").format( new Date() ) + "_empty_container.xls" );
		returnMap.put( ExcelConstant.TITLE, "반입허용 컨테이너 관리" );
		returnMap.put( ExcelConstant.FILTER, "" );
		returnMap.put( ExcelConstant.HEAD, headList );
		returnMap.put( ExcelConstant.BODY, resultList );
		
		return returnMap;
	}
}
