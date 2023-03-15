package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBklstVo;
import kr.co.e1ct.vo.TEdicustomerVo;
import kr.co.e1ct.vo.TGateVo;

@Mapper
public interface TEdicustomerRepository {

	public TEdicustomerVo cntrListCopinoGetSender(TGateVo tGateVo);
	public TEdicustomerVo findCstNmByDocuAndTerminalIdAndCstId(TBklstVo truckerCdVo);
	public TEdicustomerVo findByCstId(SearchVo searchVo);

}
