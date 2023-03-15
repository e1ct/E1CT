package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TOperVo;

@Mapper
public interface TOperRepository {

	public List<String> findOperCdListForAnonymousAndManager();
	public List<TOperVo> codeShipperData(SearchVo searchVo);
	public int codeShipperDataCount(SearchVo searchVo);
	public List<TOperVo> copinoGateInInsOperList();
	public TOperVo findByOperCdAndGoutChk(SearchVo searchVo);
	public TOperVo findByOperCd(SearchVo searchVo);
	public TOperVo findOndockAndMvChkAndLocChkAndDoChkAndBkChkByOperCd(SearchVo searchVo);
	public TOperVo findOperChkByOperCd(SearchVo searchVo);
	public TOperVo copinoFindByOperCd(SearchVo searchVo);
	public TOperVo findByOperCdAfterCopinoEnd(SearchVo searchVo);
	public TOperVo findByOperCdAndBkChk(SearchVo searchVo);
	public TOperVo findDemurChk(SearchVo searchVo);

}
