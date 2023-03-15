package kr.co.e1ct.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.e1ct.services.AdminService;
import kr.co.e1ct.services.BoardService;
import kr.co.e1ct.util.StringUtils;
import kr.co.e1ct.vo.TSecretVo;
import kr.co.e1ct.vo.WBoardVo;

@Controller
@RequestMapping( "/admin" )
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BoardService boardService;

	@RequestMapping( { "", "/" } )
	public String index( Model model ) {
		return "admin/index";
	}
	
	@RequestMapping( "/member" )
	public String memberManage( Model model ) {
		model.addAttribute( "list", adminService.findMemberList() );
		return "admin/member";
	}
	
	@RequestMapping( "/member/enrol" )
	public String memberEnrol( Model model ) {
		return "admin/memberEnrol";
	}
	
	@RequestMapping( "/member/enrol/proc" )
	@ResponseBody
	public ResponseEntity<?> memberEnrolProc( TSecretVo tSecretVo ) {
		return ResponseEntity.ok().body( adminService.memberEnrolProc( tSecretVo ) );
	}
	
	@RequestMapping( "/member/delete" )
	@ResponseBody
	public ResponseEntity<?> memberDelete( TSecretVo tSecretVo ) {
		return ResponseEntity.ok().body( adminService.memberDelete( tSecretVo ) );
	}
	
	@RequestMapping( "/member/oper/{empNo}")
	public String memberOper( Model model, @PathVariable(name = "empNo") String empNo ) {
		TSecretVo tSecretVo = new TSecretVo();
		tSecretVo.setEmpNo( empNo );
		model.addAttribute( "data", adminService.findMember( tSecretVo ) );
		return "admin/memberOper";
	}
	
	@RequestMapping( "/member/oper/proc" )
	@ResponseBody
	public ResponseEntity<?> memberOperProc( TSecretVo tSecretVo ) {
		return ResponseEntity.ok().body( adminService.memberOperProc(tSecretVo) );
	}
	
	@RequestMapping( "/slide" )
	public String slideManage( Model model ) {
		WBoardVo wBoardVo = new WBoardVo();
		wBoardVo.setMType( "MS" );
		model.addAttribute( "list", boardService.findByMType( wBoardVo ) );
		return "admin/slide";
	}
	
	@RequestMapping( "/slide/enrol" )
	public String slideEnrol( Model model ) {
		return "admin/slideEnrol";
	}
	
	@RequestMapping( "/slide/modify/{MSeq}")
	public String slideModify( Model model, @PathVariable(name = "MSeq") Long mSeq ) {
		WBoardVo wBoardVo = new WBoardVo();
		wBoardVo.setMSeq(mSeq);
		model.addAttribute( "data", adminService.findSlideOne( wBoardVo ) );
		return "admin/slideEnrol";
	}
	
	@RequestMapping( "/slide/save" )
	@ResponseBody
	public ResponseEntity<?> slideSave( WBoardVo wBoardVo, HttpServletRequest request ) throws Exception {
		wBoardVo.setMType( "MS" );
		wBoardVo.setMIp( StringUtils.getRemoteIp(request) );
		return ResponseEntity.ok().body( adminService.slideSave( wBoardVo ) );
	}
	
	@RequestMapping( "/slide/delete" )
	@ResponseBody
	public ResponseEntity<?> slideDelete( WBoardVo wBoardVo ) {
		return ResponseEntity.ok().body( adminService.slideDelete( wBoardVo ) );
	}
}
