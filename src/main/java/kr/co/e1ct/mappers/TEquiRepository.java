package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TEquiVo;

@Mapper
public interface TEquiRepository {

	public List<TEquiVo> intraVesselDetailGetCcInfo();

}
