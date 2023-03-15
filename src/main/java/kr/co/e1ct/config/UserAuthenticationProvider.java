package kr.co.e1ct.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import kr.co.e1ct.services.TSecretService;
import kr.co.e1ct.vo.TSecretVo;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired TSecretService tSecretService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String empNo = authentication.getName();
		String empPwd = (String) authentication.getCredentials();
		
		TSecretVo vo = tSecretService.authenticate( empNo, empPwd );
		if( vo == null ) {
			throw new BadCredentialsException( "login error" );
		}
		vo.setEmpPwd( null );
		
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add( new SimpleGrantedAuthority( "ROLE_" + vo.getJo() ) );
		return new UsernamePasswordAuthenticationToken( vo, null, authorities );
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals( UsernamePasswordAuthenticationToken.class );
	}
}
