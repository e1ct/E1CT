package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.WBoardVo;

@Mapper
public interface WBoardRepository {
	public List<WBoardVo> mainBoardList();
	public List<WBoardVo> findPageByMType(WBoardVo wBoardVo);
	public int findPageByMTypeCount(WBoardVo wBoardVo);
	public WBoardVo findOne( WBoardVo wBoardVo );
	public int insert(WBoardVo wBoardVo );
	public int update(WBoardVo board);
	public int readCountAdd(WBoardVo wBoardVo);
	public int delete(WBoardVo wBoardVo);
	public List<WBoardVo> findByMType(WBoardVo wBoardVo);
}
