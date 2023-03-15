package kr.co.e1ct.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.e1ct.services.BoardService;
import kr.co.e1ct.vo.WBoardVo;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/company")
@Slf4j
public class CompanyController {
	
	@Autowired
	private BoardService boardService;

	@RequestMapping( "/greeting" )
	public String greeting() {
		return "company/greeting";
	}
	
	@RequestMapping( "/history" )
	public String history() {
		return "company/history";
	}
	
	@RequestMapping( "/organization" )
	public String organization() {
		return "company/organization";
	}
	
	@RequestMapping( "/location" )
	public String location() {
		return "company/location";
	}
	
	@RequestMapping( "/contact" )
	public String contact() {
		return "company/contact";
	}
	
	@RequestMapping( "/ci" )
	public String ci() {
		return "company/ci";
	}
	
	@RequestMapping( "/ci/intro" )
	public String ciIntro() {
		return "company/ci";
	}
	
	@RequestMapping( "/ci/mean" )
	public String ciMean() {
		return "company/ci_mean";
	}
	
	@RequestMapping( "/ci/design" )
	public String ciDesign() {
		return "company/ci_design";
	}
	
	@RequestMapping( "/ci/apply" )
	public String ciApply() {
		return "company/ci_apply";
	}
	
	@RequestMapping( "/notice" )
	public String notice( Model model, WBoardVo wBoardVo, @PageableDefault(size = 10) Pageable pageable ) {
		wBoardVo.setMType( "AT" );
		wBoardVo.setCustomPageable(pageable);
		model.addAttribute( "list", boardService.findPageByMType( wBoardVo ) );
		model.addAttribute( "pager", boardService.findPageByMTypeCount( wBoardVo ) );
		return "company/notice";
	}
	
	@RequestMapping( "/notice/view" )
	public String noticeView( Model model, WBoardVo wBoardVo ) {
		boardService.mReadnumAdd( wBoardVo );
		model.addAttribute( "data", boardService.findOne( wBoardVo ) );
		model.addAttribute( "params", wBoardVo );
		return "company/noticeView";
	}
	
	@RequestMapping( "/notice/write" )
	public String noticeWrite( Model model, WBoardVo wBoardVo ) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		wBoardVo.setMPfrom( sdf.format( c.getTime() ) );
		c.add( Calendar.DATE, 7 );
		wBoardVo.setMPto( sdf.format(c.getTime() ) );
		model.addAttribute( "params", wBoardVo );
		return "company/noticeForm";
	}
	
	@RequestMapping( "/notice/save" )
	@ResponseBody
	public ResponseEntity<?> noticeSave( WBoardVo wBoardVo ) throws Exception {
		return ResponseEntity.ok( boardService.save( wBoardVo ) );
	}
	
	@RequestMapping( "/notice/modify" )
	public  String noticeModify( Model model, WBoardVo wBoardVo ) {
		model.addAttribute( "board", boardService.findOne( wBoardVo ) );
		model.addAttribute( "params", wBoardVo );
		return "company/noticeForm";
	}
	
	@RequestMapping( "/safeservice" )
	public String safeService() {
		return "company/safeService";
	}
	
	@RequestMapping( "/hr" )
	public String hr() {
		return "company/hr";
	}
	
	@RequestMapping( "/recruit" )
	public String recruit( Model model, WBoardVo wBoardVo, @PageableDefault(size = 10) Pageable pageable ) {
		wBoardVo.setMType( "RE" );
		wBoardVo.setCustomPageable(pageable);
		model.addAttribute( "list", boardService.findPageByMType( wBoardVo ) );
		model.addAttribute( "pager", boardService.findPageByMTypeCount( wBoardVo ) );
		return "company/recruit";
	}
	
	@RequestMapping( "/recruit/view" )
	public String recruitView( Model model, WBoardVo wBoardVo ) {
		model.addAttribute( "data", boardService.findOne( wBoardVo ) );
		model.addAttribute( "params", wBoardVo );
		return "company/recruitView";
	}
	
	@RequestMapping( "/recruit/write" )
	public String recruitWrite( Model model, WBoardVo wBoardVo, @PageableDefault(size=10) Pageable pageable ) {
		model.addAttribute( "params", wBoardVo );
		return "company/recruitForm";
	}
	
	@RequestMapping( "/recruit/modify" )
	public String recruitModify( Model model, WBoardVo wBoardVo ) {
		model.addAttribute( "board", boardService.findOne(wBoardVo) );
		model.addAttribute( "params", wBoardVo );
		return "company/recruitForm";
	}
	
	@RequestMapping( "/support" )
	public String support() {
		return "company/support";
	}
}
