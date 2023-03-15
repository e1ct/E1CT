package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TCopinoChkinVo;

@Mapper
public interface TCopinoChkinRepository {

	public TCopinoChkinVo findByCntrNoAndOperCd(SearchVo searchVo);

}
