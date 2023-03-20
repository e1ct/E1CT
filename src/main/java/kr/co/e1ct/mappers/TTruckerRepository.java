package kr.co.e1ct.mappers;

import kr.co.e1ct.request.E1EslipReissueRequest;
import kr.co.e1ct.request.EslipReissueRequest;
import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TTruckerVo;

import java.util.Map;

@Mapper
public interface TTruckerRepository {

	public TTruckerVo findCstNmByTruckerCdAndSndId(SearchVo truckerSearch);

	EslipReissueRequest getEslipIssueInfomation(E1EslipReissueRequest request);

}
