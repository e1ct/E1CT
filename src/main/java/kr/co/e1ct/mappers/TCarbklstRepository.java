package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TCarbklstVo;

@Mapper
public interface TCarbklstRepository {

	public TCarbklstVo findPassCdByTruckerCdAndCarNoAndPassCd(SearchVo searchVo);

}
