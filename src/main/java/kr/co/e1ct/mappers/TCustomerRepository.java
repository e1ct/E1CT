package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TCustomerVo;

@Mapper
public interface TCustomerRepository {
	public TCustomerVo getCustomerInfo(SearchVo searchVo);
	public TCustomerVo findCustomer( TCustomerVo tCustomerVo );
	public List<TCustomerVo> getConcludeList(SearchVo searchVo);
	public int insertCustomer(TCustomerVo tCustomerVo);
	public TCustomerVo findCstNoAndCatalogByCstNoWithTCustomerHdr( TCustomerVo tCustomerVo );
	public TCustomerVo findNextCustId();
	public int updateCustomer(TCustomerVo tCustomerVo);
	public TCustomerVo findOne(TCustomerVo tCustomerVo);
}
