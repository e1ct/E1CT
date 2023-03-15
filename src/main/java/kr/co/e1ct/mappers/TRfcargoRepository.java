package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TCntrVo;
import kr.co.e1ct.vo.TRfcargoVo;

@Mapper
public interface TRfcargoRepository {

	public TRfcargoVo findReqTempAndPlinDtByShipCdAndCallYyAndCallNoAndCntrNo(TCntrVo tCntrVo);
	public TRfcargoVo findByCntrNoAndDupNoAndPloutDt(SearchVo searchVo);

}
