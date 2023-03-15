package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TMvRemashVo;

@Mapper
public interface TMvRemashRepository {

	public int deleteByCntrNoAndDupNoAndOkDtIsNull(TMvRemashVo tMvRemashVo);

}
