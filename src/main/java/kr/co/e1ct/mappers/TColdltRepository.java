package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TColdltVo;

@Mapper
public interface TColdltRepository {

	public List<TColdltVo> cllOverStorageCheckCll(SearchVo searchVo);
	public List<TColdltVo> ediByVslCll(SearchVo searchVo);
	public List<TColdltVo> ediByVslCllDetail(SearchVo searchVo);

}
