package kr.co.e1ct.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import kr.co.e1ct.services.ExcelService;
import kr.co.e1ct.services.InfoService;
import kr.co.e1ct.util.StringUtils;
import kr.co.e1ct.view.ExcelXlsView;
import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TColdltVo;

@Controller
@RequestMapping("/excel")
public class ExcelController {
	
	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private InfoService infoService;
	
	@RequestMapping("/berthText")
	public View excelBerthText( Model model, SearchVo searchVo ) throws ParseException {
		Map<String, ? > map = excelService.berthTextExcel( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping("/cntrList/copino")
	public View excelCntrListCopino( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelCntrListCopino( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/cntrList/gateInOut" )
	public View excelCntrListGateInOut( Model model, SearchVo searchVo ) {
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
		Map<String, ?> map = excelService.excelCntrListGateInOut( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/cntrList/notGateInOut" )
	public View excelCntrListNotGateInOut( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelCntrListNotGateInOut( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/cntrList/byVslDisLod" )
	public View excelCntrListByVslDisLod( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelCntrListByVslDisLod( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/cntrList/cllOverStorage" )
	public View excelCntrListCllOverStorage( Model model, SearchVo searchVo ) throws ParseException {
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
		Map<String, ?> map = excelService.excelCntrListCllOverStorage( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/cntrList/cntrCancel" )
	public View excelCntrListCntrCancel( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelCntrListCntrCancel( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/cntrList/reeferCargo" )
	public View excelCntrListReeferCargo( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelCntrListReeferCargo( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/cntrList/cntrDanger" )
	public View excelCntrListCntrDanger( Model model, SearchVo searchVo ) {
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
		
		Map<String, ?> map = excelService.excelCntrListCntrDanger( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/cntrList/longStack" )
	public View excelCntrListLongStack( Model model, SearchVo searchVo ) throws ParseException {
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
		
		Map<String, ?> map = excelService.excelCntrListLongStack( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/cntrInfo/stock" )
	public View excelCntrInfoStock( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelCntrInfoStock( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/onDock/overStock" )
	public View excelOnDockOverStock( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelOnDockOverStock( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/onDock/stock" )
	public View excelOnDockStock( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelOnDockStock( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/onDock/damage" )
	public View excelOnDockDamage( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelOnDockDamage( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/onDock/holding" )
	public View excelOnDockHolding( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelOnDockHolding( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/onDock/gatePass" )
	public View excelOnDockGatePass( Model model, SearchVo searchVo ) {
		Map<String, ?> map = excelService.excelOnDockGatePass( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/intra/intrDamage" )
	public View excelIntraIntrDamage( Model model, SearchVo searchVo ) {
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
		
		Map<String, ?> map = excelService.excelIntraIntrDamage( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
	
	@RequestMapping( "/emptyContainer" )
	public View excelEmptyContainer( Model model, SearchVo searchVo ) {
		if( StringUtils.isEmpty( searchVo.getGubun() ) ) {
			searchVo.setGubun( "sound" );
		}
		Map<String, ?> map = excelService.excelEmptyContainer( searchVo );
		model.addAllAttributes( map );
		return new ExcelXlsView();
	}
}
