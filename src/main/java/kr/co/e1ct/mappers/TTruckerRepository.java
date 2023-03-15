package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TTruckerVo;

@Mapper
public interface TTruckerRepository {

	public TTruckerVo findCstNmByTruckerCdAndSndId(SearchVo truckerSearch);

}
