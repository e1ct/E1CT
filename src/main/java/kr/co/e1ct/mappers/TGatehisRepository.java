package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TCntrVo;
import kr.co.e1ct.vo.TGatehisVo;

@Mapper
public interface TGatehisRepository {
	public List<TGatehisVo> findSealNoFromCntrListGateInOut(TCntrVo tCntrVo);
}
