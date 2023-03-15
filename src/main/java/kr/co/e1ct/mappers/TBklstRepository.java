package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBklstVo;
import kr.co.e1ct.vo.TBkmstVo;

@Mapper
public interface TBklstRepository {

	public TBklstVo findBkNoByCntrNoAndDupNoAndOperCd(TBklstVo paramVo);
	public TBklstVo findTruckerCdByOperCdAndBkNo(TBkmstVo tBkmstVo);
	public List<TBklstVo> findCntrNoAndDupNoAndOperCdByBkNoAndOperCdAndCurStat(SearchVo searchVo);
	public int deleteByBkNoAndOperCdAndSzCdAndTyGrpCd(SearchVo searchVo);
	public TBklstVo findBkNoByBkNoAndCntrNoAndDupNo(SearchVo searchVo);

}
