package kr.co.e1ct.mappers;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TSecretVo;

@Mapper
public interface CommonRepository {
	public List<HashMap<String, Object>> findOperCdListFromSession(TSecretVo tSecretVo);
	public String getSysdate();
	public String smsDetailVacctNo();
}
