package kr.co.e1ct.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MenuVo {

	private String url;
	private String menuNameKo;
	private String menuNameEn;
	
	public MenuVo( String url, String menuNameKo, String menuNameEn ) {
		this.url = url;
		this.menuNameKo = menuNameKo;
		this.menuNameEn = menuNameEn;
	}
	
}
