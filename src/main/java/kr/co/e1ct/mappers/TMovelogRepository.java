package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBklstVo;
import kr.co.e1ct.vo.TMovelogVo;

@Mapper
public interface TMovelogRepository {

	public int deleteByCntrNoAndOperCdAndDupNo(TBklstVo tBklstVo);

	public List<TMovelogVo> findTruckerCdAndCarNoAndSndIdANdIxIdByCntrNoAndDupNo(SearchVo search);

	public TMovelogVo findByCntrNoAndDupNoAndIxCd(TMovelogVo tMovelogParam);

	public int insert(TMovelogVo tMoveInsertVo);

	public int update(TMovelogVo tMoveUpdateVo);

	public int deleteByCntrNoAndDupNoAndIxCd(TMovelogVo tMovelogDeleteVo);

}
