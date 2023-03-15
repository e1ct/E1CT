package kr.co.e1ct.vo;

import org.springframework.security.core.userdetails.User;

public class SecurityMember extends User {

	private static final long serialVersionUID = 1L;

	public SecurityMember( TSecretVo tSecretVo ) {
		super( tSecretVo.getEmpNo(), tSecretVo.getEmpPwd(), tSecretVo.getAuthorities() );
	}
}
