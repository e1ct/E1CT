package kr.co.e1ct.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.e1ct.services.InfoService;
import kr.co.e1ct.util.StringUtils;
import kr.co.e1ct.vo.InvoiceVo;
import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBkmstVo;
import kr.co.e1ct.vo.TBsiotVo;
import kr.co.e1ct.vo.TCntrVo;
import kr.co.e1ct.vo.TColdltVo;
import kr.co.e1ct.vo.TCopinoRjctVo;
import kr.co.e1ct.vo.TCustomerVo;
import kr.co.e1ct.vo.TGateVo;
import kr.co.e1ct.vo.TSecretVo;
import kr.co.e1ct.vo.WSajunVo;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping( "/info" )
@Slf4j
public class InfoController {
	
	@Autowired InfoService infoService;
	
	
	// TERMINAL START

	@RequestMapping( "/terminal/berthText" )
	public String terminalBerthText( SearchVo searchVo, @PageableDefault(size = 50) Pageable pageable, Model model ) throws ParseException {
		searchVo.setCustomPageable( pageable );
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			Calendar calendar = Calendar.getInstance();
			
			calendar.setTime( new Date() );
			calendar.add( Calendar.DATE, 7);
			
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( calendar.getTime() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		model.addAttribute( "berthList", infoService.berthText( searchVo ) );
		model.addAttribute( "totalCnt", infoService.berthTextCnt( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		
		return "info/terminal/berthText";
	}
	
	@RequestMapping( "/terminal/berthTextPrint" )
	public String terminalBerthTextPrint( SearchVo searchVo, Model model ) throws ParseException {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			Calendar calendar = Calendar.getInstance();
			
			calendar.setTime( new Date() );
			calendar.add( Calendar.DATE, 7);
			
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( calendar.getTime() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "berthList", infoService.berthTextPrint( searchVo ) );
		return "info/terminal/berthTextPrint";
	}
	
	@RequestMapping( "/terminal/berthGraphic" )
	public String terminalBerthGraphic( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		Calendar c = Calendar.getInstance();
		try {
			c.setTime( new SimpleDateFormat("yyyyMMdd").parse( searchVo.getSearchStartDt() ) );
		} catch( ParseException e ) {
			c.setTime( new Date() );
			e.printStackTrace();
		}
		
		List<String> dateList = new ArrayList<String>();
		dateList.add( new SimpleDateFormat("yyyy-MM-dd (E)").format( c.getTime() ) );
		for( int i=0; i<6; i++ ) {
			c.add( Calendar.DATE, 1 );
			String date = new SimpleDateFormat("yyyy-MM-dd (E)").format( c.getTime() );
			dateList.add( date );
		}
		
		model.addAttribute( "dateList", dateList );
		model.addAttribute( "list", infoService.terminalBerthGraphic( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		
		return "info/terminal/berthGraphic";
	}

	@RequestMapping( "/terminal/vesselWork" )
	public String terminalVesselWork( SearchVo searchVo, Model model ) {
		if( StringUtils.isEmpty( searchVo.getSearch() ) ) {
			searchVo.setSearch("ALL");
		}
		model.addAttribute( "vesselInfo", infoService.vesselInfo( searchVo ) );
		model.addAttribute( "vesselWorkDetail", infoService.vesselWorkDetail( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/terminal/vesselWork";
	}
	
	@RequestMapping( "/terminal/yardStack" )
	public String terminalYardStack( SearchVo searchVo, Model model ) {
		model.addAttribute( "searchParams", searchVo );
		return "info/terminal/yardStack";
	}
	
	@RequestMapping( "/terminal/yardStack/doNoList")
	@ResponseBody
	public List<TCntrVo> terminalYardStackDoNoList( SearchVo searchVo ) {
		return infoService.terminalYardStackDoNoList( searchVo );
	}
	
	@RequestMapping( "/terminal/yardStack/data" )
	@ResponseBody
	public List<HashMap<String, Object>> terminalYardStackData( SearchVo searchVo, Model model ) {
		List<HashMap<String, Object>> resultList = infoService.terminalYardStackData( searchVo );
		return resultList;
	}
	
	@RequestMapping( "/terminal/yardStack/workStatus" )
	public String terminalYardStackWorkStatus( Model model ) {
		model.addAttribute( "list", infoService.terminalYardStackWorkStatus() );
		return "info/terminal/yardStackWorkStatus";
	}
	
	@RequestMapping( "/terminal/cntrDetailInfo" )
	public String terminalCntrDetailInfo( Model model, @RequestBody SearchVo searchVo ) throws ParseException {
		searchVo.setSearch( searchVo.getCntrNo() );
		TCntrVo dataVo = infoService.cntrInfoDetailsDataLatest( searchVo );
		dataVo.setCntrNo( searchVo.getCntrNo() );
		model.addAttribute( "data", dataVo );
		return "info/popup/cntrDetail";
	}
	
	@RequestMapping( "/terminal/byOperYard" )
	public String terminalByOperYard( SearchVo searchVo, Model model ) {
		if( searchVo.getOper() == null || searchVo.getOper() == "" ) {
			searchVo.setOper("");
		}
		
		if( !searchVo.getOper().equals("") ) {
			Map<String, Object> byOperYardResult = infoService.terminalByOperYardList( searchVo );
			model.addAttribute( "list", byOperYardResult.get( "list" ) );
			model.addAttribute( "sumList", byOperYardResult.get( "sumList" ) );
		}
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/terminal/byOperYard";
	}
	
	@RequestMapping( "/terminal/codeVessel" )
	public String terminalCodeVessel( @PageableDefault( size = 50 ) Pageable pageable, SearchVo searchVo, Model model ) {
		searchVo.setCustomPageable( pageable );
		if( searchVo.getStrYear() == null ) {
			searchVo.setStrYear( new SimpleDateFormat( "yyyy" ).format( new Date() ) );
		}
		if( searchVo.getStrMonth() == null ) {
			Calendar c = Calendar.getInstance();
			c.setTime( new Date() );
			
			searchVo.setStrMonth( StringUtils.lpad( String.valueOf( c.get( Calendar.MONTH)+1 ) , 2, "0") );
		}
		model.addAttribute( "list", infoService.getVesselNameList( searchVo, pageable ) );
		model.addAttribute( "codeList", infoService.codeVesselData( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "totalCnt", infoService.codeVesselDataCount( searchVo ) );
		return "info/terminal/codeVessel";
	}
	
	@RequestMapping( "/terminal/codePort" )
	public String terminalCodePort( @PageableDefault( size = 50 ) Pageable pageable, SearchVo searchVo, Model model ) {
		searchVo.setCustomPageable( pageable );
		if( searchVo.getOrderCd() == null || searchVo.getOrderCd().equals( "" ) ) {
			searchVo.setOrderCd( "PORT_NM" );
		}
		model.addAttribute( "list", infoService.codePortData( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "totalCnt", infoService.codePortDataCount( searchVo ) );
		return "info/terminal/codePort";
	}
	
	@RequestMapping( "/terminal/codeShipper" )
	public String terminalCodeShipper( @PageableDefault( size = 50 ) Pageable pageable, SearchVo searchVo, Model model ) {
		searchVo.setCustomPageable( pageable );
		if( searchVo.getOrderCd() == null || searchVo.getOrderCd().equals( "" ) ) {
			searchVo.setOrderCd( "OPER_ENM" );
		}
		model.addAttribute( "list", infoService.codeShipperData( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "totalCnt", infoService.codeShipperDataCount( searchVo ) );
		return "info/terminal/codeShipper";
	}
	
	@RequestMapping( "/terminal/codeDanger" )
	public String terminalCodeDanger( @PageableDefault( size = 50 ) Pageable pageable, SearchVo searchVo, Model model ) {
		searchVo.setCustomPageable( pageable );
		if( searchVo.getCd() == null || searchVo.getCd().equals("") ) {
			searchVo.setCd( "" );
		}
		model.addAttribute( "list", infoService.codeDangerData( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "totalCnt", infoService.codeDangerDataCount( searchVo ) );
		return "info/terminal/codeDanger";
	}
	
	@RequestMapping( "/terminal/emptyContainer" )
	public String terminalEmptyContainer( Model model, SearchVo searchVo ) {
		if( StringUtils.isEmpty( searchVo.getGubun() ) ) {
			searchVo.setGubun( "sound" );
		}
		model.addAttribute( "list", infoService.terminalEmptyContainer( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/terminal/emptyContainer";
	}
	
	@RequestMapping( "/terminal/emptyContainerPrint" )
	public String terminalEmptyContainerPrint( Model model, SearchVo searchVo ) {
		if( StringUtils.isEmpty( searchVo.getGubun() ) ) {
			searchVo.setGubun( "sound" );
		}
		model.addAttribute( "list", infoService.terminalEmptyContainer( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/terminal/emptyContainerPrint";
	}

	// TERMINAL END
	
	
	// CONTAINER LIST START
	
	@RequestMapping( "/cntrList/copino" )
	public String cntrListCopino( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-","") );
		}
		model.addAttribute( "list", infoService.cntrListCopino( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/copino";
	}
	
	@RequestMapping( "/cntrList/copinoPrint")
	public String cntrListCopinoPrint( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-","") );
		}
		model.addAttribute( "list", infoService.cntrListCopino( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/copinoPrint";
	}
	
	@RequestMapping( "/cntrList/gateInOut" )
	public String cntrListGateInOut( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll( "-", "" ) );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll( "-", "" ) );
		}
		
		if( searchVo.getGubun() == null || searchVo.getGubun().equals("") ) {
			searchVo.setGubun("in");
		}
		
		Map<String, Object> resultMap = infoService.cntrListGateInOut( searchVo );
		model.addAttribute( "list", resultMap.get("list") );
		model.addAttribute( "sumMap", resultMap.get("sumMap") );
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		return "info/cntrList/gateInOut";
	}
	
	@RequestMapping( "/cntrList/gateInOutPrint" )
	public String cntrListGateInOutPrint( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll( "-", "" ) );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll( "-", "" ) );
		}
		
		if( searchVo.getGubun() == null || searchVo.getGubun().equals("") ) {
			searchVo.setGubun("in");
		}
		
		Map<String, Object> resultMap = infoService.cntrListGateInOut( searchVo );
		model.addAttribute( "list", resultMap.get("list") );
		model.addAttribute( "searchParams", searchVo );
		
		return "info/cntrList/gateInOutPrint";
	}
	
	@RequestMapping( "/cntrList/notGateInOut" )
	public String cntrListNotGateInOut( SearchVo searchVo, Model model ) {
		if( searchVo.getGubun() == null || searchVo.getGubun().equals("") ) {
			searchVo.setGubun( "out" );
		}
		
		if( searchVo.getShipCd() != null && !searchVo.getShipCd().equals("") && searchVo.getCallNo() != null && !searchVo.getCallNo().equals("") && searchVo.getCallYy() != null && !searchVo.getCallYy().equals("") ) {
			Map<String, Object> resultMap = infoService.cntrListNotGateInOut( searchVo );
			model.addAttribute( "list", resultMap.get("list") );
			model.addAttribute( "sumMap", resultMap.get("sumMap") );
		}
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/notGateInOut";
	}
	
	@RequestMapping( "/cntrList/notGateInOutPrint" )
	public String cntrListNotGateInOutPrint( SearchVo searchVo, Model model ) {
		if( searchVo.getGubun() == null || searchVo.getGubun().equals("") ) {
			searchVo.setGubun( "out" );
		}
		
		if( searchVo.getShipCd() != null && !searchVo.getShipCd().equals("") && searchVo.getCallNo() != null && !searchVo.getCallNo().equals("") && searchVo.getCallYy() != null && !searchVo.getCallYy().equals("") ) {
			Map<String, Object> resultMap = infoService.cntrListNotGateInOut( searchVo );
			model.addAttribute( "list", resultMap.get("list") );
		}
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/notGateInOutPrint";
	}
	
	@RequestMapping( "/cntrList/byVslDisLod" )
	public String cntrListByVslDisLod( SearchVo searchVo, Model model ) {
		if( searchVo.getGubun() == null || searchVo.getGubun().equals( "" ) ) {
			searchVo.setGubun( "down" );
		}
		Map<String, Object> resultMap = infoService.byVslDisLod( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get( "sumMap" ) );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/byVslDisLod";
	}
	
	@RequestMapping( "/cntrList/byVslDisLodPrint" )
	public String cntrListByVslDisLodPrint( SearchVo searchVo, Model model ) {
		if( searchVo.getGubun() == null || searchVo.getGubun().equals( "" ) ) {
			searchVo.setGubun( "down" );
		}
		
		Map<String, Object> resultMap = infoService.byVslDisLod( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "searchParams", searchVo );
		
		return "info/cntrList/byVslDisLodPrint";
	}
	
	@RequestMapping( "/cntrList/cllOverStorage" )
	public String cntrListOverStorage( SearchVo searchVo, Model model ) throws ParseException {
		/*
		 * errorCode
		 * 0 : 모선항차를 입력한 뒤 조회해주세요
		 * 1 : 아직 CLL이 접수되지 않았습니다. (CLL 접수확인 후 다시 조회해주세요)
		 * 2 : 해당 모선의 경과보관 컨테이너가 없습니다.
		 */
		int errorCode = 0;
		if( searchVo.getShipCd() == null || searchVo.getShipCd().equals("") || 
				searchVo.getCallNo() == null || searchVo.getCallNo().equals("") ||
				searchVo.getCallYy() == null || searchVo.getCallYy().equals("") ) {
			model.addAttribute( "errorCode", errorCode );
		} else {			
			
			List<TColdltVo> tColdltVo = infoService.cllOverStorageCheckCll( searchVo );
			
			if( tColdltVo == null ) {
				errorCode = 1;
				model.addAttribute( "errorCode", errorCode );
			} else if( tColdltVo.size() == 0 ) {
				errorCode = 2;
				model.addAttribute( "errorCode", errorCode );
			}
		} 
		model.addAttribute( "list", infoService.cllOverStorage( searchVo ) );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/cllOverStorage";
	}
	
	@RequestMapping( "/cntrList/cllOverStoragePrint" )
	public String cntrListOverStoragePrint( SearchVo searchVo, Model model ) throws ParseException {
		int errorCode = 0;
		if( searchVo.getShipCd() == null || searchVo.getShipCd().equals("") || 
				searchVo.getCallNo() == null || searchVo.getCallNo().equals("") ||
				searchVo.getCallYy() == null || searchVo.getCallYy().equals("") ) {
			model.addAttribute( "errorCode", errorCode );
		} else {			
			
			List<TColdltVo> tColdltVo = infoService.cllOverStorageCheckCll( searchVo );
			
			if( tColdltVo == null ) {
				errorCode = 1;
				model.addAttribute( "errorCode", errorCode );
			} else if( tColdltVo.size() == 0 ) {
				errorCode = 2;
				model.addAttribute( "errorCode", errorCode );
			}
		} 
		model.addAttribute( "list", infoService.cllOverStorage( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/cllOverStoragePrint";
	}
	
	@RequestMapping( "/cntrList/longStack" )
	public String cntrListLongStack( SearchVo searchVo, Model model ) throws ParseException {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		if( StringUtils.isEmpty( searchVo.getYardDay() ) ) searchVo.setYardDay( String.valueOf( 10 ) );
		
		Map<String, Object> resultMap = infoService.cntrListLongStack( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get( "sumMap" ) );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/longStack";
	}
	
	@RequestMapping( "/cntrList/longStackPrint" )
	public String cntrListLongStackPrint( SearchVo searchVo, Model model ) throws ParseException {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		if( StringUtils.isEmpty( searchVo.getYardDay() ) ) searchVo.setYardDay( String.valueOf( 10 ) );
		
		Map<String, Object> resultMap = infoService.cntrListLongStack( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get( "sumMap" ) );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		
		return "info/cntrList/longStackPrint";
	}
	
	@RequestMapping( "/cntrList/cntrCancel" )
	public String cntrListCntrCancel( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.cntrListCntrCancel( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get( "sumMap" ) );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/cntrCancel";
	}
	
	@RequestMapping( "/cntrList/cntrCancelPrint" )
	public String cntrListCntrCancelPrint( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.cntrListCntrCancel( searchVo );
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		return "info/cntrList/cntrCancelPrint";
	}
	
	@RequestMapping( "/cntrList/cntrRehandling" ) 
	public String cntrListCntrRehandling( @PageableDefault(size = 50) Pageable pageable, SearchVo searchVo, Model model ) {
		searchVo.setCustomPageable( pageable );
		model.addAttribute( "list", infoService.cntrListCntrRehandlingList( searchVo ) );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/cntrRehandling";
	}
	
	@RequestMapping( "/cntrList/reeferCargo" )
	public String cntrListReeferCargo( SearchVo searchVo, Model model ) {
		if( searchVo.getGubun() == null || searchVo.getGubun().equals( "" ) ) {
			searchVo.setGubun( "ice" );
		}
		Map<String, Object> resultMap = infoService.cntrListReeferCargo( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get( "sumMap" ) );
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		return "info/cntrList/reeferCargo";
	}
	
	@RequestMapping( "/cntrList/reeferCargoPrint" )
	public String cntrListReeferCargoPrint( SearchVo searchVo, Model model ) {
		if( searchVo.getGubun() == null || searchVo.getGubun().equals( "" ) ) {
			searchVo.setGubun( "ice" );
		}
		Map<String, Object> resultMap = infoService.cntrListReeferCargo( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/reeferCargoPrint";
	}
	
	@RequestMapping( "/cntrList/cntrDanger" )
	public String cntrListCntrDanger( SearchVo searchVo, Model model ) {
		if( searchVo.getGubun() == null || searchVo.getGubun().equals( "" ) ) {
			searchVo.setGubun( "down" );
		}
		
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			Calendar calendar = Calendar.getInstance();
			
			calendar.setTime( new Date() );
			calendar.add( Calendar.DATE, 7);
			
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( calendar.getTime() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		Map<String, Object> resultMap = infoService.cntrListCntrDanger( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get( "sumMap" ) );
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		return "info/cntrList/cntrDanger";
	}
	
	@RequestMapping( "/cntrList/cntrDangerPrint" )
	public String cntrListCntrDangerPrint( SearchVo searchVo, Model model ) {
		if( searchVo.getGubun() == null || searchVo.getGubun().equals( "" ) ) {
			searchVo.setGubun( "down" );
		}
		
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			Calendar calendar = Calendar.getInstance();
			
			calendar.setTime( new Date() );
			calendar.add( Calendar.DATE, 7);
			
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( calendar.getTime() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		Map<String, Object> resultMap = infoService.cntrListCntrDanger( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrList/cntrDangerPrint";
	}

	// CONTAINER LIST END
	
	
	// CONTAINER INFO START
	
	@RequestMapping( "/cntrInfo/details" )
	public String cntrInfoDetails( SearchVo searchVo, Model model ) throws ParseException {
		if( searchVo.getSearch() != null && !searchVo.getSearch().equals("") ) {
			TCntrVo dataVo = infoService.cntrInfoDetailsDataLatest( searchVo );
			dataVo.setCntrNo( searchVo.getSearch() );
			model.addAttribute( "data", dataVo );
			model.addAttribute( "list", infoService.cntrInfoDetailsDataList(searchVo));
			model.addAttribute( "cargoList", infoService.cntrInfoDetailsCargoList( dataVo ) );
			model.addAttribute( "holdList", infoService.cntrInfoDetailsHoldList( dataVo ) );
		}
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrInfo/details";
	}
	
	@RequestMapping( "/cntrInfo/stock" )
	public String cntrInfoStock( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.cntrInfoStock( searchVo );
		model.addAttribute( "list", resultMap.get("list") );
		model.addAttribute( "sumMap", resultMap.get("sumMap") );
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		return "info/cntrInfo/stock";
	}
	
	@RequestMapping( "/cntrInfo/stockPrint" )
	public String cntrInfoStockPrint( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.cntrInfoStock( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get("sumMap") );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrInfo/stockPrint";
	}
	
	@RequestMapping( "/cntrInfo/workingTime" )
	public String cntrInfoWorkingTime( SearchVo searchVo, Model model ) throws ParseException {
		model.addAttribute( "list", infoService.cntrInfoWorkingTime( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrInfo/workingTime";
	}
	
	@RequestMapping( "/cntrInfo/freeTime" )
	public String cntrInfoFreeTime( SearchVo searchVo, Model model ) throws ParseException {
		if( StringUtils.isEmpty( searchVo.getGubun() ) ) {
			searchVo.setGubun( "ship" );
		}
		
		if( StringUtils.isEmpty( searchVo.getFmCd() ) ) {
			searchVo.setFmCd( "F" );
		}
		
		model.addAttribute( "list", infoService.cntrInfoFreeTime( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cntrInfo/freeTime";
	}
	
	// CONTAINER INFO END
	
	
	// ON-DOCK START
	
	@RequestMapping( "/onDock/insertOrder" )
	public String onDockInsertOrder( TBkmstVo tBkmstVo, Model model ) {
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		return "info/onDock/insertOrder";
	}
	
	@RequestMapping( "/onDock/insertOrder/spareCheck" )
	public String onDockInsertOrderSpareCheck( SearchVo searchVo, Model model ) {
		model.addAttribute( "data", infoService.onDockInsertOrderSpareCheck( searchVo ) );
		return "info/onDock/insertOrderSpareCheck";
	}
	
	@RequestMapping( "/onDock/insertOrder/save" )
	@ResponseBody
	public ResponseEntity<?> onDockInsertOrderSave( @RequestBody TBkmstVo tBkmstVo ) {
		return ResponseEntity.ok().body( infoService.onDockInsertOrderSave( tBkmstVo ) );
	}
	
	@RequestMapping( "/onDock/insertOrder/update" )
	@ResponseBody
	public ResponseEntity<?> onDockInsertOrderUpdate( @RequestBody TBkmstVo tBkmstVo ) {
		return ResponseEntity.ok().body( infoService.onDockInsertOrderUpdate( tBkmstVo ) );
	}
	
	@RequestMapping( "/onDock/bookingList" )
	public String onDockBookingList( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		log.info( "SearchVo : " + searchVo.toString() );
		
		model.addAttribute( "list", infoService.onDockBookingList( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/bookingList";
	}
	
	@RequestMapping( "/onDock/bookingDetail" )
	public String onDockBookingDetail( SearchVo searchVo, Model model ) {
		TBkmstVo tBkmstVo = infoService.onDockBookingDetailBkMst( searchVo );
		model.addAttribute( "bkMst", tBkmstVo );
		model.addAttribute( "bkDtl", infoService.onDockBookingDetailBkDtl( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		return "info/onDock/bookingDetail";
	}
	
	@RequestMapping( "/onDock/bookingDetail/update" )
	@ResponseBody
	public ResponseEntity<?> onDockBookingDetailUpdate( @RequestBody SearchVo searchVo ) {
		return ResponseEntity.ok().body( infoService.onDockBookingDetailUpdate( searchVo ) );
	}
	
	@RequestMapping( "/onDock/bookingList/delete" )
	@ResponseBody
	public Map<String, Object> onDockBookingListDelete( @RequestBody SearchVo searchVo ) {
		return infoService.onDockBookingListDelete( searchVo );
	}
	
	@RequestMapping( "/onDock/deliveryManager" )
	public String onDockDeliveryManager( SearchVo searchVo, Model model ) {
		if( StringUtils.isEmpty( searchVo.getOkCd() ) ) {
			searchVo.setOkCd( "ship" );
		}
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "list", infoService.deliveryManager( searchVo ) );
		return "info/onDock/deliveryManager";
	}
	
	@RequestMapping( "/onDock/deliveryConfirm" )
	public String onDockDeliveryConfirm( TBsiotVo tBsiotVo, Model model ) {
		model.addAttribute( "list", infoService.onDockDeliveryConfirmList( tBsiotVo ) );
		model.addAttribute( "selfTrans", infoService.findSelfTransByDoNoAndOperCd( tBsiotVo ) );
		model.addAttribute( "demurChk", infoService.findDemurChk() );
		model.addAttribute( "rtnDt", infoService.findReturnDate( tBsiotVo ) );
		model.addAttribute( "searchParams", tBsiotVo );
		return "info/onDock/deliveryConfirm";
	}
	
	@RequestMapping( "/onDock/deliveryConfirm/save" )
	@ResponseBody
	public ResponseEntity<?> onDockDeliveryConfirmSave( @RequestBody Map<String, String> param ) {
		return ResponseEntity.ok().body( infoService.onDockDeliveryConfirmSave( param ) );
	}
	
	@RequestMapping( "/onDock/stock" )
	public String onDockStock( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.onDockStock( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get( "sumMap" ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/stock";
	}
	
	@RequestMapping( "/onDock/stockPrint" )
	public String onDockStockPrint( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.onDockStock( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/stockPrint";
	}
	
	@RequestMapping( "/onDock/freeVanPool" )
	public String onDockFreeVanPool( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.onDockFreeVanPool( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get( "sumMap" ) );
		return "info/onDock/freeVanPool";
	}
	
	@RequestMapping( "/onDock/freeVanPoolPrint" )
	public String onDockFreeVanPoolPrint( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.onDockFreeVanPool( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get( "sumMap" ) );
		return "info/onDock/freeVanPoolPrint";
	}
	
	@RequestMapping( "/onDock/damage" )
	public String onDockDamage( SearchVo searchVo, Model model ) {
		model.addAttribute( "list", infoService.onDockDamage( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/damage";
	}
	
	@RequestMapping( "/onDock/damagePrint" )
	public String onDockDamagePrint( SearchVo searchVo, Model model ) {
		model.addAttribute( "list", infoService.onDockDamage( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/damagePrint";
	}
	
	@RequestMapping( "/onDock/overStock" )
	public String onDockOverStock( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.onDockOverStock( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "sumMap", resultMap.get( "sumMap" ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/overStock";
	}
	
	@RequestMapping( "/onDock/overStockPrint" )
	public String onDockOverStockPrint( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.onDockOverStock( searchVo );
		model.addAttribute( "list", resultMap.get( "list" ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/overStockPrint";
	}
	
	@RequestMapping( "/onDock/holding" )
	public String onDockHolding( @PageableDefault(size = 50) Pageable pageable, SearchVo searchVo, Model model ) {
		searchVo.setCustomPageable( pageable );
		model.addAttribute( "list", infoService.onDockHolding( searchVo ) );
		model.addAttribute( "totalCnt", infoService.onDockHoldingCnt( searchVo ) );
		model.addAttribute( "holdings", infoService.onDockHoldingHoldingReason( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/holding";
	}
	
	@RequestMapping( "/onDock/holdingPrint" )
	public String onDockHoldingPrint( SearchVo searchVo, Model model ) {
		model.addAttribute( "list", infoService.onDockHoldingEntire( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/holdingPrint";
	}
	
	@RequestMapping( "/onDock/gatePass" )
	public String onDockGatePass( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		model.addAttribute( "list", infoService.onDockGatePass( searchVo ) );
		
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/gatePass";
	}
	
	@RequestMapping( "/onDock/gatePassPrint" )
	public String onDockGatePassPrint( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		model.addAttribute( "list", infoService.onDockGatePass( searchVo ) );
		
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/gatePassPrint";
	}
	
	@RequestMapping( "/onDock/gatePassList" )
	public String onDockGatePassList( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			Calendar c = Calendar.getInstance();
			c.add( Calendar.DATE, -7 );
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( c.getTime() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		model.addAttribute( "list", infoService.onDockGatePassList( searchVo ) );
		
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/gatePassList";
	}
	
	@RequestMapping( "/onDock/gatePassListPrint" )
	public String onDockGatePassListPrint( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			Calendar c = Calendar.getInstance();
			c.add( Calendar.DATE, -7 );
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( c.getTime() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		model.addAttribute( "list", infoService.onDockGatePassList( searchVo ) );
		
		model.addAttribute( "searchParams", searchVo );
		return "info/onDock/gatePassListPrint";
	}
	
	@RequestMapping( "/onDock/gatePass/save")
	@ResponseBody
	public ResponseEntity<?> onDockGatePassSave( @RequestBody TCopinoRjctVo tCopinoRjctVo ) {
		return ResponseEntity.ok().body( infoService.onDockGatePassSave( tCopinoRjctVo ) );
	}
	
	@RequestMapping( "/onDock/gatePassDeleteData" )
	@ResponseBody
	public ResponseEntity<?> onDockGatePassDeleteData( @RequestBody TCopinoRjctVo tCopinoRjctVo ) {
		return ResponseEntity.ok().body( infoService.onDockGatePassDeleteData( tCopinoRjctVo ) );
	}
	
	// ON-DOCK END
	
	
	// CUST-INSPECTION START
	
	@RequestMapping( "/cust/inspection" )
	public String custInspection( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		if( StringUtils.isEmpty( searchVo.getReqId() ) ) {
			searchVo.setReqId( SecurityContextHolder.getContext().getAuthentication().getName() );
		}
		
		model.addAttribute( "reqIdList", infoService.custInspectionGetReqIdList() );
		model.addAttribute( "tSecretVo", infoService.getSessionData() );
		model.addAttribute( "list", infoService.custInspection( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cust/inspection";
	}
	
	@RequestMapping( "/cust/inspection/save" )
	public String custInspectionSave( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.custInspectionSave( searchVo );
		model.addAttribute( "code", resultMap.get( "code" ) );
		model.addAttribute( "msg", resultMap.get( "msg" ) );
		return "info/cust/inspectionSaveResult";
	}
	
	@RequestMapping( "/cust/inspectionDeleteCust" )
	@ResponseBody
	public Map<String, Object> custInspectionDeleteCust( @RequestBody SearchVo searchVo ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		infoService.custInspectionDeleteItem( searchVo );
		infoService.custInspectionDeleteCust( searchVo );
		resultMap.put( "msg", "해당 컨테이너가 삭제되었습니다." );
		resultMap.put( "code", 200 );
		return resultMap;
	}
	
	@RequestMapping("/cust/custItemWindow")
	public String custInspectionCustItemWindow( SearchVo searchVo, Model model ) {
		model.addAttribute( "data", infoService.custInspectionCustItemWindow( searchVo ) );
		model.addAttribute( "itemList", infoService.custInspectionCustItemList( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cust/custItemWindow";
	}
	
	@RequestMapping("/cust/custItemWindowSave")
	public String custItemWindowSave( SearchVo searchVo ) {
		int result = infoService.custItemWindowSave( searchVo );
		return "info/cust/custitemWindowComplete";
	}
	
	@RequestMapping("/cust/custItemWindowDelete")
	@ResponseBody
	public Map<String, Object> custitemWindowDelete( @RequestBody SearchVo searchVo ) {
		return infoService.custItemWindowDelete( searchVo );
	}
	
	@RequestMapping( "/cust/cntrListWindow" )
	public String custCntrListWindow( SearchVo searchVo, Model model ) {
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "list", infoService.custCntrListWindow( searchVo ) );
		return "info/cust/custCntrListWindow";
	}
	
	@RequestMapping( "/cust/inspectionManage" )
	public String custInspectionManage( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "list", infoService.inspectionManage( searchVo ) );
		return "info/cust/inspectionManage";
	}
	
	@RequestMapping( "/cust/inspectionManageSave" )
	public String custInspectionManageSave( SearchVo searchVo, Model model ) {
		Map<String, Object> result = infoService.custInspectionManageSave( searchVo );
		model.addAttribute( "code", result.get("code") );
		model.addAttribute( "msg", result.get("msg") );
		model.addAttribute( "url", searchVo.getUrl() );
		return "info/cust/inspectionManageResult";
	}
	
	@RequestMapping( "/cust/custInspectionView" )
	public String custInspectionView( SearchVo searchVo, Model model ) {
		model.addAttribute( "data", infoService.custInspectionView( searchVo ) );
		model.addAttribute( "item", infoService.custInspectionViewItem( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cust/custInspectionView";
	}
	
	@RequestMapping( "/cust/insPvntView" )
	public String insPvntView( SearchVo searchVo, Model model ) {
		model.addAttribute( "data", infoService.custInspectionView( searchVo ) );
		model.addAttribute( "item", infoService.custInspectionViewItem( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cust/insPvntView";
	}
	
	@RequestMapping( "/cust/custInspectionViewSave")
	@ResponseBody
	public Map<String,Object> custInspectionViewSave( @RequestBody SearchVo searchVo ) {
		Map<String, Object> resultMap = infoService.custInspectionViewSave( searchVo );
		return resultMap;
	}
	
	@RequestMapping( "/cust/insPvnt" )
	public String custInsPvnt( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		if( StringUtils.isEmpty( searchVo.getReqId() ) ) {
			searchVo.setReqId( SecurityContextHolder.getContext().getAuthentication().getName() );
		}
		
		model.addAttribute( "reqIdList", infoService.custInspectionGetReqIdList() );
		model.addAttribute( "tSecretVo", infoService.getSessionData() );
		model.addAttribute( "list", infoService.custInsPvnt( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/cust/insPvnt";
	}
	
	@RequestMapping( "/cust/insPvnt/save" )
	public String custInsPvntSave( SearchVo searchVo, Model model ) {
		Map<String, Object> resultMap = infoService.custInsPvntSave( searchVo );
		model.addAttribute( "code", resultMap.get( "code" ) );
		model.addAttribute( "msg", resultMap.get( "msg" ) );
		return "info/cust/insPvntSaveResult";
	}
	
	@RequestMapping( "/cust/insPvntDeleteCust" )
	@ResponseBody
	public Map<String, Object> custInsPvntDeleteCust( @RequestBody SearchVo searchVo ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		infoService.custInsPvntDeleteItem( searchVo );
		infoService.custInsPvntDeleteCust( searchVo );
		resultMap.put( "msg", "해당 컨테이너가 삭제되었습니다." );
		resultMap.put( "code", 200 );
		return resultMap;
	}
	
	@RequestMapping( "/cust/insPvntManage" )
	public String custInsPvntManage( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "list", infoService.insPvntManage( searchVo ) );
		return "info/cust/insPvntManage";
	}
	
	// CUST-INSPECTION END
	
	
	// EDI START
	
	@RequestMapping( "/edi/copinoList" )
	public String ediCopinoList( SearchVo searchVo, Model model ) {
		if( StringUtils.isEmpty( searchVo.getOkCd() ) ) {
			searchVo.setOkCd( "A" );
		}
		model.addAttribute( "list", infoService.ediCopinoList( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/edi/copinoList";
	}
	
	@RequestMapping( "/edi/copinoListErrWin" )
	public String ediCopinoListErrWin( TGateVo tGateVo, Model model ) {
		model.addAttribute( "data", tGateVo );
		return "info/edi/copinoListErrWin";
	}
	
	@RequestMapping( "/edi/copinoPortList" )
	public String ediCopinoPortList( SearchVo searchVo, Model model ) {
		model.addAttribute( "list", infoService.ediCopinoPortList( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/edi/copinoPortList";
	}
	
	@RequestMapping( "/edi/copinoCarFind" ) 
	public String ediCopinoCarFind( SearchVo searchVo, Model model ) {
		model.addAttribute( "searchParams", searchVo );
		model.addAttribute( "list", infoService.ediCopinoCarFind( searchVo ) );
		return "info/edi/copinoCarFind";
	}
	
	@RequestMapping( "/edi/copinoGateInIns" )
	public String ediCopinoGateInIns( SearchVo searchVo, Model model ) {
		model.addAttribute( "empList", infoService.copinoGateInRsrvEmpList() );
		model.addAttribute( "copinoTimes", infoService.copinoTimes() );
		model.addAttribute( "operList", infoService.copinoGateInInsOperList() );
		return "info/edi/copinoGateInIns";
	}
	
	@RequestMapping( "/edi/copinoGateOutIns" )
	public String ediCopinoGateOutIns( SearchVo searchVo, Model model ) {
		return "info/edi/copinoGateOutIns";
	}
	
	@RequestMapping( "/edi/copinoErrCheck" )
	@ResponseBody
	public Map<String, Object> ediCopinoErrCheck( @RequestBody SearchVo searchVo ) {
		return infoService.ediCopinoErrCheck( searchVo );
	}
	
	@RequestMapping( "/edi/byVslCll" )
	public String ediByVslCll( SearchVo searchVo, Model model ) {
		model.addAttribute( "list", infoService.ediByVslCll( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/edi/byVslCll";
	}
	
	@RequestMapping( "/edi/byVslCll/detail" )
	@ResponseBody
	public List<TColdltVo> ediByVslCllDetail( @RequestBody SearchVo searchVo ) {
		return infoService.ediByVslCllDetail( searchVo );
	}
	
	@RequestMapping( "/edi/copinoGateInRsrv" )
	public String ediCopinoGateInRsrv( SearchVo searchVo, Model model ) {
		TSecretVo tSecretVo = new TSecretVo();
		tSecretVo.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
		tSecretVo = infoService.findTSecretByEmpNo( tSecretVo );
		
		if( tSecretVo.getJo().toUpperCase().equals( "T" ) ) {
			model.addAttribute( "reqId", tSecretVo.getEmpNo() );
			model.addAttribute( "emComp", tSecretVo.getEmpComp() );
		}
		
		model.addAttribute( "empList", infoService.copinoGateInRsrvEmpList() );
		return "info/edi/copinoGateInRsrv";
	}
	
	@RequestMapping( "/edi/copinoGateInRsrvSave" )
	public String ediCopinoGateInRsrvSave( WSajunVo wSajunVo, Model model ) {
		int result = infoService.copinoGateInRsrvSave( wSajunVo );
		return "redirect:/info/edi/copinoGateInRsrv?save=" + result;
	}
	
	@RequestMapping( "/edi/copinoGateInRsrvList" )
	public String ediCopinoGateInRsrvList( @PageableDefault(size = 50) Pageable pageable, SearchVo searchVo, Model model ) {
		searchVo.setCustomPageable( pageable );
		
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		TSecretVo tSecretVo = new TSecretVo();
		tSecretVo.setEmpNo( SecurityContextHolder.getContext().getAuthentication().getName() );
		tSecretVo = infoService.findTSecretByEmpNo(tSecretVo);
		if( tSecretVo.getJo().toUpperCase().equals( "T" ) ) {
			searchVo.setEmpNo( tSecretVo.getEmpNo() );
			model.addAttribute( "reqId",tSecretVo.getEmpNo() );
			model.addAttribute( "emComp", tSecretVo.getEmpComp() );
		}
		model.addAttribute( "list", infoService.ediCopinoGateInRsrvList( searchVo ) );
		model.addAttribute( "totalCnt", infoService.ediCopinoGateInRsrvListCount( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/edi/copinoGateInRsrvList";
	}
	
	@RequestMapping( "/edi/copinoGateInRsrvList/{type}" )
	@ResponseBody
	public Map<String, String> copinoGateInRsrvListProc( @PathVariable(name = "type") String type, @RequestBody WSajunVo wSajunVo ) {
		return infoService.copinoGateInRsrvListProc( wSajunVo, type );
	}
	
	// EDI END
	
	
	// SMS START
	
	@RequestMapping( "/sms/customer" )
	public String smsCustomer( SearchVo searchVo, Model model ) {
		if( !StringUtils.isEmpty( searchVo.getCstNo() ) ) {
			model.addAttribute( "customer", infoService.getCustomerInfo( searchVo ) );
		}
		model.addAttribute( "searchParams", searchVo );
		return "info/sms/customer";
	}
	
	@GetMapping( "/sms/customer/enrol" )
	public String smsCustomerEnrol(Model model) {
		model.addAttribute( "customer", new TCustomerVo() );
		return "info/sms/customerEnrol";
	}
	
	@PostMapping( "/sms/customer/save" )
	@ResponseBody
	public Map<String, Object> smsCustomerSave( @RequestBody TCustomerVo tCustomerVo ) {
		return infoService.smsCustomerSaveProc( tCustomerVo );
	}
	
	@GetMapping( "/sms/customer/modify" )
	public String smsCustomerModify( TCustomerVo tCustomerVo, Model model ) {
		model.addAttribute( "customer", infoService.findCustomer( tCustomerVo ) );
		return "info/sms/customerEnrol";
	}
	
	@RequestMapping( "/sms/details" )
	public String smsDetails( Model model, SearchVo searchVo ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		if( !StringUtils.isEmpty( searchVo.getReqId() ) && !StringUtils.isEmpty( searchVo.getCntrNo() ) ) {
			model.addAttribute( "customer", infoService.smsCustomer( searchVo ) );
			model.addAttribute( "list", infoService.smsDetailsList( searchVo ) );
			model.addAttribute( "vacctNo", infoService.smsDetailVacctNo() );
		}
		model.addAttribute( "searchParams", searchVo );
		return "info/sms/details";
	}
	
	@RequestMapping( "/sms/details/submit" )
	@ResponseBody
	public ResponseEntity<?> smsDetailsSubmit( Model model, @RequestBody Map<String, List<Object>> params ) {
		return ResponseEntity.ok().body( infoService.smsDetailSubmit( params ) );
	}
	
	@RequestMapping( "/sms/details/invoice" )
	public String smsDetailInvoice( Model model, InvoiceVo invoiceVo ) {
		log.info( "invoice Vo : " + invoiceVo.toString() );
		model.addAttribute( "tmnl", infoService.findE1ctTmnlInfo() );
		List<InvoiceVo> invoices = infoService.findInvoiceList( invoiceVo );
		Long totalInvoice = 0L;
		Long totalTax = 0L;
		Long saleAmt = 0L;
		for( int i=0; i<invoices.size(); i++ ) {
			if( invoices.get(i).getTotalInvoice() != null ) totalInvoice += invoices.get(i).getTotalInvoice();
			if( invoices.get(i).getTotalTax() != null ) totalTax += invoices.get(i).getTotalTax();
			if( invoices.get(i).getSaleAmt() != null ) saleAmt += invoices.get(i).getSaleAmt();
		}
		model.addAttribute( "totalInvoice", totalInvoice );
		model.addAttribute( "totalTax", totalTax );
		model.addAttribute( "saleAmt", saleAmt );
		model.addAttribute( "list", invoices );
		TCustomerVo tCustomerVo = new TCustomerVo();
		tCustomerVo.setCstNo( invoiceVo.getCstCd() );
		model.addAttribute( "cust", infoService.findCustomer(tCustomerVo));
		model.addAttribute( "params", invoiceVo );
		return "info/sms/detailsInvoice";
	}
	
	@RequestMapping( "/sms/conclude" )
	public String smsConclude( SearchVo searchVo, Model model ) {
		model.addAttribute( "list", infoService.getConcludeList( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/sms/conclude";
	}
	
	// SMS END
	
	
	// INTRANET START
	
	@RequestMapping( "/intra/damageReq" )
	public String intraDamageReq( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		model.addAttribute( "list", infoService.intraDamageReq( searchVo ) );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/intra/damageReq";
	}
	
	@RequestMapping( "/intra/damageReqSave" )
	@ResponseBody
	public Map<String, Object> intraDamageReqSave( @RequestBody SearchVo searchVo ) {
		return infoService.intraDamageReqSave( searchVo );
	}
	
	@RequestMapping( "/intra/damageCom" )
	public String intraDamageCom( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		model.addAttribute( "list", infoService.intraDamageCom( searchVo ) );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/intra/damageCom";
	}
	
	@RequestMapping( "/intra/intrDamage" )
	public String intraIntrDamage( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		model.addAttribute( "list", infoService.intraIntrDamage( searchVo ) );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/intra/intrDamage";
	}
	
	@RequestMapping( "/intra/intrDamagePrint" )
	public String intraIntrDamagePrint( SearchVo searchVo, Model model ) {
		if( searchVo.getSearchStartDt() == null ) {
			searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchStartDt( searchVo.getSearchStartDt().replaceAll("-", "") );
		}
		
		if( searchVo.getSearchEndDt() == null ) {
			searchVo.setSearchEndDt( new SimpleDateFormat("yyyyMMdd").format( new Date() ) );
		} else {
			searchVo.setSearchEndDt( searchVo.getSearchEndDt().replaceAll("-", ""));
		}
		
		model.addAttribute( "list", infoService.intraIntrDamage( searchVo ) );
		model.addAttribute( "operList", infoService.getOperListByAuthority() );
		model.addAttribute( "searchParams", searchVo );
		return "info/intra/intrDamagePrint";
	}
	
	@RequestMapping( "/intra/vesselDetail" )
	public String intraVesselDetail( SearchVo searchVo, Model model ) {
		model.addAttribute( "ccList", infoService.intraVesselDetailGetCcInfo() );
		model.addAttribute( "searchParams", searchVo );
		return "info/intra/vesselDetail";
	}
	
	@RequestMapping( "/intra/vesselDetail/load" )
	@ResponseBody
	public Map<String, Object> intraVesselDetailLoad( @RequestBody SearchVo searchVo ) {
		return infoService.intraVesselDetailLoad( searchVo );
	}
	
	@RequestMapping( "/intra/vesselDetail/getBays" )
	@ResponseBody
	public Map<String, Object> intraVesselDetailGetBays( @RequestBody SearchVo searchVo ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put( "list", infoService.intraVesselDetailGetBays( searchVo ) );
		return resultMap;
	}
	
	@RequestMapping( "/intra/vesselDetail/getShip" )
	@ResponseBody
	public Map<String, Object> intraVesselDetailGetShip( @RequestBody SearchVo searchVo ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put( "list", infoService.intraVesselDetailGetShip( searchVo ) );
		resultMap.put( "param", searchVo );
		return resultMap;
	}
	
	// INTRANET END
	
	// POPUP
	@RequestMapping( "/popup/cntrDetail" )
	public String popupCntrDetail( SearchVo searchVo, Model model ) throws ParseException {
		searchVo.setSearch( searchVo.getCntrNo() );
		TCntrVo dataVo = infoService.cntrInfoDetailsDataLatest( searchVo );
		model.addAttribute( "data", dataVo );
		model.addAttribute( "searchParams", searchVo );
		return "info/popup/cntrDetail";
	}
	
	@RequestMapping( "/popup/cntrDetailTwo" )
	public String popupCntrDetailTwo( SearchVo searchVo, Model model ) throws ParseException {
		searchVo.setSearch( searchVo.getCntrNo() );
		model.addAttribute( "data", infoService.cntrInfoDetailsDataLatest( searchVo ) );
		model.addAttribute( "list", infoService.popupCntrDetailTwo( searchVo ) );
		model.addAttribute( "searchParams", searchVo );
		return "info/popup/cntrDetailTwo";
	}


}
