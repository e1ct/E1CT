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
public class TUnnoVo extends VO {
	private @NonNull String unno;
	private String dgYtype;
	private String dgFgrade;
	private String imdg;
	private String dgNm;
	private float dgJisu;
	private @NonNull String packingGroup;
}
