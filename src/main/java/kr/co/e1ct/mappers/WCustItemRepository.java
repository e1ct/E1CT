package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.WCustHdVo;
import kr.co.e1ct.vo.WCustItemVo;

@Mapper
public interface WCustItemRepository {

	public int custInspectionDeleteItem(SearchVo searchVo);
	public List<WCustItemVo> custInspectionCustItemList(SearchVo searchVo);
	public int findCountByItemIdAndCustId(SearchVo paramVo);
	public int findCountByItemIdAndCustId(WCustHdVo paramVo);
	public int updateByItemIdAndCustId(SearchVo paramVo);
	public WCustItemVo findMaxItemIdByCustId(SearchVo paramVo);
	public int insertWCustItem(SearchVo paramVo);
	public int deleteByItemIdAndCustId(SearchVo searchVo);
	public int findCountByCustId(SearchVo paramVo);
	public List<WCustItemVo> custInspectionViewItem(SearchVo searchVo);
	public int custInsPvntDeleteItem(SearchVo searchVo);
	public int custInsPvntDeleteCust(SearchVo searchVo);

}
