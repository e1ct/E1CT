package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBkdtlVo;

@Mapper
public interface TBkdtlRepository {

	public TBkdtlVo findBkAamtByBknoAndOperCdAndSzCdAndTyGrpCd(SearchVo searchVo);
	public int deleteByBkNoAndOperCdAndSzCdAndTyGrpCd(SearchVo searchVo);
	public TBkdtlVo findCountByBkNoAndOperCd(SearchVo searchVo);
	public TBkdtlVo findByBkNoAndOperCdAndBkCd(SearchVo searchVo);
	public TBkdtlVo findSzCdAndTyGrpCdAndBkEamtByOperCdANdBkNo(SearchVo searchVo);
	public int insertOperCdAndBkNoAndSzCdAndTyGrpCdAndBkEamtAndReqArrDtAndCyCd(TBkdtlVo tBkdtlVo);
	public int onDockInsertOrderUpdateDelete(SearchVo searchVo);
	public TBkdtlVo onDockBookingDetailUpdate(SearchVo operListAdded);
	public TBkdtlVo findByOperCdAndBkNoAndSzCdAndTyGrpCd(SearchVo operListAdded);
	public int insertBookingDetail(SearchVo operListAdded);
	public int updateBookingDetail(SearchVo operListAdded);
}
