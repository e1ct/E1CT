package kr.co.e1ct.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthorityVo {

	private String username;
	private String authorityName;
	
	@Builder
	public AuthorityVo( String username, String authorityName ) {
		this.username = username;
		this.authorityName = authorityName;
	}
}
