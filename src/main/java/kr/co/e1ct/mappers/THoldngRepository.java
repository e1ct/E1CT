package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TCntrVo;
import kr.co.e1ct.vo.THoldngVo;

@Mapper
public interface THoldngRepository {

	public List<THoldngVo> findCdNmAndCdValByCntrNoAndDupNoAndHoldCdAndCdIdAndRelsDt(TCntrVo dataVo);
	public List<THoldngVo> onDockHolding(SearchVo searchVo);
	public int onDockHoldingCnt(SearchVo operListAdded);
	public List<THoldngVo> onDockHoldingEntire(SearchVo operListAdded);
	public int findCountByCntrNoAndOperCdAndHoldCdAndIoCdAndRelsDt(SearchVo searchVo);

}
