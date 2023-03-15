package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TTmnlVo;

@Mapper
public interface TTmnlRepository {

	TTmnlVo findOneByTmnlCd(TTmnlVo tTmnlVo);

}
