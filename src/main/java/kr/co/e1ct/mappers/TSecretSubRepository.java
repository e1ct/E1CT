package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TSecretSubVo;

@Mapper
public interface TSecretSubRepository {

	int deleteByEmpNo(TSecretSubVo tSecretSubVo);

	int insert(TSecretSubVo tSecretSubVo);

	int deleteByEmpNoAndOperCd(TSecretSubVo orgVo);

}
