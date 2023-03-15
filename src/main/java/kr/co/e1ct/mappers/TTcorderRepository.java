package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.TTcorderVo;

@Mapper
public interface TTcorderRepository {

	public TTcorderVo yardWorkCount();

	public List<TTcorderVo> terminalYardStackWorkStatus();

}
