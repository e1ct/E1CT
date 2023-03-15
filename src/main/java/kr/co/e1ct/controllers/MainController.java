package kr.co.e1ct.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.e1ct.services.BoardService;
import kr.co.e1ct.services.MainService;
import kr.co.e1ct.vo.WBoardVo;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private BoardService boardService;

	@RequestMapping( value = "/" )
	public String main( Model model ) {
		model.addAttribute( "yardWorkStatus", mainService.yardWorkService() );
		model.addAttribute( "yardWorkCount", mainService.yardWorkCount() );
		model.addAttribute( "boardList", mainService.mainBoardList() );
		model.addAttribute( "slideList", mainService.slideList() );
		return "index";
	}
	
	@RequestMapping( value = "/noticePopup/{mSeq}" )
	public String noticePopup( Model model, @PathVariable(name = "mSeq") String mSeq ) {
		WBoardVo wBoardVo = new WBoardVo();
		wBoardVo.setMSeq( Long.valueOf( mSeq ) );
		model.addAttribute( "board", boardService.findOne( wBoardVo ) );
		return "noticePopup";
	}
	
	@RequestMapping("/privacy")
	public String privacy() {
		return "privacy";
	}
	
	@RequestMapping("/emailcollect")
	public String emailcollect() {
		return "emailcollect";
	}

}
