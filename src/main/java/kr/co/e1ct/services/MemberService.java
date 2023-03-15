package kr.co.e1ct.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.e1ct.mappers.TSecretRepository;
import kr.co.e1ct.vo.SecurityMember;
import kr.co.e1ct.vo.TSecretVo;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@NoArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {
	
	@Autowired
	private TSecretRepository tSecretRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TSecretVo searchVo = new TSecretVo();
		searchVo.setEmpNo( username );
		
		TSecretVo tSecretVo = tSecretRepository.findByEmpNo( searchVo );
		
		if( tSecretVo != null ) {
			tSecretVo.setAuthorities( makeGrantedAuthority( tSecretVo.getJo() ) );
			
		}
		
		return new SecurityMember( tSecretVo );
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority( String role ) {
		List<GrantedAuthority> list = new ArrayList<>();
		list.add( new SimpleGrantedAuthority( "ROLE_" + role ) );
		return list;
	}
}
