package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBsiotSubVo;
import kr.co.e1ct.vo.TCntrVo;

@Mapper
public interface TBsiotSubRepository {

	public TBsiotSubVo findSealNoByShipCdAndCallNoAndCallYyAndCntrNoAndDupNo(TBsiotSubVo paramVo);
	public List<TBsiotSubVo> findCargoNoListByCntrNoAndDupNo(TCntrVo dataVo);
	public List<TBsiotSubVo> custCntrListWindow(SearchVo searchVo);
	public List<TBsiotSubVo> cntrListLongStackCargoNoList(TCntrVo tCntrVo);
	public TBsiotSubVo findByCntrNoAndDupNoAndBanchCd(SearchVo searchVo);
	public TBsiotSubVo findCntrNoFromSlcBycntrNoAndDupNo(SearchVo searchVo);
	public TBsiotSubVo findCntrNoByCntrNoAndDupNo(SearchVo searchVo);
	public TBsiotSubVo findCntrNoWmConcatByCargoNo(SearchVo searchVo);

}
