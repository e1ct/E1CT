package kr.co.e1ct.mappers;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TCarVo;

@Mapper
public interface TCarRepository {

	public TCarVo ediCopinoCarFind(SearchVo searchVo);

	public TCarVo findCarNmAndSndIdWhereTruckerCdAndCarNo(SearchVo searchCar);

}
