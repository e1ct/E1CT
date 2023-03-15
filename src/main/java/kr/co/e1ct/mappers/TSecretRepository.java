package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TSecretVo;

@Mapper
public interface TSecretRepository {

	public TSecretVo findByEmpNo(TSecretVo tSecretVo);
	public List<String> findOperCdListForOthers(TSecretVo operCdParam);
	public List<TSecretVo> findEmpNoListByJo(TSecretVo tSecretVo);
	public List<TSecretVo> custInspectionGetReqIdList();
	public TSecretVo getSessionData(TSecretVo tSecretVo);
	public List<TSecretVo> findAll();
	public int deleteByEmpNo(TSecretVo tSecretVo);
	public int insert(TSecretVo tSecretVo);
	public int update(TSecretVo tSecretVo);

}
