package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TGateVo;

@Mapper
public interface TGateRepository {
	public List<TGateVo> cntrListCopino(SearchVo searchVo);
	public List<TGateVo> ediCopinoList(SearchVo searchVo);
	public List<TGateVo> copinoTimes();
	public int findCountByCntrNoAndIoCdAndErrCd(SearchVo searchVo);
	public int findCountByCuCdAndCntrNo(SearchVo searchVo);
	public TGateVo findDupChkAndErrCdAndWkCdByTruckerCdAndCarNoAndCntrNoAndUpdCd(SearchVo searchVo);
	public int copinoGateInInsert(TGateVo insertVo);
	public int updateCopinoInIns(TGateVo updateVo);
}
