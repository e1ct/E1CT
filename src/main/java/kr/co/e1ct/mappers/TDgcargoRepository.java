package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TDgcargoVo;

@Mapper
public interface TDgcargoRepository {

	public TDgcargoVo findByCntrNoAndShipCdAndCallYyAndCallNo(SearchVo searchVo);

	public int updateImdg1ByCntrNoAndShipCdAndCallYyAndCallNo(SearchVo searchVo);

}
