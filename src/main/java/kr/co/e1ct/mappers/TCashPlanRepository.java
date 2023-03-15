package kr.co.e1ct.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TCashPlanVo;

@Mapper
public interface TCashPlanRepository {

	public TCashPlanVo findByCntrNoAndDupNoAndDealDdAndCstNoAndVacctNoAndCloseDd(TCashPlanVo tCashPlanVo);
	public int smsDetailSubmitTCashPlanInsert(TCashPlanVo tCashPlanVo);
	public int smsDetailSubmitTCashPlanUpdate(Map<String, Object> row);
	public int deleteTcashPlanByCntrNoAndDupNoAndDealDdAndCstNoAndVacctNoAndCloseDd(TCashPlanVo deleteVo);
	public int updateTcashPlanByCntrNoAndDupNoAndDealDdAndCstNoAndVacctNoAndCloseDd(TCashPlanVo updateVo);

}
