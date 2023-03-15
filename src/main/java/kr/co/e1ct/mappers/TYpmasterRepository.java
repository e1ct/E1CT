package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TYpmasterVo;

@Mapper
public interface TYpmasterRepository {

	public TYpmasterVo findPlanAmtAndStackAmtAndCopinoAmtByShipCdAndCallYyAndCallNoAndPodAndSzCdAndFmCd(SearchVo searchVo);

}
