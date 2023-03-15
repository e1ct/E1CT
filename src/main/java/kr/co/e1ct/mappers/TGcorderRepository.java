package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TGcorderVo;
import kr.co.e1ct.vo.TVescallVo;

@Mapper
public interface TGcorderRepository {

	public TGcorderVo vesselInfoOkDt(TGcorderVo forParamVo);
	public int vesselWorkSetCount( TVescallVo tVescallVo );
	public int cntrInfoWorkingTimeGetVanhour(TGcorderVo tGcorderVo);
	public TGcorderVo cntrInfoWorkingTimeGetMaxOkDt(TGcorderVo tGcorderVo);
	public List<TGcorderVo> cntrInfoWorkingGetCntrNoList(TGcorderVo tGcorderVo);
	public TGcorderVo cntrInfoWorkingTimeFindTGcorderInfo(SearchVo searchVo);
	public List<TGcorderVo> intraVesselDetailGetBays(SearchVo searchVo);
	public List<TGcorderVo> findSbayAndHDeckId(SearchVo tempVo);
	public List<TGcorderVo> intraVesselDetailLoad(SearchVo searchVo);
}
