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
public class TTruckerVo extends VO {
	private @NonNull String truckerCd;
	private @NonNull String sndId;
	private String cstTel;
	private String cstNm;
	private String trdamNm;
}
