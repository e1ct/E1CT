package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TOwnerBklstVo;

@Mapper
public interface TOwnerBklstRepository {

	public TOwnerBklstVo findByOwnerNm(SearchVo searchVo);

}
