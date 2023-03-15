package kr.co.e1ct.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.e1ct.services.BoardService;
import kr.co.e1ct.vo.WBoardVo;

@Controller
@RequestMapping( "/community" )
public class CommunityController {
	
	@Autowired
	private BoardService boardService;

	@RequestMapping( "/news" )
	public String news( Model model, @PageableDefault(size = 10) Pageable pageable ) {
		WBoardVo wBoardVo = new WBoardVo();
		wBoardVo.setMType( "NW" );
		wBoardVo.setCustomPageable(pageable);
		model.addAttribute( "list", boardService.findPageByMType( wBoardVo ) );
		model.addAttribute( "pager", boardService.findPageByMTypeCount( wBoardVo ) );
		return "community/news";
	}
	
	@RequestMapping( "/news/view" )
	public String newsView( Model model, WBoardVo wBoardVo ) {
		boardService.mReadnumAdd( wBoardVo );
		model.addAttribute( "data", boardService.findOne( wBoardVo ) );
		model.addAttribute( "params", wBoardVo );
		return "community/newsView";
	}
	
	@RequestMapping( "/news/write" )
	public String newsWrite( Model model, WBoardVo wBoardVo ) {
		model.addAttribute( "params", wBoardVo );
		return "community/newsForm";
	}
	
	@RequestMapping( "/news/modify" )
	public String newsModify( Model model, WBoardVo wBoardVo ) {
		model.addAttribute( "board", boardService.findOne( wBoardVo ) );
		model.addAttribute( "params", wBoardVo );
		return "community/newsForm";
	}
	
	@RequestMapping( "/notice" )
	public String notice( Model model, @PageableDefault(size = 10) Pageable pageable ) {
		WBoardVo wBoardVo = new WBoardVo();
		wBoardVo.setMType( "NT" );
		wBoardVo.setCustomPageable(pageable);
		model.addAttribute( "list", boardService.findPageByMType( wBoardVo ) );
		model.addAttribute( "pager", boardService.findPageByMTypeCount( wBoardVo ) );
		return "community/notice";
	}
	
	@RequestMapping( "/notice/view" )
	public String noticeView( Model model, WBoardVo wBoardVo ) {
		boardService.mReadnumAdd( wBoardVo );
		model.addAttribute( "data", boardService.findOne( wBoardVo ) );
		model.addAttribute( "params", wBoardVo );
		return "community/noticeView";
	}
	
	@RequestMapping( "/notice/write" )
	public String noticeWrite( Model model, WBoardVo wBoardVo ) {
		model.addAttribute( "params", wBoardVo );
		return "community/noticeForm";
	}
	
	@RequestMapping( "/notice/modify" )
	public String noticeModify( Model model, WBoardVo wBoardVo ) {
		model.addAttribute( "board", boardService.findOne( wBoardVo ) );
		model.addAttribute( "params", wBoardVo );
		return "community/noticeForm";
	}
	
	@RequestMapping( "/reference" )
	public String reference( Model model, @PageableDefault(size = 10) Pageable pageable ) {
		WBoardVo wBoardVo = new WBoardVo();
		wBoardVo.setMType( "DT" );
		wBoardVo.setCustomPageable(pageable);
		model.addAttribute( "list", boardService.findPageByMType( wBoardVo ) );
		model.addAttribute( "pager", boardService.findPageByMTypeCount( wBoardVo ) );
		return "community/reference";
	}
	
	@RequestMapping( "/reference/view" )
	public String referenceView( Model model, WBoardVo wBoardVo ) {
		boardService.mReadnumAdd( wBoardVo );
		model.addAttribute( "data", boardService.findOne( wBoardVo ) );
		model.addAttribute( "params", wBoardVo );
		return "community/referenceView";
	}
	
	@RequestMapping( "/reference/write" )
	public String referenceWrite( Model model, WBoardVo wBoardVo ) {
		model.addAttribute( "params", wBoardVo );
		return "community/referenceForm";
	}
	
	@RequestMapping( "/reference/modify" )
	public String referenceModify( Model model, WBoardVo wBoardVo ) {
		model.addAttribute( "board", boardService.findOne( wBoardVo ) );
		model.addAttribute( "params", wBoardVo );
		return "community/referenceForm";
	}
	
	@RequestMapping( "/faq" )
	public String faq( Model model, @PageableDefault(size = 10) Pageable pageable ) {
		WBoardVo wBoardVo = new WBoardVo();
		wBoardVo.setMType( "FQ" );
		model.addAttribute( "list", boardService.findPageByMType( wBoardVo ) );
		return "community/faq";
	}
	
	@RequestMapping( "/shipping" )
	public String shipping() {
		return "community/shipping";
	}
}
