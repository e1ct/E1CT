package kr.co.e1ct.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TSecretVo extends VO {
	private String 	empNo;
	private String 	empPwd;
	private String 	empNm;
	private String 	jo;
	private String 	operCd;
	private String	pwdChgDtm;
	private String 	pwdChgYn;
	private String	pwdChgIp;
	private String 	empTel;
	private String 	empComp;
	private String 	relsPwd;
	private String  orgOperCd;
	
	private Collection<? extends GrantedAuthority> authorities;
}
