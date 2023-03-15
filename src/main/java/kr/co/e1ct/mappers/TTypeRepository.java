package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TTypeVo;

@Mapper
public interface TTypeRepository {

	public TTypeVo findTyGrpCdByTyCd(SearchVo searchVo);

}
