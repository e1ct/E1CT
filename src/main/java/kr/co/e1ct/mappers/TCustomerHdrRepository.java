package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TCustomerVo;

@Mapper
public interface TCustomerHdrRepository {

	public int insertCustomer(TCustomerVo tCustomerVo);
	public int updateCustomer(TCustomerVo tCustomerVo);

}
