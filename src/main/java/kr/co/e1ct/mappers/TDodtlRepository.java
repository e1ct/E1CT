package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TDodtlVo;

@Mapper
public interface TDodtlRepository {

	public TDodtlVo findDoNoByCntrNoAndDupNo(TDodtlVo paramVo);

	public int insert(TDodtlVo insertVo);

	public TDodtlVo findByDoNoAndCntrNoAndDupNo(TDodtlVo tDodtlVo);

	public int delete(TDodtlVo deleteVo);

	public List<TDodtlVo> findByDoNo(TDodtlVo tDodtlVo);

}
