package kr.co.e1ct.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.e1ct.services.BoardService;
import kr.co.e1ct.vo.WBoardVo;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping( "/board" )
@AllArgsConstructor
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping( "/save" )
	@ResponseBody
	public ResponseEntity<?> boardSave( WBoardVo wBoardVo ) throws Exception{
		return ResponseEntity.ok().body( boardService.save( wBoardVo ) );
	}
	
	@RequestMapping( "/delete" )
	@ResponseBody
	public ResponseEntity<?> boardDelete( WBoardVo wBoardVo ) {
		return ResponseEntity.ok().body( boardService.delete( wBoardVo ) );
	}
}
