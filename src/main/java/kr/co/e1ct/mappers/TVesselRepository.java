package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TVesselVo;

@Mapper
public interface TVesselRepository {
	public List<TVesselVo> getVesselNameList( SearchVo searchVo, Pageable pageable );
}
