package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TPortVo;

@Mapper
public interface TPortRepository {

	public List<TPortVo> findTCodeListPageByPortNm(SearchVo searchVo);
	public int findTCodeCountPageByPortNm( SearchVo searchVo );
	public List<TPortVo> ediCopinoPortList(SearchVo searchVo);
	public TPortVo findCountrycdByPortCd1(SearchVo searchVo);

}
