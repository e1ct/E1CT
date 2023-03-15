package kr.co.e1ct.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@GetMapping( "/signUp" )
	public String signUp() {
		return "member/signUp";
	}
	
	@PostMapping( "/signUp" )
	public String signUpProcess( ) {
		return "redirect:/signIn";
	}
	
	@GetMapping( "/signIn" )
	public String signIn() {
		return "member/signIn";
	}

	@RequestMapping( "/signIn/result" )
	public String signInResult() {
		return "redirect:/";
	}
	
	@PostMapping( "/signOut" )
	public String signOut() {
		return "member/signOut";
	}
	
	@RequestMapping( "/signOut/result" )
	public String signOutResult() {
		return "redirect:/";
	}
	
	@RequestMapping( "/invalid" )
	public String memberInvalid() {
		return "member/invalid";
	}
	
	@RequestMapping( "/accessDenied" )
	public String accessDenied( Model model ) {
		return "member/accessDenied";
	}
}
