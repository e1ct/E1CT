package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TMailVo;

@Mapper
public interface TMailRepository {

	public TMailVo findByMailIdAndOperCd(SearchVo searchVo);

	public TMailVo findByOperCdAndMailIdAndLineCd(SearchVo searchVo);

}
