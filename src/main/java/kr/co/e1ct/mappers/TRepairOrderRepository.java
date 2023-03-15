package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;

@Mapper
public interface TRepairOrderRepository {

	public int saveDamageReq(SearchVo paramVo);

}
