package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TCodeVo;

@Mapper
public interface TCodeRepository {
	public List<TCodeVo> onDockHoldingHoldingReason(SearchVo searchVo);
}
