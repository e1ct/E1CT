package kr.co.e1ct.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VO {
	private boolean isNew = false;

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	// for pagination
	
	private int rnum;
	private int totcnt;
	
	private int startRow;
	private int endRow;
	private int pageIndex = 1;
	private int pageSize = 10;
	private int pageGroupSize = 10;
	
	// for search filter
	private String searchText;
	private String searchStartDt;
	private String searchEndDt;
}
