package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBkmstVo;
import kr.co.e1ct.vo.TGcorderVo;
import kr.co.e1ct.vo.TVescallVo;

@Mapper
public interface TVescallRepository {

	public List<TVescallVo> berthText(SearchVo searchVo);
	public int berthTextCnt(SearchVo searchVo);
	public TVescallVo berthTextSum(TVescallVo tVescallVo);
	public TVescallVo vesselInfo(SearchVo searchVo);
	public List<TVescallVo> vesselWorkDetail(SearchVo searchVo);
	public List<TVescallVo> berthTextPrint(SearchVo searchVo);
	public TVescallVo vesselWorkFindAtbDt(TVescallVo tempVo);
	public List<TVescallVo> codeVesselData( SearchVo searchVo );
	public int codeVesselDataCount(SearchVo searchVo);
	public List<TVescallVo> terminalBerthGraphic(SearchVo searchVo);
	public TVescallVo cntrInfoWorkingTimeGetAtbDt(TGcorderVo tGcorderVo);
	public TVescallVo findOperCdAndStvEndDtByShipCdAndCallNoAndCallYy(SearchVo searchVo);
	public TVescallVo findAtbDtByShipCdAndCallNoAndCallYy(TGcorderVo tGcorderVo);
	public List<TVescallVo> yardWorkService(SearchVo searchVo);
	public List<TVescallVo> intraVesselDetailGetShip(SearchVo searchVo);
	public TVescallVo findShipCdByShipCdAndCallNoAndCallYy(SearchVo searchVo);
	public TVescallVo findClosingDtAndAtdDtByShipCdAndCallNoAndCallYy(SearchVo searchVo);
	public TVescallVo findByShipCdAndCallNoAndCtpCd(SearchVo searchVo);
	public TVescallVo findAdtDtByBkNoAndOperCd(SearchVo searchVo);
	public TVescallVo findAdtDtByShipCdAndCallNoAndCallYy(TBkmstVo tBkmstVo);
	public TVescallVo findReturnDate(SearchVo searchVo);
}
