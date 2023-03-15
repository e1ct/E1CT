package kr.co.e1ct.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;

@Mapper
public interface TInLiftCashCntrRepository {

	List<Map<String, Object>> findCntrNoAndDupNoAndFnGetOverpriceExNewByCntrNoAndDupNo(SearchVo searchVo);

	Map<String, Object> findOneCntrNoAndDupNoAndFnGetOverpriceExNewByCntrNoAndDupNo(SearchVo searchVo);

}
