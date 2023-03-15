package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBsiotVo;
import kr.co.e1ct.vo.TCntrVo;

@Mapper
public interface TBsiotRepository {

	public List<TBsiotVo> deliveryManagerByShip(SearchVo searchVo);
	public TBsiotVo findShipCdAndCallNoAndCallYyByMstblNo(SearchVo searchVo);
	public List<TBsiotVo> cntrListLongStackMstBlNoList(TCntrVo tCntrVo);
	public List<TBsiotVo> onDockDeliveryConfirmList(SearchVo searchVo);

}
