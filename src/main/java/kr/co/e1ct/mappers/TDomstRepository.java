package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TDomstVo;

@Mapper
public interface TDomstRepository {

	public TDomstVo findCopinoErrCheck(SearchVo searchVo);

	public TDomstVo findSelfTransByDoNoAndOperCd(SearchVo paramVo);

	public TDomstVo findByDoNoAndOperCd(TDomstVo tDomstParam);

	public int updateByDoNoAndOperCd(TDomstVo updateVo);

	public int insertDelivery(TDomstVo insertVo);

	public int updateSelfTransByDoNoAndOperCd(TDomstVo tDomstUpdateVo);

	public int deleteByDoNo(TDomstVo tDomstDeleteVo);

}
