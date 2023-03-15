package kr.co.e1ct.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.e1ct.services.BoardService;
import kr.co.e1ct.vo.WBoardVo;

@Controller
@RequestMapping( "/terminal" )
public class TerminalController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping( "/facilities" ) 
	public String facilities() {
		return "redirect:/terminal/facilities/terminal";
	}
	
	@RequestMapping( "/facilities/terminal" )
	public String facilitiesTerminal() {
		return "terminal/facilities";
	}
	
	@RequestMapping( "/facilities/yard")
	public String facilitiesYard() {
		return "terminal/facilitiesYard";
	}

	@RequestMapping( "/equipment" )
	public String equipment() {
		return "terminal/equipment";
	}
	
	@RequestMapping( "/feature" )
	public String feature() {
		return "terminal/feature";
	}
	
	@RequestMapping( "/movie" )
	public String movie() {
		return "terminal/movie";
	}
	
	@RequestMapping( "/brochure" )
	public String brochure() {
		return "terminal/brochure";
	}
	
	@RequestMapping( "/gallery" )
	public String gallery( Model model, @PageableDefault(size = 6) Pageable pageable ) {
		WBoardVo wBoardVo = new WBoardVo();
		wBoardVo.setMType( "GA" );
		wBoardVo.setCustomPageable(pageable);
		model.addAttribute( "list", boardService.findPageByMType( wBoardVo ) );
		model.addAttribute( "pager", boardService.findPageByMTypeCount( wBoardVo ) );
		return "terminal/gallery";
	}
	
	@RequestMapping( "/gallery/write" )
	public String galleryWrite( Model model, WBoardVo wBoardVo ) {
		model.addAttribute( "params", wBoardVo );
		return "terminal/galleryForm";
	}
	
	@RequestMapping( "/gallery/delete" )
	@ResponseBody
	public ResponseEntity<?> galleryDelete( WBoardVo wBoardVo ) {
		return ResponseEntity.ok().body( boardService.delete(wBoardVo) );
	}
}
