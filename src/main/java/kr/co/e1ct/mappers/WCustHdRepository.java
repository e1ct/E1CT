package kr.co.e1ct.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.WCustHdVo;

@Mapper
public interface WCustHdRepository {
	public List<WCustHdVo> custInspection(SearchVo searchVo);
	public int custInspectionDeleteCust(SearchVo searchVo);
	public WCustHdVo custInspectionCustItemWindow(SearchVo searchVo);
	public int updateInspStatByCustId(SearchVo searchVo);
	public int updateInspStatEdByCustId(SearchVo searchVo);
	public List<WCustHdVo> inspectionManage(SearchVo searchVo);
	public WCustHdVo custInspectionView(SearchVo searchVo);
	public int custInspectionViewSave(SearchVo searchVo);
	public int custInspectionManageSave(SearchVo searchVo);
	public List<WCustHdVo> custInsPvnt(SearchVo searchVo);
	public int custInsPvntDeleteCust(SearchVo searchVo);
	public WCustHdVo custInspectionSaveReadCount(SearchVo paramVo);
	public int custInspectionSaveUpdate(SearchVo paramVo);
	public int custInspectionSaveInsert(SearchVo paramVo);
	public WCustHdVo custinsPvntSaveReadCount(SearchVo paramVo);
	public List<WCustHdVo> insPvntManage(SearchVo searchVo);
}
