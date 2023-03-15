package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.UploadFileVo;

@Mapper
public interface UploadFileRepository {
	public List<UploadFileVo> findAll();
	public UploadFileVo findOne(UploadFileVo uploadFileVo);
	public Long save(UploadFileVo saveFile);
}
