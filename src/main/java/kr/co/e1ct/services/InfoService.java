package kr.co.e1ct.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import kr.co.e1ct.response.ExportableInformationDTO;
import kr.co.e1ct.response.ExportableInformationResponse;
import kr.co.e1ct.response.IntegratedInformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.co.e1ct.mappers.CommonRepository;
import kr.co.e1ct.mappers.TBkdtlRepository;
import kr.co.e1ct.mappers.TBklstRepository;
import kr.co.e1ct.mappers.TBkmstRepository;
import kr.co.e1ct.mappers.TBsiotRepository;
import kr.co.e1ct.mappers.TBsiotSubRepository;
import kr.co.e1ct.mappers.TCarRepository;
import kr.co.e1ct.mappers.TCarbklstRepository;
import kr.co.e1ct.mappers.TCashPlanRepository;
import kr.co.e1ct.mappers.TCntrRepository;
import kr.co.e1ct.mappers.TCodeRepository;
import kr.co.e1ct.mappers.TColdltRepository;
import kr.co.e1ct.mappers.TCopinoChkinRepository;
import kr.co.e1ct.mappers.TCopinoRjctRepository;
import kr.co.e1ct.mappers.TCustomerHdrRepository;
import kr.co.e1ct.mappers.TCustomerRepository;
import kr.co.e1ct.mappers.TDgcargoRepository;
import kr.co.e1ct.mappers.TDodtlRepository;
import kr.co.e1ct.mappers.TDomstRepository;
import kr.co.e1ct.mappers.TEdicustomerRepository;
import kr.co.e1ct.mappers.TEquiRepository;
import kr.co.e1ct.mappers.TFreedayRepository;
import kr.co.e1ct.mappers.TFreevanpoolRepository;
import kr.co.e1ct.mappers.TGateRepository;
import kr.co.e1ct.mappers.TGatehisRepository;
import kr.co.e1ct.mappers.TGcorderRepository;
import kr.co.e1ct.mappers.THoldngRepository;
import kr.co.e1ct.mappers.TInLiftCashCntrRepository;
import kr.co.e1ct.mappers.TMailRepository;
import kr.co.e1ct.mappers.TMovelogRepository;
import kr.co.e1ct.mappers.TMvRemashRepository;
import kr.co.e1ct.mappers.TOperRepository;
import kr.co.e1ct.mappers.TOwnerBklstRepository;
import kr.co.e1ct.mappers.TPortRepository;
import kr.co.e1ct.mappers.TRepairOrderRepository;
import kr.co.e1ct.mappers.TRfcargoRepository;
import kr.co.e1ct.mappers.TSecretRepository;
import kr.co.e1ct.mappers.TTcorderRepository;
import kr.co.e1ct.mappers.TTmnlRepository;
import kr.co.e1ct.mappers.TTruckerRepository;
import kr.co.e1ct.mappers.TTypeRepository;
import kr.co.e1ct.mappers.TUnnoRepository;
import kr.co.e1ct.mappers.TVescallRepository;
import kr.co.e1ct.mappers.TVesselRepository;
import kr.co.e1ct.mappers.TYpmasterRepository;
import kr.co.e1ct.mappers.TbRvasMastRepository;
import kr.co.e1ct.mappers.WCustHdRepository;
import kr.co.e1ct.mappers.WCustItemRepository;
import kr.co.e1ct.mappers.WSajunRepository;
import kr.co.e1ct.util.StringUtils;
import kr.co.e1ct.vo.InvoiceVo;
import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBkdtlVo;
import kr.co.e1ct.vo.TBklstVo;
import kr.co.e1ct.vo.TBkmstVo;
import kr.co.e1ct.vo.TBsiotSubVo;
import kr.co.e1ct.vo.TBsiotVo;
import kr.co.e1ct.vo.TCarVo;
import kr.co.e1ct.vo.TCarbklstVo;
import kr.co.e1ct.vo.TCashPlanVo;
import kr.co.e1ct.vo.TCntrVo;
import kr.co.e1ct.vo.TCodeVo;
import kr.co.e1ct.vo.TColdltVo;
import kr.co.e1ct.vo.TCopinoChkinVo;
import kr.co.e1ct.vo.TCopinoRjctVo;
import kr.co.e1ct.vo.TCustomerVo;
import kr.co.e1ct.vo.TDgcargoVo;
import kr.co.e1ct.vo.TDodtlVo;
import kr.co.e1ct.vo.TDomstVo;
import kr.co.e1ct.vo.TEdicustomerVo;
import kr.co.e1ct.vo.TEquiVo;
import kr.co.e1ct.vo.TFreedayVo;
import kr.co.e1ct.vo.TFreevanpoolVo;
import kr.co.e1ct.vo.TGateVo;
import kr.co.e1ct.vo.TGatehisVo;
import kr.co.e1ct.vo.TGcorderVo;
import kr.co.e1ct.vo.THoldngVo;
import kr.co.e1ct.vo.TMailVo;
import kr.co.e1ct.vo.TMovelogVo;
import kr.co.e1ct.vo.TMvRemashVo;
import kr.co.e1ct.vo.TOperVo;
import kr.co.e1ct.vo.TOwnerBklstVo;
import kr.co.e1ct.vo.TPortVo;
import kr.co.e1ct.vo.TRfcargoVo;
import kr.co.e1ct.vo.TSecretVo;
import kr.co.e1ct.vo.TTcorderVo;
import kr.co.e1ct.vo.TTmnlVo;
import kr.co.e1ct.vo.TTruckerVo;
import kr.co.e1ct.vo.TTypeVo;
import kr.co.e1ct.vo.TUnnoVo;
import kr.co.e1ct.vo.TVescallVo;
import kr.co.e1ct.vo.TVesselVo;
import kr.co.e1ct.vo.TYpmasterVo;
import kr.co.e1ct.vo.TbRvasMastVo;
import kr.co.e1ct.vo.WCustHdVo;
import kr.co.e1ct.vo.WCustItemVo;
import kr.co.e1ct.vo.WSajunVo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InfoService {
	
	@Autowired
	private TFreevanpoolRepository tFreevanpoolRepository;
	
	@Autowired
	private TCashPlanRepository tCashPlanRepository;
	
	@Autowired
	private TbRvasMastRepository tbRvasMastRepository;
	
	@Autowired
	private CommonRepository commonRepository;

	@Autowired
	private TMvRemashRepository tMvRemashRepository;
	
	@Autowired
	private TYpmasterRepository tYpmasterRepository;
	
	@Autowired
	private TInLiftCashCntrRepository tInLiftCashCntrRepository;
	
	@Autowired
	private TTruckerRepository tTruckerRepository;

	@Autowired
	private TVescallRepository tVescallRepository;
	
	@Autowired
	private TVesselRepository tVesselRepository;
	
	@Autowired
	private TCodeRepository tCodeRepository;
	
	@Autowired
	private TCustomerRepository tCustomerRepository;
	
	@Autowired
	private TCustomerHdrRepository tCustomerHdrRepository;
	
	@Autowired
	private TGcorderRepository tGcorderRepository;
	
	@Autowired
	private TGateRepository tGateRepository;
	
	@Autowired
	private TGatehisRepository tGatehisRepository;
	
	@Autowired
	private TEdicustomerRepository tEdicustomerRepository;
	
	@Autowired
	private TCntrRepository tCntrRepository;
	
	@Autowired
	private TBklstRepository tBklstRepository;
	
	@Autowired
	private TDodtlRepository tDodtlRepository;
	
	@Autowired
	private TBsiotSubRepository tBsiotSubRepository;
	
	@Autowired
	private TCarRepository tCarRepository;
	
	@Autowired
	private TSecretRepository tSecretRepository;
	
	@Autowired
	private TOperRepository tOperRepository;
	
	@Autowired
	private TPortRepository tPortRepository;
	
	@Autowired
	private TUnnoRepository tUnnoRepository;
	
	@Autowired
	private THoldngRepository tHoldngRepository;
	
	@Autowired
	private TRfcargoRepository tRfcargoRepository;
	
	@Autowired
	private TColdltRepository tColdltRepository;
	
	@Autowired
	private TBsiotRepository tBsiotRepository;
	
	@Autowired
	private TFreedayRepository tFreedayRepository;
	
	@Autowired
	private TBkmstRepository tBkmstRepository;
	
	@Autowired
	private TBkdtlRepository tBkdtlRepository;
	
	@Autowired
	private WSajunRepository wSajunRepository;
	
	@Autowired
	private WCustHdRepository wCustHdRepository;
	
	@Autowired
	private TMovelogRepository tMovelogRepository;
	
	@Autowired
	private WCustItemRepository wCustItemRepository;
	
	@Autowired
	private TRepairOrderRepository tRepairOrderRepository;
	
	@Autowired
	private TOwnerBklstRepository tOwnerBklstRepository;
	
	@Autowired
	private TEquiRepository tEquiRepository;
	
	@Autowired
	private TMailRepository tMailRepository;
	
	@Autowired
	private TDgcargoRepository tDgcargoRepository;
	
	@Autowired
	private TTypeRepository tTypeRepository;
	
	@Autowired
	private TCarbklstRepository tCarbklstRepository;
	
	@Autowired
	private TCopinoRjctRepository tCopinoRjctRepository;
	
	@Autowired
	private TDomstRepository tDomstRepository;
	
	@Autowired
	private TCopinoChkinRepository tCopinoChkinRepository;
	
	@Autowired
	private TTcorderRepository tTcorderRepository;
	
	@Autowired
	private TTmnlRepository tTmnlRepository;
	
	public List<TVescallVo> berthText(SearchVo searchVo) throws ParseException {
		List<TVescallVo> tempList = tVescallRepository.berthText( searchVo );
		for( int i=0; i<tempList.size(); i++ ) {
			if( tempList != null ) {
				/*
				 * 15 : disEndDt
				 * 16 : lodEndDt
				 * 8 : closingDt
				 */
				
				if( StringUtils.isEmpty( tempList.get(i).getDisEndDt() ) && StringUtils.isEmpty( tempList.get(i).getLodEndDt() ) ) {
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
				
				if( !StringUtils.isEmpty( tempList.get(i).getAtdDt() ) ) {
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
			}
		}

		return tempList;
	}
	
	public List<TVescallVo> berthTextPrint(SearchVo searchVo) throws ParseException {
		List<TVescallVo> tempList = tVescallRepository.berthTextPrint( searchVo );
		for( int i=0; i<tempList.size(); i++ ) {
			if( tempList != null ) {
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
			}
		}

		return tempList;
	}

	public TVescallVo vesselInfo(SearchVo searchVo) {
		TVescallVo vesselInfoVo = tVescallRepository.vesselInfo( searchVo );
		
		
		if( vesselInfoVo != null ) {
			if( vesselInfoVo.getStvBeginDt().equals("-") ) {
				TGcorderVo forParamVo = new TGcorderVo();
				forParamVo.setShipCd( vesselInfoVo.getShipCd() );
				forParamVo.setCallYy( vesselInfoVo.getCallYy() );
				forParamVo.setCallNo( vesselInfoVo.getCallNo() );
				TGcorderVo tGcorderVo = tGcorderRepository.vesselInfoOkDt( forParamVo );
				
				if( tGcorderVo != null ) {
					vesselInfoVo.setStvBeginDt( tGcorderVo.getOkDt() );
				}
			}
		}
		
		return vesselInfoVo;
	}

	public List<TVescallVo> vesselWorkDetail(SearchVo searchVo) {
		List<TVescallVo> resultList = tVescallRepository.vesselWorkDetail( searchVo );
		
		String ship = "";
		String callNo = "";
		
		for( int i=0; i<resultList.size(); i++ ) {
			TVescallVo tempVo = resultList.get(i);
			
			int count = tGcorderRepository.vesselWorkSetCount( tempVo );
			
			resultList.get(i).setVesselWorkCount( count );
			
			int ccSeq = (int) (tempVo.getDisRest() + tempVo.getLodRest());
			TVescallVo arrivalVo = tVescallRepository.vesselWorkFindAtbDt( tempVo );
			
			if( arrivalVo.getAtbDt().equals("-") ) {
				resultList.get(i).setIsArrived("Not Arrived Yet");
			} else if( ccSeq == 0 ) {
				resultList.get(i).setIsArrived( "작업완료" );
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime( new Date() );
				
				try {
					calendar.add( Calendar.MINUTE, ( ccSeq / count ) * 60 );
				} catch ( ArithmeticException e ) {
					calendar.add( Calendar.MINUTE, 0);
				}
				
				resultList.get(i).setIsArrived( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( calendar.getTime() ) );
			}
			
			if( !ship.equals( resultList.get(i).getShipCd() ) || callNo.equals( resultList.get(i).getCallNo() ) ) {
				resultList.get(i).setShipCd( resultList.get(i).getShipCd() + " - " + resultList.get(i).getCallNo() + " - " + resultList.get(i).getCallYy() );
			}
			
			ship = resultList.get(i).getShipCd();
			callNo = String.valueOf( resultList.get(i).getCallNo() );
		}
		
		return resultList;
	}

	public TCustomerVo getCustomerInfo(SearchVo searchVo) {
		TCustomerVo vo = new TCustomerVo();
		if( !StringUtils.isEmpty( searchVo.getCstNo() ) ) {
			vo = tCustomerRepository.getCustomerInfo( searchVo );
			if( vo != null && vo.getCustId() != null ) {
				String custId = vo.getCustId();
				vo.setCustId( custId.substring(0, 2) + "-" + custId.substring(2, 5) + "-" + custId.substring(6) );
			}
		}
		return vo;
	}

	public List<TCustomerVo> getConcludeList(SearchVo searchVo) {
		if( StringUtils.isEmpty( searchVo.getCstNo() ) ) {
			return null;
		}
		return tCustomerRepository.getConcludeList( searchVo );
	}

	public List<TVesselVo> getVesselNameList(SearchVo searchVo, Pageable pageable) {
		return tVesselRepository.getVesselNameList( searchVo, pageable );
	}
	
	public int berthTextCnt(SearchVo searchVo) {
		return tVescallRepository.berthTextCnt( searchVo );
	}

	public List<TGateVo> cntrListCopino(SearchVo searchVo) {
		List<TGateVo> copinoList = tGateRepository.cntrListCopino( searchVo );
		
		for( int i=0; i<copinoList.size(); i++ ) {
			TEdicustomerVo tEdicustomerVo = tEdicustomerRepository.cntrListCopinoGetSender( copinoList.get(i) );
			
			if( tEdicustomerVo != null ) {
				copinoList.get(i).setSendName( StringUtils.replaceAll( tEdicustomerVo.getCstNm(), "(주)", "" ) );
			} 
		}
		return copinoList;
	}

	public List<TVescallVo> codeVesselData(SearchVo searchVo) {
		return tVescallRepository.codeVesselData( searchVo );
	}
	
	public int codeVesselDataCount( SearchVo searchVo ) {
		return tVescallRepository.codeVesselDataCount( searchVo );
	}

	public Map<String, Object> cntrListGateInOut(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<TCntrVo> resultList = tCntrRepository.cntrListGateInOut( searchVo );
		
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		sumMap.put( "f20", 0 );
		sumMap.put( "m20", 0 );
		sumMap.put( "f40h", 0 );
		sumMap.put( "m40h", 0 );
		sumMap.put( "f40", 0 );
		sumMap.put( "m40", 0 );
		sumMap.put( "f45", 0 );
		sumMap.put( "m45", 0 );
		
		
			for( int i=0; i<resultList.size(); i++ ) {
				// 운송사 가져오기
				if( searchVo.getGubun().equals( "out" ) ) {
					if( resultList.get(i).getFmCd().equals("M") ) {
						TBklstVo paramVo = new TBklstVo();
						paramVo.setCntrNo( resultList.get(i).getCntrNo() );
						paramVo.setDupNo( resultList.get(i).getDupNo() );
						paramVo.setOperCd( searchVo.getOperCd() );
						TBklstVo tBklstVo = tBklstRepository.findBkNoByCntrNoAndDupNoAndOperCd( paramVo );
						
						resultList.get(i).setBkdoNo( tBklstVo.getBkNo() );
					} else if( resultList.get(i).getFmCd().equals( "F" ) ) {
						TDodtlVo paramVo = new TDodtlVo();
						paramVo.setCntrNo( resultList.get(i).getCntrNo() );
						paramVo.setDupNo( resultList.get(i).getDupNo() );
						TDodtlVo tDodtlVo = tDodtlRepository.findDoNoByCntrNoAndDupNo( paramVo );
						if( tDodtlVo != null ) {
							resultList.get(i).setBkdoNo( tDodtlVo.getDoNo() );
						}
					}
				} else if( searchVo.getGubun().equals( "in" ) ) {
					TCntrVo paramVo = new TCntrVo();
					paramVo.setCntrNo( resultList.get(i).getCntrNo() );
					paramVo.setDupNo( resultList.get(i).getDupNo() );
					TCntrVo tCntrVo = tCntrRepository.findFnGetBknoByCntrNoAndDupNo( paramVo );
					
					resultList.get(i).setBkdoNo( tCntrVo.getBkNo() );
					
					// copino-seal
					if( resultList.get(i).getFmCd().toUpperCase().equals("F") ) {
						List<TGatehisVo> tGatehisList = tGatehisRepository.findSealNoFromCntrListGateInOut( resultList.get(i) ) ;
						
						if( tGatehisList.size() > 0 ) {
							resultList.get(i).setCopinoSeal( tGatehisList.get(0).getSealNo() );
						}
					}
				}
				
				if( resultList.get(i).getSealNo() == null || resultList.get(i).getSealNo().equals("") ) {
					TBsiotSubVo paramVo = new TBsiotSubVo();
					paramVo.setShipCd( resultList.get(i).getShipCd() );
					paramVo.setCallNo( resultList.get(i).getCallNo() );
					paramVo.setCallYy( resultList.get(i).getCallYy() );
					paramVo.setCntrNo( resultList.get(i).getCntrNo() );
					paramVo.setDupNo( resultList.get(i).getDupNo() );
					
					TBsiotSubVo tBsiotSubVo = tBsiotSubRepository.findSealNoByShipCdAndCallNoAndCallYyAndCntrNoAndDupNo( paramVo );
					
					resultList.get(i).setSealNo( tBsiotSubVo.getSealNo() );
				} else {
					if( !StringUtils.isEmpty( resultList.get(i).getSealNo1() ) ) {
						resultList.get(i).setSealNo( resultList.get(i).getSealNo1() );
					}
				}
				
				if( resultList.get(i).getSzCd().substring(0, 1).equals("2") ) {
					if( resultList.get(i).getFmCd().equals("F") ) {
						sumMap.put( "f20", sumMap.get("f20") + 1 );
					} else if( resultList.get(i).getFmCd().equals("M") ) {
						sumMap.put( "m20", sumMap.get("m20") + 1 );
					}
				} else if( resultList.get(i).getSzCd().equals("44") || resultList.get(i).getSzCd().equals("45") ) {
					if( resultList.get(i).getFmCd().equals("F") ) {
						sumMap.put( "f40h", sumMap.get("f40h") + 1 );
					} else if( resultList.get(i).getFmCd().equals("M") ) {
						sumMap.put( "m40h", sumMap.get("m40h") + 1 );
					}
				} else if( resultList.get(i).getSzCd().substring(0,1).equals("4") ) {
					if( resultList.get(i).getFmCd().equals("F") ) {
						sumMap.put( "f40", sumMap.get("f40") + 1 );
					} else if( resultList.get(i).getFmCd().equals("M") ) {
						sumMap.put( "m40", sumMap.get("m40") + 1 );
					}
				} else if( resultList.get(i).getSzCd().substring(0,1).equals("9") || resultList.get(i).getSzCd().substring(0,1).equals("L") ) {
					if( resultList.get(i).getFmCd().equals("F") ) {
						sumMap.put( "f45", sumMap.get("f45") + 1 );
					} else if( resultList.get(i).getFmCd().equals("M") ) {
						sumMap.put( "f45", sumMap.get("m45") + 1 );
					}
				}
			}
			
			resultMap.put( "list", resultList );
		resultMap.put( "sumMap", sumMap );
		
		return resultMap;
	}

	public TSecretVo findTSecretByEmpNo(TSecretVo tSecretVo) {
		return tSecretRepository.findByEmpNo( tSecretVo );
	}

	public List<String> cntrListGateInOutOperCdInitialize(TSecretVo tSecretVo) {
		if( tSecretVo == null ) {
			return tOperRepository.findOperCdListForAnonymousAndManager();
		} else if( tSecretVo.getJo().equals("S") || tSecretVo.getJo().equals("G") ) {
			return tOperRepository.findOperCdListForAnonymousAndManager();
		} else {
			TSecretVo operCdParam = new TSecretVo();
			operCdParam.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
			return tSecretRepository.findOperCdListForOthers( operCdParam );
		}
	}

	public List<TPortVo> codePortData(SearchVo searchVo) {
		return tPortRepository.findTCodeListPageByPortNm( searchVo );
	}

	public int codePortDataCount(SearchVo searchVo) {
		return tPortRepository.findTCodeCountPageByPortNm( searchVo );
	}

	public List<TOperVo> codeShipperData(SearchVo searchVo) {
		return tOperRepository.codeShipperData( searchVo );
	}
	
	public int codeShipperDataCount( SearchVo searchVo ) {
		return tOperRepository.codeShipperDataCount( searchVo );
	}

	public List<TUnnoVo> codeDangerData(SearchVo searchVo) {
		return tUnnoRepository.codeDangerData( searchVo );
	}
	
	public int codeDangerDataCount(SearchVo searchVo) {
		return tUnnoRepository.codeDangerDataCount( searchVo );
	}

	public List<String> getOperListByAuthority() {
		TSecretVo tSecretParamVo = new TSecretVo();
		tSecretParamVo.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
		
		TSecretVo tSecretVo = tSecretRepository.findByEmpNo( tSecretParamVo );
		
		if( tSecretVo == null ) {
			return tOperRepository.findOperCdListForAnonymousAndManager();
		} else if( tSecretVo.getJo().equals("S") || tSecretVo.getJo().equals("G") ) {
			return tOperRepository.findOperCdListForAnonymousAndManager();
		} else {
			TSecretVo operCdParam = new TSecretVo();
			operCdParam.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
			return tSecretRepository.findOperCdListForOthers( operCdParam );
		}
	}

	public Map<String, Object> terminalByOperYardList(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		sumMap.put( "imp", 0 );
		sumMap.put( "exp", 0 );
		sumMap.put( "ondock", 0 );
		sumMap.put( "size20", 0 );
		sumMap.put( "sizef40", 0 );
		sumMap.put( "sizef45", 0 );
		sumMap.put( "van", 0 );
		sumMap.put( "teu", 0 );
		
		TSecretVo tSecretParamVo = new TSecretVo();
		tSecretParamVo.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
		
		TSecretVo tSecretVo = tSecretRepository.findByEmpNo( tSecretParamVo );
		if( tSecretVo != null ) {
			searchVo.setJo( tSecretVo.getJo() );
		}
		
		List<TCntrVo> resultList = tCntrRepository.terminalByOperYardList( searchVo );
		
		for( int i=0; i<resultList.size(); i++ ) {
			sumMap.put( "imp", sumMap.get("imp") + resultList.get(i).getImp() );
			sumMap.put( "exp", sumMap.get("exp") + resultList.get(i).getExp() );
			sumMap.put( "ondock", sumMap.get("ondock") + resultList.get(i).getOndock() );
			sumMap.put( "size20", sumMap.get("size20") + resultList.get(i).getSize20() );
			sumMap.put( "sizef40", sumMap.get("sizef40") + resultList.get(i).getSizef40() );
			sumMap.put( "sizef45", sumMap.get("sizef45") + resultList.get(i).getSizef45() );
			sumMap.put( "van", sumMap.get("van") + resultList.get(i).getVan() );
			sumMap.put( "teu", sumMap.get("teu") + resultList.get(i).getTeu() );
		}
		
		resultMap.put( "list", resultList );
		resultMap.put( "sumList", sumMap );	
		
		return resultMap;
	}

	public List<TVescallVo> terminalBerthGraphic(SearchVo searchVo) {
		return tVescallRepository.terminalBerthGraphic( searchVo );
	}

	public Map<String, Object> cntrListNotGateInOut(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		sumMap.put( "f20", 0 );
		sumMap.put( "m20", 0 );
		sumMap.put( "f40", 0 );
		sumMap.put( "m40", 0 );
		sumMap.put( "f40h", 0 );
		sumMap.put( "m40h", 0 );
		sumMap.put( "f45", 0 );
		sumMap.put( "m45", 0 );
		
		
		if( searchVo.getGubun().toUpperCase().equals("OUT") ) { 
			List<TCntrVo> resultList = tCntrRepository.cntrListNotGateOut( searchVo );
			
			for( int i=0; i<resultList.size(); i++ ) {
				if( resultList.get(i).getSzCd().substring(0,1).equals("2") ) {
					if( resultList.get(i).getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f20", sumMap.get("f20") + 1 );
					} else if( resultList.get(i).getFmCd().toUpperCase().equals( "M" ) ) {
						sumMap.put( "m20",  sumMap.get("m20") + 1 );
					}
				} else if( resultList.get(i).getSzCd().equals("45") || resultList.get(i).getSzCd().equals("44") ) {
					if( resultList.get(i).getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f40h", sumMap.get("f40h") + 1 );
					} else if( resultList.get(i).getFmCd().toUpperCase().equals( "M" ) ) {
						sumMap.put( "m40h",  sumMap.get("m40h") + 1 );
					}
				} else if( resultList.get(i).getSzCd().substring(0, 1).equals("4") ) {
					if( resultList.get(i).getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f40", sumMap.get("f40") + 1 );
					} else if( resultList.get(i).getFmCd().toUpperCase().equals( "M" ) ) {
						sumMap.put( "m40",  sumMap.get("m40") + 1 );
					}
				} else if( resultList.get(i).getSzCd().substring(0, 1).equals("9") || resultList.get(i).getSzCd().substring(0, 1).toUpperCase().equals("L") ) {
					if( resultList.get(i).getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f45", sumMap.get("f45") + 1 );
					} else if( resultList.get(i).getFmCd().toUpperCase().equals( "M" ) ) {
						sumMap.put( "m45",  sumMap.get("m45") + 1 );
					}
				}
			}
			
			resultMap.put( "list", resultList );
			resultMap.put( "sumMap", sumMap );
		} else { 
			
			List<TCntrVo> resultList = tCntrRepository.cntrListNotGateIn( searchVo );
			
			for( int i=0; i<resultList.size(); i++ ) {
				if( resultList.get(i).getSztp().substring(0,1).equals("2") ) {
					if( resultList.get(i).getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f20", sumMap.get("f20") + 1 );
					} else if( resultList.get(i).getFmCd().toUpperCase().equals( "M" ) ) {
						sumMap.put( "m20",  sumMap.get("m20") + 1 );
					}
				} else if( resultList.get(i).getSztp().substring(0, 1).equals( "4" ) ) {
					if( resultList.get(i).getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f40", sumMap.get("f40") + 1 );
					} else if( resultList.get(i).getFmCd().toUpperCase().equals( "M" ) ) {
						sumMap.put( "m40",  sumMap.get("m40") + 1 );
					}
				} else if( resultList.get(i).getSztp().substring(0, 1).equals("9") || resultList.get(i).getSztp().substring(0, 1).toUpperCase().equals("L") ) {
					if( resultList.get(i).getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f45", sumMap.get("f45") + 1 );
					} else if( resultList.get(i).getFmCd().toUpperCase().equals( "M" ) ) {
						sumMap.put( "m45",  sumMap.get("m45") + 1 );
					}
				}
			}
			
			resultMap.put( "list", resultList );
			resultMap.put( "sumMap", sumMap );
		}
		
		return resultMap;
	}

	public List<HashMap<String, Object>> terminalYardStackData(SearchVo searchVo) {
		if( searchVo.getGubun().toUpperCase().equals("CNTR") && searchVo.getCntrNo() != null && !searchVo.getCntrNo().equals( "" ) ) {
			TCntrVo tCntrVo = tCntrRepository.findYTblockAndYTbayByCntrNoAndStcking( searchVo );
			searchVo.setBlock( tCntrVo.getYTblock() );
			searchVo.setBay( String.valueOf( tCntrVo.getYTbay() ) );
		}
		
		List<TCntrVo> stackList = tCntrRepository.terminalYardStackData( searchVo );
		List<HashMap<String, Object>> resultList = new ArrayList< HashMap<String, Object > >();
		
		for( int i=0; i<stackList.size(); i++ ) {
			HashMap<String, Object> tempMap = new HashMap<String, Object>();
			
			tempMap.put( "cntrNo", stackList.get(i).getCntrNo() );
			tempMap.put( "curStat", stackList.get(i).getCurStat() );
			tempMap.put( "szCd", stackList.get(i).getSzCd() );
			tempMap.put( "yTrow", stackList.get(i).getYTrow() );
			tempMap.put( "yTtier", stackList.get(i).getYTtier() );
			tempMap.put( "yTblock", searchVo.getBlock() );
			tempMap.put( "yTbay", searchVo.getBay() );
			
			resultList.add( tempMap );
		}
		
		return resultList;
	}

	public TCntrVo cntrInfoDetailsDataLatest(SearchVo searchVo) throws ParseException {
		TCntrVo tCntrVo = tCntrRepository.cntrInfoDetailsDataLatest( searchVo );
		if( tCntrVo != null ) {
			tCntrVo.setCntrNo( searchVo.getSearch() );
			TCntrVo stockDayVo = tCntrRepository.cntrInfoDetailsStockDays( tCntrVo );
			
			
			if( !StringUtils.isEmpty( tCntrVo.getSealNo2() ) ) {
				tCntrVo.setSealNo( tCntrVo.getSealNo2() );
			} else if( !StringUtils.isEmpty( tCntrVo.getSealNo1() ) ) {
				tCntrVo.setSealNo( tCntrVo.getSealNo1() );
			} 
			
			SimpleDateFormat fullFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
			
			Date currentDate = new Date();
			
			String queryDateString = "";
			String queryDateString2 = "";
			if( stockDayVo != null ) {
				if( StringUtils.isEmpty( tCntrVo.getGoutDt() ) && StringUtils.isEmpty( tCntrVo.getGcLdt() ) ) {
					queryDateString = sdf.format( currentDate ) + " 00:00:00";
					queryDateString2 = sdf.format( currentDate) + " 08:00:00";
				} else if( StringUtils.isEmpty( tCntrVo.getGoutDt() ) ) {
					Calendar c = Calendar.getInstance();
					c.setTime( fullFormat.parse( stockDayVo.getAtbDt() ) );
					c.add( Calendar.DATE, -1 );
					
					queryDateString = sdf.format( c.getTime() ) + " 08:00:00";
				} else {
					queryDateString = tCntrVo.getGoutDt().trim();
					queryDateString2 = tCntrVo.getGoutDt().trim();
					
					log.info( "GOUTDT : " + tCntrVo.getGoutDt() );
				}
			}
			
			Long diffTime = 0L;
			
			// TYLU2804910 = Stock days 0일
			
			// CAIU6420548 = 양하 14일 07:39 
			
			// 
			
			log.info( stockDayVo.getFromDt() );
			if( stockDayVo.getFromDt() == null || stockDayVo.getFromDt().trim().equals("") ) {
				tCntrVo.setYardDay( 0 );
			} else if( tCntrVo.getCurStat().substring(0,1).toUpperCase().equals("X") || tCntrVo.getCurStat().substring(0, 1).toUpperCase().equals( "M" ) ) {
				if( !StringUtils.isEmpty( tCntrVo.getTsId() ) && tCntrVo.getTsId().equals("1") ) {
					String tsFromDateString;
					if( Integer.valueOf( timeFormat.format( timeFormat.parse( stockDayVo.getTaShipDt() ) ) ) >= Integer.valueOf( "080000" ) ) {
						Calendar c = Calendar.getInstance();
						c.setTime( fullFormat.parse( stockDayVo.getTaShipDt() ) );
						c.add( Calendar.DATE, 1);
						
						tsFromDateString = sdf.format( c.getTime() ) + " 08:00:00";
					} else {
						tsFromDateString = sdf.format( sdf.parse( stockDayVo.getTaShipDt() ) ) + " 08:00:00";
					}
					
					diffTime = fullFormat.parse( tsFromDateString ).getTime() - fullFormat.parse( queryDateString ).getTime();
				} else {
					try {
						diffTime = fullFormat.parse( stockDayVo.getFromDt() ).getTime() - fullFormat.parse( queryDateString ).getTime();
						diffTime -= 24 * 60 * 60 * 1000;
					} catch( ParseException e ) {
						diffTime = 0L;
					}
				}
			} else {
				log.info( "LOGIC IN" );
				log.info( "stockDayVo.getFromDt() : " + stockDayVo.getFromDt() );
				log.info( "queryDateString2 : " + queryDateString2 );
				if( stockDayVo.getFromDt().equals("") || queryDateString2.equals(" ") ) {
					diffTime = 0L;
				} else {
					try {
						Long fromDtTime = fullFormat.parse( stockDayVo.getFromDt() ).getTime();
						if( queryDateString2.length() <19 ) {
							queryDateString2 += ":00";
						}
						Long queryDateStringTime = fullFormat.parse( queryDateString2 ).getTime();
						diffTime = fromDtTime - queryDateStringTime;
						
						log.info( "stockDayVo.getFromDt() : " + stockDayVo.getFromDt() );
						log.info( "queryDateString2 : " + queryDateString2 );
						log.info( "fromDtTime : " + fromDtTime );
						log.info( "queryDateStringTime : " + queryDateStringTime );
					} catch ( ParseException e ) {
						diffTime = 0L;
					}
				}
			}

			if( diffTime != 0 ) {
				tCntrVo.setYardDay( (int) Math.abs( diffTime / ( 24 * 60 * 60 * 1000 ) ) + 1 );
			} else {
				Calendar c = Calendar.getInstance();
				if( c.get( Calendar.HOUR_OF_DAY ) >= 8 ) {
					tCntrVo.setYardDay( 1 );
				}
			}
			
			if( StringUtils.isEmpty( tCntrVo.getBkNo() ) || tCntrVo.getBkNo().equals("0") || !tCntrVo.getBkNo().substring(2, 3).equals("D") && tCntrVo.getFmCd().equals("F") ) {
				tCntrVo.setOrderNo( "" );
			} else {
				tCntrVo.setOrderNo( tCntrVo.getBkNo() );
			}
		}
		return tCntrVo;
	}

	public List<TBsiotSubVo> cntrInfoDetailsCargoList(TCntrVo dataVo) {
		return tBsiotSubRepository.findCargoNoListByCntrNoAndDupNo( dataVo );
	}

	public List<THoldngVo> cntrInfoDetailsHoldList(TCntrVo dataVo) {
		return tHoldngRepository.findCdNmAndCdValByCntrNoAndDupNoAndHoldCdAndCdIdAndRelsDt( dataVo );
	}

	public List<TCntrVo> cntrListCntrRehandlingList(SearchVo searchVo) {
		if( !StringUtils.isEmpty( searchVo.getShipCd() ) && !StringUtils.isEmpty( searchVo.getCallNo() ) && !StringUtils.isEmpty( searchVo.getCallYy() ) ) {
			return tCntrRepository.cntrListCntrRehandlingList( searchVo );
		} else {
			return new ArrayList<TCntrVo>();
		}
	}

	public Map<String, Object> cntrListReeferCargo(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SearchVo operListAdded = setOperList(searchVo);
		if( operListAdded.getOperList().size() > 0 ) {
			searchVo.setOper( operListAdded.getOperList().get(0) );
		}
		List<TCntrVo> list = tCntrRepository.cntrListReeferCargo( searchVo );
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		sumMap.put( "f20", 0 );
		sumMap.put( "m20", 0 );
		sumMap.put( "f40", 0 );
		sumMap.put( "m40", 0 );
		sumMap.put( "f45", 0 );
		sumMap.put( "m45", 0 );
		
		for( int i=0; i<list.size(); i++ ) {
			if( searchVo.getGubun().toLowerCase().equals( "dry" ) ) {
				TRfcargoVo cargoVo = tRfcargoRepository.findReqTempAndPlinDtByShipCdAndCallYyAndCallNoAndCntrNo( list.get(i) );
				
				if( cargoVo != null ) {
					list.get(i).setReqTemp( cargoVo.getReqTemp() );
					list.get(i).setPlinDt( cargoVo.getPlinDt() );
					list.get(i).setPloutDt( cargoVo.getPloutDt() );
				}
			}
			
			if( list.get(i).getSzCd().substring(0, 1).equals("2") ) {
				if( list.get(i).getFmCd().toUpperCase().equals("F") ) {
					sumMap.put( "f20", sumMap.get("f20") + 1 );
				} else if( list.get(i).getFmCd().toUpperCase().equals("M") ) {
					sumMap.put( "m20", sumMap.get("m20") + 1 );
				}
			} else if( list.get(i).getSzCd().substring(0, 1).equals("4") ) {
				if( list.get(i).getFmCd().toUpperCase().equals("F") ) {
					sumMap.put( "f40", sumMap.get("f40") + 1 );
				} else if( list.get(i).getFmCd().toUpperCase().equals("M") ) {
					sumMap.put( "m40", sumMap.get("m40") + 1 );
				}
			} else if( list.get(i).getSzCd().substring(0, 1).equals("9") || list.get(i).getSzCd().substring(0, 1).equals("L") ) {
				if( list.get(i).getFmCd().toUpperCase().equals("F") ) {
					sumMap.put( "f45", sumMap.get("f45") + 1 );
				} else if( list.get(i).getFmCd().toUpperCase().equals("M") ) {
					sumMap.put( "m45", sumMap.get("m45") + 1 );
				}
			}
			
			list.get(i).setTempId( list.get(i).getTempId().substring(0, 1) );
			
			if( StringUtils.isEmpty( list.get(i).getTsId() ) ) {
				list.get(i).setTsId("");
			}
			if( StringUtils.isEmpty( list.get(i).getRtnCd() ) ) {
				list.get(i).setRtnCd( "" );
			}
		}
		
		resultMap.put( "list", list );
		resultMap.put( "sumMap", sumMap );
		
		return resultMap;
	}

	public Map<String, Object> cntrListCntrDanger(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<TCntrVo> list = tCntrRepository.cntrListCntrDanger( searchVo );
		
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		sumMap.put( "f20", 0 );
		sumMap.put( "m20", 0 );
		sumMap.put( "f40", 0 );
		sumMap.put( "m40", 0 );
		sumMap.put( "f45", 0 );
		sumMap.put( "m45", 0 );
		
		for( int i=0; i<list.size(); i++ ) {
			if( list.get(i).getSzCd().substring(0, 1).equals("2") ) {
				if( list.get(i).getFmCd().toUpperCase().equals("F") ) {
					sumMap.put( "f20", sumMap.get("f20") + 1 );
				} else if( list.get(i).getFmCd().toUpperCase().equals("M") ) {
					sumMap.put( "m20", sumMap.get("m20") + 1 );
				}
			} else if( list.get(i).getSzCd().substring(0, 1).equals("4") ) {
				if( list.get(i).getFmCd().toUpperCase().equals("F") ) {
					sumMap.put( "f40", sumMap.get("f40") + 1 );
				} else if( list.get(i).getFmCd().toUpperCase().equals("M") ) {
					sumMap.put( "m40", sumMap.get("m40") + 1 );
				}
			} else if( list.get(i).getSzCd().substring(0, 1).equals("9") || list.get(i).getSzCd().substring(0, 1).equals("L") ) {
				if( list.get(i).getFmCd().toUpperCase().equals("F") ) {
					sumMap.put( "f45", sumMap.get("f45") + 1 );
				} else if( list.get(i).getFmCd().toUpperCase().equals("M") ) {
					sumMap.put( "m45", sumMap.get("m45") + 1 );
				}
			}
			
			if( list.get(i).getTsId() == null ) {
				list.get(i).setTsId("");
			}
			if( list.get(i).getRtnCd() == null ) {
				list.get(i).setRtnCd( "" );
			}
		}
		
		resultMap.put( "list", list );
		resultMap.put( "sumMap", sumMap );
		
		return resultMap;
	}

	public Map<String, Object> cntrListCntrCancel(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		SearchVo operListAdded = setOperList(searchVo);
		if( operListAdded.getOperList().size() > 0 ) {
			searchVo.setOper( operListAdded.getOperList().get(0) );
		}
		List<TCntrVo> list = tCntrRepository.cntrListCntrCancel( searchVo );
		
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		sumMap.put( "f20", 0 );
		sumMap.put( "m20", 0 );
		sumMap.put( "f40", 0 );
		sumMap.put( "m40", 0 );
		sumMap.put( "f40h", 0 );
		sumMap.put( "m40h", 0 );
		sumMap.put( "f45", 0 );
		sumMap.put( "m45", 0 );
		
		for( int i=0; i<list.size(); i++ ) {
			TCntrVo data = list.get(i);
			
			if( data.getSzCd().substring(0, 1).equals("2") ) {
				if( data.getFmCd().toUpperCase().equals("F") ) {
					sumMap.put( "f20", sumMap.get("f20") + 1 );
				} else if( data.getFmCd().toUpperCase().equals("M") ) {
					sumMap.put( "m20", sumMap.get("m20") + 1 );
				}
			} else if( data.getSzCd().equals("45") || data.getSzCd().equals( "44" ) ) {
				if( data.getFmCd().toUpperCase().equals("F") ) {
					sumMap.put( "f40h", sumMap.get("f40h") + 1 );
				} else if( data.getFmCd().toUpperCase().equals("M") ) {
					sumMap.put( "m40h", sumMap.get("m40h") + 1 );
				}
			} else if( list.get(i).getSzCd().substring(0, 1).equals("4") ) {
				if( list.get(i).getFmCd().toUpperCase().equals("F") ) {
					sumMap.put( "f40", sumMap.get("f40") + 1 );
				} else if( list.get(i).getFmCd().toUpperCase().equals("M") ) {
					sumMap.put( "m40", sumMap.get("m40") + 1 );
				}
			} else if( data.getSzCd().substring(0, 1).equals("9") || data.getSzCd().substring(0, 1).equals("L") ) {
				if( data.getFmCd().toUpperCase().equals("F") ) {
					sumMap.put( "f45", sumMap.get("f45") + 1 );
				} else if( data.getFmCd().toUpperCase().equals("M") ) {
					sumMap.put( "m45", sumMap.get("m45") + 1 );
				}
			}
			
			if( data.getOvH() == 0L ) {
				list.get(i).setOv1( "" );
			} else {
				list.get(i).setOv1( "1" );
			}
			
			if( data.getOvWp() == 0L && data.getOvWs() == 0L ) {
				list.get(i).setOv2( "" );
			} else if( data.getOvWp() != 0L && data.getOvWs() == 0L ) {
				list.get(i).setOv2( "1" );
			} else if( data.getOvWp() == 0L && data.getOvWs() != 0L ) {
				list.get(i).setOv2( "2" );
			} else {
				list.get(i).setOv2( "3" );
			}
			
			if( data.getOvLf() == 0L && data.getOvLb() == 0L ) {
				list.get(i).setOv3( "" );
			} else if( data.getOvLf() != 0L && data.getOvLb() == 0L ) {
				list.get(i).setOv3( "1" );
			} else if( data.getOvLf() == 0L && data.getOvLb() != 0L ) {
				list.get(i).setOv3( "2" );
			} else {
				list.get(i).setOv3( "3" );
			}
		}
		
		resultMap.put( "list", list );
		resultMap.put( "sumMap", sumMap );
		
		return resultMap;
	}

	public Map<String, Object> cntrListLongStack(SearchVo searchVo) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<TCntrVo> list = new ArrayList<TCntrVo>();
		log.info( "fmCd : " + searchVo.getFmCd() );
		if( !StringUtils.isEmpty( searchVo.getOper() ) ) {
			list = tCntrRepository.cntrListLongStackRenewal( searchVo );
		}
		
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		sumMap.put( "f20", 0 );
		sumMap.put( "m20", 0 );
		sumMap.put( "f40", 0 );
		sumMap.put( "m40", 0 );
		sumMap.put( "f40h", 0 );
		sumMap.put( "m40h", 0 );
		sumMap.put( "f45", 0 );
		sumMap.put( "m45", 0 );
		
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
			TCntrVo data = list.get(i);
			
//			if( !StringUtils.isEmpty( list.get(i).getFromDt() ) ) {
//				String replacedFromDt = StringUtils.replaceAll( StringUtils.replaceAll( list.get(i).getFromDt(), "-", StringUtils.EMPTY), ":", StringUtils.EMPTY );
//				Calendar fromDtCalendar = Calendar.getInstance();
//				fromDtCalendar.set( Calendar.YEAR, Integer.valueOf( replacedFromDt.substring(0, 4) ) );
//				fromDtCalendar.set( Calendar.MONTH, Integer.valueOf( replacedFromDt.substring(5, 6) ) );
//				fromDtCalendar.set( Calendar.DATE, Integer.valueOf( replacedFromDt.substring(7,8) ) );
//				fromDtCalendar.set( Calendar.HOUR, Integer.valueOf( replacedFromDt.substring(9,10) ) );
//				fromDtCalendar.set( Calendar.MINUTE, Integer.valueOf( replacedFromDt.substring(11,12) ) );
//				fromDtCalendar.set( Calendar.SECOND, Integer.valueOf( replacedFromDt.substring(13,14) ) );
//				
//				if( data.getCurStat().substring(0, 1).toUpperCase().equals("X") || data.getCurStat().substring(0, 1).toUpperCase().equals("M") ) {
//					int diffDay = (int) (Math.abs( ( queryDate.getTime() - fromDtCalendar.getTime().getTime() ) / (24 * 60 * 60 * 1000) ) + 1);
//					list.get(i).setYardDay( diffDay );
//				} else {
//					int diffDay = (int) Math.abs( ( queryDate2.getTime() - fromDtCalendar.getTime().getTime() ) / (24 * 60 * 60 * 1000) ) + 1;
//					list.get(i).setYardDay( diffDay );
//				}
//			} else {
//				list.get(i).setYardDay( 0 );
//			}
			
			List<String> cargoNoList = new ArrayList<String>();
			List<String> cargoNo1List = new ArrayList<String>();
			
			List<TBsiotVo> mstblNoList = tBsiotRepository.cntrListLongStackMstBlNoList( list.get(i) );
			
			for( int j=0; j<mstblNoList.size(); j++ ) {
				cargoNoList.add( mstblNoList.get(j).getMstblNo() );
			}
			
			List<TBsiotSubVo> cargoNoDataList = tBsiotSubRepository.cntrListLongStackCargoNoList( list.get(i) );
			
			for( int j=0; j<cargoNoDataList.size(); j++ ) {
				cargoNo1List.add( cargoNoDataList.get(j).getCargoNo() );
			}
			
			list.get(i).setCargoNoList( cargoNoList );
			list.get(i).setCargoNo1List( cargoNo1List );
			
			if( list.get(i).getYardDay() > 10 ) {
				if( data.getSzCd().substring(0, 1).equals("2") ) {
					if( data.getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f20", sumMap.get("f20") + 1 );
					} else if( data.getFmCd().toUpperCase().equals("M") ) {
						sumMap.put( "m20", sumMap.get("m20") + 1 );
					}
				} else if( data.getSzCd().equals("45") || data.getSzCd().equals( "44" ) ) {
					if( data.getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f40h", sumMap.get("f40h") + 1 );
					} else if( data.getFmCd().toUpperCase().equals("M") ) {
						sumMap.put( "m40h", sumMap.get("m40h") + 1 );
					}
				} else if( list.get(i).getSzCd().substring(0, 1).equals("4") ) {
					if( list.get(i).getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f40", sumMap.get("f40") + 1 );
					} else if( list.get(i).getFmCd().toUpperCase().equals("M") ) {
						sumMap.put( "m40", sumMap.get("m40") + 1 );
					}
				} else if( data.getSzCd().substring(0, 1).equals("9") || data.getSzCd().substring(0, 1).equals("L") ) {
					if( data.getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f45", sumMap.get("f45") + 1 );
					} else if( data.getFmCd().toUpperCase().equals("M") ) {
						sumMap.put( "m45", sumMap.get("m45") + 1 );
					}
				}
			}
			
		}
		
		resultMap.put( "list", list );
		resultMap.put( "sumMap", sumMap );
		
		return resultMap;
	}

	public List<TCntrVo> cllOverStorage(SearchVo searchVo) throws ParseException {
		if( searchVo.getShipCd() == null || searchVo.getShipCd().equals("") || 
				searchVo.getCallNo() == null || searchVo.getCallNo().equals("") ||
				searchVo.getCallYy() == null || searchVo.getCallYy().equals("") ) {
			return new ArrayList<TCntrVo>();
		} else {
			List<TCntrVo> list = tCntrRepository.cllOverStorage( searchVo );
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
			return list;
		}
	}

	public List<TColdltVo> cllOverStorageCheckCll(SearchVo searchVo) {
		if( searchVo.getShipCd() == null || searchVo.getShipCd().equals("") || 
				searchVo.getCallNo() == null || searchVo.getCallNo().equals("") ||
				searchVo.getCallYy() == null || searchVo.getCallYy().equals("") ) {
			return null;
		} else {
			return tColdltRepository.cllOverStorageCheckCll( searchVo );
		}
	}

	public Map<String, Object> byVslDisLod(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<TCntrVo> list = null;
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		sumMap.put( "f20", 0 );
		sumMap.put( "m20", 0 );
		sumMap.put( "f40", 0 );
		sumMap.put( "m40", 0 );
		sumMap.put( "f40h", 0 );
		sumMap.put( "m40h", 0 );
		sumMap.put( "f45", 0 );
		sumMap.put( "m45", 0 );
		
		if( !StringUtils.isEmpty( searchVo.getShipCd() ) && !StringUtils.isEmpty( searchVo.getCallNo() ) && !StringUtils.isEmpty( searchVo.getCallYy() ) ) {
			list = tCntrRepository.byVslDisLod( searchVo );
			
			for( int i=0; i<list.size(); i++ ) {
				TCntrVo data = list.get(i);
				
				if( data.getSzCd().substring(0, 1).equals("2") ) {
					if( data.getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f20", sumMap.get("f20") + 1 );
					} else if( data.getFmCd().toUpperCase().equals("M") ) {
						sumMap.put( "m20", sumMap.get("m20") + 1 );
					}
				} else if( data.getSzCd().equals("45") || data.getSzCd().equals( "44" ) ) {
					if( data.getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f40h", sumMap.get("f40h") + 1 );
					} else if( data.getFmCd().toUpperCase().equals("M") ) {
						sumMap.put( "m40h", sumMap.get("m40h") + 1 );
					}
				} else if( list.get(i).getSzCd().substring(0, 1).equals("4") ) {
					if( list.get(i).getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f40", sumMap.get("f40") + 1 );
					} else if( list.get(i).getFmCd().toUpperCase().equals("M") ) {
						sumMap.put( "m40", sumMap.get("m40") + 1 );
					}
				} else if( data.getSzCd().substring(0, 1).equals("9") || data.getSzCd().substring(0, 1).equals("L") ) {
					if( data.getFmCd().toUpperCase().equals("F") ) {
						sumMap.put( "f45", sumMap.get("f45") + 1 );
					} else if( data.getFmCd().toUpperCase().equals("M") ) {
						sumMap.put( "m45", sumMap.get("m45") + 1 );
					}
				}
				
			}
		} else {
			list = new ArrayList<TCntrVo>(); 
		}
		
		resultMap.put( "list", list );
		resultMap.put( "sumMap", sumMap );
		
		return resultMap;
	}

	public Map<String, Object> cntrInfoStock(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SearchVo operListAdded = setOperList(searchVo);
		if( operListAdded.getOperList().size() > 0 ) {
			searchVo.setOper( operListAdded.getOperList().get(0) );
		}
		List<TCntrVo> list = tCntrRepository.cntrInfoStock( searchVo );
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		sumMap.put( "f20", 0 );
		sumMap.put( "f40", 0 );
		sumMap.put( "f45", 0 );
		
		sumMap.put( "m20", 0 );
		sumMap.put( "m40", 0 );
		sumMap.put( "m45", 0 );
		
		sumMap.put( "t20", 0 );
		sumMap.put( "t40", 0 );
		sumMap.put( "t45", 0 );
		
		sumMap.put( "teu", 0 );
		
		for( int i=0; i<list.size(); i++ ) {
			sumMap.put( "f20", Integer.valueOf( sumMap.get("f20") ) + Integer.valueOf( list.get(i).getF20() ) );
			sumMap.put( "f40", Integer.valueOf( sumMap.get("f40") ) + Integer.valueOf( list.get(i).getF40() ) );
			sumMap.put( "f45", Integer.valueOf( sumMap.get("f45") ) + Integer.valueOf( list.get(i).getF45() ) );
			sumMap.put( "m20", Integer.valueOf( sumMap.get("m20") ) + Integer.valueOf( list.get(i).getM20() ) );
			sumMap.put( "m40", Integer.valueOf( sumMap.get("m40") ) + Integer.valueOf( list.get(i).getM40() ) );
			sumMap.put( "m45", Integer.valueOf( sumMap.get("m45") ) + Integer.valueOf( list.get(i).getM45() ) );
			sumMap.put( "t20", Integer.valueOf( sumMap.get("t20") ) + Integer.valueOf( list.get(i).getT20() ) );
			sumMap.put( "t40", Integer.valueOf( sumMap.get("t40") ) + Integer.valueOf( list.get(i).getT40() ) );
			sumMap.put( "t45", Integer.valueOf( sumMap.get("t45") ) + Integer.valueOf( list.get(i).getT45() ) );
			sumMap.put( "teu", Integer.valueOf( sumMap.get("teu") ) + Integer.valueOf( list.get(i).getTeu() ) );
		}
		
		resultMap.put( "list", list );
		resultMap.put( "sumMap", sumMap );
		return resultMap;
	}

	public TGcorderVo cntrInfoWorkingTime(SearchVo searchVo) throws ParseException {
		
		if( !StringUtils.isEmpty( searchVo.getCntrNo() ) ) {
			TGcorderVo tGcorderVo = tGcorderRepository.cntrInfoWorkingTimeFindTGcorderInfo( searchVo );
			
			if( tGcorderVo == null ) {
				return null;
			} else {
				if( StringUtils.removeSpace( tGcorderVo.getOkDt() ).equals( StringUtils.EMPTY ) ) {
					tGcorderVo.setOkDt( StringUtils.EMPTY );
				}
				
				int vanHour = tGcorderRepository.cntrInfoWorkingTimeGetVanhour( tGcorderVo );
				vanHour = vanHour == 0 ? 1 : vanHour;
				
				TVescallVo tVescallVo = tVescallRepository.findAtbDtByShipCdAndCallNoAndCallYy( tGcorderVo );
				if( tVescallVo == null ) {
					tGcorderVo.setResultDt( null );
				} else {
					tGcorderVo.setResultDt( tVescallVo.getAtbDt() );
				}
				
				if( StringUtils.isEmpty( tGcorderVo.getOkDt() ) ) {
					TGcorderVo maxOkDtVo = tGcorderRepository.cntrInfoWorkingTimeGetMaxOkDt( tGcorderVo );
					tGcorderVo.setMaxOkDt( maxOkDtVo != null ? maxOkDtVo.getOkDt() : null );
				}
				
				Calendar c = Calendar.getInstance();
				if( StringUtils.isEmpty( tGcorderVo.getMaxOkDt() ) ) {
					if( tVescallVo != null && !StringUtils.isEmpty( tVescallVo.getAtbDt() ) ) {
						c.setTime( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( tVescallVo.getAtbDt() ) );
					}
				} else {
					List<TGcorderVo> cntrList = tGcorderRepository.cntrInfoWorkingGetCntrNoList( tGcorderVo );
					
					
					for( int i=0; i<cntrList.size(); i++ ) {
						c.setTime( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( tGcorderVo.getMaxOkDt() ) );
						if( cntrList.get(i).getCntrNo().toUpperCase().equals( searchVo.getCntrNo().toUpperCase() ) ) {
							c.add( Calendar.MINUTE, ( (i+1)/vanHour ) * 60 );
							break;
						} else {
							c.add( Calendar.MINUTE, (int) (( ( tGcorderVo.getCcSeq() )/vanHour ) * 60) );
						}
					}
				}
				
				tGcorderVo.setResultDt( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( c.getTime() ) );
			}
			return tGcorderVo;
		} else {
			return null;
		}
	}
	
	public TCustomerVo findCustomer( TCustomerVo tCustomerVo ) {
		return tCustomerRepository.findCustomer( tCustomerVo );
	}

	public Map<String, Object> smsCustomerSaveProc(TCustomerVo tCustomerVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int code = 0;
		String message = "";
		if( tCustomerVo.getCstNo() == null || tCustomerVo.getCstNo().replace(" ", "").equals("") ) {
			code = 101;
			message = "사업자번호를 입력하지 않았습니다.";
		} else if( tCustomerVo.getCstNo().replace( " ", "" ).replace( "-", "" ).length() != 10 ) {
			code = 102;
			message = "사업자번호는 '-'를 제외한 숫자 10자리를 입력하셔야 합니다.";
		} else if( !StringUtils.isNumeric( tCustomerVo.getCstNo() ) ) {
			code = 103;
			message = "사업자번호는 반드시 숫자입니다.";
		} else if( StringUtils.isEmpty( tCustomerVo.getCstNm() ) ) {
			code = 104;
			message = "상호를 입력하지 않았습니다.";
		} else if( StringUtils.isEmpty( tCustomerVo.getOperOwnerNm() ) ) {
			code = 105;
			message = "대표자명을 입력하지 않았습니다.";
		} else if( StringUtils.isEmpty( tCustomerVo.getCstType() ) ) {
			code = 106;
			message = "업태를 입력하지 않았습니다.";
		} else if( StringUtils.isEmpty( tCustomerVo.getCstTrad() ) ) {
			code = 107;
			message = "업종을 입력하지 않았습니다.";
		} else if( StringUtils.isEmpty( tCustomerVo.getAddr() ) ) {
			code = 108;
			message = "주소를 입력하지 않았습니다.";
		} else if( StringUtils.isEmpty( tCustomerVo.getTelNo() ) ) {
			code = 109;
			message = "전화번호를 입력하지 않았습니다.";
		} else if( !StringUtils.isNumeric( tCustomerVo.getTelNo() ) ) {
			code = 110;
			message = "전화번호는 반드시 숫자입니다.";
		} else if( StringUtils.isEmpty( tCustomerVo.getFaxNo() ) ) {
			code = 111;
			message = "팩스번호를 입력하지 않았습니다.";
		} else if( !StringUtils.isNumeric( tCustomerVo.getFaxNo() ) ) {
			code = 112;
			message = "팩스번호는 반드시 숫자입니다.";
		} else if( !StringUtils.isEmail( tCustomerVo.getEmail() ) ) {
			code = 113;
			message = "Email 형식이 잘못되었습니다.";
		} else if( StringUtils.isEmpty( tCustomerVo.getEdiCharge() ) ) {
			code = 114;
			message = "이메일 담당자를 입력하지 않았습니다.";
		} else if( StringUtils.isEmpty( tCustomerVo.getEdiChargeTel() ) ) {
			code = 115;
			message = "담당자 연락처를 입력하지 않았습니다.";
		} else if( !StringUtils.isNumeric( tCustomerVo.getEdiChargeTel() ) ) {
			code = 116;
			message = "담당자 연락처는 반드시 숫자입니다.";
		} else {
			TCustomerVo dupCheckVo = tCustomerRepository.findCstNoAndCatalogByCstNoWithTCustomerHdr( tCustomerVo );
			if( dupCheckVo == null ) {
				// insert
				TCustomerVo custIdVo = tCustomerRepository.findNextCustId();
				tCustomerVo.setCustId( custIdVo.getCustId() );
				tCustomerRepository.insertCustomer( tCustomerVo );
				tCustomerHdrRepository.insertCustomer( tCustomerVo );
				
				code = 200;
			} else {
				// update
				if( dupCheckVo.getCatalog().equals("운송사") ) {
					tCustomerRepository.updateCustomer( tCustomerVo );
					tCustomerHdrRepository.updateCustomer( tCustomerVo );
					
					code = 200;
				} else {
					code = 117;
					message = "운송사 외에는 저장이 안됩니다.";
				}
			}
			
		}
		
		resultMap.put("code", code);
		resultMap.put("message", message);
		
		return resultMap;
	}

	public List<TBsiotVo> deliveryManager(SearchVo searchVo) {
		SearchVo operListAdded = setOperList( searchVo );
		
		if( operListAdded.getOkCd().toUpperCase().equals("SHIP") 
				&& !StringUtils.isEmpty( operListAdded.getShipCd() ) 
				&& !StringUtils.isEmpty( operListAdded.getCallNo() ) 
				&& !StringUtils.isEmpty( operListAdded.getCallYy() ) ) {
			return tBsiotRepository.deliveryManagerByShip( operListAdded );
		} else if( operListAdded.getOkCd().toUpperCase().equals("BL") && !StringUtils.isEmpty( operListAdded.getBlNo() ) ) {
			TBsiotVo tBsiotVo = tBsiotRepository.findShipCdAndCallNoAndCallYyByMstblNo( operListAdded );
			if( !StringUtils.isEmpty( tBsiotVo.getShipCd() ) && !StringUtils.isEmpty( tBsiotVo.getCallNo() ) && !StringUtils.isEmpty( tBsiotVo.getCallYy() ) ) {
				searchVo.setShipCd( tBsiotVo.getShipCd() );
				searchVo.setCallNo( tBsiotVo.getCallNo() );
				searchVo.setCallYy( tBsiotVo.getCallYy() );
				return tBsiotRepository.deliveryManagerByShip( operListAdded );
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Map<String, Object> cntrInfoFreeTime(SearchVo searchVo) throws ParseException {
		Map<String, Object> resultMap = null;
		
		if( searchVo.getGubun().toUpperCase().equals( "CNTR" ) ) {
			TCntrVo tCntrVo = tCntrRepository.cntrInfoFreeTimeFindByCntrNo( searchVo );
			
			if( tCntrVo != null ) {
				resultMap  = new HashMap<String, Object>();
				
				searchVo.setShipCd( tCntrVo.getShipCd() );
				searchVo.setCallNo( String.valueOf( tCntrVo.getCallNo() ) );
				searchVo.setCallYy( String.valueOf( tCntrVo.getCallYy() ) );
			}
		}
		
		if( !StringUtils.isEmpty( searchVo.getShipCd() ) && !StringUtils.isEmpty( searchVo.getCallNo() ) && !StringUtils.isEmpty( searchVo.getCallYy() ) ) {
			resultMap  = new HashMap<String, Object>();
			TVescallVo tVescallVo = tVescallRepository.findOperCdAndStvEndDtByShipCdAndCallNoAndCallYy( searchVo );
			
			if( tVescallVo != null && tVescallVo.getStvEndDt() != null && !StringUtils.removeSpace( tVescallVo.getStvEndDt() ).equals("") ) { // result = "yes"
				searchVo.setOper( tVescallVo.getOperCd() );
				searchVo.setStvEndDt( tVescallVo.getStvEndDt() );
				
				Calendar c = Calendar.getInstance();
				c.setTime( new SimpleDateFormat("yyyyMMddHHmm").parse( searchVo.getStvEndDt() ) );
				
				if( c.get( Calendar.HOUR ) >= 8 ) {
					c.add( Calendar.DATE, 1);
				}
				
				Calendar std = Calendar.getInstance();
				std.setTime( c.getTime() );
				if( std.get( Calendar.HOUR_OF_DAY ) >= 8 ) {
					std.add( Calendar.DATE, 1);
				}
				resultMap.put( "stvEndDt", new SimpleDateFormat("yyyyMMddHHmm").format( c.getTime() ) );
				resultMap.put( "stdDt", new SimpleDateFormat("yyyyMMddHHmm").format( std.getTime() ) );
				
				List<TFreedayVo> tFreedayList = tFreedayRepository.cntrInfoFreeTime( searchVo );
				
				SimpleDateFormat returnFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				for( int i=0; i<tFreedayList.size(); i++ ) {
					String cargoCd = tFreedayList.get(i).getCargoCd();
					int freeday = tFreedayList.get(i).getFreeDay().intValue();
					resultMap.put( cargoCd, freeday );
					
					c.setTime( new SimpleDateFormat("yyyyMMddHHmm").parse( searchVo.getStvEndDt() ) );
					c.add( Calendar.DATE, freeday );
					if( cargoCd.equals("1") ) {
						resultMap.put( "igeneral", returnFormat.format( c.getTime() ) );
					} else if( cargoCd.equals("2") ) {
						resultMap.put( "ibsiot", returnFormat.format( c.getTime() ) );
					} else if( cargoCd.equals("3") ) {
						resultMap.put( "icustom", returnFormat.format( c.getTime() ) );
					} else if( cargoCd.equals("4") ) {
						resultMap.put( "its", returnFormat.format( c.getTime() ) );
					} else if( cargoCd.equals("5") ) {
						resultMap.put( "ioutts", returnFormat.format( c.getTime() ) );
					}
				}
				
				resultMap.put("shipCd", searchVo.getShipCd() );
				resultMap.put("callNo", searchVo.getCallNo() );
				resultMap.put("callYy", searchVo.getCallYy() );
				
				
			}
		}
		
		
		return resultMap;
	}

	public List<TBkmstVo> onDockBookingList(SearchVo searchVo) {
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
		
		List<TBkmstVo> bkList = null;
		
		if( tSecretVo.getJo().toUpperCase().equals( "O" )
				|| tSecretVo.getJo().toUpperCase().equals( "S" ) 
				|| tSecretVo.getJo().toUpperCase().equals( "G" )
				|| tSecretVo.getJo().toUpperCase().equals("T") ) {
			bkList = tBkmstRepository.onDockBookingList( searchVo );
		
			for( int i=0; i<bkList.size(); i++ ) {
				SearchVo tempSearchVo = new SearchVo();
				tempSearchVo.setOperList( searchVo.getOperList() );
				tempSearchVo.setBkNo( bkList.get(i).getBkNo() );
				tempSearchVo.setSzCd( bkList.get(i).getSzCd() );
				tempSearchVo.setTyCd( bkList.get(i).getTyGrpCd() );
				List<TCntrVo> tCntrList = tCntrRepository.findCountFromOnDockBookingList( tempSearchVo );
				List<TCntrVo> tCntr1List = tCntrRepository.findCount1FromOnDockBookingList( tempSearchVo );
				
				int cntrCount = 0;
				int cntrCount1 = 0;
				
				List<String> cntrOutList = new ArrayList<String>();
				for( int j=0; j<tCntrList.size(); j++ ) {
					cntrCount += tCntrList.get(j).getCnt();
					
					cntrOutList.add( tCntrList.get(j).getCntrNo() );
				}
				
				bkList.get(i).setCntrOutList( cntrOutList );
				
				List<String> cntrFixList = new ArrayList<String>();
				for( int j=0; j<tCntr1List.size(); j++ ) {
					cntrCount1 += tCntr1List.get(j).getCnt();
					
					cntrFixList.add( tCntr1List.get(j).getCntrNo() );
				}
				
				bkList.get(i).setCntrFixList( cntrFixList );
				
				bkList.get(i).setCntrCount( cntrCount );
				bkList.get(i).setCntrCount1( cntrCount1 );
				
				TBklstVo truckerCdVo = tBklstRepository.findTruckerCdByOperCdAndBkNo( bkList.get(i) );
				if( truckerCdVo != null ) {
					
					TEdicustomerVo cstNmVo = tEdicustomerRepository.findCstNmByDocuAndTerminalIdAndCstId( truckerCdVo );
					if( cstNmVo != null ) {
						bkList.get(i).setTruckerNm( cstNmVo.getCstNm() );
					}
				}
				
				
			}
		} else if( tSecretVo.getJo().toUpperCase().equals( "T" ) ) {
			bkList = tBkmstRepository.onDockBookingListForT( searchVo );
		}
		
		return bkList;
	}

	public Map<String, Object> onDockStock(SearchVo searchVo) {
		
		SearchVo operListAdded = setOperList( searchVo );
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		sumMap.put( "fIm20", 0 );
		sumMap.put( "fIm40", 0 );
		sumMap.put( "fIm40h", 0 );
		sumMap.put( "fIm45", 0 );
		sumMap.put( "fXm20", 0 );
		sumMap.put( "fXm40", 0 );
		sumMap.put( "fXm40h", 0 );
		sumMap.put( "fXm45", 0 );
		sumMap.put( "fMm20", 0 );
		sumMap.put( "fMm40", 0 );
		sumMap.put( "fMm40h", 0 );
		sumMap.put( "fMm45", 0 );
		sumMap.put( "fTo20", 0 );
		sumMap.put( "fTo40", 0 );
		sumMap.put( "fTo40h", 0 );
		sumMap.put( "fTo45", 0 );
		
		sumMap.put( "eIm20", 0 );
		sumMap.put( "eIm40", 0 );
		sumMap.put( "eIm40h", 0 );
		sumMap.put( "eIm45", 0 );
		sumMap.put( "eXm20", 0 );
		sumMap.put( "eXm40", 0 );
		sumMap.put( "eXm40h", 0 );
		sumMap.put( "eXm45", 0 );
		sumMap.put( "eMm20", 0 );
		sumMap.put( "eMm40", 0 );
		sumMap.put( "eMm40h", 0 );
		sumMap.put( "eMm45", 0 );
		sumMap.put( "eTo20", 0 );
		sumMap.put( "eTo40", 0 );
		sumMap.put( "eTo40h", 0 );
		sumMap.put( "eTo45", 0 );
		
		sumMap.put( "totali", 0 );
		sumMap.put( "totalx", 0 );
		sumMap.put( "totalm", 0 );
		sumMap.put( "total", 0 );
		
		sumMap.put( "teuTotali", 0 );
		sumMap.put( "teuTotalx", 0 );
		sumMap.put( "teuTotalm", 0 );
		sumMap.put( "teuTotal", 0 );
		
		List<TCntrVo> list = tCntrRepository.onDockStock( operListAdded );
		
		Calendar c = Calendar.getInstance();
		
		for( int i=0; i<list.size(); i++ ) {
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
				
				if( !StringUtils.isEmpty( searchVo.getSearchStartDt() ) && !StringUtils.isEmpty( searchVo.getSearchEndDt() ) ) {
					if( datediff >= Integer.valueOf( searchVo.getSearchStartDt() ) && datediff <= Integer.valueOf( searchVo.getSearchEndDt() ) ) {
						if( list.get(i).getFmCd().toUpperCase().equals( "F" ) ) {
							if( cClass.equals( "I" ) ) {
								if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
									sumMap.put( "fIm20", sumMap.get("fIm20") + 1 );
								} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
									sumMap.put( "fIm40h",sumMap.get("fIm40h") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
									sumMap.put( "fIm40", sumMap.get("fIm40") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
									sumMap.put( "fIm45", sumMap.get("fIm45") + 1 );
								}
							} else if( cClass.equals( "X" ) ) {
								if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
									sumMap.put( "fXm20", sumMap.get("fXm20") + 1 );
								} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
									sumMap.put( "fXm40h",sumMap.get("fXm40h") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
									sumMap.put( "fXm40", sumMap.get("fXm40") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
									sumMap.put( "fXm45", sumMap.get("fXm45") + 1 );
								}
							} else if( cClass.equals( "M" ) ) {
								if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
									sumMap.put( "fMm20", sumMap.get("fMm20") + 1 );
								} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
									sumMap.put( "fMm40h", sumMap.get("fMm40h") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
									sumMap.put( "fMm40", sumMap.get("fMm40") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
									sumMap.put( "fMm45", sumMap.get("fMm45") + 1 );
								}
							}
						} else if( list.get(i).getFmCd().toUpperCase().equals( "M" ) ) {
							if( cClass.equals( "I" ) ) {
								if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
									sumMap.put( "eIm20", sumMap.get("eIm20") + 1 );
								} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
									sumMap.put( "eIm40h",sumMap.get("eIm40h") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
									sumMap.put( "eIm40", sumMap.get("eIm40") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
									sumMap.put( "eIm45", sumMap.get("eIm45") + 1 );
								}
							} else if( cClass.equals( "X" ) ) {
								if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
									sumMap.put( "eXm20", sumMap.get("eXm20") + 1 );
								} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
									sumMap.put( "eXm40h",sumMap.get("eXm40h") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
									sumMap.put( "eXm40", sumMap.get("eXm40") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
									sumMap.put( "eXm45", sumMap.get("eXm45") + 1 );
								}
							} else if( cClass.equals( "M" ) ) {
								if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
									sumMap.put( "eMm20", sumMap.get("eMm20") + 1 );
								} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
									sumMap.put( "eMm40h", sumMap.get("eMm40h") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
									sumMap.put( "eMm40", sumMap.get("eMm40") + 1 );
								} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
									sumMap.put( "eMm45", sumMap.get("eMm45") + 1 );
								}
							}
						}
					}
				} else {
					if( list.get(i).getFmCd().toUpperCase().equals( "F" ) ) {
						if( cClass.equals( "I" ) ) {
							if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
								sumMap.put( "fIm20", sumMap.get("fIm20") + 1 );
							} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
								sumMap.put( "fIm40h",sumMap.get("fIm40h") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
								sumMap.put( "fIm40", sumMap.get("fIm40") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
								sumMap.put( "fIm45", sumMap.get("fIm45") + 1 );
							}
						} else if( cClass.equals( "X" ) ) {
							if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
								sumMap.put( "fXm20", sumMap.get("fXm20") + 1 );
							} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
								sumMap.put( "fXm40h",sumMap.get("fXm40h") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
								sumMap.put( "fXm40", sumMap.get("fXm40") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
								sumMap.put( "fXm45", sumMap.get("fXm45") + 1 );
							}
						} else if( cClass.equals( "M" ) ) {
							if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
								sumMap.put( "fMm20", sumMap.get("fMm20") + 1 );
							} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
								sumMap.put( "fMm40h", sumMap.get("fMm40h") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
								sumMap.put( "fMm40", sumMap.get("fMm40") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
								sumMap.put( "fMm45", sumMap.get("fMm45") + 1 );
							}
						}
					} else if( list.get(i).getFmCd().toUpperCase().equals( "M" ) ) {
						if( cClass.equals( "I" ) ) {
							if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
								sumMap.put( "eIm20", sumMap.get("eIm20") + 1 );
							} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
								sumMap.put( "eIm40h",sumMap.get("eIm40h") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
								sumMap.put( "eIm40", sumMap.get("eIm40") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
								sumMap.put( "eIm45", sumMap.get("eIm45") + 1 );
							}
						} else if( cClass.equals( "X" ) ) {
							if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
								sumMap.put( "eXm20", sumMap.get("eXm20") + 1 );
							} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
								sumMap.put( "eXm40h",sumMap.get("eXm40h") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
								sumMap.put( "eXm40", sumMap.get("eXm40") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
								sumMap.put( "eXm45", sumMap.get("eXm45") + 1 );
							}
						} else if( cClass.equals( "M" ) ) {
							if( list.get(i).getSzCd().substring(0, 1).equals( "2" ) || list.get(i).getSzCd().substring(0, 1).equals( "3" ) ) {
								sumMap.put( "eMm20", sumMap.get("eMm20") + 1 );
							} else if( list.get(i).getSzCd().equals( "45" ) || list.get(i).getSzCd().equals( "44" ) ) {
								sumMap.put( "eMm40h", sumMap.get("eMm40h") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "4" ) ) {
								sumMap.put( "eMm40", sumMap.get("eMm40") + 1 );
							} else if( list.get(i).getSzCd().substring(0, 1).equals( "9" ) || list.get(i).getSzCd().substring(0, 1).equals( "L" ) ) {
								sumMap.put( "eMm45", sumMap.get("eMm45") + 1 );
							}
						}
					}
				}
				
				sumMap.put( "fTo20", sumMap.get( "fIm20" ) + sumMap.get( "fXm20" ) + sumMap.get( "fMm20" ) );
				sumMap.put( "fTo40", sumMap.get( "fIm40" ) + sumMap.get( "fXm40" ) + sumMap.get( "fMm40" ) );
				sumMap.put( "fTo40h", sumMap.get( "fIm40h" ) + sumMap.get( "fXm40h" ) + sumMap.get( "fMm40h" ) );
				sumMap.put( "fTo45", sumMap.get( "fIm45" ) + sumMap.get( "fXm45" ) + sumMap.get( "fMm45" ) );
				
				sumMap.put( "eTo20", sumMap.get( "eIm20" ) + sumMap.get( "eXm20" ) + sumMap.get( "eMm20" ) );
				sumMap.put( "eTo40", sumMap.get( "eIm40" ) + sumMap.get( "eXm40" ) + sumMap.get( "eMm40" ) );
				sumMap.put( "eTo40h", sumMap.get( "eIm40h" ) + sumMap.get( "eXm40h" ) + sumMap.get( "eMm40h" ) );
				sumMap.put( "eTo45", sumMap.get( "eIm45" ) + sumMap.get( "eXm45" ) + sumMap.get( "eMm45" ) );
				
				sumMap.put( "totali", sumMap.get( "fIm20" ) + sumMap.get( "fIm40" ) + sumMap.get( "fIm40h" ) + sumMap.get( "fIm45" ) 
							+ sumMap.get( "eIm20" ) + sumMap.get( "eIm40" ) + sumMap.get( "eIm40h" ) + sumMap.get( "eIm45" ) );
				sumMap.put( "totalx", sumMap.get( "fXm20" ) + sumMap.get( "fXm40" ) + sumMap.get( "fXm40h" ) + sumMap.get( "fXm45" ) 
							+ sumMap.get( "eXm20" ) + sumMap.get( "eXm40" ) + sumMap.get( "eXm40h" ) + sumMap.get( "eXm45" ) );
				sumMap.put( "totalm", sumMap.get( "fMm20" ) + sumMap.get( "fMm40" ) + sumMap.get( "fMm40h" ) + sumMap.get( "fMm45" ) 
							+ sumMap.get( "eMm20" ) + sumMap.get( "eMm40" ) + sumMap.get( "eMm40h" ) + sumMap.get( "eMm45" ) );
				sumMap.put( "total", sumMap.get( "totali" ) + sumMap.get( "totalx" ) + sumMap.get( "totalm" ) );
				
				sumMap.put( "teuTotali", sumMap.get( "fIm20" ) + ( sumMap.get( "fIm40" ) * 2 ) + ( sumMap.get( "fIm40h" ) * 2 ) + ( sumMap.get( "fIm45" ) * 2 ) 
						+ sumMap.get( "eIm20" ) + ( sumMap.get( "eIm40" ) * 2 ) + ( sumMap.get( "eIm40h" ) * 2 ) + ( sumMap.get( "eIm45" ) * 2 ) );
				sumMap.put( "teuTotalx", sumMap.get( "fXm20" ) + ( sumMap.get( "fXm40" ) * 2 ) + ( sumMap.get( "fXm40h" ) * 2 ) + ( sumMap.get( "fXm45" ) * 2 ) 
						+ sumMap.get( "eXm20" ) + ( sumMap.get( "eXm40" ) * 2 ) + ( sumMap.get( "eXm40h" ) * 2 ) + ( sumMap.get( "eXm45" ) * 2 ) );
				sumMap.put( "teuTotalm", sumMap.get( "fMm20" ) + ( sumMap.get( "fMm40" ) * 2 ) + ( sumMap.get( "fMm40h" ) * 2 ) + ( sumMap.get( "fMm45" ) * 2 ) 
						+ sumMap.get( "eMm20" ) + ( sumMap.get( "eMm40" ) * 2 ) + ( sumMap.get( "eMm40h" ) * 2 ) + ( sumMap.get( "eMm45" ) * 2 ) );
				sumMap.put( "teuTotal", sumMap.get( "teuTotali" ) + sumMap.get( "teuTotalx" ) + sumMap.get( "teuTotalm" ) );
			}
		}
		
		resultMap.put( "list", list );
		resultMap.put( "sumMap", sumMap );
		
		return resultMap;
	}

	public Map<String, Object> onDockFreeVanPool( SearchVo searchVo ) {
		SearchVo operListAdded = setOperList( searchVo );
		List<TCntrVo> list = tCntrRepository.onDockFreeVanPool( operListAdded );
		
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		int SUM_FF20I_T = 0, SUM_FF20I_V = 0;
		int SUM_FF40I_T = 0, SUM_FF40I_V = 0;
		int SUM_FF40HQI_T = 0, SUM_FF40HQI_V = 0;
		int SUM_FF45I_T = 0, SUM_FF45I_V = 0;
		int SUM_TIFFUL_T = 0, SUM_TIFFUL_V = 0;
		
		int SUM_FF20X_T = 0, SUM_FF20X_V = 0;
		int SUM_FF40X_T = 0, SUM_FF40X_V = 0;
		int SUM_FF40HQX_T = 0, SUM_FF40HQX_V = 0;
		int SUM_FF45X_T = 0, SUM_FF45X_V = 0;
		int SUM_TXFFUL_T = 0, SUM_TXFFUL_V = 0;
		
		int SUM_FF20M_T = 0, SUM_FF20M_V = 0;
		int SUM_FF40M_T = 0, SUM_FF40M_V = 0;
		int SUM_FF40HQM_T = 0, SUM_FF40HQM_V = 0;
		int SUM_FF45M_T = 0, SUM_FF45M_V = 0;
		int SUM_TOFFUL_T = 0, SUM_TOFFUL_V = 0;
		
		int SUM_FULL_VAN = 0, SUM_FULL_TEU = 0;
		int SUM_SUM_VAN = 0, SUM_SUM_TEU = 0;
		
		int SUM_MM20I_T = 0, SUM_MM20I_V = 0;
		int SUM_MM40I_T = 0, SUM_MM40I_V = 0;
		int SUM_MM40HQI_T = 0, SUM_MM40HQI_V = 0;
		int SUM_MM45I_T = 0, SUM_MM45I_V = 0;
		int SUM_TIMMUL_T = 0, SUM_TIMMUL_V = 0;
		
		int SUM_MM20X_T = 0, SUM_MM20X_V = 0;
		int SUM_MM40X_T = 0, SUM_MM40X_V = 0;
		int SUM_MM40HQX_T = 0, SUM_MM40HQX_V = 0;
		int SUM_MM45X_T = 0, SUM_MM45X_V = 0;
		int SUM_TXMMUL_T = 0, SUM_TXMMUL_V = 0;
		
		int SUM_MM20M_T = 0, SUM_MM20M_V = 0;
		int SUM_MM40M_T = 0, SUM_MM40M_V = 0;
		int SUM_MM40HQM_T = 0, SUM_MM40HQM_V = 0;
		int SUM_MM45M_T = 0, SUM_MM45M_V = 0;
		int SUM_TMMMUL_T = 0, SUM_TMMMUL_V = 0;
		
		int SUM_HM20IXM_T = 0, SUM_HM20IXM_V = 0;
		int SUM_HM40IXM_T = 0, SUM_HM40IXM_V = 0;
		int SUM_HM40HQIXM_T = 0, SUM_HM40HQIXM_V = 0;
		int SUM_HM45IXM_T = 0, SUM_HM45IXM_V = 0;
		int SUM_HTMMMUL_T = 0, SUM_HTMMMUL_V = 0;
		
		int SUM_EMPTY_VAN = 0, SUM_EMPTY_TEU = 0;
		
		int OVER_FULL_VAN = 0, OVER_EMPTY_VAN = 0;
		int OVER_FULL_TEU = 0, OVER_EMPTY_TEU = 0;
		
		
		for( int i=0; i<list.size(); i++ ) {
			SUM_FF20I_T += list.get(i).getFf20i();
			SUM_FF20I_V += list.get(i).getFf20i();
			
			SUM_FF40I_T += list.get(i).getFf40i() * 2;
			SUM_FF40I_V += list.get(i).getFf40i();
			
			SUM_FF40HQI_T += list.get(i).getFf40hqi() * 2;
			SUM_FF40HQI_V += list.get(i).getFf40hqi();
			
			SUM_FF45I_T += list.get(i).getFf45i() * 2;
			SUM_FF45I_V += list.get(i).getFf45i();
			
			SUM_TIFFUL_V += list.get(i).getTifful();
			//----------------------------------------------//
			SUM_FF20X_T += list.get(i).getFf20x();
			SUM_FF20X_V += list.get(i).getFf20x();
			
			SUM_FF40X_T += list.get(i).getFf40x() * 2;
			SUM_FF40X_V += list.get(i).getFf40x();
			
			SUM_FF40HQX_T += list.get(i).getFf40hqx() * 2;
			SUM_FF40HQX_V += list.get(i).getFf40hqx();
			
			SUM_FF45X_T += list.get(i).getFf45x() * 2;
			SUM_FF45X_V += list.get(i).getFf45x();
			
			SUM_TXFFUL_V += list.get(i).getTxfful();
			//----------------------------------------------//
			SUM_FF20M_T += list.get(i).getFf20m();
			SUM_FF20M_V += list.get(i).getFf20m();
			
			SUM_FF40M_T += list.get(i).getFf40m() * 2;
			SUM_FF40M_V += list.get(i).getFf40m();
			
			SUM_FF40HQM_T += list.get(i).getFf40hqm() * 2;
			SUM_FF40HQM_V += list.get(i).getFf40hqm();
			
			SUM_FF45M_T += list.get(i).getFf45m() * 2;
			SUM_FF45M_V += list.get(i).getFf45m();
			
			SUM_TOFFUL_V += list.get(i).getTofful();
			
			//----------------------------------------------//
			//----------------------------------------------//
			//----------------------------------------------//
			
			SUM_MM20I_T += list.get(i).getMm20i();
			SUM_MM20I_V += list.get(i).getMm20i();
			
			SUM_MM40I_T += list.get(i).getMm40i() * 2;
			SUM_MM40I_V += list.get(i).getMm40i();
			
			SUM_MM40HQI_T += list.get(i).getMm40hqi() * 2;
			SUM_MM40HQI_V += list.get(i).getMm40hqi();
			
			SUM_MM45I_T += list.get(i).getMm45i() * 2;
			SUM_MM45I_V += list.get(i).getMm45i();
			
			SUM_TIMMUL_V += list.get(i).getTimmul();
			//----------------------------------------------//
			SUM_MM20X_T += list.get(i).getMm20x();
			SUM_MM20X_V += list.get(i).getMm20x();
			
			SUM_MM40X_T += list.get(i).getMm40x() * 2;
			SUM_MM40X_V += list.get(i).getMm40x();
			
			SUM_MM40HQX_T += list.get(i).getMm40hqx() * 2;
			SUM_MM40HQX_V += list.get(i).getMm40hqx();
			
			SUM_MM45X_T += list.get(i).getMm45x() * 2;
			SUM_MM45X_V += list.get(i).getMm45x();
			
			SUM_TXMMUL_V += list.get(i).getTxmmul();
			//----------------------------------------------//
			SUM_MM20M_T += list.get(i).getMm20m();
			SUM_MM20M_V += list.get(i).getMm20m();
			
			SUM_MM40M_T += list.get(i).getMm40m() * 2;
			SUM_MM40M_V += list.get(i).getMm40m();
			
			SUM_MM40HQM_T += list.get(i).getMm40hqm() * 2;
			SUM_MM40HQM_V += list.get(i).getMm40hqm();
			
			SUM_MM45M_T += list.get(i).getMm45m() * 2;
			SUM_MM45M_V += list.get(i).getMm45m();
			
			SUM_TMMMUL_V += list.get(i).getTmmmul();
			
			//----------------------------------------------//
			//----------------------------------------------//
			//----------------------------------------------//
			
			SUM_HM20IXM_T += list.get(i).getHm20ixm();
			SUM_HM20IXM_V += list.get(i).getHm20ixm();
			
			SUM_HM40IXM_T += list.get(i).getHm40ixm() * 2;
			SUM_HM40IXM_V += list.get(i).getHm40ixm();
			
			SUM_HM40HQIXM_T += list.get(i).getHm40hqixm() * 2;
			SUM_HM40HQIXM_V += list.get(i).getHm40hqixm();
			
			SUM_HM45IXM_T += list.get(i).getHm45ixm() * 2;
			SUM_HM45IXM_V += list.get(i).getHm45ixm();
			
			SUM_HTMMMUL_V += list.get(i).getHtmmmul();
		}
		
		SUM_TIFFUL_T = SUM_FF20I_T + SUM_FF40I_T + SUM_FF40HQI_T + SUM_FF45I_T;
		SUM_TXFFUL_T = SUM_FF20X_T + SUM_FF40X_T + SUM_FF40HQX_T + SUM_FF45X_T;
		SUM_TOFFUL_T = SUM_FF20M_T + SUM_FF40M_T + SUM_FF40HQM_T + SUM_FF45M_T;
		
		SUM_TIMMUL_T = SUM_MM20I_T + SUM_MM40I_T + SUM_MM40HQI_T + SUM_MM45I_T;
		SUM_TXMMUL_T = SUM_MM20X_T + SUM_MM40X_T + SUM_MM40HQX_T + SUM_MM45X_T;
		SUM_TMMMUL_T = SUM_MM20M_T + SUM_MM40M_T + SUM_MM40HQM_T + SUM_MM45M_T;
		SUM_HTMMMUL_T = SUM_HM20IXM_T + SUM_HM40IXM_T + SUM_HM40HQIXM_T + SUM_HM45IXM_T;
		
		SUM_FULL_VAN = SUM_TIFFUL_V + SUM_TXFFUL_V + SUM_TOFFUL_V;
		SUM_FULL_TEU = SUM_TIFFUL_T + SUM_TXFFUL_T + SUM_TOFFUL_T;
		
		SUM_EMPTY_VAN = SUM_TIMMUL_V + SUM_TXMMUL_V + SUM_TMMMUL_V;
		SUM_EMPTY_TEU = SUM_TIMMUL_T + SUM_TXMMUL_T + SUM_TMMMUL_T;
		
		if( !SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains( new SimpleGrantedAuthority( "ROLE_S" ) ) ) {
			TFreevanpoolVo tFreevanpoolVo = tFreevanpoolRepository.findFullQtAndFullUnitAndEmptyQtAndEmptyUnit(operListAdded);
			
			if( tFreevanpoolVo.getFullQt() > 0 && tFreevanpoolVo.getFullUnit().toUpperCase().equals("V") ) {
				OVER_FULL_VAN = (int) (SUM_FULL_VAN - tFreevanpoolVo.getFullQt());
				OVER_FULL_VAN = OVER_FULL_VAN > 0 ? OVER_FULL_VAN : 0;
			}
			
			if( tFreevanpoolVo.getEmptyQt() > 0 && tFreevanpoolVo.getEmptyUnit().toUpperCase().equals("V") ) {
				OVER_EMPTY_VAN = (int) (SUM_EMPTY_VAN - tFreevanpoolVo.getEmptyQt() );
				OVER_EMPTY_VAN = OVER_EMPTY_VAN > 0 ? OVER_EMPTY_VAN : 0;
			}
			
			if( tFreevanpoolVo.getFullQt() > 0 && tFreevanpoolVo.getFullUnit().toUpperCase().equals("T") ) {
				OVER_FULL_TEU = (int) (SUM_FULL_VAN - tFreevanpoolVo.getFullQt());
				OVER_FULL_TEU = OVER_FULL_TEU > 0 ? OVER_FULL_TEU : 0;
			}
			
			if( tFreevanpoolVo.getEmptyQt() > 0 && tFreevanpoolVo.getEmptyUnit().toUpperCase().equals("T") ) {
				OVER_EMPTY_TEU = (int) (SUM_EMPTY_VAN - tFreevanpoolVo.getEmptyQt() );
				OVER_EMPTY_TEU = OVER_EMPTY_TEU > 0 ? OVER_EMPTY_TEU : 0;
			}
		}
		
		SUM_SUM_VAN = SUM_FULL_VAN + SUM_EMPTY_VAN;
		SUM_SUM_TEU = SUM_FULL_TEU + SUM_EMPTY_TEU;
		
		
		sumMap.put( "SUM_FF20I_T", SUM_FF20I_T );
		sumMap.put( "SUM_FF20I_V", SUM_FF20I_V );
		
		sumMap.put( "SUM_FF40I_T", SUM_FF40I_T );
		sumMap.put( "SUM_FF40I_V", SUM_FF40I_V );
		
		sumMap.put( "SUM_FF40HQI_T", SUM_FF40HQI_T );
		sumMap.put( "SUM_FF40HQI_V", SUM_FF40HQI_V );
		
		sumMap.put( "SUM_FF45I_T", SUM_FF45I_T );
		sumMap.put( "SUM_FF45I_V", SUM_FF45I_V );
		
		sumMap.put( "SUM_TIFFUL_T", SUM_TIFFUL_T );
		sumMap.put( "SUM_TIFFUL_V", SUM_TIFFUL_V );
		
		
		sumMap.put( "SUM_FF20X_T", SUM_FF20X_T );
		sumMap.put( "SUM_FF20X_V", SUM_FF20X_V );
		
		sumMap.put( "SUM_FF40X_T", SUM_FF40X_T );
		sumMap.put( "SUM_FF40X_V", SUM_FF40X_V );
		
		sumMap.put( "SUM_FF40HQX_T", SUM_FF40HQX_T );
		sumMap.put( "SUM_FF40HQX_V", SUM_FF40HQX_V );
		
		sumMap.put( "SUM_FF45X_T", SUM_FF45X_T );
		sumMap.put( "SUM_FF45X_V", SUM_FF45X_V );
		
		sumMap.put( "SUM_TXFFUL_T", SUM_TXFFUL_T );
		sumMap.put( "SUM_TXFFUL_V", SUM_TXFFUL_V );
		
		
		sumMap.put( "SUM_FF20M_T", SUM_FF20M_T );
		sumMap.put( "SUM_FF20M_V", SUM_FF20M_V );
		
		sumMap.put( "SUM_FF40M_T", SUM_FF40M_T );
		sumMap.put( "SUM_FF40M_V", SUM_FF40M_V );
		
		sumMap.put( "SUM_FF40HQM_T", SUM_FF40HQM_T );
		sumMap.put( "SUM_FF40HQM_V", SUM_FF40HQM_V );
		
		sumMap.put( "SUM_FF45M_T", SUM_FF45M_T );
		sumMap.put( "SUM_FF45M_V", SUM_FF45M_V );
		
		sumMap.put( "SUM_TOFFUL_T", SUM_TOFFUL_T );
		sumMap.put( "SUM_TOFFUL_V", SUM_TOFFUL_V );
		
		
		sumMap.put( "SUM_FULL_VAN", SUM_FULL_VAN );
		sumMap.put( "SUM_FULL_TEU", SUM_FULL_TEU );
		
		sumMap.put( "SUM_SUM_VAN", SUM_SUM_VAN );
		sumMap.put( "SUM_SUM_TEU", SUM_SUM_TEU );
		
		
		
		sumMap.put( "SUM_MM20I_T", SUM_MM20I_T );
		sumMap.put( "SUM_MM20I_V", SUM_MM20I_V );
		
		sumMap.put( "SUM_MM40I_T", SUM_MM40I_T );
		sumMap.put( "SUM_MM40I_V", SUM_MM40I_V );
		
		sumMap.put( "SUM_MM40HQI_T", SUM_MM40HQI_T );
		sumMap.put( "SUM_MM40HQI_V", SUM_MM40HQI_V );
		
		sumMap.put( "SUM_MM45I_T", SUM_MM45I_T );
		sumMap.put( "SUM_MM45I_V", SUM_MM45I_V );
		
		sumMap.put( "SUM_TIMMUL_T", SUM_TIMMUL_T );
		sumMap.put( "SUM_TIMMUL_V", SUM_TIMMUL_V );
		
		
		sumMap.put( "SUM_MM20X_T", SUM_MM20X_T );
		sumMap.put( "SUM_MM20X_V", SUM_MM20X_V );
		
		sumMap.put( "SUM_MM40X_T", SUM_MM40X_T );
		sumMap.put( "SUM_MM40X_V", SUM_MM40X_V );
		
		sumMap.put( "SUM_MM40HQX_T", SUM_MM40HQX_T );
		sumMap.put( "SUM_MM40HQX_V", SUM_MM40HQX_V );
		
		sumMap.put( "SUM_MM45X_T", SUM_MM45X_T );
		sumMap.put( "SUM_MM45X_V", SUM_MM45X_V );
		
		sumMap.put( "SUM_TXMMUL_T", SUM_TXMMUL_T );
		sumMap.put( "SUM_TXMMUL_V", SUM_TXMMUL_V );
		
		
		sumMap.put( "SUM_MM20M_T", SUM_MM20M_T );
		sumMap.put( "SUM_MM20M_V", SUM_MM20M_V );
		
		sumMap.put( "SUM_MM40M_T", SUM_MM40M_T );
		sumMap.put( "SUM_MM40M_V", SUM_MM40M_V );
		
		sumMap.put( "SUM_MM40HQM_T", SUM_MM40HQM_T );
		sumMap.put( "SUM_MM40HQM_V", SUM_MM40HQM_V );
		
		sumMap.put( "SUM_MM45M_T", SUM_MM45M_T );
		sumMap.put( "SUM_MM45M_V", SUM_MM45M_V );
		
		sumMap.put( "SUM_TMMMUL_T", SUM_TMMMUL_T );
		sumMap.put( "SUM_TMMMUL_V", SUM_TMMMUL_V );
		
		sumMap.put( "SUM_HM20IXM_T", SUM_HM20IXM_T );
		sumMap.put( "SUM_HM20IXM_V", SUM_HM20IXM_V );
		sumMap.put( "SUM_HM40IXM_T", SUM_HM40IXM_T );
		sumMap.put( "SUM_HM40IXM_V", SUM_HM40IXM_V );
		sumMap.put( "SUM_HM40HQIXM_T", SUM_HM40HQIXM_T );
		sumMap.put( "SUM_HM40HQIXM_V", SUM_HM40HQIXM_V );
		sumMap.put( "SUM_HM45IXM_T", SUM_HM45IXM_T );
		sumMap.put( "SUM_HM45IXM_V", SUM_HM45IXM_V );
		sumMap.put( "SUM_HTMMMUL_T", SUM_HTMMMUL_T );
		sumMap.put( "SUM_HTMMMUL_V", SUM_HTMMMUL_V );
		
		sumMap.put( "SUM_EMPTY_VAN", SUM_EMPTY_VAN );
		sumMap.put( "SUM_EMPTY_TEU", SUM_EMPTY_TEU );
		
		sumMap.put( "OVER_FULL_VAN", OVER_FULL_VAN );
		sumMap.put( "OVER_EMPTY_VAN", OVER_EMPTY_VAN );
		sumMap.put( "OVER_FULL_TEU", OVER_FULL_TEU );
		sumMap.put( "OVER_EMPTY_TEU", OVER_EMPTY_TEU );
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put( "list", list );
		resultMap.put( "sumMap", sumMap );
		return resultMap;
	}

	public List<TCntrVo> onDockDamage(SearchVo searchVo) {
		SearchVo operListAdded = setOperList( searchVo );
		return tCntrRepository.onDockDamage( operListAdded );
	}

	public Map<String, Object> onDockOverStock(SearchVo searchVo) {
		SearchVo operListAdded = setOperList( searchVo );
		
		List<TCntrVo> list = tCntrRepository.onDockOverStock( operListAdded );
		
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
		int im20 = 0, im40 = 0, im40h = 0, im45 = 0;
		int xm20 = 0, xm40 = 0, xm40h = 0, xm45 = 0;
		int mm20 = 0, mm40 = 0, mm40h = 0, mm45 = 0;
		int to20 = 0, to40 = 0, to40h = 0, to45 = 0;
		int totali = 0, totalx = 0, totalm = 0, total = 0;
		
		for( int i=0; i<list.size(); i++ ) {
			String cClass = list.get(i).getCurStat().substring(0, 1).toUpperCase();
			String tyCdSub = list.get(i).getSzCd().substring(0, 1).toUpperCase();
			
			if( cClass.equals( "I" ) ) {
				if( tyCdSub.equals( "2" ) || tyCdSub.equals( "3" ) ) {
					im20++;
				} else if( list.get(i).getSzCd().equals( "45" ) ) {
					im40h++;
				} else if( tyCdSub.equals( "4" ) ) { 
					im40++;
				} else if( tyCdSub.equals( "9" ) || tyCdSub.equals( "L" ) ) {
					im45++;
				}
			} else if( cClass.equals( "X" ) ) {
				if( tyCdSub.equals( "2" ) || tyCdSub.equals( "3" ) ) {
					xm20++;
				} else if( list.get(i).getSzCd().equals( "45" ) ) {
					xm40h++;
				} else if( tyCdSub.equals( "4" ) ) { 
					xm40++;
				} else if( tyCdSub.equals( "9" ) || tyCdSub.equals( "L" ) ) {
					xm45++;
				}
			} else if( cClass.equals( "M" ) ) {
				if( tyCdSub.equals( "2" ) || tyCdSub.equals( "3" ) ) {
					mm20++;
				} else if( list.get(i).getSzCd().equals( "45" ) ) {
					mm40h++;
				} else if( tyCdSub.equals( "4" ) ) { 
					mm40++;
				} else if( tyCdSub.equals( "9" ) || tyCdSub.equals( "L" ) ) {
					mm45++;
				}
			}
		}
		
		to20 = im20 + xm20 + mm20;
		to40 = im40 + xm40 + mm40;
		to40h = im40h + xm40h + mm40h;
		to45 = im45 + xm45 + mm45;
		
		totali = im20 + im40 + im40h + im45;
		totalx = xm20 + xm40 + xm40h + xm45;
		totalm = mm20 + mm40 + mm40h + mm45;
		
		total = totali + totalx + totalm;
		
		sumMap.put( "im20", im20 );
		sumMap.put( "im40", im40 );
		sumMap.put( "im40h", im40h );
		sumMap.put( "im45", im45 );
		
		sumMap.put( "xm20", xm20 );
		sumMap.put( "xm40", xm40 );
		sumMap.put( "xm40h", xm40h );
		sumMap.put( "xm45", xm45 );
		
		sumMap.put( "mm20", mm20 );
		sumMap.put( "mm40", mm40 );
		sumMap.put( "mm40h", mm40h );
		sumMap.put( "mm45", mm45 );
		
		sumMap.put( "to20", to20 );
		sumMap.put( "to40", to40 );
		sumMap.put( "to40h", to40h );
		sumMap.put( "to45", to45 );
		
		sumMap.put( "totali", totali );
		sumMap.put( "totalx", totalx );
		sumMap.put( "totalm", totalm );
		sumMap.put( "total", total );
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put( "list", list );
		resultMap.put( "sumMap", sumMap );
		
		return resultMap;
	}

	public List<TCodeVo> onDockHoldingHoldingReason(SearchVo searchVo) {
		return tCodeRepository.onDockHoldingHoldingReason( searchVo );
	}

	public List<THoldngVo> onDockHolding(SearchVo searchVo) {
		SearchVo operListAdded = setOperList( searchVo );
		return tHoldngRepository.onDockHolding( operListAdded );
	}
	
	public List<THoldngVo> onDockHoldingEntire(SearchVo searchVo) {
		SearchVo operListAdded = setOperList( searchVo );
		return tHoldngRepository.onDockHoldingEntire( operListAdded );
	}

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

	public int onDockHoldingCnt(SearchVo searchVo) {
		SearchVo operListAdded = setOperList( searchVo );
		return tHoldngRepository.onDockHoldingCnt( operListAdded );
	}

	public List<WSajunVo> ediCopinoGateInRsrvList(SearchVo searchVo) {
		return wSajunRepository.ediCopinoGateInRsrvList( searchVo );
	}

	public int ediCopinoGateInRsrvListCount(SearchVo searchVo) {
		return wSajunRepository.ediCopinoGateInRsrvListCount( searchVo );
	}

	public Map<String, String> copinoGateInRsrvListProc(WSajunVo wSajunVo, String type) {
		Map<String, String> resultMap = new HashMap<String, String>();
		int result = 0;
		if( type.toUpperCase().equals( "ACCEPT" ) ) {
			result = wSajunRepository.copinoGateInRsrvListProcAccept( wSajunVo );
		} else if( type.toUpperCase().equals( "CANCEL" ) ) {
			result = wSajunRepository.copinoGateInRsrvListProcCancel( wSajunVo );
		} else if( type.toUpperCase().equals( "DENY" ) ) {
			result = wSajunRepository.copinoGateInRsrvListProcDeny( wSajunVo );
		} else if( type.toUpperCase().equals( "DELETE" ) ) {
			result = wSajunRepository.copinoGateInRsrvListProcDelete( wSajunVo );
		}
		
		if( result > 0 ) {
			resultMap.put( "code", "200" );
			resultMap.put( "msg", "" );
		} else {
			resultMap.put( "code", "0" );
			resultMap.put( "msg", "Error occured" );
		}
		return resultMap;
	}

	public List<TSecretVo> copinoGateInRsrvEmpList() {
		TSecretVo tSecretVo = new TSecretVo();
		tSecretVo.setJo( "T" );
		return tSecretRepository.findEmpNoListByJo( tSecretVo );
	}

	public int copinoGateInRsrvSave(WSajunVo wSajunVo) {
		log.info( wSajunVo.toString() );
		if( StringUtils.isEmpty( wSajunVo.getEmpTel() ) ||
				StringUtils.isEmpty( wSajunVo.getDrvTel() ) ||
				StringUtils.isEmpty( wSajunVo.getIoStat() ) ||
				StringUtils.isEmpty( wSajunVo.getFmCd() ) ||
				StringUtils.isEmpty( wSajunVo.getCntrNo() ) ||
				StringUtils.isEmpty( wSajunVo.getEstDay() ) ||
				StringUtils.isEmpty( wSajunVo.getEstTime() ) ||
				StringUtils.isEmpty( wSajunVo.getEstMin() ) ) {
			return 0;
		} else {
			return wSajunRepository.copinoGateInRsrvSave( wSajunVo );
		}
	}

	public List<TGateVo> ediCopinoList(SearchVo searchVo) {
		if( searchVo.getOkCd().toUpperCase().equals( "A" ) ) {
			TSecretVo tSecretVo = new TSecretVo();
			tSecretVo.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
			tSecretVo = tSecretRepository.findByEmpNo( tSecretVo );
			
			searchVo.setJo( tSecretVo.getJo() );
			
			if( searchVo.getJo().toUpperCase().equals( "T" ) ) {
				searchVo.setEmpNo( tSecretVo.getEmpNo() );
			} else if( searchVo.getJo().toUpperCase().equals( "O" ) ) {
				searchVo.setOperList( setOperList( searchVo ).getOperList() );
			}
		} else if( searchVo.getOkCd().toUpperCase().equals( "B" ) ) {
			searchVo.setTruckerCd( searchVo.getTruckerCd1() );
			searchVo.setCarNo( searchVo.getCarNo1() );
		}
		return tGateRepository.ediCopinoList( searchVo );
	}

	public List<TColdltVo> ediByVslCll(SearchVo searchVo) {
		if( StringUtils.isEmpty( searchVo.getShipCd() ) || StringUtils.isEmpty( searchVo.getCallNo() ) || StringUtils.isEmpty( searchVo.getCallYy() ) ) {
			return null;
		} else {
			searchVo.setOperList( setOperList( searchVo ).getOperList() );
			return tColdltRepository.ediByVslCll( searchVo );
		}
	}

	public List<TColdltVo> ediByVslCllDetail(SearchVo searchVo) {
		searchVo.setOperList( setOperList( searchVo ).getOperList() );
		List<TColdltVo> list = tColdltRepository.ediByVslCllDetail( searchVo );
		
		for( int i=0; i<list.size(); i++ ) {
			searchVo.setCntrNo( list.get(i).getCntrNo() );
			
			TCntrVo tCntrVo = tCntrRepository.ediByVslCllDetail( searchVo );
			
			list.get(i).setDirId( tCntrVo.getDirId() );
			list.get(i).setCurStat( tCntrVo.getCurStat() );
			list.get(i).setGinDt( tCntrVo.getGinDt() );
		}
		
		return list;
	}

	public Map<String, Object> onDockBookingListDelete(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		TBkdtlVo tBkdtlVo = tBkdtlRepository.findBkAamtByBknoAndOperCdAndSzCdAndTyGrpCd( searchVo );
		
		int check = (int) (tBkdtlVo != null ? tBkdtlVo.getBkAamt() : 0);
		if( check != 0 ) {
			List<TBklstVo> tBklstList = tBklstRepository.findCntrNoAndDupNoAndOperCdByBkNoAndOperCdAndCurStat( searchVo );
			
			if( tBklstList.size() == 0 ) {
				for( int i=0; i<tBklstList.size(); i++ ) {
					tCntrRepository.updatePickCdByCntrNoAndDupNoAndOperCdAndCurStat( tBklstList.get(i) );
					tMovelogRepository.deleteByCntrNoAndOperCdAndDupNo( tBklstList.get(i ) );
					tBklstRepository.deleteByBkNoAndOperCdAndSzCdAndTyGrpCd( searchVo );
				}
				
				tBkdtlRepository.deleteByBkNoAndOperCdAndSzCdAndTyGrpCd( searchVo );
				
				TBkdtlVo countVo = tBkdtlRepository.findCountByBkNoAndOperCd( searchVo );
				
				if( countVo != null && countVo.getCnt() == 0 ) {
					tBkmstRepository.deleteByBkNoAndOperCd( searchVo );
				}
			} else {
				resultMap.put( "result", 0 );
				resultMap.put( "msg", "반출된 컨테이너가 존재합니다. 삭제요청은 담당자에게 문의하세요." );
				
				return resultMap;
			}
		} else {
			tBkdtlRepository.deleteByBkNoAndOperCdAndSzCdAndTyGrpCd( searchVo );
			
			TBkdtlVo countVo = tBkdtlRepository.findCountByBkNoAndOperCd( searchVo );
			
			if( countVo != null && countVo.getCnt() == 0 ) {
				tBkmstRepository.deleteByBkNoAndOperCd( searchVo );
			}
		}
		
		resultMap.put( "result", 1 );
		resultMap.put( "msg", "삭제되었습니다." );
		
		return resultMap;
	}

	public List<TSecretVo> custInspectionGetReqIdList() {
		return tSecretRepository.custInspectionGetReqIdList();
	}

	public TSecretVo getSessionData() {
		TSecretVo tSecretVo = new TSecretVo();
		tSecretVo.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
		return tSecretRepository.findByEmpNo( tSecretVo );
	}

	public List<WCustHdVo> custInspection(SearchVo searchVo) {
		return wCustHdRepository.custInspection( searchVo );
	}

	public int custInspectionDeleteItem(SearchVo searchVo) {
		return wCustItemRepository.custInspectionDeleteItem( searchVo );
	}

	public int custInspectionDeleteCust(SearchVo searchVo) {
		return wCustHdRepository.custInspectionDeleteCust( searchVo );
	}

	public WCustHdVo custInspectionCustItemWindow(SearchVo searchVo) {
		return wCustHdRepository.custInspectionCustItemWindow( searchVo );
	}

	public List<WCustItemVo> custInspectionCustItemList(SearchVo searchVo) {
		return wCustItemRepository.custInspectionCustItemList( searchVo );
	}

	public int custItemWindowSave(SearchVo searchVo) {
		
		
		int result = 0;
		
		String[] itemNmSplit = searchVo.getItemNm().split(",");
		String[] itemIdSplit = searchVo.getItemId().split(",");
		String[] unitSplit = searchVo.getUnit().split(",");
		String[] qtySplit = searchVo.getQty().split(",");
		String[] weightSplit = searchVo.getWeight().split(",");
		String[] modelNoSplit = searchVo.getModelNo().split(",");
		String[] cartonNoSplit = searchVo.getCartonNo().split(",");
		String[] packageNoSplit = searchVo.getPackageNo().split(",");
		
		for( int i=0; i<itemNmSplit.length; i++ ) {
			SearchVo paramVo = new SearchVo();
			paramVo.setCustId( searchVo.getCustId() );
			paramVo.setItemNm( itemNmSplit[i] );
			paramVo.setItemId( itemIdSplit[i] );
			paramVo.setUnit( unitSplit[i] );
			paramVo.setQty( qtySplit[i] );
			paramVo.setWeight( weightSplit[i] );
			paramVo.setModelNo( modelNoSplit[i] );
			paramVo.setCartonNo( cartonNoSplit[i] );
			paramVo.setPackageNo( packageNoSplit[i] );
			
			wCustHdRepository.updateInspStatByCustId( paramVo );
			int itemCount = wCustItemRepository.findCountByItemIdAndCustId( paramVo );
			
			if( itemCount > 0 ) {
				result += wCustItemRepository.updateByItemIdAndCustId( paramVo );
			} else {
				WCustItemVo maxIdVo = wCustItemRepository.findMaxItemIdByCustId( paramVo );
				paramVo.setItemId( String.valueOf( maxIdVo.getItemId() + 1 ) );
				result += wCustItemRepository.insertWCustItem( paramVo );
			}
		}
		
		return result;
	}

	public Map<String, Object> custItemWindowDelete(SearchVo searchVo) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int result = wCustItemRepository.deleteByItemIdAndCustId( searchVo );
		
		if( result > 0 ) {
			resultMap.put("code", 200);
		} else {
			resultMap.put("code", 0);
		}
		
		return resultMap;
	}

	public List<TBsiotSubVo> custCntrListWindow(SearchVo searchVo) {
		if( searchVo.getGubun() == null ) searchVo.setGubun( "1" );
		if( searchVo.getGubun().equals("1") && ( StringUtils.isEmpty( searchVo.getMrn() ) || StringUtils.isEmpty( searchVo.getMsn() ) || StringUtils.isEmpty( searchVo.getHsn() ) ) ) {
			return null;
		} else if( searchVo.getGubun().equals("2") && StringUtils.isEmpty( searchVo.getCntrNo() ) ) {
			return null;
		}
		return tBsiotSubRepository.custCntrListWindow( searchVo );
	}

	public List<WCustHdVo> inspectionManage(SearchVo searchVo) {
		if( StringUtils.isEmpty( searchVo.getGubun() ) ) searchVo.setGubun("0");
		log.info( "searchVo : " + searchVo.toString() );
		List<WCustHdVo> list = wCustHdRepository.inspectionManage( searchVo );
		
		for( int i=0; i<list.size(); i++ ) {
			SearchVo paramVo = new SearchVo();
			paramVo.setCustId( String.valueOf( list.get(i).getCustId() ) );
			int itemCount = wCustItemRepository.findCountByCustId( paramVo );
			
			list.get(i).setItemCount( itemCount );
		}
		
		return list;
	}

	public WCustHdVo custInspectionView(SearchVo searchVo) {
		return wCustHdRepository.custInspectionView( searchVo );
	}

	public List<WCustItemVo> custInspectionViewItem(SearchVo searchVo) {
		return wCustItemRepository.custInspectionViewItem( searchVo );
	}

	public Map<String, Object> custInspectionViewSave(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if( StringUtils.isEmpty( searchVo.getCustId() ) ) {
			resultMap.put( "code", 0 );
			resultMap.put( "msg", "저장 대상 데이터가 존재하지않습니다." );
			
		} else {
			int result = 0;
			String[] custIdSplit = searchVo.getCustId().split(",");
			
			for( int i=0; i<custIdSplit.length; i++ ) {
				SearchVo paramVo = new SearchVo();
				paramVo.setCustId( custIdSplit[i] );
				result += wCustHdRepository.custInspectionViewSave( paramVo );
			}
			
			if( result > 0 ) {
				resultMap.put( "code", 200 );
				resultMap.put( "msg", "저장되었습니다." );
			} else {
				resultMap.put( "code", 1 );
				resultMap.put( "msg", "처리 중 오류가 발생하였습니다. 잠시 후 다시 시도하세요." );
			}
		}
		return resultMap;
	}

	public Map<String, Object> custInspectionManageSave(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		log.info( "searchVo1 : " + searchVo.toString());
		
		if( StringUtils.isEmpty( searchVo.getNoticeDt() ) || StringUtils.isEmpty( searchVo.getNDate() ) || StringUtils.isEmpty( searchVo.getCustId() ) ) {
			resultMap.put( "code", 0 );
			resultMap.put( "msg", "처리 대상이 존재하지 않습니다." );
		} else {
			int result = 0;
			
			String[] chkSplit = searchVo.getChk().split(",");
			String[] custIdSplit = searchVo.getCustId().split(",");
			String[] nDateSplit = searchVo.getNDate().split(",");
			String[] noticeDtSplit = searchVo.getNoticeDt().split(",");
			
			
			for( int i=0; i<chkSplit.length; i++ ) {
				SearchVo paramVo = new SearchVo();
				
				paramVo.setCustId( custIdSplit[i] );
				paramVo.setNDate( nDateSplit[i] );
				paramVo.setNoticeDt( noticeDtSplit[i] );
				
				result += wCustHdRepository.custInspectionManageSave( paramVo );
			}
			
			if( result > 0 ) {
				resultMap.put( "code", 200 );
				resultMap.put( "msg", "처리되었습니다." );
			} else {
				resultMap.put( "code", 1 );
				resultMap.put( "msg", "처리중 오류가 발생하였습니다. 잠시 후 다시 시도해주세요." );
			}
		}
		
		return resultMap;
	}

	public List<WCustHdVo> custInsPvnt(SearchVo searchVo) {
		return wCustHdRepository.custInsPvnt( searchVo );
	}

	public int custInsPvntDeleteItem(SearchVo searchVo) {
		return wCustItemRepository.custInspectionDeleteItem( searchVo );
	}

	public int custInsPvntDeleteCust(SearchVo searchVo) {
		return wCustHdRepository.custInspectionDeleteCust( searchVo );
	}

	public Map<String, Object> custInspectionSave(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int result = 0;
		
		String[] cargoNoSplit = searchVo.getCargoNo().split(",");
		String[] banipCdSplit = searchVo.getBanipCd().split(",");
		String[] cntrNoSplit = searchVo.getCntrNo().split(",");
		String[] isoSplit = searchVo.getIso().split(",");
		String[] inspTypeSplit = searchVo.getInspType().split(",");
		String[] reqCompSplit = searchVo.getReqComp().split(",");
		String[] reqTelSplit = searchVo.getReqTel().split(",");
		String[] inspGubunSplit = searchVo.getInspGubun().split(",");
		
		for( int i=0; i<cargoNoSplit.length; i++ ) {
			SearchVo paramVo = new SearchVo();
			paramVo.setCargoNo( cargoNoSplit[i] );
			paramVo.setBanipCd( banipCdSplit[i] );
			paramVo.setCntrNo( cntrNoSplit[i] );
			paramVo.setIso( isoSplit[i] );
			paramVo.setInspType( inspTypeSplit[i] );
			paramVo.setReqComp( reqCompSplit[i] );
			paramVo.setReqTel( reqTelSplit[i] );
			paramVo.setInspGubun( inspGubunSplit[i] );
			paramVo.setReqId( searchVo.getReqId() );
			paramVo.setCargoType( "INS" );
			
			WCustHdVo readCountVo = wCustHdRepository.custInspectionSaveReadCount( paramVo );
			
			if( readCountVo != null ) {
				if( readCountVo.getInspStat().toUpperCase().equals( "XX" ) || readCountVo.getInspStat().toUpperCase().equals( "RE" ) ) {
					paramVo.setCustId( String.valueOf( readCountVo.getCustId() ) );
					result += wCustHdRepository.custInspectionSaveUpdate( paramVo );
				} else if( readCountVo.getInspStat().toUpperCase().equals( "ED" ) ) {
					result += wCustHdRepository.custInspectionSaveInsert( paramVo );
				}
			} else {
				result += wCustHdRepository.custInspectionSaveInsert( paramVo );
			}
		}
		
		if( result > 0 ) {
			resultMap.put( "code", 200 );
			resultMap.put( "msg", "정상적으로 처리되었습니다." );
		} else {
			resultMap.put( "code",  0 );
			resultMap.put( "msg", "처리중 오류가 발생하였습니다. 잠시 후 다시 시도해주세요." );
		}
		
		return resultMap;
	}

	public Map<String, Object> custInsPvntSave(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int result = 0;
		
		String[] cargoNoSplit = searchVo.getCargoNo().split(",");
		String[] banipCdSplit = searchVo.getBanipCd().split(",");
		String[] cntrNoSplit = searchVo.getCntrNo().split(",");
		String[] isoSplit = searchVo.getIso().split(",");
		String[] inspTypeSplit = searchVo.getInspType().split(",");
		String[] reqCompSplit = searchVo.getReqComp().split(",");
		String[] reqTelSplit = searchVo.getReqTel().split(",");
		String[] inspGubunSplit = searchVo.getInspGubun().split(",");
		
		for( int i=0; i<cargoNoSplit.length; i++ ) {
			SearchVo paramVo = new SearchVo();
			paramVo.setCargoNo( cargoNoSplit[i] );
			paramVo.setBanipCd( banipCdSplit[i] );
			paramVo.setCntrNo( cntrNoSplit[i] );
			paramVo.setIso( isoSplit[i] );
			paramVo.setInspType( inspTypeSplit[i] );
			paramVo.setReqComp( reqCompSplit[i] );
			paramVo.setReqTel( reqTelSplit[i] );
			paramVo.setInspGubun( inspGubunSplit[i] );
			paramVo.setReqId( searchVo.getReqId() );
			paramVo.setCargoType( "PVN" );
			
			WCustHdVo readCountVo = wCustHdRepository.custinsPvntSaveReadCount( paramVo );
			
			if( readCountVo != null ) {
				if( readCountVo.getInspStat().toUpperCase().equals( "XX" ) || readCountVo.getInspStat().toUpperCase().equals( "RE" ) ) {
					paramVo.setCustId( String.valueOf( readCountVo.getCustId() ) );
					result += wCustHdRepository.custInspectionSaveUpdate( paramVo );
				} else if( readCountVo.getInspStat().toUpperCase().equals( "ED" ) ) {
					result += wCustHdRepository.custInspectionSaveInsert( paramVo );
				}
			} else {
				result += wCustHdRepository.custInspectionSaveInsert( paramVo );
			}
		}
		
		if( result > 0 ) {
			resultMap.put( "code", 200 );
			resultMap.put( "msg", "정상적으로 처리되었습니다." );
		} else {
			resultMap.put( "code",  0 );
			resultMap.put( "msg", "처리중 오류가 발생하였습니다. 잠시 후 다시 시도해주세요." );
		}
		
		return resultMap;
	}

	public List<WCustHdVo> insPvntManage(SearchVo searchVo) {
		if( StringUtils.isEmpty( searchVo.getGubun() ) ) searchVo.setGubun("0");
		List<WCustHdVo> list = wCustHdRepository.insPvntManage( searchVo );
		
		for( int i=0; i<list.size(); i++ ) {
			SearchVo paramVo = new SearchVo();
			paramVo.setCustId( String.valueOf( list.get(i).getCustId() ) );
			int itemCount = wCustItemRepository.findCountByCustId( paramVo );
			
			list.get(i).setItemCount( itemCount );
		}
		
		return list;
	}

	public List<TCntrVo> intraDamageCom(SearchVo searchVo) {
		if( StringUtils.isEmpty( searchVo.getOper() ) ) {
			return null;
		} else {
			return tCntrRepository.intraDamageCom( searchVo );
		}
	}

	public List<TCntrVo> intraIntrDamage(SearchVo searchVo) {
		if( StringUtils.isEmpty( searchVo.getOper() ) ) {
			return null;
		} else {
			return tCntrRepository.intraIntrDamage( searchVo );
		}
	}

	public List<TCntrVo> intraDamageReq(SearchVo searchVo) {
		if( StringUtils.isEmpty( searchVo.getOper() ) ) return null;
		if( StringUtils.isEmpty( searchVo.getCurStat() ) ) searchVo.setCurStat( "R" );
		return tCntrRepository.intraDamageReqCurStat( searchVo );
	}

	public Map<String, Object> intraDamageReqSave(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if( StringUtils.isEmpty( searchVo.getCntrNo() ) ) {
			resultMap.put( "code", 0 );
			resultMap.put( "msg", "처리할 데이터가 존재하지 않습니다." );
		} else {
			String[] cntrNoSplit = searchVo.getCntrNo().split(",");
			
			int result = 0;
			
			for( int i=0; i<cntrNoSplit.length; i++ ) {
				if( !StringUtils.isEmpty( cntrNoSplit[i] ) ) {
					SearchVo tempVo = new SearchVo();
					tempVo.setCntrNo( cntrNoSplit[i] );
					TCntrVo tCntrVo = tCntrRepository.findOneByCntrNoAndDupNo( tempVo );
					SearchVo paramVo = new SearchVo();
					paramVo.setCntrNo( tCntrVo.getCntrNo() );
					paramVo.setDupNo( String.valueOf( tCntrVo.getDupNo() ) );
					paramVo.setOperCd( tCntrVo.getOperCd() );
					paramVo.setSzCd( tCntrVo.getSzCd() );
					paramVo.setTyCd( tCntrVo.getTyCd() );
					paramVo.setFmCd( tCntrVo.getFmCd() );
					paramVo.setUser( SecurityContextHolder.getContext().getAuthentication().getName() );
					
					result += tRepairOrderRepository.saveDamageReq( paramVo );
				}
			}
			
			if( result == cntrNoSplit.length ) {
				resultMap.put( "code", 200 );
				resultMap.put( "msg", "정상적으로 처리되었습니다." );
			} else {
				resultMap.put( "code", 201 );
				resultMap.put( "msg", "일시적인 오류로 인해 일부의 데이터만 처리되었습니다." );
			}
		}
		return resultMap;
	}

	public List<TEquiVo> intraVesselDetailGetCcInfo() {
		return tEquiRepository.intraVesselDetailGetCcInfo();
	}

	public List<Map<String,Object>> intraVesselDetailGetBays(SearchVo searchVo) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		List<TGcorderVo> tGcorderList = tGcorderRepository.intraVesselDetailGetBays( searchVo );
		
		for( int i=0; i<tGcorderList.size(); i++ ) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			SearchVo tempVo = new SearchVo();
			tempVo.setEqNo( searchVo.getEqNo() );
			tempVo.setEqPlseq( String.valueOf( tGcorderList.get(i).getEqPlseq() ) );
			tempVo.setShip( searchVo.getShip() );
			List<TGcorderVo> sbayAndHDeckIdList = tGcorderRepository.findSbayAndHDeckId( tempVo );
			
			map.put("sbayd", StringUtils.EMPTY );
			map.put("sbayh", StringUtils.EMPTY );
			
			boolean addable = false;
				
			for( int j=0; j<sbayAndHDeckIdList.size(); j++ ) {
				if( sbayAndHDeckIdList.get(j).getHdeckId().toUpperCase().equals("D") ) {
					if( j == 0 ) {
						map.put("sbayd", sbayAndHDeckIdList.get(j).getSbay() );
					} else {
						map.put("sbayd", map.get("sbayd").toString() + "|" + sbayAndHDeckIdList.get(j).getSbay() );
					}
				} else {
					if( j == 0 ) {
						map.put( "sbayh", sbayAndHDeckIdList.get(j).getSbay() );
					} else {
						map.put( "sbayh", map.get("sbayh").toString() + "|" + sbayAndHDeckIdList.get(j).getSbay() );
					}
				}
				
				addable = true;
			}
			
			if( addable ) resultList.add( map );
		}
		
		
		return resultList;
	}

	public List<TVescallVo> intraVesselDetailGetShip(SearchVo searchVo) {
		return tVescallRepository.intraVesselDetailGetShip( searchVo );
	}

	public Map<String, Object> intraVesselDetailLoad(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		StringTokenizer st = new StringTokenizer( searchVo.getBays(), "|" );
		String[] baysSplit = new String[st.countTokens()];
		boolean baysListAddable = false;
		int i=0;
		while( st.hasMoreTokens() ) {
			baysSplit[i] = st.nextToken();
			i++;
			baysListAddable = true;
		}
		
		if( baysListAddable ) {
			searchVo.setBaysList(baysSplit);
			searchVo.setHdeckId( baysSplit[baysSplit.length-1] );
		}
		
		resultMap.put( "list", tGcorderRepository.intraVesselDetailLoad( searchVo ) );
		resultMap.put( "param", searchVo );
		return resultMap;
	}

	public List<TGateVo> copinoTimes() {
		return tGateRepository.copinoTimes();
	}

	public List<TOperVo> copinoGateInInsOperList() {
		return tOperRepository.copinoGateInInsOperList();
	}

	public List<TPortVo> ediCopinoPortList(SearchVo searchVo) {
		return tPortRepository.ediCopinoPortList( searchVo );
	}

	public TCarVo ediCopinoCarFind(SearchVo searchVo) {
		return tCarRepository.ediCopinoCarFind( searchVo );
	}

	public Map<String, Object> ediCopinoErrCheck(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int errCnt = 0;
		List<String> msgList = new ArrayList<String>();
		
		
		
		if( searchVo.getCntrNo().length() != 11 ) {
			errCnt++;
			msgList.add( "컨테이너 번호 확인" );
		} else {
			String cTemp = searchVo.getSzCd().substring(0,1);
			if( cTemp.equals("2") || cTemp.equals("4") || cTemp.equals("9") || cTemp.equals("L") ) {
				cTemp = searchVo.getSzCd().substring(1,2);
				if( !cTemp.equals("0") && !cTemp.equals("1") && !cTemp.equals("2") && !cTemp.equals("3") && !cTemp.equals("4") && !cTemp.equals("5") && !cTemp.equals("6") && !cTemp.equals("7") && !cTemp.equals("8") && !cTemp.equals("9") ) {
					errCnt++;
					msgList.add( "Size 미등록" );
				} 
			} else {
				errCnt++;
				msgList.add( "Size 미등록" );
			}
			
			if( searchVo.getSzCd().substring(1, 2).equals("L") ) {
				searchVo.setSzCd( "9" + searchVo.getSzCd().substring(1,2) );
			}
			
			String cBondId = "";
			String cCurStat = "";
			String cDatalvOk = "";
			String cDataupChk = "";
			String cOperChk = "";
			String cHoldCd = "";
			String overSw = "";
			String overPw = "";
			String overFl = "";
			String overBl = "";
			String cYpmasterErr = "";
			String cSzCd = "";
			int pcDupno = 0;
			boolean CLLCheck = false;
			
			
			if( searchVo.getIoCd().toUpperCase().equals("IN") ) {
				
				searchVo.setCioCd( "I" );
				
				if( !StringUtils.isEmpty( searchVo.getTempId() ) && !searchVo.getTempId().toUpperCase().equals("D") ) {
					if( StringUtils.isEmpty( searchVo.getTempCd() ) ) {
						errCnt++;
						msgList.add( "냉동온도 확인" );
					} else if( !searchVo.getTempCd().substring(0,1).equals("+") && !searchVo.getTempCd().substring(0,1).equals("-") ) {
						errCnt++;
						msgList.add( "냉동온도 영상(+), 영하(-) 기호 오류" );
					}
				}
				
				if( searchVo.getFmCd().toUpperCase().equals("F") && ( searchVo.getTyCd().substring(1,2).equals("3") || searchVo.getTyCd().substring(1,2).equals("4") ) ) {
					if( searchVo.getTempId().toUpperCase().equals("D") ) {
						errCnt++;
						msgList.add( "냉동 온도 확인(냉동, Full, Dry컨)" );
					}
				}
				
				if( searchVo.getFmCd().toUpperCase().equals("F") && Float.valueOf( searchVo.getWeight() ) == 0f ) {
					errCnt++;
					msgList.add( "중량 미수신" );
				} else if( searchVo.getFmCd().toUpperCase().equals("M") && Float.valueOf( searchVo.getWeight() ) > 6 ) {
					errCnt++;
					msgList.add( "중량 초과" );
				} else if( searchVo.getFmCd().toUpperCase().equals("F") && Float.valueOf( searchVo.getWeight() ) > 40.6 ) {
					errCnt++;
					msgList.add( "중량 초과(40.6톤)" );
				} else if( searchVo.getFmCd().toUpperCase().equals("F") && searchVo.getSzCd().substring(1,2).equals("2") && Float.valueOf( searchVo.getWeight() ) < 2 ) {
					errCnt++;
					msgList.add( "FULL 중량 미달" );
				} else if( searchVo.getFmCd().toUpperCase().equals("F") && searchVo.getSzCd().substring(1,2).equals("4") && Float.valueOf( searchVo.getWeight() ) < 4 ) {
					errCnt++;
					msgList.add( "FULL 중량 미달" );
				}
				
				if( StringUtils.isEmpty( searchVo.getPodCd() ) ) {
					errCnt++;
					msgList.add( "POD 미수신" );
				} else {
					TPortVo tPortVo = tPortRepository.findCountrycdByPortCd1( searchVo );
					if( tPortVo == null ) {
						errCnt++;
						msgList.add( "POD 미등록" );
					}
				}
				
				TVescallVo tVescallVo = tVescallRepository.findClosingDtAndAtdDtByShipCdAndCallNoAndCallYy( searchVo );
				if( tVescallVo == null ) {
					errCnt++;
					msgList.add( "모선항차 미등록" );
				} else {
					String closingDt = tVescallVo.getClosingDt();
					String atdDt = tVescallVo.getAtdDt();
					String ctpCd = tVescallVo.getCtpCd();
					
					String currentDateTime = commonRepository.getSysdate();
					
					if( !StringUtils.isEmpty( atdDt ) ) {
						if( Long.valueOf( StringUtils.removeSpace( StringUtils.replaceAll( atdDt, "-", "" ) ) ) < Long.valueOf( StringUtils.replaceAll( currentDateTime, "-", "" ) ) ) {
							TCntrVo tCntrVo = tCntrRepository.findCurStatAndDirIdByShipCdAndCallYyAndCallNoAndCntrNo( searchVo );
							
							if( tCntrVo == null ) {
								errCnt++;
								msgList.add( "출항한 모선" );
								cDatalvOk = "C";
							} else {
								if( !tCntrVo.getCurStat().toUpperCase().equals("XB") || StringUtils.isEmpty( tCntrVo.getDirId() ) ) {
									errCnt++;
									msgList.add( "출항한 모선" );
									cDatalvOk = "C";
								}
							}
						}
					}
					
					if( !StringUtils.isEmpty( closingDt ) ) {
						if( Long.valueOf( StringUtils.removeSpace( StringUtils.replaceAll( closingDt, "-", "" ) ) ) < Long.valueOf( StringUtils.replaceAll( currentDateTime, "-", "" ) ) ) {
							TCntrVo tCntrVo = tCntrRepository.findCurStatAndDirIdByShipCdAndCallYyAndCallNoAndCntrNo( searchVo );
							
							if( tCntrVo == null ) {
								errCnt++;
								msgList.add( "Closing Time 초과" );
								cDatalvOk = "C";
							} else {
								if( !tCntrVo.getCurStat().toUpperCase().equals("XB") || StringUtils.isEmpty( tCntrVo.getDirId() ) ) {
									errCnt++;
									msgList.add( "Closing Time 초과" );
									cDatalvOk = "C";
								}
							}
						}
					}
				}
				
				if( !StringUtils.isEmpty( searchVo.getDgCode() ) ) {
					TDgcargoVo tDgcargoVo = tDgcargoRepository.findByCntrNoAndShipCdAndCallYyAndCallNo( searchVo );
					if( tDgcargoVo != null ) {
						tDgcargoRepository.updateImdg1ByCntrNoAndShipCdAndCallYyAndCallNo(searchVo);
					}
				}
				
				if( searchVo.getFmCd().toUpperCase().equals("F") && StringUtils.isEmpty( searchVo.getSealNo() ) ) {
					errCnt++;
					msgList.add( "Seal No. 미수신" );
				}
				
				if( searchVo.getCntrNo().length() == 11 ) {
					String cTempData = "1012131415161718192021232425262728293031323435363738";
					int iTempPos1 = Integer.valueOf( cTempData.substring( ( ( (int)searchVo.getCntrNo().substring(0,1).toCharArray()[0] - 64 ) * 2 - 1 ), ( ( (int)searchVo.getCntrNo().substring(0,1).toCharArray()[0] - 64 ) * 2 - 1 ) + 2 ) ) * 1;
					int iTempPos2 = Integer.valueOf( cTempData.substring( ( ( (int)searchVo.getCntrNo().substring(1,2).toCharArray()[0] - 64 ) * 2 - 1 ), ( ( (int)searchVo.getCntrNo().substring(1,2).toCharArray()[0] - 64 ) * 2 - 1 ) + 2 ) ) * 2;
					int iTempPos3 = Integer.valueOf( cTempData.substring( ( ( (int)searchVo.getCntrNo().substring(2,3).toCharArray()[0] - 64 ) * 2 - 1 ), ( ( (int)searchVo.getCntrNo().substring(2,3).toCharArray()[0] - 64 ) * 2 - 1 ) + 2 ) ) * 4;
					int iTempPos4 = Integer.valueOf( cTempData.substring( ( ( (int)searchVo.getCntrNo().substring(3,4).toCharArray()[0] - 64 ) * 2 - 1 ), ( ( (int)searchVo.getCntrNo().substring(3,4).toCharArray()[0] - 64 ) * 2 - 1 ) + 2 ) ) * 8;
					
					int iTempSum = iTempPos1 + iTempPos2 + iTempPos3 + iTempPos4 
							+ ( Integer.valueOf( searchVo.getCntrNo().substring(4,5) ) * 16 )
							+ ( Integer.valueOf( searchVo.getCntrNo().substring(5,6) ) * 32 )
							+ ( Integer.valueOf( searchVo.getCntrNo().substring(6,7) ) * 64 )
							+ ( Integer.valueOf( searchVo.getCntrNo().substring(7,8) ) * 128 )
							+ ( Integer.valueOf( searchVo.getCntrNo().substring(8,9) ) * 256 )
							+ ( Integer.valueOf( searchVo.getCntrNo().substring(9,10) ) * 512 );
					int iTempMod = iTempSum % 11;
					
					if( !searchVo.getCntrNo().substring(10, 11).equals( String.valueOf( iTempMod ).substring( String.valueOf( iTempMod ).length() - 1, String.valueOf( iTempMod ).length() ) ) ) {
						errCnt++;
						msgList.add( "CheckDigit" );
					}
				}
				
			} else if( searchVo.getIoCd().toUpperCase().equals("OUT") ) {
				
				searchVo.setCioCd( "O" );
				
				TOperVo tOperVo = tOperRepository.findByOperCdAndGoutChk( searchVo );
				if( tOperVo != null ) {
					TMailVo tMailVo = tMailRepository.findByMailIdAndOperCd( searchVo );
					
					if( tMailVo == null ) {
						errCnt++;
						msgList.add( "반출불가운송사" );
					} else {
						cDatalvOk = "Y";
					}
				} else {
					cDatalvOk = "Y";
				}
				
				if( StringUtils.isEmpty( searchVo.getShipCd() ) || StringUtils.isEmpty( searchVo.getCallNo() ) || StringUtils.isEmpty( searchVo.getCallYy() ) ) {
					errCnt++;
					msgList.add( "모선미수신" );
				}
				
				TVescallVo tVescallVo = tVescallRepository.findShipCdByShipCdAndCallNoAndCallYy( searchVo );
				if( tVescallVo == null ) {
					errCnt++;
					msgList.add( "모선미등록" );
				}
			}
			
			if( searchVo.getOrderNo().length() > 18 ) {
				errCnt++;
				msgList.add( "D/O 또는 B/O 자리수 초과" );
			}
			
			if( searchVo.getCarNo().length() == 8 ) {
				TCarVo tCarVo = tCarRepository.ediCopinoCarFind( searchVo );
				if( tCarVo == null ) {
					errCnt++;
					msgList.add( "차량번호 미등록" );
				}
			} else {
				errCnt++;
				msgList.add( "차량번호 오류" );
			}
			
			if( searchVo.getFmCd().toUpperCase().equals("F") && searchVo.getShipCd().toUpperCase().equals("ODPO") ) {
				errCnt++;
				msgList.add( "모선 != 적공 오류" );
			} else if( searchVo.getFmCd().toUpperCase().equals("M") && searchVo.getShipCd().toUpperCase().equals("TBNP" ) ) {
				errCnt++;
				msgList.add( "모선 != 적공 오류" );
			}
			

			TEdicustomerVo tEdicustomerVo = tEdicustomerRepository.findByCstId( searchVo );
			if( tEdicustomerVo == null ) {
				errCnt++;
				msgList.add( "미등록 운송사" );
			}
			
			TOwnerBklstVo tOwnerBklstVo = tOwnerBklstRepository.findByOwnerNm( searchVo );
			if( tOwnerBklstVo != null ) {
				if( tOwnerBklstVo.getKindCd().toUpperCase().equals( "A1" ) ) {
					errCnt++;
					msgList.add( "화주명오류(금지단어)" );
				} else if( tOwnerBklstVo.getKindCd().toUpperCase().equals( "A2" ) ) {
					errCnt++;
					msgList.add( "화주명오류(선사,포워딩,운송사단어)" );
				} else {
					errCnt++;
					msgList.add( "화주명오류" );
				}
			}
			
			if( StringUtils.isEmpty( searchVo.getOperCd() ) ) {
				errCnt++;
				msgList.add( "선사코드 미수신" );
			} else {
				TOperVo tOperVo = tOperRepository.findByOperCd( searchVo );
				if( tOperVo == null ) {
					errCnt++;
					msgList.add( "선사코드 미등록" );
				}
			}
			
			
			int tGateCnt = tGateRepository.findCountByCntrNoAndIoCdAndErrCd( searchVo );
			
			if( tGateCnt > 0 ) {
				cDataupChk = "*";
			}
			
			TCntrVo tCntrVo = tCntrRepository.findMaxDupNoAndCurStatByCntrNoGroupByDupNoAndCurStatOrderByDupNoDesc( searchVo );
			if( tCntrVo == null ) {
				pcDupno = 1;
			} else {
				if( StringUtils.isEmpty( tCntrVo.getDupNo() ) ) {
					CLLCheck = false;
				} else {
					pcDupno = Integer.valueOf( tCntrVo.getDupNo() );
					
					if( tCntrVo.getCurStat().substring(1,2).equals("D") ) {
						pcDupno++;
					}
					
					if( tCntrVo.getCurStat().substring(1,2).equals("B") ) {
						CLLCheck = true;
					} else {
						CLLCheck = false;
					}
				}
			}
			
			
			
			/////////////// SICE0000000 OR SICE0000001 시작
			if( searchVo.getCntrNo().toUpperCase().equals( "E1CT0000000" ) || searchVo.getCntrNo().toUpperCase().equals( "E1CT0000001") ) {
				TVescallVo tVescallVo = tVescallRepository.findByShipCdAndCallNoAndCtpCd( searchVo );
				if( tVescallVo == null ) {
					errCnt++;
					msgList.add( "모선 비일치" );
				}
				
				if( searchVo.getOrderCd().toUpperCase().equals( "BO" ) ) {
					tVescallVo = tVescallRepository.findAdtDtByBkNoAndOperCd( searchVo );
					
					if( tVescallVo == null ) {
						errCnt++;
						msgList.add( "B/O 모선정보 없음" );
					} else if( tVescallVo.getAtdDt().toUpperCase().equals("Y") ) {
						errCnt++;
						msgList.add( "출항모선 B/O" );
					}
				}
				
				TOperVo tOperVo = tOperRepository.findOndockAndMvChkAndLocChkAndDoChkAndBkChkByOperCd( searchVo );
				
				if( tOperVo != null ) {
					if( tOperVo.getMvChk().toUpperCase().equals( "Y" ) ) {
						TMailVo tMailVo = tMailRepository.findByOperCdAndMailIdAndLineCd( searchVo );
						
						if( tMailVo != null && searchVo.getCmcNo().length() != 3 ) {
							errCnt++;
							msgList.add( "조작코드 미수신" );
						}
					}
					
					if( tOperVo.getLocChk().toUpperCase().equals("Y" ) ) {
						if( searchVo.getIoCd().toUpperCase().equals( "OUT" ) && StringUtils.isEmpty( searchVo.getPodCd() ) ) {
							errCnt++;
							msgList.add( "도착지 미수신" );
						} else if( searchVo.getIoCd().toUpperCase().equals( "IN" ) && StringUtils.isEmpty( searchVo.getPosCd() ) ) {
							errCnt++;
							msgList.add( "출발지 미수신" );
						}
					}
				}
				
				TTypeVo tTypeVo = tTypeRepository.findTyGrpCdByTyCd( searchVo );
				if( tTypeVo != null && !tTypeVo.getTyGrpCd().toUpperCase().equals("GP") ) {
					errCnt++;
					msgList.add( "TYPE 오류" );
				}
				
				String cSzCheck = "";
				String cTyCheck = "";
				
				if( searchVo.getSzCd().substring(0,1).equals("2") ) {
					cSzCheck = "20";
					cTyCheck = "GP";
				} else if( searchVo.getSzCd().substring(0,1).equals("4") ) {
					cSzCheck = "40";
					if( searchVo.getSzCd().substring(1,2).equals("4") || searchVo.getSzCd().substring(1,2).equals("5") ) {
						cTyCheck = "HQ";
					} else {
						cTyCheck = "GP";
					}
				} else if( searchVo.getSzCd().substring(0,1).equals("9") ) {
					cSzCheck = "45";
					cTyCheck = "GP";
				}
				
				boolean bCheck = false;
				
				List<TBkmstVo> tBkmstVo = tBkmstRepository.findSzCdAndTyGrpCdAndCyCdAndBkEamtAndBkAamtAndHandCdByBkNoAndOperCd( searchVo );
				if( tBkmstVo == null || tBkmstVo.size() == 0 ) {
					errCnt++;
					msgList.add( "B/O 미등록" );
				} else {
					for( int i=0; i<tBkmstVo.size(); i++ ) {
						if( tBkmstVo.get(i).getSzCd().toUpperCase().equals( cSzCheck ) && tBkmstVo.get(i).getTyGrpCd().toUpperCase().equals( cTyCheck ) ) {
							if( tBkmstVo.get(i).getHandCd().toUpperCase().equals("Y") ) {
								errCnt++;
								msgList.add( "수동요청 B/O" );
							}
						} else if( Integer.valueOf( tBkmstVo.get(i).getBkEamt() ) <= Integer.valueOf( tBkmstVo.get(i).getBkAamt() ) ) {
							errCnt++;
							msgList.add( "Auto pickup 자료없음" );
						}
						bCheck = true;
					}
					
					if( !bCheck ) {
						errCnt++;
						msgList.add( "미요청 SIZE" );
					}
				}
			} else {
				// SICE0000000 OR SICE0000001 이 아닐 경우 => 일반
				TCarbklstVo tCarbklstVo = tCarbklstRepository.findPassCdByTruckerCdAndCarNoAndPassCd( searchVo );
				if( tCarbklstVo != null ) {
					errCnt++;
					msgList.add( "의무불이행차량" );
				}
				
				if( searchVo.getIoCd().toUpperCase().equals( "IN" ) ) {
					if( ( searchVo.getTyCd().substring(0, 1).equals("3") || searchVo.getTyCd().substring(0,1).equals("4") ) && searchVo.getFmCd().toUpperCase().equals("F") ) {
						if( !searchVo.getTempId().toUpperCase().equals("D") ) {
							if( StringUtils.isEmpty( searchVo.getTempId() ) || StringUtils.isEmpty( searchVo.getTempCd() ) ) {
								errCnt++;
								msgList.add( "냉동온도 미수신" );
							}
						}
					} else if( !searchVo.getTyCd().substring(0,1).equals("3") && !searchVo.getTyCd().substring(0,1).equals("4") ) {
						searchVo.setTempId( StringUtils.EMPTY );
						searchVo.setTempCd( StringUtils.EMPTY );
					}
					searchVo.setPcDupno( String.valueOf( pcDupno - 1 ) );
					TCntrVo tCntrVoIn = tCntrRepository.findSzCdAndTyCdAndOperCdAndTempAndTempIdAndPtiYnByCntrNoAndDupNo( searchVo );
					
					if( tCntrVoIn != null ) {
						if( !StringUtils.isEmpty( tCntrVoIn.getSzCd() ) ) {
							if( tCntrVoIn.getSzCd().substring(0,1).equals("2") && !searchVo.getSzCd().substring(0,1).equals("2") ) {
								errCnt++;
								msgList.add( "Size 불일치" );
							} else if( tCntrVoIn.getSzCd().substring(0,1).equals("4") ) {
								if( tCntrVoIn.getSzCd().substring(1,2).equals("4") || tCntrVoIn.getSzCd().substring(1,2).equals("5") ) {
									if( !searchVo.getSzCd().equals("44") && !searchVo.getSzCd().equals("45") ) {
										errCnt++;
										msgList.add( "Size 불일치" );
									}
								} else {
									if( !searchVo.getSzCd().equals("40") && !searchVo.getSzCd().equals("41") && !searchVo.getSzCd().equals("42") && !searchVo.getSzCd().equals("43") ) {
										errCnt++;
										msgList.add( "Size 불일치" );
									}
								}
							} else if( tCntrVoIn.getSzCd().substring(0,1).equals("9") ) {
								if( !searchVo.getSzCd().substring(0,1).equals("9") ) {
									errCnt++;
									msgList.add( "Size 불일치" );
								}
							}
						}
						
						if( !StringUtils.isEmpty( tCntrVoIn.getTyCd() ) ) {
							SearchVo tempVo = new SearchVo();
							tempVo.setTyCd( tCntrVoIn.getTyCd() );
							if( !tTypeRepository.findTyGrpCdByTyCd(searchVo).getTyGrpCd().equals( tTypeRepository.findTyGrpCdByTyCd( tempVo ).getTyGrpCd() ) ) {
								errCnt++;
								msgList.add( "Type 비일치" );
							}
						}
						
						if( cOperChk.toUpperCase().equals( "Y" ) ) {
							if( CLLCheck == false && searchVo.getCtpCd().equals("1") ) {
								if( !tCntrVoIn.getOperCd().equals(searchVo.getOperCd() ) ) {
									searchVo.setPcDupno( String.valueOf( Integer.valueOf( searchVo.getPcDupno() ) + 1 ) ); 
									TCopinoRjctVo tCopinoRjctVo = tCopinoRjctRepository.findByCntrNoAndDupNoAndOperCd( searchVo );
									if( tCopinoRjctVo == null ) {
										errCnt++;
										msgList.add( "선사비일치" );
									}
								}
							}
						}
						
						String tempIdVal = "";
						
						if( searchVo.getTempId().toUpperCase().equals("D") ) {
							tempIdVal = "DRY";
						} else if( searchVo.getTempId().toUpperCase().equals("C") ) {
							tempIdVal = "CEL";
						} else if( searchVo.getTempId().toUpperCase().equals("F") ) {
							tempIdVal = "FAH";
						} 
						
						if( searchVo.getFmCd().toUpperCase().equals("F") && ( searchVo.getTyCd().substring(0,1).equals("3") || searchVo.getTyCd().substring(0,1).equals("4") ) ) {
							if( tCntrVoIn.getPtiYn().toUpperCase().equals("Y") ) {
								if( !StringUtils.isEmpty( tCntrVoIn.getTemp() ) && !StringUtils.isEmpty( tCntrVoIn.getTempId() ) ) {
									if( !searchVo.getTempCd().equals( tCntrVoIn.getTemp() ) || !searchVo.getTempCd().equals( tempIdVal ) ) {
										errCnt++;
										msgList.add( "PTI값과 비일치 확인" );
									}
								}
							}
						}
						
					}
				}
				
				
				
				TOperVo tOperVo = tOperRepository.findOperChkByOperCd( searchVo );
				if( tOperVo != null ) {
					cOperChk = tOperVo.getOperChk();
				}
				
				TCntrVo cntrVo = tCntrRepository.findByCntrNoAndDupNo( searchVo );
				if( cntrVo == null ) {
					if( searchVo.getIoCd().toUpperCase().equals("OUT") ) {
						errCnt++;
						msgList.add( "반출컨테이너없음" );
					}
					
					if( searchVo.getIoCd().toUpperCase().equals("IN") && !StringUtils.isEmpty( searchVo.getDgCode() ) ) {
						errCnt++;
						msgList.add( "위험물코드 미등록" );
					}
				} else {
					cCurStat = cntrVo.getCurStat();
					if( searchVo.getIoCd().toUpperCase().equals("IN") ) {
						if( cntrVo.getCurStat().toUpperCase().equals("IB") || cntrVo.getCurStat().toUpperCase().equals("SB") ) {
							errCnt++;
							msgList.add( "양하예정 컨테이너 반입" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("XB") && 
								( !cntrVo.getShipCd().toUpperCase().equals( searchVo.getShipCd().toUpperCase() )
										|| !String.valueOf( cntrVo.getCallNo() ).equals( searchVo.getCallNo() )
										|| !String.valueOf( cntrVo.getCallYy() ).equals( searchVo.getCallYy() ) ) 
								) {
							errCnt++;
							msgList.add( "다른모선으로 계획된 반입" );
						} else if( !cntrVo.getCurStat().substring(1,2).equals("B") ) {
							errCnt++;
							msgList.add( "반입된 컨테이너" );
						}
						
						if( !cntrVo.getShipCd().toUpperCase().equals( searchVo.getShipCd().toUpperCase() )
										|| !String.valueOf( cntrVo.getCallNo() ).equals( searchVo.getCallNo() )
										|| !String.valueOf( cntrVo.getCallYy() ).equals( searchVo.getCallYy() ) ) {
							errCnt++;
							msgList.add( "모선 비일치" );
						}
						
						if( !StringUtils.isEmpty( cntrVo.getPod() ) && !cntrVo.getPod().toUpperCase().equals( searchVo.getPodCd().toUpperCase() ) ) {
							errCnt++;
							msgList.add( "POD 비일치" );
						}
						
						String tempIdVal = "";
						
						if( searchVo.getTempId().toUpperCase().equals("D") ) {
							tempIdVal = "DRY";
						} else if( searchVo.getTempId().toUpperCase().equals("C") ) {
							tempIdVal = "CEL";
						} else if( searchVo.getTempId().toUpperCase().equals("F") ) {
							tempIdVal = "FAH";
						} 
						
						if( searchVo.getFmCd().toUpperCase().equals("F") && ( searchVo.getTyCd().substring(0,1).equals("3") || searchVo.getTyCd().substring(0,1).equals("4") ) ) {
							if( !StringUtils.isEmpty( cntrVo.getTemp() ) && !StringUtils.isEmpty( cntrVo.getTempId() ) ) {
								errCnt++;
								msgList.add( "CLL 온도 비일치" );
							}
						}
						
						if( StringUtils.isEmpty( cntrVo.getImdg() ) ) {
							if( StringUtils.isEmpty( searchVo.getDgCode() ) ) {
								errCnt++;
								msgList.add( "위험물코드 미등록" );
							}
						} else {
							if( StringUtils.isEmpty( searchVo.getDgCode() ) ) {
								errCnt++;
								msgList.add( "위험물코드 미수신" );
							}
							
							if( !cntrVo.getImdg().toUpperCase().equals(searchVo.getDgCode() ) ) {
								errCnt++;
								msgList.add( "위험물코드 비일치" );
							}
						}
						
						if( cntrVo.getDirId().equals("1") && StringUtils.isEmpty( cntrVo.getImdg() ) ) {
							if( !cntrVo.getFmCd().toUpperCase().equals(searchVo.getFmCd().toUpperCase()) ) {
								errCnt++;
								msgList.add( "적공 구분오류" );
							}
						}
						
						if( cntrVo.getDirId().equals("1") && !StringUtils.isEmpty( cntrVo.getImdg() ) ) {
							if( !cntrVo.getFmCd().toUpperCase().equals(searchVo.getFmCd().toUpperCase()) ) {
								errCnt++;
								msgList.add( "적공 구분오류" );
							}
						}
						
						if( ( cntrVo.getDirId().equals("1") || cntrVo.getDirId().equals("9") ) && StringUtils.isEmpty( cntrVo.getImdg() ) ) {
							if( cntrVo.getSzCd().substring(0,1).equals("2") && !cntrVo.getSzCd().substring(0,1).equals( searchVo.getSzCd().substring(0,1) ) ) {
								errCnt++;
								msgList.add( "Size 비일치" );
							} else if( cntrVo.getSzCd().substring(0,1).equals("4") ) {
								if( cntrVo.getSzCd().substring(1,2).equals("4") || cntrVo.getSzCd().substring(1,2).equals("5") ) {
									if( !searchVo.getSzCd().equals("44") && !searchVo.getSzCd().equals("45") ) {
										errCnt++;
										msgList.add( "Size 비일치" );
									}
								} else {
									if( !searchVo.getSzCd().equals("40") && !searchVo.getSzCd().equals("41") && !searchVo.getSzCd().equals("42") && !searchVo.getSzCd().equals("43") ) {
										errCnt++;
										msgList.add( "Size 비일치" );
									}
								}
							} else if( cntrVo.getSzCd().substring(0,1).equals("9") ) {
								if( !searchVo.getSzCd().substring(0,1).equals("9") ) {
									errCnt++;
									msgList.add( "Size 비일치" );
								}
							}
							
							if( !StringUtils.isEmpty( cntrVo.getTyCd() ) ) {
								SearchVo tempVo = new SearchVo();
								tempVo.setTyCd( cntrVo.getTyCd() );
								if( !tTypeRepository.findTyGrpCdByTyCd(searchVo).getTyGrpCd().equals( tTypeRepository.findTyGrpCdByTyCd( tempVo ).getTyGrpCd() ) ) {
									errCnt++;
									msgList.add( "Type 비일치" );
								}
							}
						}
						
						if( ( cntrVo.getDirId().equals("1") || cntrVo.getDirId().equals("9") ) && !StringUtils.isEmpty( cntrVo.getImdg() ) ) {
							if( cntrVo.getSzCd().substring(0,1).equals("2") && !cntrVo.getSzCd().substring(0,1).equals( searchVo.getSzCd().substring(0,1) ) ) {
								errCnt++;
								msgList.add( "Size 비일치" );
							} else if( cntrVo.getSzCd().substring(0,1).equals("4") ) {
								if( cntrVo.getSzCd().substring(1,2).equals("4") || cntrVo.getSzCd().substring(1,2).equals("5") ) {
									if( !searchVo.getSzCd().equals("44") && !searchVo.getSzCd().equals("45") ) {
										errCnt++;
										msgList.add( "Size 비일치" );
									}
								} else {
									if( !searchVo.getSzCd().equals("40") && !searchVo.getSzCd().equals("41") && !searchVo.getSzCd().equals("42") && !searchVo.getSzCd().equals("43") ) {
										errCnt++;
										msgList.add( "Size 비일치" );
									}
								}
							} else if( cntrVo.getSzCd().substring(0,1).equals("9") ) {
								if( !searchVo.getSzCd().substring(0,1).equals("9") ) {
									errCnt++;
									msgList.add( "Size 비일치" );
								}
							}
							
							if( !StringUtils.isEmpty( cntrVo.getTyCd() ) ) {
								SearchVo tempVo = new SearchVo();
								tempVo.setTyCd( cntrVo.getTyCd() );
								if( !tTypeRepository.findTyGrpCdByTyCd(searchVo).getTyGrpCd().equals( tTypeRepository.findTyGrpCdByTyCd( tempVo ).getTyGrpCd() ) ) {
									errCnt++;
									msgList.add( "Type 비일치" );
								}
							}
						}
					} else if( searchVo.getIoCd().toUpperCase().equals("OUT") ) {
						if( cntrVo.getHoldCd().equals("*") || cntrVo.getPickCd().toUpperCase().equals("H") ) {
							errCnt++;
							msgList.add( "터미널 HOLD" );
						}
						
						if( cntrVo.getTsId().equals("1") ) {
							errCnt++;
							msgList.add( "자부두 TS반출" );
						}
						
						if( cntrVo.getCurStat().toUpperCase().equals("IG") || cntrVo.getCurStat().toUpperCase().equals("IP") || cntrVo.getCurStat().toUpperCase().equals("MP") ) {
							errCnt++;
							msgList.add( "반출요청중복" );
						} else if( ( cntrVo.getCurStat().toUpperCase().equals("XY") || cntrVo.getCurStat().toUpperCase().equals("XO" ) ) && !cntrVo.getRtnCd().toUpperCase().equals("C") ) {
							errCnt++;
							msgList.add( "선적취소 후 반출가능" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("MY") && cntrVo.getFmCd().toUpperCase().equals("F") && !cntrVo.getRtnCd().toUpperCase().equals("C") ) {
							errCnt++;
							msgList.add( "선적취소 후 반출가능" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("IY") && cntrVo.getTsId().equals("1") ) {
							errCnt++;
							msgList.add( "이선적양하" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("XY") && cntrVo.getTsId().equals("1") ) {
							errCnt++;
							msgList.add( "이선적적하" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("XG") || cntrVo.getCurStat().toUpperCase().equals("XB") ) {
							errCnt++;
							msgList.add( "상태코드 확인" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("IB") && StringUtils.isEmpty( cntrVo.getDirId() ) ) {
							errCnt++;
							msgList.add( "양하예정" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("ID") || cntrVo.getCurStat().toUpperCase().equals("MD") ) {
							errCnt++;
							msgList.add( "반출 완료된 컨테이너" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("XD") ) {
							errCnt++;
							msgList.add( "선적완료" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("SD" ) || cntrVo.getCurStat().toUpperCase().equals("SY") || cntrVo.getCurStat().toUpperCase().equals("SB") ) {
							errCnt++;
							msgList.add( "선내이적" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("MG") && !StringUtils.isEmpty( cntrVo.getGreqDt() ) ) {
							errCnt++;
							msgList.add( "반출요청 중복" );
						} else if( ( cntrVo.getCurStat().toUpperCase().equals("XG") || cntrVo.getCurStat().toUpperCase().equals("XP") ) && cntrVo.getRtnCd().toUpperCase().equals("C") ) {
							errCnt++;
							msgList.add( "반출요청 중복" );
						} else if( cntrVo.getCurStat().toUpperCase().equals("IO" ) ) {
							errCnt++;
							msgList.add( "양하작업중" );
						}
						
						if( !cntrVo.getShipCd().toUpperCase().equals( searchVo.getShipCd().toUpperCase() )
								|| !String.valueOf( cntrVo.getCallNo() ).equals( searchVo.getCallNo() )
								|| !String.valueOf( cntrVo.getCallYy() ).equals( searchVo.getCallYy() ) ) {
							errCnt++;
							msgList.add( "모선 비일치" );
						}
						
						if( !StringUtils.isEmpty( cntrVo.getOperCd() ) && !cntrVo.getOperCd().toUpperCase().equals(searchVo.getOperCd().toUpperCase() ) ) {
							errCnt++;
							msgList.add( "선사코드 비일치" );
						}
						
						if( !cntrVo.getFmCd().toUpperCase().equals(searchVo.getFmCd().toUpperCase() ) ) {
							errCnt++;
							msgList.add( "적공 구분오류" );
						}
						
						if( cntrVo.getSzCd().substring(0,1).equals("2") && !cntrVo.getSzCd().substring(0,1).equals( searchVo.getSzCd().substring(0,1) ) ) {
							errCnt++;
							msgList.add( "Size 비일치" );
						} else if( cntrVo.getSzCd().substring(0,1).equals("4") ) {
							if( cntrVo.getSzCd().substring(1,2).equals("4") || cntrVo.getSzCd().substring(1,2).equals("5") ) {
								if( !searchVo.getSzCd().equals("44") && !searchVo.getSzCd().equals("45") ) {
									errCnt++;
									msgList.add( "Size 비일치" );
								}
							} else {
								if( !searchVo.getSzCd().equals("40") && !searchVo.getSzCd().equals("41") && !searchVo.getSzCd().equals("42") && !searchVo.getSzCd().equals("43") ) {
									errCnt++;
									msgList.add( "Size 비일치" );
								}
							}
						} else if( cntrVo.getSzCd().substring(0,1).equals("9") ) {
							if( !searchVo.getSzCd().substring(0,1).equals("9") ) {
								errCnt++;
								msgList.add( "Size 비일치" );
							}
						}
						
						if( !StringUtils.isEmpty( cntrVo.getTyCd() ) ) {
							SearchVo tempVo = new SearchVo();
							tempVo.setTyCd( cntrVo.getTyCd() );
							if( !tTypeRepository.findTyGrpCdByTyCd(searchVo).getTyGrpCd().equals( tTypeRepository.findTyGrpCdByTyCd( tempVo ).getTyGrpCd() ) ) {
								errCnt++;
								msgList.add( "Type 비일치" );
							}
						}
						
						TOperVo operVo = tOperRepository.copinoFindByOperCd( searchVo ); 
						if( ( operVo.getOndock().toUpperCase().equals("Y") || operVo.getDoChk().toUpperCase().equals("Y") || operVo.getBkChk().toUpperCase().equals("Y") ) && !cntrVo.getRtnCd().toUpperCase().equals("C") ) {
							if( searchVo.getFmCd().toUpperCase().equals("F") && operVo.getOndock().toUpperCase().equals("Y") && operVo.getDoChk().toUpperCase().equals("Y") && cntrVo.getCurStat().substring(0,1).toUpperCase().equals("I") ) {
								if( cntrVo.getBondId().toUpperCase().equals("B") || cntrVo.getBondId().toUpperCase().equals("C") ) {
									TBsiotSubVo tBsiotSubVo = tBsiotSubRepository.findByCntrNoAndDupNoAndBanchCd( searchVo );
									if( tBsiotSubVo == null ) {
										if( StringUtils.isEmpty( searchVo.getOrderNo() ) && searchVo.getOrderCd().toUpperCase().equals("DO") ) {
											errCnt++;
											msgList.add( "DO 미수신" );
										} else {
											TDomstVo tDomstVo = tDomstRepository.findCopinoErrCheck( searchVo );
											
											if( tDomstVo == null ) {
												errCnt++;
												msgList.add( "DO HOLD" );
											} else if( !tDomstVo.getDoNo().equals( searchVo.getOrderNo() ) && searchVo.getOrderCd().toUpperCase().equals("DO") ) {
												errCnt++;
												msgList.add( "DO 비일치" );
											}
											
											if( operVo.getDemurChk().toUpperCase().equals("Y") && tDomstVo != null ) {
												if( Integer.valueOf( tDomstVo.getClosingDt() ) < 0 ) {
													errCnt++;
													msgList.add( "Demurrage HOLD" );
												}
											}
											
											if( operVo.getDosndChk().toUpperCase().equals("Y") && tDomstVo != null ) {
												if( !searchVo.getTransId().toUpperCase().equals( tDomstVo.getCarNm().toUpperCase() ) ) {
													errCnt++;
													msgList.add( "D/O운송사 비일치");
												}
											}
										}
									}
								}
							} else if( searchVo.getFmCd().toUpperCase().equals("F") && operVo.getOndock().toUpperCase().equals("Y") && operVo.getDoChk().toUpperCase().equals("A") && cntrVo.getCurStat().substring(0,1).toUpperCase().equals("I") ) {
								if( cntrVo.getBondId().toUpperCase().equals("B") || cntrVo.getBondId().toUpperCase().equals("C") ) {
									if( searchVo.getOrderCd().toUpperCase().equals("DO") && StringUtils.isEmpty( searchVo.getOrderCd() ) ) {
										errCnt++;
										msgList.add( "DO 미수신" );
									} else {
										TDomstVo tDomstVo = tDomstRepository.findCopinoErrCheck( searchVo );
										
										if( tDomstVo == null ) {
											errCnt++;
											msgList.add( "DO HOLD" );
										} else if( !tDomstVo.getDoNo().equals( searchVo.getOrderNo() ) && searchVo.getOrderCd().toUpperCase().equals("DO") ) {
											errCnt++;
											msgList.add( "DO 비일치" );
										}
										
										if( operVo.getDemurChk().toUpperCase().equals("Y") && tDomstVo != null ) {
											if( Integer.valueOf( tDomstVo.getClosingDt() ) < 0 ) {
												errCnt++;
												msgList.add( "Demurrage HOLD" );
											}
										}
										
										if( operVo.getDosndChk().toUpperCase().equals("Y") && tDomstVo != null ) {
											if( !searchVo.getTransId().toUpperCase().equals( tDomstVo.getCarNm().toUpperCase() ) ) {
												errCnt++;
												msgList.add( "D/O운송사 비일치");
											}
										}
									}
								}
							} else if( searchVo.getFmCd().toUpperCase().equals("F") && operVo.getOndock().toUpperCase().equals("N") && operVo.getDoChk().toUpperCase().equals("Y") && cntrVo.getCurStat().substring(0,1).toUpperCase().equals("I") ) {
								if( cntrVo.getBondId().toUpperCase().equals("B") || cntrVo.getBondId().toUpperCase().equals("C") ) {
									if( searchVo.getOrderCd().toUpperCase().equals("DO") && StringUtils.isEmpty( searchVo.getOrderCd() ) ) {
										errCnt++;
										msgList.add( "DO 미수신" );
									} else {
										TDomstVo tDomstVo = tDomstRepository.findCopinoErrCheck( searchVo );
										
										if( tDomstVo == null ) {
											errCnt++;
											msgList.add( "DO HOLD" );
										} else if( !tDomstVo.getDoNo().equals( searchVo.getOrderNo() ) && searchVo.getOrderCd().toUpperCase().equals("DO") ) {
											errCnt++;
											msgList.add( "DO 비일치" );
										}
										
										if( operVo.getDemurChk().toUpperCase().equals("Y") && tDomstVo != null ) {
											if( Integer.valueOf( tDomstVo.getClosingDt() ) < 0 ) {
												errCnt++;
												msgList.add( "Demurrage HOLD" );
											}
										}
										
										if( operVo.getDosndChk().toUpperCase().equals("Y") && tDomstVo != null ) {
											if( !searchVo.getTransId().toUpperCase().equals( tDomstVo.getCarNm().toUpperCase() ) ) {
												errCnt++;
												msgList.add( "D/O운송사 비일치");
											}
										}
									}
								}
							} else if( searchVo.getFmCd().toUpperCase().equals("F") && operVo.getOndock().toUpperCase().equals("N") && operVo.getDoChk().toUpperCase().equals("A") && cntrVo.getCurStat().substring(0,1).toUpperCase().equals("I") ) {
								if( cntrVo.getBondId().toUpperCase().equals("B") || cntrVo.getBondId().toUpperCase().equals("C") ) {
									if( searchVo.getOrderCd().toUpperCase().equals("DO") && StringUtils.isEmpty( searchVo.getOrderCd() ) ) {
										errCnt++;
										msgList.add( "DO 미수신" );
									} else {
										TDomstVo tDomstVo = tDomstRepository.findCopinoErrCheck( searchVo );
										
										if( tDomstVo == null ) {
											errCnt++;
											msgList.add( "DO HOLD" );
										} else if( !tDomstVo.getDoNo().equals( searchVo.getOrderNo() ) && searchVo.getOrderCd().toUpperCase().equals("DO") ) {
											errCnt++;
											msgList.add( "DO 비일치" );
										}
										
										if( operVo.getDemurChk().toUpperCase().equals("Y") && tDomstVo != null ) {
											if( Integer.valueOf( tDomstVo.getClosingDt() ) < 0 ) {
												errCnt++;
												msgList.add( "Demurrage HOLD" );
											}
										}
										
										if( operVo.getDosndChk().toUpperCase().equals("Y") && tDomstVo != null ) {
											if( !searchVo.getTransId().toUpperCase().equals( tDomstVo.getCarNm().toUpperCase() ) ) {
												errCnt++;
												msgList.add( "D/O운송사 비일치");
											}
										}
									}
								}
							} else if( searchVo.getFmCd().toUpperCase().equals("M") && operVo.getOndock().toUpperCase().equals("Y") && operVo.getDoChk().toUpperCase().equals("Y") ) {
								if( StringUtils.isEmpty( searchVo.getOrderNo() ) && searchVo.getOrderCd().toUpperCase().equals("BO") ) {
									errCnt++;
									msgList.add( "B/O 미수신" );
								} else {
									TVescallVo tVescallVo = tVescallRepository.findAdtDtByBkNoAndOperCd(searchVo);
									if( tVescallVo != null ) {
										if( tVescallVo.getAtdDt().toUpperCase().equals("Y") ) {
											errCnt++;
											msgList.add( "출항모선 BO" );
										} 
									} else {
										errCnt++;
										msgList.add( "B/O모선정보없음" );
									}
									
									TBklstVo tBklstVo = tBklstRepository.findBkNoByBkNoAndCntrNoAndDupNo( searchVo );
									if( tBklstVo == null ) {
										errCnt++;
										msgList.add( "B/O 미일치" );
									}
								}
							} else if( searchVo.getFmCd().toUpperCase().equals("M") && operVo.getOndock().toUpperCase().equals("N") && operVo.getDoChk().toUpperCase().equals("Y") ) {
								if( StringUtils.isEmpty( searchVo.getOrderNo() ) && searchVo.getOrderCd().toUpperCase().equals("BO") ) {
									errCnt++;
									msgList.add( "B/O 미수신" );
								}
							} // COPINO BANCHUL END
						}
					}
				}
				
				boolean chkGin = false;
				
				tOperVo = tOperRepository.findByOperCdAfterCopinoEnd(searchVo);
				if( tOperVo != null ) {
					if( tOperVo.getMginChk().toUpperCase().equals("Y") ) {
						if( searchVo.getFmCd().toUpperCase().equals("M") && searchVo.getCtpCd().equals("1") && searchVo.getIoCd().toUpperCase().equals("IN") ) {
							
							TCntrVo tCntrAfterCopino = tCntrRepository.afterCopinoFindCurStatAndFmCdByCntrNoAndDupNo( searchVo );
							if( tCntrAfterCopino == null ) {
								chkGin = false;
							} else {
								if( tCntrAfterCopino.getCurStat().toUpperCase().equals("I") && tCntrAfterCopino.getFmCd().toUpperCase().equals("F") ) {
									chkGin = true;
								} else {
									chkGin = false;
								}
							}
							
							if( !chkGin ) {
								TCopinoChkinVo tCopinoChkinVo = tCopinoChkinRepository.findByCntrNoAndOperCd( searchVo );
								if( tCopinoChkinVo == null ) {
									TCopinoRjctVo rjctVo = tCopinoRjctRepository.findByCntrNoAndOperCdAndRejectOperAndRejectFirstAndRejectIn( searchVo );
									if( rjctVo == null ) {
										errCnt++;
										msgList.add( "재유통반입불가" );
									}
								}
							}
						}
					}
					
					if( tOperVo.getMginallChk().toUpperCase().equals("Y") ) {
						if( searchVo.getFmCd().toUpperCase().equals("M") && searchVo.getIoCd().toUpperCase().equals("IN") ) {
							TCopinoRjctVo rjctVo = tCopinoRjctRepository.findByCntrNoAndOperCdAndRejectOperAndRejectFirstAndRejectIn( searchVo );
							if( rjctVo == null ) {
								errCnt++;
								msgList.add( "EMPTY반입통제" );
							}
						}
					}
					
					if( tOperVo.getIginChk().toUpperCase().equals("Y") ) {
						if( searchVo.getFmCd().toUpperCase().equals("M") && searchVo.getCtpCd().equals("1") && searchVo.getIoCd().toUpperCase().equals("IN") ) {
							TCntrVo ignChkVo = tCntrRepository.findCurStatAndRtnCdAndGoutDtWithTCntrByCntrNoAndDupNo( searchVo );
							if( ignChkVo != null ) {
								if( ( ignChkVo.getCurStat().toUpperCase().equals("X") && StringUtils.isEmpty( ignChkVo.getRtnCd() ) ) || ignChkVo.getCurStat().toUpperCase().equals("S") || ignChkVo.getCurStat().toUpperCase().equals("T") ) {
									errCnt++;
									msgList.add( "반입금지(Detention)" );
								} else {
									if( Integer.valueOf( ignChkVo.getGoutDt() ) > Integer.valueOf( ignChkVo.getIlsu() ) ) {
										errCnt++;
										msgList.add( "반입금지(Detention)" );
									}
								}
							}
						}
					}
					
					if( searchVo.getIoCd().toUpperCase().equals("IN") && tOperVo.getBkChk().toUpperCase().equals("Y") ) {
						if( StringUtils.isEmpty( searchVo.getOrderNo() ) && StringUtils.isEmpty( searchVo.getCtpCd() ) ) {
							errCnt++;
							msgList.add( "BO 미수신" );
						}
					}
					
					if( tOperVo.getMvChk().toUpperCase().equals("Y") ) {
						TMailVo tMailVo = tMailRepository.findByOperCdAndMailIdAndLineCd(searchVo);
						if( tMailVo != null ) {
							if( searchVo.getCmcNo().length() != 3 ) {
								errCnt++;
								msgList.add( "조작코드 미수신" );
							}
						}
					}
					
					if( tOperVo.getLocChk().toUpperCase().equals("Y") ) {
						if( searchVo.getIoCd().toUpperCase().equals("OUT") && StringUtils.isEmpty( searchVo.getPodCd() ) ) {
							errCnt++;
							msgList.add( "도착지 미수신" );
						} else if( searchVo.getIoCd().toUpperCase().equals("In") && StringUtils.isEmpty( searchVo.getPosCd() ) ) {
							errCnt++;
							msgList.add( "출발지 미수신" );
						}
					}
				}
				
				TCntrVo bondIdVo = null;
				
				if( cCurStat.substring(1,2).toUpperCase().equals("W") || cCurStat.substring(1,2).toUpperCase().equals("T") ) {
					bondIdVo = tCntrRepository.findBondIdFromSlcTCntrVyCntrNoAndDupNo( searchVo );
				} else if( cCurStat.substring(1,2).toUpperCase().equals("Y") ) {
					bondIdVo = tCntrRepository.findBondIdByCntrNoAndDupNo( searchVo );
				}
				
				if( bondIdVo != null ) {
					cBondId = bondIdVo.getBondId();
				}
				
				
				if( searchVo.getIoCd().toUpperCase().equals("OUT") && ( cBondId.toUpperCase().equals("C") || cBondId.toUpperCase().equals("B") ) ) {
					TBsiotSubVo tBsiotSubVo = null;
					if( cCurStat.substring(1,2).toUpperCase().equals("W") || cCurStat.substring(1,2).toUpperCase().equals("T") ) {
						tBsiotSubVo = tBsiotSubRepository.findCntrNoFromSlcBycntrNoAndDupNo( searchVo );
					} else {
						tBsiotSubVo = tBsiotSubRepository.findCntrNoByCntrNoAndDupNo( searchVo );
					}
					
					if( tBsiotSubVo == null ) {
						errCnt++;
						msgList.add( "세관 HOLD" );
					} else if( StringUtils.isEmpty( tBsiotSubVo.getCntrNo() ) ) {
						errCnt++;
						msgList.add( "세관 HOLD" );
					}
				}
				
				if( searchVo.getIoCd().toUpperCase().equals("OUT") && searchVo.getFmCd().toUpperCase().equals("M") ) {
					TBkdtlVo tBkdtlVo = tBkdtlRepository.findByBkNoAndOperCdAndBkCd( searchVo );
					if( tBkdtlVo != null ) {
						TRfcargoVo tRfcargoVo = tRfcargoRepository.findByCntrNoAndDupNoAndPloutDt( searchVo );
						if( tRfcargoVo == null ) {
							errCnt++;
							msgList.add( "PTI진행중(센터확인요망)" );
						}
					}
				}
				
				if( searchVo.getIoCd().toUpperCase().equals("IN") ) {
					if( !searchVo.getTyCd().substring(0,1).equals("3") && !searchVo.getTyCd().substring(0,1).equals("4") ) {
						int cnt = tGateRepository.findCountByCuCdAndCntrNo( searchVo );
						if( cnt != 0 ) {
							errCnt++;
							msgList.add( "냉동TYPE 오류(GATE)" );
						}
					}
				}
			}
			
			if( searchVo.getIoCd().toUpperCase().equals("OUT") && searchVo.getFmCd().toUpperCase().equals("M") ) {
				TOperVo operVo = tOperRepository.findByOperCdAndBkChk( searchVo );
				
				if( operVo != null ) {
					TBkmstVo tBkmstVo = tBkmstRepository.findByOperCdAndBkNoAndTransCd( searchVo );
					
					if( tBkmstVo != null ) {
						errCnt++;
						msgList.add( "부킹HOLD" );
					}
				}
			}
			
			if( searchVo.getIoCd().toUpperCase().equals( "IN" ) ) {
				if( !CLLCheck && searchVo.getCtpCd().equals("1") ) {
					int cnt = tHoldngRepository.findCountByCntrNoAndOperCdAndHoldCdAndIoCdAndRelsDt( searchVo );
					
					if( cnt != 0 ) {
						cHoldCd = "H";
						errCnt++;
						msgList.add( "반입금지(선사요청)" );
					}
				}
			}
			
			if( !StringUtils.isEmpty( searchVo.getOverW() ) ) {
				overSw = String.valueOf( Float.valueOf( searchVo.getOverW() ) / 2 );
			}
			if( !StringUtils.isEmpty( searchVo.getOverW() ) ) {
				overPw = String.valueOf( Float.valueOf( searchVo.getOverW() ) / 2 );
			}
			if( !StringUtils.isEmpty( searchVo.getOverW() ) ) {
				overFl = String.valueOf( Float.valueOf( searchVo.getOverL() ) / 2 );
			}
			if( !StringUtils.isEmpty( searchVo.getOverW() ) ) {
				overBl = String.valueOf( Float.valueOf( searchVo.getOverL() ) / 2 );
			}
			
			
			if( searchVo.getIoCd().toUpperCase().equals("IN") && errCnt == 0 ) {
				if( ( !StringUtils.isEmpty( searchVo.getOverH() ) 
						|| !StringUtils.isEmpty( overPw ) 
						|| !StringUtils.isEmpty( overSw ) 
						|| !StringUtils.isEmpty( overFl ) 
						|| !StringUtils.isEmpty( overBl )
						|| !StringUtils.isEmpty( searchVo.getTempId() ) ) && !searchVo.getTempId().equals("D") ) {
					cYpmasterErr = "3";
				} else {
					if( searchVo.getSzCd().substring(0,1).equals("2") || searchVo.getSzCd().substring(0,1).equals("3") ) {
						cSzCd = "20";
					} else if( searchVo.getSzCd().substring(0,1).equals("4") && ( searchVo.getSzCd().substring(1,2).equals("4") || searchVo.getSzCd().substring(1,2).equals("5") ) ) {
						cSzCd = "45";
					} else if( searchVo.getSzCd().substring(0,1).equals("4") ) {
						cSzCd = "40";
					} else if( searchVo.getSzCd().substring(0,1).equals("9") || searchVo.getSzCd().substring(0,1).equals("L") ) {
						cSzCd = "95";
					}
					
					searchVo.setCSzCd( cSzCd );
					
					TYpmasterVo tYpmasterVo = tYpmasterRepository.findPlanAmtAndStackAmtAndCopinoAmtByShipCdAndCallYyAndCallNoAndPodAndSzCdAndFmCd( searchVo );
					if( tYpmasterVo == null ) {
						cYpmasterErr = "2";
					} else {
						if( tYpmasterVo.getPlanAmt() > ( tYpmasterVo.getStackAmt() + tYpmasterVo.getCopinoAmt() ) ) {
							cYpmasterErr = "";
						} else {
							cYpmasterErr = "1";
						}
					}
				}
			}
			
			if( errCnt > 0 ) {
				cYpmasterErr = "*";
			}
			
			TGateVo tGateVo = tGateRepository.findDupChkAndErrCdAndWkCdByTruckerCdAndCarNoAndCntrNoAndUpdCd( searchVo );
			
			if( tGateVo == null ) {
				// insert
				TGateVo insertVo = new TGateVo();
				
				String iLocCd = "";
				String iPod = "";
				String iPol = "";
				String iDest = "";
				String mcTs2Id = "";
				String mcCyCd = "";
				String mcChassNo = "";
				String mcFactorLoc = "";
				String orderNo1 = "";
				String orderNo2 = "";
				String estDate = StringUtils.replaceAll( searchVo.getEstDay(), "-", "") + "00";
				String tempId = "";
				
				if( searchVo.getOperCd().toUpperCase().equals("YML") ) {
					iLocCd = searchVo.getLocCd();
				} else {
					iLocCd = searchVo.getPosCd();
				}
				iPod = searchVo.getPodCd();
				iDest = searchVo.getPolCd();
				
				if( searchVo.getIoCd().toUpperCase().equals("IN") ) {
					iPol = searchVo.getPolCd();
				}
				
				if( searchVo.getOrderCd().toUpperCase().equals("DO") ) {
					orderNo1 = searchVo.getOrderNo();
				} else if( searchVo.getOrderCd().toUpperCase().equals("BO") ) {
					orderNo2 = searchVo.getOrderNo();
				}
				
				if( searchVo.getTempId().toUpperCase().equals("D") ) {
					tempId = "DRY";
				} else if( searchVo.getTempId().toUpperCase().equals("C") ) {
					tempId = "CEL";
				} else if( searchVo.getTempId().toUpperCase().equals("F") ) {
					tempId = "FAH";
				}
				
				insertVo.setTruckerCd( searchVo.getCarNo().substring(0,4) );
				insertVo.setCarNo( searchVo.getCarNo().substring(4,8) );
				insertVo.setCntrNo( searchVo.getCntrNo() );
				insertVo.setIoCd( searchVo.getIoCd().substring(0,1) );
				insertVo.setMvCd( searchVo.getCmcNo() );
				insertVo.setLocCd( iLocCd );
				insertVo.setShipCd( searchVo.getShipCd() );
				insertVo.setCallYy( Integer.valueOf( searchVo.getCallYy() ) );
				insertVo.setCallNo( searchVo.getCallNo() );
				insertVo.setOperCd( searchVo.getOperCd() );
				insertVo.setSzCd( searchVo.getSzCd() );
				insertVo.setTyCd( searchVo.getTyCd() );
				insertVo.setFmCd( searchVo.getFmCd() );
				insertVo.setTemp( searchVo.getTempCd() );
				insertVo.setTempId( searchVo.getTempId() );
				insertVo.setWeight( Float.valueOf( searchVo.getWeight() ) );
				insertVo.setImdg( searchVo.getDgCode() );
				insertVo.setUnno( "" );
				insertVo.setOvH( Integer.valueOf( searchVo.getOverH() ) );
				insertVo.setOvWp( Integer.valueOf( overPw ) );
				insertVo.setOvWs( Integer.valueOf( overSw ) );
				insertVo.setOvLf( Integer.valueOf( overFl ) );
				insertVo.setOvLb( Integer.valueOf( overBl ) );
				insertVo.setPod( iPod );
				insertVo.setPol( iPol );
				insertVo.setDest( iDest );
				insertVo.setSealNo( searchVo.getSealNo() );
				insertVo.setBondId( searchVo.getBondCd() );
				insertVo.setTsId( mcTs2Id );
				insertVo.setTranTp( "3" );
				insertVo.setFlocation( mcCyCd );
				insertVo.setDoNo( orderNo1 );
				insertVo.setBkNo( orderNo2 );
				insertVo.setBlNo( orderNo1 );
				insertVo.setDlvOk( cDatalvOk );
				insertVo.setErrCd( cYpmasterErr );
				insertVo.setChassNo( mcChassNo );
				insertVo.setDupChk( cDataupChk );
				insertVo.setSndId( searchVo.getTransId() );
				insertVo.setHoldCd( cHoldCd );
				insertVo.setIoUdest( mcFactorLoc );
				insertVo.setOwnerNm( searchVo.getCustomerNm() );
				insertVo.setEstDt( estDate );
				String errDes = "";
				for( int l=0; l<msgList.size(); l++ ) {
					errDes += msgList.get(l);
				}
				insertVo.setErrDes( errDes );
				insertVo.setOnLine( "W" );
				insertVo.setIxCd( searchVo.getIxCd() );
				insertVo.setCyCd( searchVo.getXCyCd() );
				
				int insertResult = tGateRepository.copinoGateInInsert( insertVo );
				
				if( searchVo.getIoCd().toUpperCase().equals( "OUT" ) && errCnt > 0 ) {
					TCntrVo updateVo = new TCntrVo();
					updateVo.setCopinoYn( "Y" );
					updateVo.setCntrNo( searchVo.getCntrNo() );
					updateVo.setShipCd( searchVo.getShipCd() );
					updateVo.setCallYy( Long.valueOf( searchVo.getCallYy() ) );
					updateVo.setCallNo( Long.valueOf( searchVo.getCallNo() ) );
					int updateResult = tCntrRepository.updateCopinoYnByCntrNoAndShipCdAndCallYyAndCallNo( updateVo );
					
					TMvRemashVo tMvRemashVo = new TMvRemashVo();
					tMvRemashVo.setCntrNo( searchVo.getCntrNo() );
					tMvRemashVo.setDupNo( String.valueOf( pcDupno ) );
					int deleteResult = tMvRemashRepository.deleteByCntrNoAndDupNoAndOkDtIsNull( tMvRemashVo );
				}
				
				resultMap.put( "code", 200 );
				resultMap.put( "msg", "정상처리 되었습니다." );
			} else {
				// update
				if( StringUtils.isEmpty( tGateVo.getWkCd() ) ) {
					String cWdupChk = "";
					
					if( tGateVo.getDupChk().equals("*") ) {
						cWdupChk = "*";
					}
					
					String iLocCd = "";
					String iPod = "";
					String iPol = "";
					String iDest = "";
					String mcTs2Id = "";
					String mcCyCd = "";
					String mcChassNo = "";
					String mcFactorLoc = "";
					String orderNo1 = "";
					String orderNo2 = "";
					String estDate = StringUtils.replaceAll( searchVo.getEstDay(), "-", "") + "00";
					String tempId = "";
					
					if( searchVo.getOperCd().toUpperCase().equals("YML") ) {
						iLocCd = searchVo.getLocCd();
					} else {
						iLocCd = searchVo.getPosCd();
					}
					iPod = searchVo.getPodCd();
					iDest = searchVo.getPolCd();
					
					if( searchVo.getIoCd().toUpperCase().equals("IN") ) {
						iPol = searchVo.getPolCd();
					}
					
					if( searchVo.getOrderCd().toUpperCase().equals("DO") ) {
						orderNo1 = searchVo.getOrderNo();
					} else if( searchVo.getOrderCd().toUpperCase().equals("BO") ) {
						orderNo2 = searchVo.getOrderNo();
					}
					
					if( searchVo.getTempId().toUpperCase().equals("D") ) {
						tempId = "DRY";
					} else if( searchVo.getTempId().toUpperCase().equals("C") ) {
						tempId = "CEL";
					} else if( searchVo.getTempId().toUpperCase().equals("F") ) {
						tempId = "FAH";
					}
					
					TGateVo updateVo = new TGateVo();
					
					updateVo.setTruckerCd( searchVo.getCarNo().substring(0,4) );
					updateVo.setCarNo( searchVo.getCarNo().substring(4,8) );
					updateVo.setCntrNo( searchVo.getCntrNo() );
					updateVo.setIoCd( searchVo.getIoCd().substring(0,1) );
					updateVo.setMvCd( searchVo.getCmcNo() );
					updateVo.setLocCd( iLocCd );
					updateVo.setShipCd( searchVo.getShipCd() );
					updateVo.setCallYy( Integer.valueOf( searchVo.getCallYy() ) );
					updateVo.setCallNo( searchVo.getCallNo() );
					updateVo.setOperCd( searchVo.getOperCd() );
					updateVo.setSzCd( searchVo.getSzCd() );
					updateVo.setTyCd( searchVo.getTyCd() );
					updateVo.setFmCd( searchVo.getFmCd() );
					updateVo.setTemp( searchVo.getTempCd() );
					updateVo.setTempId( searchVo.getTempId() );
					updateVo.setWeight( Float.valueOf( searchVo.getWeight() ) );
					updateVo.setImdg( searchVo.getDgCode() );
					updateVo.setUnno( "" );
					updateVo.setOvH( Integer.valueOf( searchVo.getOverH() ) );
					updateVo.setOvWp( Integer.valueOf( overPw ) );
					updateVo.setOvWs( Integer.valueOf( overSw ) );
					updateVo.setOvLf( Integer.valueOf( overFl ) );
					updateVo.setOvLb( Integer.valueOf( overBl ) );
					updateVo.setPod( iPod );
					updateVo.setPol( iPol );
					updateVo.setDest( iDest );
					updateVo.setSealNo( searchVo.getSealNo() );
					updateVo.setBondId( searchVo.getBondCd() );
					updateVo.setTsId( mcTs2Id );
					updateVo.setTranTp( "3" );
					updateVo.setFlocation( mcCyCd );
					updateVo.setDoNo( orderNo1 );
					updateVo.setBkNo( orderNo2 );
					updateVo.setBlNo( orderNo1 );
					updateVo.setDlvOk( cDatalvOk );
					updateVo.setErrCd( cYpmasterErr );
					updateVo.setChassNo( mcChassNo );
					updateVo.setDupChk( cWdupChk );
					updateVo.setSndId( searchVo.getTransId() );
					updateVo.setHoldCd( cHoldCd );
					updateVo.setIoUdest( mcFactorLoc );
					updateVo.setOwnerNm( searchVo.getCustomerNm() );
					updateVo.setEstDt( estDate );
					String errDes = "";
					for( int l=0; l<msgList.size(); l++ ) {
						errDes += msgList.get(l);
					}
					updateVo.setErrDes( errDes );
					updateVo.setOnLine( "W" );
					updateVo.setIxCd( searchVo.getIxCd() );
					updateVo.setCyCd( searchVo.getXCyCd() );
					
					int updateResult = tGateRepository.updateCopinoInIns( updateVo );
					
					if( searchVo.getIoCd().toUpperCase().equals("OUT") && errCnt > 0 ) {
						TCntrVo updateCntrVo = new TCntrVo();
						updateCntrVo.setCopinoYn( "Y" );
						updateCntrVo.setCntrNo( searchVo.getCntrNo() );
						updateCntrVo.setShipCd( searchVo.getShipCd() );
						updateCntrVo.setCallYy( Long.valueOf( searchVo.getCallYy() ) );
						updateCntrVo.setCallNo( Long.valueOf( searchVo.getCallNo() ) );
						int updateCntrResult = tCntrRepository.updateCopinoYnByCntrNoAndShipCdAndCallYyAndCallNo( updateCntrVo );
						
						TMvRemashVo tMvRemashVo = new TMvRemashVo();
						tMvRemashVo.setCntrNo( searchVo.getCntrNo() );
						tMvRemashVo.setDupNo( String.valueOf( pcDupno ) );
						int deleteResult = tMvRemashRepository.deleteByCntrNoAndDupNoAndOkDtIsNull( tMvRemashVo );
					}
					
					resultMap.put( "code", 200 );
					resultMap.put( "msg", "정상처리 되었습니다." );
				} else {
					// insert
					TGateVo insertVo = new TGateVo();
					
					String iLocCd = "";
					String iPod = "";
					String iPol = "";
					String iDest = "";
					String mcTs2Id = "";
					String mcCyCd = "";
					String mcChassNo = "";
					String mcFactorLoc = "";
					String orderNo1 = "";
					String orderNo2 = "";
					String estDate = StringUtils.replaceAll( searchVo.getEstDay(), "-", "") + "00";
					String tempId = "";
					
					if( searchVo.getOperCd().toUpperCase().equals("YML") ) {
						iLocCd = searchVo.getLocCd();
					} else {
						iLocCd = searchVo.getPosCd();
					}
					iPod = searchVo.getPodCd();
					iDest = searchVo.getPolCd();
					
					if( searchVo.getIoCd().toUpperCase().equals("IN") ) {
						iPol = searchVo.getPolCd();
					}
					
					if( searchVo.getOrderCd().toUpperCase().equals("DO") ) {
						orderNo1 = searchVo.getOrderNo();
					} else if( searchVo.getOrderCd().toUpperCase().equals("BO") ) {
						orderNo2 = searchVo.getOrderNo();
					}
					
					if( searchVo.getTempId().toUpperCase().equals("D") ) {
						tempId = "DRY";
					} else if( searchVo.getTempId().toUpperCase().equals("C") ) {
						tempId = "CEL";
					} else if( searchVo.getTempId().toUpperCase().equals("F") ) {
						tempId = "FAH";
					}
					
					insertVo.setTruckerCd( searchVo.getCarNo().substring(0,4) );
					insertVo.setCarNo( searchVo.getCarNo().substring(4,8) );
					insertVo.setCntrNo( searchVo.getCntrNo() );
					insertVo.setIoCd( searchVo.getIoCd().substring(0,1) );
					insertVo.setMvCd( searchVo.getCmcNo() );
					insertVo.setLocCd( iLocCd );
					insertVo.setShipCd( searchVo.getShipCd() );
					insertVo.setCallYy( Integer.valueOf( searchVo.getCallYy() ) );
					insertVo.setCallNo( searchVo.getCallNo() );
					insertVo.setOperCd( searchVo.getOperCd() );
					insertVo.setSzCd( searchVo.getSzCd() );
					insertVo.setTyCd( searchVo.getTyCd() );
					insertVo.setFmCd( searchVo.getFmCd() );
					insertVo.setTemp( searchVo.getTempCd() );
					insertVo.setTempId( searchVo.getTempId() );
					insertVo.setWeight( Float.valueOf( searchVo.getWeight() ) );
					insertVo.setImdg( searchVo.getDgCode() );
					insertVo.setUnno( "" );
					insertVo.setOvH( Integer.valueOf( searchVo.getOverH() ) );
					insertVo.setOvWp( Integer.valueOf( overPw ) );
					insertVo.setOvWs( Integer.valueOf( overSw ) );
					insertVo.setOvLf( Integer.valueOf( overFl ) );
					insertVo.setOvLb( Integer.valueOf( overBl ) );
					insertVo.setPod( iPod );
					insertVo.setPol( iPol );
					insertVo.setDest( iDest );
					insertVo.setSealNo( searchVo.getSealNo() );
					insertVo.setBondId( searchVo.getBondCd() );
					insertVo.setTsId( mcTs2Id );
					insertVo.setTranTp( "3" );
					insertVo.setFlocation( mcCyCd );
					insertVo.setDoNo( orderNo1 );
					insertVo.setBkNo( orderNo2 );
					insertVo.setBlNo( orderNo1 );
					insertVo.setDlvOk( cDatalvOk );
					insertVo.setErrCd( cYpmasterErr );
					insertVo.setChassNo( mcChassNo );
					insertVo.setDupChk( cDataupChk );
					insertVo.setSndId( searchVo.getTransId() );
					insertVo.setHoldCd( cHoldCd );
					insertVo.setIoUdest( mcFactorLoc );
					insertVo.setOwnerNm( searchVo.getCustomerNm() );
					insertVo.setEstDt( estDate );
					String errDes = "";
					for( int l=0; l<msgList.size(); l++ ) {
						errDes += msgList.get(l);
					}
					insertVo.setErrDes( errDes );
					insertVo.setOnLine( "W" );
					insertVo.setIxCd( searchVo.getIxCd() );
					insertVo.setCyCd( searchVo.getXCyCd() );
					
					int insertResult = tGateRepository.copinoGateInInsert( insertVo );
					
					if( searchVo.getIoCd().toUpperCase().equals( "OUT" ) && errCnt > 0 ) {
						TCntrVo updateVo = new TCntrVo();
						updateVo.setCopinoYn( "Y" );
						updateVo.setCntrNo( searchVo.getCntrNo() );
						updateVo.setShipCd( searchVo.getShipCd() );
						updateVo.setCallYy( Long.valueOf( searchVo.getCallYy() ) );
						updateVo.setCallNo( Long.valueOf( searchVo.getCallNo() ) );
						int updateResult = tCntrRepository.updateCopinoYnByCntrNoAndShipCdAndCallYyAndCallNo( updateVo );
					}
					
					resultMap.put( "code", 200 );
					resultMap.put( "msg", "정상처리 되었습니다." );
				}
			}
		}
		return resultMap;
	}

	public List<TCntrVo> cntrInfoDetailsDataList(SearchVo searchVo) {
		List<TCntrVo> list = tCntrRepository.cntrInfoDetailsDataList(searchVo);
		
		for( int i=0; i<list.size(); i++ ) {
			String iTruckerCd = "", iCarNo = "", iSndId = "";
			String oTruckerCd = "", oCarNo = "", oSndId = "";
			
			SearchVo search = new SearchVo();
			search.setCntrNo( searchVo.getSearch() );
			search.setDupNo( list.get(i).getDupNo() );
			List<TMovelogVo> moveLogList = tMovelogRepository.findTruckerCdAndCarNoAndSndIdANdIxIdByCntrNoAndDupNo( search );
			for( int j=0; j<moveLogList.size(); j++ ) {
				if( moveLogList.get(j).getIxCd().toUpperCase().equals("I") ) {
					iTruckerCd = StringUtils.isEmpty( moveLogList.get(j).getTruckerCd() ) ? "" : moveLogList.get(j).getTruckerCd();
					iCarNo = StringUtils.isEmpty( moveLogList.get(j).getCarNo() ) ? "" : moveLogList.get(j).getCarNo();
					iSndId = StringUtils.isEmpty( moveLogList.get(j).getSndId() ) ? "" : moveLogList.get(j).getSndId();
					
				} else if( moveLogList.get(j).getIxCd().toUpperCase().equals("O") ) {
					oTruckerCd = StringUtils.isEmpty( moveLogList.get(j).getTruckerCd() ) ? "" : moveLogList.get(j).getTruckerCd();
					oCarNo = StringUtils.isEmpty( moveLogList.get(j).getCarNo() ) ? "" : moveLogList.get(j).getCarNo();
					oSndId = StringUtils.isEmpty( moveLogList.get(j).getSndId() ) ? "" : moveLogList.get(j).getSndId();
					
				}
			}
			
			list.get(i).setITruckerCd( iTruckerCd );
			list.get(i).setICarNo( iCarNo );
			list.get(i).setISndId( iSndId );
			
			list.get(i).setOTruckerCd( oTruckerCd );
			list.get(i).setOCarNo(oCarNo);
			list.get(i).setOSndId(oSndId);
			
			TBklstVo customerParam = new TBklstVo();
			customerParam.setTruckerCd( iSndId );
			TEdicustomerVo iCustomer = tEdicustomerRepository.findCstNmByDocuAndTerminalIdAndCstId( customerParam );
			customerParam.setTruckerCd( oSndId );
			TEdicustomerVo oCustomer = tEdicustomerRepository.findCstNmByDocuAndTerminalIdAndCstId( customerParam );
			
			if( iCustomer != null ) {
				list.get(i).setICustomer( iCustomer.getCstNm() == null ? "" : iCustomer.getCstNm() );
			} else {
				list.get(i).setICustomer( "-" );
			}
			
			if( oCustomer != null ) {
				list.get(i).setOCustomer( oCustomer.getCstNm() == null ? "" : oCustomer.getCstNm() );
			} else {
				list.get(i).setOCustomer( "-" );
			}
			
			String TempCar = null;
			String TruckerName = null;
			
			if( StringUtils.isEmpty( iTruckerCd ) && StringUtils.isEmpty( oTruckerCd ) ) {
				SearchVo searchCar = new SearchVo();
				TCarVo tCarVo = null;
				
				
				if( !StringUtils.isEmpty( list.get(i).getTruckerCd() ) ) {
					searchCar.setTruckerCd(list.get(i).getTruckerCd() );
					searchCar.setCarNo( list.get(i).getCarNo() );
					
					
					tCarVo = tCarRepository.findCarNmAndSndIdWhereTruckerCdAndCarNo( searchCar );
					
					if( tCarVo != null ) {
						TempCar = tCarVo.getSndId();
						
						SearchVo truckerSearch = new SearchVo();
						truckerSearch.setTruckerCd( list.get(i).getTruckerCd().substring(0,4) );
						truckerSearch.setSndId( TempCar );
						
						TTruckerVo tTruckerVo = tTruckerRepository.findCstNmByTruckerCdAndSndId( truckerSearch );
						
						if( tTruckerVo != null ) {
							TruckerName = tTruckerVo.getCstNm();
							list.get(i).setTruckerNm( tTruckerVo.getCstNm() );
						} else {
							list.get(i).setTruckerNm( "" );
						}
					}
				}
				
				
				
				if( i > 0 ) {
					
					if( list.get(i-1).getCurStat().toUpperCase().equals("MD") && !StringUtils.isEmpty( list.get(i).getGcLdt() ) && list.get(i-1).getGoutDt().equals(list.get(i).getGinDt() ) ) {
						/////////////////
						list.get(i).setCarrier("");
						list.get(i).setTruckerName( "" );
					} else {
						
						list.get(i).setTruckerCd( list.get(i).getTruckerCd() == null ? "" : list.get(i).getTruckerCd() );
						list.get(i).setCarNo( list.get(i).getCarNo() == null ? "" : list.get(i).getCarNo() );
						
						list.get(i).setCarrier( list.get(i).getTruckerCd() + "-" + list.get(i).getCarNo() );
						list.get(i).setTruckerName( list.get(i).getTruckerNm() );
					}
				} else {
					
					list.get(i).setTruckerCd( StringUtils.isEmpty( iTruckerCd ) ? "" : iTruckerCd );
					list.get(i).setCarNo( StringUtils.isEmpty( iCarNo ) ? "" : iCarNo );
					
					list.get(i).setCarrier( ( StringUtils.isEmpty( iTruckerCd ) ? "" : iTruckerCd ) + "-" + ( StringUtils.isEmpty( iCarNo ) ? "" : iCarNo ) );
					list.get(i).setTruckerName( list.get(i).getTruckerNm() );
				}
			} else {
				if( i > 0 ) {
					
					if( list.get(i-1).getCurStat().toUpperCase().equals("MD") && !StringUtils.isEmpty( list.get(i).getGcLdt() ) && list.get(i-1).getGoutDt().equals(list.get(i).getGinDt() ) ) {
						list.get(i).setCarrier("");
					} else {
						list.get(i).setCarrier( "<font color='#1e90ff'>" +  iTruckerCd + "-" + iCarNo + "</font><br><font color='#ff6600'>" + oTruckerCd + "-" + oCarNo + "</font>" );
						list.get(i).setTruckerName( "<font color='#1e90ff'>" +  list.get(i).getICustomer() + "</font><br><font color='#ff6600'>" + list.get(i).getOCustomer() + "</font>" );
					}
				} else {
					list.get(i).setCarrier( "<font color='#1e90ff'>" + iTruckerCd + "-" + iCarNo + "</font><br><font color='#ff6600'>" + oTruckerCd + "-" + oCarNo + "</font>" );
					list.get(i).setTruckerName( "<font color='#1e90ff'>" + list.get(i).getICustomer() + "</font><br><font color='#ff6600'>" + list.get(i).getOCustomer() + "</font>" );
				}
			}
			
			
		}
		return list;
	}

	public TBkmstVo onDockBookingDetailBkMst(SearchVo searchVo) {
		return tBkmstRepository.findOneByOperCdAndBkNoBkMst(searchVo);
	}

	public TBkdtlVo onDockBookingDetailBkDtl(SearchVo searchVo) {
		return tBkdtlRepository.findSzCdAndTyGrpCdAndBkEamtByOperCdANdBkNo( searchVo );
	}

	public TCustomerVo smsCustomer(SearchVo searchVo) {
		TCustomerVo tCustomerVo = new TCustomerVo();
		tCustomerVo.setCstNo( searchVo.getReqId() );
		return tCustomerRepository.findOne( tCustomerVo );
	}

	public List<Map<String, Object>> smsDetailsList(SearchVo searchVo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> cntrArr = new ArrayList<String>();
		if( searchVo.getCunBl().toUpperCase().equals("CNTRNO") ) {
			for( int i=0; i<searchVo.getCntrNo().split(",").length; i++ ) {
				cntrArr.add( searchVo.getCntrNo().split(",")[i] );
			}
		} else if( searchVo.getCunBl().toUpperCase().equals("BLNO") ) {
			TBsiotSubVo tBsiotSubVo = tBsiotSubRepository.findCntrNoWmConcatByCargoNo( searchVo );
			if( tBsiotSubVo != null && !tBsiotSubVo.getCntrNo().equals("0") ) {
				for( int i=0; i<tBsiotSubVo.getCntrNo().split(",").length; i++ ) {
					cntrArr.add( tBsiotSubVo.getCntrNo().split(",")[i] );
				}
			}
		}
		
		
		if( cntrArr.size() > 0 ) {
			searchVo.setCntrList( cntrArr );
			
			List<Map<String, Object>> tempList = new ArrayList<Map<String,Object>>();
			
			if( searchVo.getBanCi().toUpperCase().equals("C") ) {
				tempList = tCntrRepository.findCntrNoAndDupNoAndFnGetOverpriceExNewByCntrNoAndDupNo( searchVo );
			} else if( searchVo.getBanCi().toUpperCase().equals("I") ) {
				tempList = tInLiftCashCntrRepository.findCntrNoAndDupNoAndFnGetOverpriceExNewByCntrNoAndDupNo( searchVo );
			}
			
			for( int i=0; i<tempList.size(); i++ ) {
				Map<String, Object> tempMap = new HashMap<String, Object>();
				
				tempMap.put( "cntrNo", tempList.get(i).get("CNTR_NO") );
				tempMap.put( "dupNo", tempList.get(i).get("DUP_NO") );
				String[] overSplit = tempList.get(i).get("CASH").toString().split(",");
				
				if( searchVo.getBanCi().toUpperCase().equals("C") ) {
					tempMap.put( "overDaysDay", Double.valueOf( overSplit[0] ) );
					tempMap.put( "overDaysMoney", Double.valueOf( overSplit[1] ) );
					tempMap.put( "refferDay", Double.valueOf( overSplit[2] ) );
					tempMap.put( "refferMoney", Double.valueOf( overSplit[3] ) );
					tempMap.put( "sanQty", Double.valueOf( overSplit[4] ) );
					tempMap.put( "sanMoney", Double.valueOf( overSplit[5] ) );
					tempMap.put( "rtnYn", Double.valueOf( overSplit[6] ) );
					tempMap.put( "rtnMoney", Double.valueOf( overSplit[7] ) );
					tempMap.put( "bondMoney", Double.valueOf( overSplit[8] ) );
					tempMap.put( "xrayMoney", Double.valueOf( overSplit[9] ) );
					tempMap.put( "dgMoney", Double.valueOf( overSplit[10] ) );
				} else if( searchVo.getBanCi().toUpperCase().equals("I") ) {
					tempMap.put( "overDaysDay", 0 );
					tempMap.put( "overDaysMoney", 0 );
					tempMap.put( "refferDay", 0 );
					tempMap.put( "refferMoney", 0 );
					tempMap.put( "sanQty", Double.valueOf( overSplit[0] ) );
					tempMap.put( "sanMoney", Double.valueOf( overSplit[1] ) );
					tempMap.put( "rtnYn", 0 );
					tempMap.put( "rtnMoney", 0 );
					tempMap.put( "bondMoney", 0 );
					tempMap.put( "xrayMoney", 0 );
					tempMap.put( "dgMoney", 0 );
				}
				
				list.add( tempMap );
			}
		}
		return list;
	}

	public String smsDetailVacctNo() {
		return commonRepository.smsDetailVacctNo();
	}

	public List<TCopinoRjctVo> onDockGatePass(SearchVo searchVo) {
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
		return tCopinoRjctRepository.onDockGatePass( searchVo );
	}

	public Map<String, Object> onDockGatePassDeleteData(TCopinoRjctVo tCopinoRjctVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		TSecretVo tSecretVo = new TSecretVo();
		
		tSecretVo.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
		
		tSecretVo = tSecretRepository.findByEmpNo( tSecretVo );
		List<HashMap<String, Object>> sessionOperList = commonRepository.findOperCdListFromSession( tSecretVo );
		
		List<String> operList = new ArrayList<String>();
		
		for( int i=0; i<sessionOperList.size(); i++ ) {
			operList.add( sessionOperList.get(i).get( "OPER_CD" ).toString() );
		}
		
		if( operList.size() > 0 ) {
			tCopinoRjctVo.setOperCd( operList.get(0) );
		}
		
		int result = tCopinoRjctRepository.onDockGatePassDeleteData( tCopinoRjctVo );
		if( result > 0 ) {
			resultMap.put( "code", 200 );
			resultMap.put( "msg", "정상처리 되었습니다." );
		} else {
			resultMap.put( "code",  0 );
			resultMap.put( "msg", "데이터베이스 오류로 인해 처리하지 못하였습니다." );
		}
		
		return resultMap;
	}

	public List<TCntrVo> onDockGatePassList(SearchVo searchVo) {
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
		
		return tCntrRepository.onDockGatePassList( searchVo );
	}

	public Map<String, Object> onDockGatePassSave(TCopinoRjctVo tCopinoRjctVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		TSecretVo tSecretVo = new TSecretVo();
		
		tSecretVo.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
		
		tSecretVo = tSecretRepository.findByEmpNo( tSecretVo );
		List<HashMap<String, Object>> sessionOperList = commonRepository.findOperCdListFromSession( tSecretVo );
		
		List<String> operList = new ArrayList<String>();
		
		for( int i=0; i<sessionOperList.size(); i++ ) {
			operList.add( sessionOperList.get(i).get( "OPER_CD" ).toString() );
		}
		
		if( operList.size() > 0 ) {
			tCopinoRjctVo.setOperCd( operList.get(0) );
		}
		
		TCntrVo cntrParam = new TCntrVo();
		cntrParam.setCntrNo( tCopinoRjctVo.getCntrNo() );
		TCntrVo tCntrVo = tCntrRepository.findDupNoAndCurStatByCntrNo( cntrParam );
		
		String miDupNo = "1";
		if( tCntrVo != null ) {
			miDupNo = tCntrVo.getDupNo();
		}
		
		if( !tCntrVo.getCurStat().toUpperCase().equals("XB") ) {
			miDupNo = String.valueOf( Integer.valueOf( miDupNo ) + 1 );
		}
		
		TCopinoRjctVo tCopinoRjctParam = new TCopinoRjctVo();
		tCopinoRjctParam.setCntrNo( tCopinoRjctVo.getCntrNo() );
		tCopinoRjctParam.setDupNo( Long.valueOf( miDupNo ) );
		
		TCopinoRjctVo tCopinoCheckVo = tCopinoRjctRepository.findOneByCntrNoANdDupNo( tCopinoRjctParam );
		int result = 0;
		if( tCopinoCheckVo == null ) {
			//insert
			tCopinoRjctVo.setDupNo( Long.valueOf( miDupNo ) );
			result = tCopinoRjctRepository.insertOnDockGatePassSave( tCopinoRjctVo );
		} else {
			//update
			result = tCopinoRjctRepository.updateOnDockGatePassSave( tCopinoRjctVo );
		}
		
		if( result > 0 ) {
			resultMap.put("code", 200);
			resultMap.put("msg", "정상처리 되었습니다.");
		} else {
			resultMap.put( "code", 0 );
			resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다." );
		}
		
		return resultMap;
	}

	public Map<String, Object> terminalYardStackWorkStatus() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		List<TTcorderVo> list = tTcorderRepository.terminalYardStackWorkStatus();
		
		int[][] result = new int[28][6];
		
		for( int i=0; i<28; i++ ) {
			for( int j=0; j<6; j++ ) {
				result[i][j] = 0;
			}
		}
		
		int[] total = { 0, 0, 0, 0, 0, 0 };
		
		
		String[] blocks = { "1A", "1B", "1C", "1D", "1E", "1F", "1G", "1H", "1I", "1J", "1K", "1M", "1Q", "1R", "1S", "1X", "1Z", "2J", "2K", "2M", "2Q", "2R", "2S", "2Z", "3K", "3M", "3S", "4M" };
		String[] wkCds = { "U", "L", "R", "D", "B", "E" };
		
		for( int i=0; i<list.size(); i++ ) {
			for( int j=0; j<blocks.length; j++ ) {
				if( list.get(i).getOdBlock().toUpperCase().equals( blocks[j] ) ) {
					for( int k=0; k<wkCds.length; k++ ) {
						if( list.get(i).getWkCd().toUpperCase().equals( wkCds[k] ) ) {
							result[j][k] = list.get(i).getCnt();
							total[k] += list.get(i).getCnt();
						}
					}
				}
			}
		}
		
		TCntrVo paramVo = new TCntrVo();
		paramVo.setYTblockArr( blocks );
		List<TCntrVo> tCntrVo = tCntrRepository.findYtblockAndVanAndTeuByYTblock( paramVo );
		
		int[] sumArr = { 0, 0, 0, 0, 0, 0, 0, 0 };
		
		for( int i=0; i<28; i++ ) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put( "block", blocks[i] );
			tempMap.put( "cnt", result[i] );
			
			tempMap.put( "van", 0 );
			tempMap.put( "teu", 0 );
			for(int j=0; j<tCntrVo.size(); j++ ) {
				if( blocks[i].toUpperCase().equals(tCntrVo.get(j).getYTblock().toUpperCase() ) ) {
					tempMap.put( "van", tCntrVo.get(j).getVan() );
					tempMap.put( "teu", tCntrVo.get(j).getTeu() );
				}
			}
			
			sumArr[0] += result[i][0];
			sumArr[1] += result[i][1];
			sumArr[2] += result[i][2];
			sumArr[3] += result[i][3];
			sumArr[4] += result[i][4];
			sumArr[5] += result[i][5];
			sumArr[6] += (int)tempMap.get("van");
			sumArr[7] += (int)tempMap.get("teu");
			
			resultList.add( tempMap );
		}
		
		resultMap.put( "resultList", resultList );
		resultMap.put( "total", sumArr );
		
		return resultMap;
	}

	public List<TCntrVo> popupCntrDetailTwo(SearchVo searchVo) {
		return tCntrRepository.popupCntrDetailTwo( searchVo );
	}

	public TCntrVo popupCntrDetail(SearchVo searchVo) {
		
		return null;
	}

	public Map<String, Object> onDockInsertOrderSpareCheck(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SearchVo operListAdded = setOperList( searchVo );
		
		int totalSumCntr = tCntrRepository.onDockInsertOrderSpareCheckTotalSum( operListAdded );
		int requestSumCntr = tCntrRepository.onDockInsertOrderSpareCheckRequestSumCntr( operListAdded );
		int deliverySumCntr = tCntrRepository.onDockInsertOrderSpareCheckDeliverySumCntr( operListAdded );
		int appointSumCntr = tCntrRepository.onDockInsertOrderSpareCheckAppointSumCntr( operListAdded );
		
		int temp1 = requestSumCntr - deliverySumCntr;
		int temp2 = 0;
		int spareSumCntr = 0;
		
		if( temp1 < 0 ) {
			temp2 = appointSumCntr + temp1;
		} else {
			temp2 = appointSumCntr - temp1;
		}
		
		if( temp2 < 0 ) {
			spareSumCntr = temp2 + totalSumCntr;
		} else {
			if( temp2 == 0 ) {
				spareSumCntr = temp2 + totalSumCntr;
			} else {
				spareSumCntr = temp2 - totalSumCntr;
			}
		}
		
		resultMap.put( "totalSumCntr", totalSumCntr );
		resultMap.put( "requestSumCntr", requestSumCntr );
		resultMap.put( "deliverySumCntr", deliverySumCntr );
		resultMap.put( "appointSumCntr", appointSumCntr );
		resultMap.put( "temp1", temp1 );
		resultMap.put( "temp2", temp2 );
		resultMap.put( "spareSumCntr", spareSumCntr );
		resultMap.put( "szCd", operListAdded.getSzCd() );
		resultMap.put( "tyCd", operListAdded.getTyCd() );
		resultMap.put( "operList", operListAdded.getOperList() );
		
		return resultMap;
	}

	public Map<String, Object> onDockInsertOrderSave(TBkmstVo tBkmstVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		TVescallVo tVescallVo = tVescallRepository.findAdtDtByShipCdAndCallNoAndCallYy( tBkmstVo );
		if( tVescallVo == null ) {
			resultMap.put( "code", 0 );
			resultMap.put( "msg", "모선정보가 존재하지 않습니다." );
		} else if( !StringUtils.isEmpty( tVescallVo.getAtdDt() ) ) { 
			resultMap.put( "code", 0 );
			resultMap.put( "msg", "이미 출항한 모선입니다." );
		} else {
			SearchVo searchVo = setOperList( new SearchVo() );
			searchVo.setBkNo( tBkmstVo.getBkNo() );
			List<TBkmstVo> bkMstList = tBkmstRepository.findByOperCdAndBkNo( searchVo );
			
			if( bkMstList.size() > 0 ) {
				resultMap.put( "code", 100 );
				resultMap.put( "msg", "기존데이터가 존재합니다. 수정하시겠습니까?" );
			} else {
				int result = tBkmstRepository.insertOrder( tBkmstVo );
				
				if( !StringUtils.isEmpty( tBkmstVo.getSzCd1() ) && !StringUtils.isEmpty( tBkmstVo.getTyCd1() ) && !StringUtils.isEmpty( tBkmstVo.getCnt1() ) ) {
					TBkdtlVo tBkdtlVo = new TBkdtlVo();
					
					tBkdtlVo.setOperCd( tBkmstVo.getOperCd() );
					tBkdtlVo.setBkNo( tBkmstVo.getBkNo() );
					tBkdtlVo.setSzCd( tBkmstVo.getSzCd1() );
					tBkdtlVo.setTyGrpCd( tBkmstVo.getTyCd1() );
					tBkdtlVo.setBkEamt( Long.valueOf( tBkmstVo.getCnt1() ) );
					
					tBkdtlRepository.insertOperCdAndBkNoAndSzCdAndTyGrpCdAndBkEamtAndReqArrDtAndCyCd( tBkdtlVo );
				}
				
				if( !StringUtils.isEmpty( tBkmstVo.getSzCd2() ) && !StringUtils.isEmpty( tBkmstVo.getTyCd2() ) && !StringUtils.isEmpty( tBkmstVo.getCnt2() ) ) {
					TBkdtlVo tBkdtlVo = new TBkdtlVo();
					
					tBkdtlVo.setOperCd( tBkmstVo.getOperCd() );
					tBkdtlVo.setBkNo( tBkmstVo.getBkNo() );
					tBkdtlVo.setSzCd( tBkmstVo.getSzCd2() );
					tBkdtlVo.setTyGrpCd( tBkmstVo.getTyCd2() );
					tBkdtlVo.setBkEamt( Long.valueOf( tBkmstVo.getCnt2() ) );
					
					tBkdtlRepository.insertOperCdAndBkNoAndSzCdAndTyGrpCdAndBkEamtAndReqArrDtAndCyCd( tBkdtlVo );
				}
				
				if( !StringUtils.isEmpty( tBkmstVo.getSzCd3() ) && !StringUtils.isEmpty( tBkmstVo.getTyCd3() ) && !StringUtils.isEmpty( tBkmstVo.getCnt3() ) ) {
					TBkdtlVo tBkdtlVo = new TBkdtlVo();
					
					tBkdtlVo.setOperCd( tBkmstVo.getOperCd() );
					tBkdtlVo.setBkNo( tBkmstVo.getBkNo() );
					tBkdtlVo.setSzCd( tBkmstVo.getSzCd3() );
					tBkdtlVo.setTyGrpCd( tBkmstVo.getTyCd3() );
					tBkdtlVo.setBkEamt( Long.valueOf( tBkmstVo.getCnt3() ) );
					
					tBkdtlRepository.insertOperCdAndBkNoAndSzCdAndTyGrpCdAndBkEamtAndReqArrDtAndCyCd( tBkdtlVo );
				}
				
				if( result > 0 ) {
					resultMap.put( "code", 200 );
					resultMap.put( "msg", "정상처리 되었습니다." );
				} else {
					resultMap.put( "code", 0 );
					resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다." );
				}
			}
		}
		
		return resultMap;
	}

	public Map<String, Object> onDockInsertOrderUpdate(TBkmstVo tBkmstVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		SearchVo searchVo = setOperList( new SearchVo() );
		searchVo.setBkNo( tBkmstVo.getBkNo() );
		int result = tBkdtlRepository.onDockInsertOrderUpdateDelete( searchVo );
		
		if( !StringUtils.isEmpty( tBkmstVo.getSzCd1() ) && !StringUtils.isEmpty( tBkmstVo.getTyCd1() ) && !StringUtils.isEmpty( tBkmstVo.getCnt1() ) ) {
			TBkdtlVo tBkdtlVo = new TBkdtlVo();
			
			tBkdtlVo.setOperCd( tBkmstVo.getOperCd() );
			tBkdtlVo.setBkNo( tBkmstVo.getBkNo() );
			tBkdtlVo.setSzCd( tBkmstVo.getSzCd1() );
			tBkdtlVo.setTyGrpCd( tBkmstVo.getTyCd1() );
			tBkdtlVo.setBkEamt( Long.valueOf( tBkmstVo.getCnt1() ) );
			
			tBkdtlRepository.insertOperCdAndBkNoAndSzCdAndTyGrpCdAndBkEamtAndReqArrDtAndCyCd( tBkdtlVo );
		}
		
		if( !StringUtils.isEmpty( tBkmstVo.getSzCd2() ) && !StringUtils.isEmpty( tBkmstVo.getTyCd2() ) && !StringUtils.isEmpty( tBkmstVo.getCnt2() ) ) {
			TBkdtlVo tBkdtlVo = new TBkdtlVo();
			
			tBkdtlVo.setOperCd( tBkmstVo.getOperCd() );
			tBkdtlVo.setBkNo( tBkmstVo.getBkNo() );
			tBkdtlVo.setSzCd( tBkmstVo.getSzCd2() );
			tBkdtlVo.setTyGrpCd( tBkmstVo.getTyCd2() );
			tBkdtlVo.setBkEamt( Long.valueOf( tBkmstVo.getCnt2() ) );
			
			tBkdtlRepository.insertOperCdAndBkNoAndSzCdAndTyGrpCdAndBkEamtAndReqArrDtAndCyCd( tBkdtlVo );
		}
		
		if( !StringUtils.isEmpty( tBkmstVo.getSzCd3() ) && !StringUtils.isEmpty( tBkmstVo.getTyCd3() ) && !StringUtils.isEmpty( tBkmstVo.getCnt3() ) ) {
			TBkdtlVo tBkdtlVo = new TBkdtlVo();
			
			tBkdtlVo.setOperCd( tBkmstVo.getOperCd() );
			tBkdtlVo.setBkNo( tBkmstVo.getBkNo() );
			tBkdtlVo.setSzCd( tBkmstVo.getSzCd3() );
			tBkdtlVo.setTyGrpCd( tBkmstVo.getTyCd3() );
			tBkdtlVo.setBkEamt( Long.valueOf( tBkmstVo.getCnt3() ) );
			
			tBkdtlRepository.insertOperCdAndBkNoAndSzCdAndTyGrpCdAndBkEamtAndReqArrDtAndCyCd( tBkdtlVo );
		}
		
		if( result > 0 ) {
			resultMap.put( "code", 200 );
			resultMap.put( "msg", "정상처리 되었습니다." );
		} else {
			resultMap.put( "code", 0 );
			resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다." );
		}
		
		return resultMap;
	}

	public Map<String, Object> smsDetailSubmit(Map<String, List<Object>> params) {
		
		log.info( "PARAMS : " + params.toString() );
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put( "code", 200 );
		resultMap.put( "msg", "처리되었습니다." );
		resultMap.put( "params", params );
		
		List<Object> list = params.get("data");
		
		Map<String, Object> firstRow = ((Map<String,Object>)list.get(0));
		
		String reqId = firstRow.get("reqId").toString();
		Long totalAll = Long.valueOf( firstRow.get("totalAll").toString() );
		Long totalAt = (Long) (totalAll + ( (int)( totalAll * 0.1 ) ));
		
		TCustomerVo tCustomerVo = new TCustomerVo();
		tCustomerVo.setCstNo( reqId );
		
		tCustomerVo = tCustomerRepository.findOne( tCustomerVo );
		
		TbRvasMastVo tbRvasMastVo = new TbRvasMastVo();
		tbRvasMastVo.setPayAmt( totalAt );
		tbRvasMastVo.setPayToDate( firstRow.get("searchStartDt").toString() );
		tbRvasMastVo.setCustCd( reqId );
		tbRvasMastVo.setCustNm( StringUtils.substrByte( tCustomerVo.getCstNm(), 20 ) );
		
		tbRvasMastVo.setEntryIdno( "WEB" );
		tbRvasMastVo.setVacctNo( firstRow.get("vacctNo").toString() );
		int tbRvasMastUpdateResult = tbRvasMastRepository.smsDetailSubmitUpdate( tbRvasMastVo );
		
		for( int i=0; i<list.size(); i++ ) {
			Map<String, Object> row = ( (Map<String,Object>) list.get(i) );
			TCntrVo tCntrVo = new TCntrVo();
			tCntrVo.setCntrNo( row.get("cntrNo").toString() );
			
			tCntrVo = tCntrRepository.findOneByCntrNoLastest( tCntrVo );
			
			String PR_CNTR_NO = tCntrVo.getCntrNo();
			String PR_DUP_NO = tCntrVo.getDupNo();
			String PR_DUP_NO2 = String.valueOf( Integer.valueOf( tCntrVo.getDupNo() ) + 1 );
			String PR_SZ_CD = tCntrVo.getSzCd();
			String PR_TY_CD = tCntrVo.getTyCd();
			String PR_CUR_STAT = tCntrVo.getCurStat();
			String PR_CST_NO = reqId;
			String PR_CST_NO_SUB = row.get("cusName").toString();
			String PR_VACCT_NO = row.get("vacctNo").toString();
			String PR_VACCT_NO2 = PR_VACCT_NO.substring(0,3) + "-" + PR_VACCT_NO.substring(3, 7) + "-" + PR_VACCT_NO.substring(7, 11) + "-" + PR_VACCT_NO.substring(11, 14);
			String PR_CLOSE_DD2 = row.get("searchStartDt").toString();
			String PR_CLOSE_DD3 = PR_CLOSE_DD2.substring(0,4) + "-" + PR_CLOSE_DD2.substring(4,6) + "-" + PR_CLOSE_DD2.substring(6,8);
			String PR_IO_CD = row.get("banCi").toString().equals("I") ? "I" : "O";
			String PR_SHIP_CD = tCntrVo.getShipCd();
			String PR_CALL_NO = String.valueOf( tCntrVo.getCallNo() );
			String PR_CALL_YY = String.valueOf( tCntrVo.getCallYy() );
			
			TCashPlanVo tCashPlanVo = new TCashPlanVo();
			tCashPlanVo.setCntrNo( PR_CNTR_NO );
			if( PR_IO_CD.equals("I") ) {
				tCashPlanVo.setDupNo( Long.valueOf( PR_DUP_NO2 ) );
			} else {
				tCashPlanVo.setDupNo( Long.valueOf( PR_DUP_NO ) );
			}
			tCashPlanVo.setCstNo( PR_CST_NO );
			tCashPlanVo.setVacctNo( PR_VACCT_NO );
			tCashPlanVo.setCloseDd( PR_CLOSE_DD3 );
			
			TCashPlanVo tCashPlan = tCashPlanRepository.findByCntrNoAndDupNoAndDealDdAndCstNoAndVacctNoAndCloseDd( tCashPlanVo );
			
			if( tCashPlan == null ) {
				tCashPlanVo.setSzCd( PR_SZ_CD );
				tCashPlanVo.setTyCd( PR_TY_CD );
				tCashPlanVo.setRemark( StringUtils.EMPTY );
				tCashPlanVo.setTaxYn( StringUtils.EMPTY );
				tCashPlanVo.setSaleAmt( 0L );
				tCashPlanVo.setTaxAmt( 0L );
				tCashPlanVo.setCstNoSub( PR_CST_NO_SUB );
				tCashPlanVo.setIoCd( PR_IO_CD );
				tCashPlanVo.setStatCd( "B" );
				
				int tCashPlanInsertResult = tCashPlanRepository.smsDetailSubmitTCashPlanInsert( tCashPlanVo );
			}
		}
		
		for( int i=0; i<list.size(); i++ ) {
			Map<String, Object> row = ( (Map<String,Object>) list.get(i) );
			
			int OverDays_Day = 0;
			int OverDays_Money = 0;
			int Reffer_Day = 0;
			int Reffer_Money = 0;
			int Lift_qty = 0;
			int Lift_Money = 0;
			int Rtn_Yn = 0;
			int Rtn_Money = 0;
			int BondMoney = 0;
			int Xray_Money = 0;
			int Dg_Money = 0;
			int Chk_Money = 0;
			
			if( row.get("banCi").toString().equals("I") ) {
				SearchVo searchVo = new SearchVo();
				searchVo.setCntrNo( row.get("cntrNo").toString() );
				
				Map<String, Object> overSplitMap = tInLiftCashCntrRepository.findOneCntrNoAndDupNoAndFnGetOverpriceExNewByCntrNoAndDupNo( searchVo );
				
				String[] overSplit = overSplitMap.get("CASH").toString().split(",");
				
				Lift_qty = Integer.valueOf( overSplit[0] );
				Lift_Money = Integer.valueOf( overSplit[1] );
				row.put( "dupNo", overSplitMap.get("DUP_NO") );
			} else {
				SearchVo searchVo = new SearchVo();
				searchVo.setCntrNo( row.get("cntrNo").toString() );
				searchVo.setSearchStartDt( row.get("searchStartDt").toString() );
				Map<String, Object> overSplitMap = tCntrRepository.findOneCntrNoAndDupNoAndFnGetOverpriceExNewByCntrNoAndDupNo( searchVo );
				
				String[] overSplit = overSplitMap.get("CASH").toString().split(",");
				
				OverDays_Day = Integer.valueOf( overSplit[0] );
				OverDays_Money = Integer.valueOf( overSplit[1] );
				Reffer_Day = Integer.valueOf( overSplit[2] );
				Reffer_Money = Integer.valueOf( overSplit[3] );
				Lift_qty = Integer.valueOf( overSplit[4] );
				Lift_Money = Integer.valueOf( overSplit[5] );
				Rtn_Yn = Integer.valueOf( overSplit[6] );
				Rtn_Money = Integer.valueOf( overSplit[7] );
				Chk_Money = Integer.valueOf( overSplit[8] );
				Xray_Money = Integer.valueOf( overSplit[9] );
				Dg_Money = Integer.valueOf( overSplit[10] );
				row.put( "dupNo", overSplitMap.get("DUP_NO") );
			}
			
			// 요금타입 따라 분기처리 필요
			
			row.put( "OverDays_Day", OverDays_Day );
			row.put( "OverDays_Money", OverDays_Money );
			row.put( "Reffer_Day", Reffer_Day );
			row.put( "Reffer_Money", Reffer_Money );
			row.put( "Lift_qty", Lift_qty );
			row.put( "Lift_Money", Lift_Money );
			row.put( "Rtn_Yn", Rtn_Yn );
			row.put( "Rtn_Money", Rtn_Money );
			row.put( "BondMoney", BondMoney );
			row.put( "Xray_Money", Xray_Money );
			row.put( "Dg_Money", Dg_Money );
			row.put( "Chk_Money", Chk_Money );
			
			row.put( "closeDd", row.get("searchStartDt").toString().substring(0,4) + "-" + row.get("searchStartDt").toString().substring(4,6) + "-" + row.get("searchStartDt").toString().substring(6,8) );
			
			log.info( "ROW : " + row.toString() );
			
			int result = tCashPlanRepository.smsDetailSubmitTCashPlanUpdate( row );
			
			if( result == 0 ) {
				resultMap.put( "code", 0 );
				resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다." );
			}
		}
		
		for( int i=0; i<list.size(); i++ ) {
			Map<String, Object> row = ( (Map<String,Object>) list.get(i) );
			
			TCntrVo tCntrVo = new TCntrVo();
			tCntrVo.setCntrNo( row.get("cntrNo").toString() );
			
			tCntrVo = tCntrRepository.findOneByCntrNoLastest( tCntrVo );
			
			TCashPlanVo tCashPlanVo = new TCashPlanVo();
			tCashPlanVo.setCntrNo( tCntrVo.getCntrNo() );
			if( row.get("banCi").toString().equals("I") ) {
				tCashPlanVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) + 1 );
			} else {
				tCashPlanVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) );
			}
			tCashPlanVo.setCstNo( reqId );
			tCashPlanVo.setVacctNo( row.get("vacctNo").toString() );
			tCashPlanVo.setCloseDd( row.get("searchStartDt").toString().substring(0,4) + "-" + row.get("searchStartDt").toString().substring(4,6) + "-" + row.get("searchStartDt").toString().substring(6,8) );
			
			TCashPlanVo tCashPlan = tCashPlanRepository.findByCntrNoAndDupNoAndDealDdAndCstNoAndVacctNoAndCloseDd(tCashPlanVo);
			
			Long FI_OVER_AMT = tCashPlan.getOverAmt();
			Long FI_TEMP_AMT = tCashPlan.getTempAmt();
			Long FI_LIFT_AMT = tCashPlan.getLiftAmt();
			Long FI_CHK_AMT = tCashPlan.getChkAmt();
			Long FI_XRAY_AMT = tCashPlan.getXrayAmt();
			Long FI_RTN_AMT = tCashPlan.getReturnAmt();
			Long FI_DG_AMT = tCashPlan.getCarAmt();
			
			Long FI_SUM = FI_OVER_AMT + FI_TEMP_AMT + FI_LIFT_AMT + FI_CHK_AMT + FI_XRAY_AMT + FI_RTN_AMT + FI_DG_AMT;
			Long FI_TAX = FI_SUM / 10;
			Long FI_ALL = FI_SUM + FI_TAX;
			
			if( FI_SUM == 0 ) {
				TCashPlanVo deleteVo = new TCashPlanVo();
				deleteVo.setCntrNo( tCntrVo.getCntrNo() );
				if( row.get("banCi").toString().equals("I") ) {
					deleteVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) + 1 );
				} else {
					deleteVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) );
				}
				deleteVo.setCstNo( reqId );
				deleteVo.setVacctNo( row.get("vacctNo").toString() );
				deleteVo.setCloseDd( row.get("searchStartDt").toString().substring(0,4) + "-" + row.get("searchStartDt").toString().substring(4,6) + "-" + row.get("searchStartDt").toString().substring(6,8) );
				int result = tCashPlanRepository.deleteTcashPlanByCntrNoAndDupNoAndDealDdAndCstNoAndVacctNoAndCloseDd( deleteVo );
				
				if( result == 0 ) {
					resultMap.put( "code", 0 );
					resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다." );
				}
			} else {
				TCashPlanVo updateVo = new TCashPlanVo();
				updateVo.setTaxYn( "Y" );
				updateVo.setSaleAmt( FI_SUM );
				updateVo.setTaxAmt( FI_TAX );
				updateVo.setCntrNo( tCntrVo.getCntrNo() );
				if( row.get("banCi").toString().equals("I") ) {
					updateVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) + 1 );
				} else {
					updateVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) );
				}
				updateVo.setCstNo( reqId );
				updateVo.setVacctNo( row.get("vacctNo").toString() );
				updateVo.setCloseDd( row.get("searchStartDt").toString().substring(0,4) + "-" + row.get("searchStartDt").toString().substring(4,6) + "-" + row.get("searchStartDt").toString().substring(6,8) );
				int result = tCashPlanRepository.updateTcashPlanByCntrNoAndDupNoAndDealDdAndCstNoAndVacctNoAndCloseDd( updateVo );
				
				if( result == 0 ) {
					resultMap.put( "code", 0 );
					resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다." );
				}
			}
		}
		
		
		if( resultMap.get("code").toString().equals("200") ) {
			TTmnlVo tTmnlVo = new TTmnlVo();
			tTmnlVo.setTmnlCd( "E1CT" );
			
			resultMap.put("tmnl", tTmnlRepository.findOneByTmnlCd( tTmnlVo ) );
			
			List<InvoiceVo> invoices = new ArrayList<InvoiceVo>();
			
			for( int i=0; i<list.size(); i++ ) {
				Map<String, Object> row = ( (Map<String,Object>) list.get(i) );
				
				TCntrVo tCntrVo = new TCntrVo();
				tCntrVo.setCntrNo( row.get("cntrNo").toString() );
				
				tCntrVo = tCntrRepository.findOneByCntrNoLastest( tCntrVo );
				
				InvoiceVo invoiceVo = new InvoiceVo();
				
				invoiceVo.setCntrNo( tCntrVo.getCntrNo() );
				invoiceVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) );
				invoiceVo.setDupNo2( Long.valueOf( tCntrVo.getDupNo() ) + 1 );
				invoiceVo.setShipCd( tCntrVo.getShipCd() );
				invoiceVo.setCallNo( tCntrVo.getCallNo() );
				invoiceVo.setCallYy( tCntrVo.getCallYy() );
				invoiceVo.setSzCd( tCntrVo.getSzCd() );
				invoiceVo.setTyCd( tCntrVo.getTyCd() );
				invoiceVo.setCloseDd( row.get("searchStartDt").toString().substring(0,4) + "-" + row.get("searchStartDt").toString().substring(4,6) + "-" + row.get("searchStartDt").toString().substring(6,8) );
				invoiceVo.setVacctNo( row.get("vacctNo").toString().substring(0,3) + "-" + row.get("vacctNo").toString().substring(3, 7) + "-" + row.get("vacctNo").toString().substring(7, 11) + "-" + row.get("vacctNo").toString().substring(11, 14) );
				invoiceVo.setIxCd( tCntrVo.getCurStat() );
				
				TCashPlanVo tCashPlanVo = new TCashPlanVo();
				tCashPlanVo.setCntrNo( tCntrVo.getCntrNo() );
				if( row.get("banCi").toString().equals("I") ) {
					tCashPlanVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) + 1 );
				} else {
					tCashPlanVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) );
				}
				tCashPlanVo.setCstNo( reqId );
				tCashPlanVo.setVacctNo( row.get("vacctNo").toString() );
				tCashPlanVo.setCloseDd( row.get("searchStartDt").toString().substring(0,4) + "-" + row.get("searchStartDt").toString().substring(4,6) + "-" + row.get("searchStartDt").toString().substring(6,8) );
				
				TCashPlanVo tCashPlan = tCashPlanRepository.findByCntrNoAndDupNoAndDealDdAndCstNoAndVacctNoAndCloseDd(tCashPlanVo);
				if( tCashPlan != null ) {
					invoiceVo.setOverYn( tCashPlan.getOverYn() );
					invoiceVo.setOverDay( tCashPlan.getOverDay() );
					invoiceVo.setOverAmt( tCashPlan.getOverAmt() );
					invoiceVo.setOverUt( tCashPlan.getOverAmt() != 0 ? (Long) ( tCashPlan.getOverAmt() / tCashPlan.getOverDay() ) : 0L );
					invoiceVo.setTempYn( tCashPlan.getTempYn() );
					invoiceVo.setTempDay( tCashPlan.getTempDay() );
					invoiceVo.setTempAmt( tCashPlan.getTempAmt() );
					invoiceVo.setTempUt( tCashPlan.getTempAmt() != 0 ? (Long)(tCashPlan.getTempAmt() / tCashPlan.getTempDay() ) : 0L );
					invoiceVo.setChkAmt( tCashPlan.getChkAmt() );
					invoiceVo.setXrayAmt( tCashPlan.getXrayAmt() );
					invoiceVo.setLiftYn( tCashPlan.getLiftYn() );
					invoiceVo.setLiftQty( tCashPlan.getLiftQty() );
					invoiceVo.setLiftAmt( tCashPlan.getLiftAmt() );
					invoiceVo.setLiftUt( tCashPlan.getLiftAmt() != 0 ? (Long)(tCashPlan.getLiftAmt() / tCashPlan.getLiftQty() ) : 0L );
					invoiceVo.setReturnYn( tCashPlan.getReturnYn() );
					invoiceVo.setReturnAmt( tCashPlan.getReturnAmt() );
					invoiceVo.setDgAmt( tCashPlan.getCarAmt() );
					invoiceVo.setTotalInvoice( invoiceVo.getOverAmt() + invoiceVo.getTempAmt() + invoiceVo.getChkAmt() + invoiceVo.getXrayAmt() + invoiceVo.getLiftAmt() + invoiceVo.getReturnAmt() + invoiceVo.getDgAmt() );
					invoiceVo.setTotalTax( invoiceVo.getTotalInvoice() / 10 );
					invoiceVo.setSaleAmt( invoiceVo.getTotalInvoice() + invoiceVo.getTotalTax() );
				}
				
				invoices.add( invoiceVo );
			}
			
			resultMap.put( "invoices", invoices );
		}
		
		return resultMap;
	}

	public TTmnlVo findE1ctTmnlInfo() {
		TTmnlVo tTmnlVo = new TTmnlVo();
		tTmnlVo.setTmnlCd( "E1CT" );
		return tTmnlRepository.findOneByTmnlCd( tTmnlVo );
	}

	public List<InvoiceVo> findInvoiceList(InvoiceVo invoiceVo) {
		List<InvoiceVo> invoices = new ArrayList<InvoiceVo>();
		
		String[] cntrArray = invoiceVo.getCntrNo().split(",");
		for( int i=0; i<cntrArray.length; i++ ) {
			TCntrVo tCntrVo = new TCntrVo();
			tCntrVo.setCntrNo( cntrArray[i] );
			
			tCntrVo = tCntrRepository.findOneByCntrNoLastest( tCntrVo );
			
			InvoiceVo invoice = new InvoiceVo();
			
			invoice.setCntrNo( tCntrVo.getCntrNo() );
			invoice.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) );
			invoice.setDupNo2( Long.valueOf( tCntrVo.getDupNo() ) + 1 );
			invoice.setShipCd( tCntrVo.getShipCd() );
			invoice.setCallNo( tCntrVo.getCallNo() );
			invoice.setCallYy( tCntrVo.getCallYy() );
			invoice.setSzCd( tCntrVo.getSzCd() );
			invoice.setTyCd( tCntrVo.getTyCd() );
			invoice.setCloseDd( invoiceVo.getCloseDd() );
			invoice.setVacctNo( invoiceVo.getVacctNo().substring(0,3) + "-" + invoiceVo.getVacctNo().substring(3, 7) + "-" + invoiceVo.getVacctNo().substring(7, 11) + "-" + invoiceVo.getVacctNo().substring(11, 14) );
			invoice.setIxCd( tCntrVo.getCurStat() );
			
			TCashPlanVo tCashPlanVo = new TCashPlanVo();
			tCashPlanVo.setCntrNo( tCntrVo.getCntrNo() );
			if( invoiceVo.getBanCi().equals("I") ) {
				tCashPlanVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) + 1 );
			} else {
				tCashPlanVo.setDupNo( Long.valueOf( tCntrVo.getDupNo() ) );
			}
			tCashPlanVo.setCstNo( invoiceVo.getCstCd() );
			tCashPlanVo.setVacctNo( invoiceVo.getVacctNo() );
			tCashPlanVo.setCloseDd( invoiceVo.getCloseDd() );
			
			TCashPlanVo tCashPlan = tCashPlanRepository.findByCntrNoAndDupNoAndDealDdAndCstNoAndVacctNoAndCloseDd(tCashPlanVo);
			if( tCashPlan != null ) {
				
				if( tCashPlan.getOverDay() == null ) tCashPlan.setOverDay( 0L );
				if( tCashPlan.getOverAmt() == null ) tCashPlan.setOverAmt( 0L );
				if( tCashPlan.getTempDay() == null ) tCashPlan.setTempDay( 0L );
				if( tCashPlan.getTempAmt() == null ) tCashPlan.setTempAmt( 0L );
				if( tCashPlan.getChkAmt() == null ) tCashPlan.setChkAmt( 0L );
				if( tCashPlan.getXrayAmt() == null ) tCashPlan.setXrayAmt( 0L );
				if( tCashPlan.getLiftQty() == null ) tCashPlan.setLiftQty( 0L );
				if( tCashPlan.getLiftAmt() == null ) tCashPlan.setLiftAmt( 0L );
				if( tCashPlan.getReturnAmt() == null ) tCashPlan.setReturnAmt( 0L );
				if( tCashPlan.getCarAmt() == null ) tCashPlan.setCarAmt( 0L );
				
				invoice.setOverYn( tCashPlan.getOverYn() );
				invoice.setOverDay( tCashPlan.getOverDay() );
				invoice.setOverAmt( tCashPlan.getOverAmt() );
				invoice.setOverUt( tCashPlan.getOverAmt() != 0 ? (Long) ( tCashPlan.getOverAmt() / tCashPlan.getOverDay() ) : 0L );
				invoice.setTempYn( tCashPlan.getTempYn() );
				invoice.setTempDay( tCashPlan.getTempDay() );
				invoice.setTempAmt( tCashPlan.getTempAmt() );
				invoice.setTempUt( tCashPlan.getTempAmt() != 0 ? (Long)(tCashPlan.getTempAmt() / tCashPlan.getTempDay() ) : 0L );
				invoice.setChkAmt( tCashPlan.getChkAmt() );
				invoice.setXrayAmt( tCashPlan.getXrayAmt() );
				invoice.setLiftYn( tCashPlan.getLiftYn() );
				invoice.setLiftQty( tCashPlan.getLiftQty() );
				invoice.setLiftAmt( tCashPlan.getLiftAmt() );
				invoice.setLiftUt( tCashPlan.getLiftAmt() != 0 ? (Long)(tCashPlan.getLiftAmt() / tCashPlan.getLiftQty() ) : 0L );
				invoice.setReturnYn( tCashPlan.getReturnYn() );
				invoice.setReturnAmt( tCashPlan.getReturnAmt() );
				invoice.setDgAmt( tCashPlan.getCarAmt() );
				invoice.setTotalInvoice( invoice.getOverAmt() + invoice.getTempAmt() + invoice.getChkAmt() + invoice.getXrayAmt() + invoice.getLiftAmt() + invoice.getReturnAmt() + invoice.getDgAmt() );
				invoice.setTotalTax( invoice.getTotalInvoice() / 10 );
				invoice.setSaleAmt( invoice.getTotalInvoice() + invoice.getTotalTax() );
			}
			
			invoices.add( invoice );
		}
		return invoices;
	}

	public Map<String, Object> onDockBookingDetailUpdate(SearchVo searchVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SearchVo operListAdded = setOperList(searchVo);
		
		TBkmstVo tBkmstVo = tBkmstRepository.onDockBookingDetailUpdate( operListAdded );
		
		if( tBkmstVo != null ) {
			
			operListAdded.setBkNo( tBkmstVo.getBkNo() );
			operListAdded.setShipCd( tBkmstVo.getShipCd() );
			operListAdded.setCallNo( String.valueOf( tBkmstVo.getCallNo() ) );
			operListAdded.setCallYy( String.valueOf( tBkmstVo.getCallYy() ) );
			operListAdded.setPod( tBkmstVo.getPod() );
			operListAdded.setPol( tBkmstVo.getPol() );
			operListAdded.setDest( tBkmstVo.getDest() == null ? "" : tBkmstVo.getDest() );
			operListAdded.setTlock( tBkmstVo.getTlock() );
			operListAdded.setCarrierNm( tBkmstVo.getCarrierNm() );
		} 
		
		
		TBkdtlVo tBkdtlVo = tBkdtlRepository.onDockBookingDetailUpdate( operListAdded );
		
		if( tBkdtlVo != null ) {
			operListAdded.setSzCd( tBkdtlVo.getSzCd() );
			operListAdded.setTyCd( tBkdtlVo.getTyGrpCd() );
			// operListAdded.setBkEamt( tBkdtlVo.getBkEamt() );
		}
		
		int result = tBkmstRepository.updateBookingDetail( operListAdded );
		
		if( tBkdtlRepository.findByOperCdAndBkNoAndSzCdAndTyGrpCd( operListAdded ) == null ) {
			result += tBkdtlRepository.insertBookingDetail( operListAdded );
		} else {
			result += tBkdtlRepository.updateBookingDetail( operListAdded );
		}
		
		if( result > 0 ) {
			resultMap.put( "code", 200 );
			resultMap.put( "msg", " 정상처리되었습니다." );
		} else {
			resultMap.put( "code", 0 );
			resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다." );
		}
		
		return resultMap;
	}

	public List<TCntrVo> terminalYardStackDoNoList(SearchVo searchVo) {
		return tCntrRepository.terminalYardStackDoNoList( searchVo );
	}

	public List<TCntrVo> onDockDeliveryConfirmList(TBsiotVo tBsiotVo) {
		List<TCntrVo> list = new ArrayList<TCntrVo>();
		if( !StringUtils.isEmpty( tBsiotVo.getBlNo() ) ) {
			SearchVo paramVo = setOperList( new SearchVo() );
			paramVo.setBlNo( tBsiotVo.getBlNo() );
			paramVo.setShipCd( tBsiotVo.getShipCd() );
			paramVo.setCallNo( tBsiotVo.getCallNo() );
			paramVo.setCallYy( tBsiotVo.getCallYy() );
			
			list = tCntrRepository.onDockDeliveryConfirmList( paramVo );
			
			for( int i=0; i<list.size(); i++ ) {
				TCntrVo tCntrVo = new TCntrVo();
				tCntrVo.setCntrNo( list.get(i).getCntrNo() );
				tCntrVo.setDoNo( tBsiotVo.getBlNo() );
				
				TCntrVo rs1Vo = tCntrRepository.onDockDeliveryConfirmRs1( tCntrVo );
				
				
				if( rs1Vo != null ) {
					list.get(i).setRs1( rs1Vo );
					list.get(i).setClosingDt( rs1Vo.getClosingDt() );
					list.get(i).setCopYn( rs1Vo.getCopYn() );
					list.get(i).setCurStat( rs1Vo.getCurStat() );
				} else {
					paramVo.setCntrNo( list.get(i).getCntrNo() );
					TCntrVo rssVo = tCntrRepository.onDockDeliveryConfirmRss( paramVo );
					
					if( rssVo != null ) {
						list.get(i).setRss1( rssVo.getCurStat() );
						
						TVescallVo rtnDtVo = findReturnDate( tBsiotVo );
						list.get(i).setOutDt( rtnDtVo.getRtnDt() );
					}
				}
			}
		}
		return list;
	}

	public TDomstVo findSelfTransByDoNoAndOperCd(TBsiotVo tBsiotVo) {
		SearchVo paramVo = setOperList( new SearchVo() );
		paramVo.setDoNo( tBsiotVo.getBlNo() );
		return tDomstRepository.findSelfTransByDoNoAndOperCd( paramVo );
	}

	public String findDemurChk() {
		SearchVo searchVo = setOperList( new SearchVo() );
		searchVo.setOperCd( searchVo.getOperList().get(0) );
		TOperVo tOperVo = new TOperVo();
		
		if( SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains( new SimpleGrantedAuthority("ROLE_S") ) ) {
			tOperVo.setDemurChk("Y");
		} else {
			tOperVo = tOperRepository.findDemurChk( searchVo );
		}
		return tOperVo.getDemurChk();
	}

	public TVescallVo findReturnDate(TBsiotVo tBsiotVo) {
		SearchVo searchVo = new SearchVo();
		searchVo.setShipCd( tBsiotVo.getShipCd() );
		searchVo.setCallNo( tBsiotVo.getCallNo() );
		searchVo.setCallYy( tBsiotVo.getCallYy() );
		return tVescallRepository.findReturnDate( searchVo );
	}

	public Map<String, Object> onDockDeliveryConfirmSave(Map<String, String> param) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		SearchVo searchVo = setOperList( new SearchVo() );
		
		String demurChk = findDemurChk();
		String blNo = param.get("blNo");
		String operDo = param.get("oper_do").substring(0, 12);
		String line = param.get("line");
		String shipCd = param.get("shipCd");
		String callNo = StringUtils.removeSpace( param.get("callNo") );
		String callYy = param.get("callYy");
		String cntrNo = param.get("cntrNo");
		String operCd = searchVo.getOperList().get(0);
		String chkCntrNo[] = param.get("chkCntr").split(",");
		String chkCount = param.get("chkCount");
		String chkoutDt[] = StringUtils.replaceAll( param.get("chkOutDate"), "-", "").split(",");
		String nonCntrNo[] = param.get("nonCntr").split(",");
		String nonCount = param.get("nonCount");
		String transType = StringUtils.isEmpty( param.get("line") ) ? "ST" : "IT";
		
		
		TDomstVo tDomstParam = new TDomstVo();
		tDomstParam.setDoNo( blNo );
		tDomstParam.setOperCd( operCd );
		
		TDomstVo tDomstVo = tDomstRepository.findByDoNoAndOperCd( tDomstParam );
		
		if( tDomstVo != null ) {
			String telNo = tDomstVo.getTelNo();
			String factorNo = tDomstVo.getFactorNm();
			String factorLoc = tDomstVo.getFactorLoc();
			String voyageNo = tDomstVo.getVoyageNo();
			String consgnNm = tDomstVo.getConsgnNm();
			
			TDomstVo updateVo = new TDomstVo();
			updateVo.setSelfTrans( transType );
			updateVo.setOperDo( operDo );
			updateVo.setDoNo( blNo );
			updateVo.setOperCd( operCd );
			
			int updateResult = tDomstRepository.updateByDoNoAndOperCd( updateVo );
		} else {
			TDomstVo insertVo = new TDomstVo();
			insertVo.setDoNo( blNo );
			insertVo.setOperCd( operCd );
			insertVo.setShipCd( shipCd );
			insertVo.setCallNo( Long.valueOf( callNo ) );
			insertVo.setCallYy( Long.valueOf( callYy ) );
			insertVo.setVoyageNo( "" );
			insertVo.setOperDo( operDo );
			insertVo.setEmpNo( "WEB" );
			insertVo.setSelfTrans( transType );
			int insertResult = tDomstRepository.insertDelivery( insertVo );
		}
		
		for( int i=0; i<Integer.valueOf( chkCount ); i++ ) {
			String dup = "";
			String szCd = "";
			String tyCd = "";
			String pickCd = "";
			
			TCntrVo tCntrParam = new TCntrVo();
			tCntrParam.setShipCd( shipCd );
			tCntrParam.setCallNo( Long.valueOf( StringUtils.removeSpace( callNo ) ) );
			tCntrParam.setCallYy( Long.valueOf( callYy ) );
			tCntrParam.setFmCd( "F" );
			tCntrParam.setCntrNo( chkCntrNo[i] );
			tCntrParam.setOperCd( operCd );
			tCntrParam.setBlNo( blNo );
			
			TCntrVo tCntrVo = tCntrRepository.findByShipCdAndCallNoAndCallYyAndFmCdAndCurStatAndCntrNoAndOperCd( tCntrParam );
			
			if( tCntrVo != null ) {
				dup = tCntrVo.getDupNo();
				szCd = tCntrVo.getSzCd();
				tyCd = tCntrVo.getTyCd();
				pickCd = tCntrVo.getPickCd();
			}
			
			TDodtlVo tDodtlVo = new TDodtlVo();
			tDodtlVo.setDoNo( blNo );
			tDodtlVo.setCntrNo( chkCntrNo[i] );
			tDodtlVo.setDupNo( dup );
			
			tDodtlVo = tDodtlRepository.findByDoNoAndCntrNoAndDupNo( tDodtlVo );
			
			if( tDodtlVo == null ) {
				
				TDodtlVo insertVo = new TDodtlVo();
				insertVo.setDoNo( blNo );
				insertVo.setCntrNo( chkCntrNo[i] );
				insertVo.setDupNo( dup );
				insertVo.setSzCd( szCd );
				insertVo.setTyCd( tyCd );
				insertVo.setFmCd( "F" );
				insertVo.setDemurChk( demurChk.toUpperCase() );
				if( demurChk.toUpperCase().equals("Y") ) {
					insertVo.setClosingDt( chkoutDt[i] );
				}
				
				int tdodtlInsertResult = tDodtlRepository.insert( insertVo );
				
				TMovelogVo tMovelogParam = new TMovelogVo();
				tMovelogParam.setCntrNo( chkCntrNo[i] );
				tMovelogParam.setDupNo( Long.valueOf( dup ) );
				tMovelogParam.setIxCd( "O" );
				
				TMovelogVo tMovelogVo = tMovelogRepository.findByCntrNoAndDupNoAndIxCd( tMovelogParam );
				
				if( tMovelogVo == null ) {
					TMovelogVo tMoveInsertVo = new TMovelogVo();
					tMoveInsertVo.setCntrNo( chkCntrNo[i] );
					tMoveInsertVo.setDupNo( Long.valueOf( dup ) );
					tMoveInsertVo.setIxCd( "O" );
					tMoveInsertVo.setShipCd( shipCd );
					tMoveInsertVo.setCallYy( Long.valueOf( callYy ) );
					tMoveInsertVo.setCallNo( Long.valueOf( callNo ) );
					tMoveInsertVo.setOperCd( operCd );
					tMoveInsertVo.setOwnerNm( "" ); // Factor_nm missing
					tMoveInsertVo.setOwnerLoc( "" ); //Factor_loc missing
					tMoveInsertVo.setOrderNo( blNo ); // Do_no missing
					tMoveInsertVo.setDoNo( blNo );
					
					int tMoveInsertResult = tMovelogRepository.insert( tMoveInsertVo );
				} else {
					TMovelogVo tMoveUpdateVo = new TMovelogVo();
					tMoveUpdateVo.setOperCd( operCd );
					tMoveUpdateVo.setOwnerNm( "" );
					tMoveUpdateVo.setOwnerLoc( "" );
					tMoveUpdateVo.setOrderNo( blNo );
					tMoveUpdateVo.setDoNo( blNo );
					tMoveUpdateVo.setEmpNo( "WEB" );
					tMoveUpdateVo.setCntrNo( chkCntrNo[i] );
					tMoveUpdateVo.setDupNo( Long.valueOf( dup ) );
					tMoveUpdateVo.setIxCd( "O" );
					
					int tMoveUpdateResult = tMovelogRepository.update( tMoveUpdateVo );
				}
			}
		}
		
		for( int i=0; i<Integer.valueOf( nonCount ); i++ ) {
			String dup = "";
			String szCd = "";
			String tyCd = "";
			String pickCd = "";
			
			TCntrVo tCntrParam = new TCntrVo();
			tCntrParam.setShipCd( shipCd );
			tCntrParam.setCallNo( Long.valueOf( StringUtils.removeSpace( callNo ) ) );
			tCntrParam.setCallYy( Long.valueOf( callYy ) );
			tCntrParam.setFmCd( "F" );
			tCntrParam.setCntrNo( nonCntrNo[i] );
			tCntrParam.setOperCd( operCd );
			tCntrParam.setBlNo( blNo );
			
			TCntrVo tCntrVo = tCntrRepository.findByShipCdAndCallNoAndCallYyAndFmCdAndCurStatAndCntrNoAndOperCd( tCntrParam );
			
			if( tCntrVo != null ) {
				dup = tCntrVo.getDupNo();
				szCd = tCntrVo.getSzCd();
				tyCd = tCntrVo.getTyCd();
				pickCd = tCntrVo.getPickCd();
			}
			
			TDodtlVo tDodtlVo = new TDodtlVo();
			tDodtlVo.setDoNo( blNo );
			tDodtlVo.setCntrNo( nonCntrNo[i] );
			tDodtlVo.setDupNo( dup );
			
			tDodtlVo = tDodtlRepository.findByDoNoAndCntrNoAndDupNo( tDodtlVo );
			
			if( tDodtlVo != null ) {
				TDodtlVo deleteVo = new TDodtlVo();
				deleteVo.setDoNo( blNo );
				deleteVo.setCntrNo( nonCntrNo[i] );
				deleteVo.setDupNo( dup );
				
				int tDodtlDeleteResult = tDodtlRepository.delete( deleteVo );
				
				TCntrVo cntrUpdateVo = new TCntrVo();
				cntrUpdateVo.setCntrNo( nonCntrNo[i] );
				cntrUpdateVo.setDupNo( dup );
				cntrUpdateVo.setOperCd( operCd );
				
				int tCntrUpdateResult = tCntrRepository.updateCopinoYnByCntrNoAndDupNoAndOperCd( cntrUpdateVo );
				
				TMovelogVo tMovelogDeleteVo = new TMovelogVo();
				tMovelogDeleteVo.setCntrNo( nonCntrNo[i] );
				tMovelogDeleteVo.setDupNo( Long.valueOf( dup ) );
				tMovelogDeleteVo.setIxCd( "O" );
				
				int tMovelogDeleteResult = tMovelogRepository.deleteByCntrNoAndDupNoAndIxCd( tMovelogDeleteVo );
			}
		}
		
		TDomstVo tDomstUpdateVo = new TDomstVo();
		tDomstUpdateVo.setSelfTrans( transType );
		tDomstUpdateVo.setDoNo( blNo );
		tDomstUpdateVo.setOperCd( operCd );
		
		int tDomstUpdateResult = tDomstRepository.updateSelfTransByDoNoAndOperCd( tDomstUpdateVo );
		
		TDodtlVo tDodtlVo = new TDodtlVo();
		tDodtlVo.setDoNo( blNo );
		if( tDodtlRepository.findByDoNo( tDodtlVo ) == null ) {
			TDomstVo tDomstDeleteVo = new TDomstVo();
			
			tDomstDeleteVo.setDoNo( blNo );
			int tDomstDeleteResult = tDomstRepository.deleteByDoNo( tDomstDeleteVo );
		}
		
		resultMap.put( "code", 200 );
		resultMap.put( "msg", "처리되었습니다." );
		
		return resultMap;
	}

	public List<TCntrVo> terminalEmptyContainer(SearchVo searchVo) {
		// TODO Auto-generated method stub
		return tCntrRepository.terminalEmptyContainer( searchVo );
	}

	public IntegratedInformationResponse getIntegratedInformation(String cntrNo){
		return tCntrRepository.getIntegratedInformation(cntrNo);
	}

	public ExportableInformationResponse getExportableInformation(List<String> cntrNoList){
		ExportableInformationResponse response = new ExportableInformationResponse();
		List<ExportableInformationDTO> dtoList = new ArrayList<>();
		for(String cntrNo : cntrNoList){
			ExportableInformationDTO dto = tCntrRepository.getExportableInformation(cntrNo);
			if(dto == null){
				dto = new ExportableInformationDTO();
				dto.setRspnsMsg("컨테이너정보없음");
				dto.setRspnsScs("N");
			}
			else {
				dto.setTrmnlCode("IT002");
				dto.setRspnsScs("Y");
				dto.setRspnsMsg(" ");
			}
			dtoList.add(dto);
		}
		if(dtoList.size() == 0){
			response.setScs("N");
			response.setMsg("컨테이너정보없음");
			return response;
		}
		response.setScs("Y");
		response.setMsg("");
		response.setCntrList(dtoList);
		return response;
	}

}
