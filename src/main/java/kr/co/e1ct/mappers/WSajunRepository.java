package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.WSajunVo;

@Mapper
public interface WSajunRepository {

	public List<WSajunVo> ediCopinoGateInRsrvList(SearchVo searchVo);
	public int ediCopinoGateInRsrvListCount(SearchVo searchVo);
	public int copinoGateInRsrvListProcAccept(WSajunVo wSajunVo);
	public int copinoGateInRsrvListProcCancel(WSajunVo wSajunVo);
	public int copinoGateInRsrvListProcDeny(WSajunVo wSajunVo);
	public int copinoGateInRsrvListProcDelete(WSajunVo wSajunVo);
	public int copinoGateInRsrvSave(WSajunVo wSajunVo);

}
