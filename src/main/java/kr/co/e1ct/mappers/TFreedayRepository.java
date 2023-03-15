package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TFreedayVo;

@Mapper
public interface TFreedayRepository {

	public List<TFreedayVo> cntrInfoFreeTime(SearchVo searchVo);

}
