package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TCopinoRjctVo;

@Mapper
public interface TCopinoRjctRepository {

	public TCopinoRjctVo findByCntrNoAndDupNoAndOperCd(SearchVo searchVo);

	public TCopinoRjctVo findByCntrNoAndOperCdAndRejectOperAndRejectFirstAndRejectIn(SearchVo searchVo);

	public List<TCopinoRjctVo> onDockGatePass(SearchVo searchVo);

	public int onDockGatePassDeleteData(TCopinoRjctVo tCopinoRjctVo);

	public TCopinoRjctVo findOneByCntrNoANdDupNo(TCopinoRjctVo tCopinoRjctParam);

	public int insertOnDockGatePassSave(TCopinoRjctVo tCopinoRjctVo);
	
	public int updateOnDockGatePassSave(TCopinoRjctVo tCopinoRjctVo);

}
