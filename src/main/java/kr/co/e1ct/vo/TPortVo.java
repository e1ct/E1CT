package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TPortVo extends VO {
	private @NonNull String portCd;
	private @NonNull String countryCd;
	private String portNm;
	private String portCd1;
	private long forecolor;
	private long backcolor;
	private String portDis;
	private String locTy;
	private String orgPortCd;
}
