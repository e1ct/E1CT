package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBkmstVo;

@Mapper
public interface TBkmstRepository {

	public List<TBkmstVo> onDockBookingList(SearchVo searchVo);
	public List<TBkmstVo> onDockBookingListForT(SearchVo searchVo);
	public int deleteByBkNoAndOperCd(SearchVo searchVo);
	public List<TBkmstVo> findSzCdAndTyGrpCdAndCyCdAndBkEamtAndBkAamtAndHandCdByBkNoAndOperCd(SearchVo searchVo);
	public TBkmstVo findByOperCdAndBkNoAndTransCd(SearchVo searchVo);
	public TBkmstVo findOneByOperCdAndBkNoBkMst(SearchVo searchVo);
	public List<TBkmstVo> findByOperCdAndBkNo(SearchVo searchVo);
	public int insertOrder(TBkmstVo tBkmstVo);
	public TBkmstVo onDockBookingDetailUpdate(SearchVo searchVo);
	public int updateBookingDetail(SearchVo operListAdded);

}
