package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TbRvasMastVo;

@Mapper
public interface TbRvasMastRepository {

	public int smsDetailSubmitUpdate(TbRvasMastVo tbRvasMastVo);

}
