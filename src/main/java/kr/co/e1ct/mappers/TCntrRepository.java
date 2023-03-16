package kr.co.e1ct.mappers;

import java.util.List;
import java.util.Map;

import kr.co.e1ct.response.ExportableInformationDTO;
import kr.co.e1ct.response.IntegratedInformationResponse;
import org.apache.ibatis.annotations.Mapper;

import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TBklstVo;
import kr.co.e1ct.vo.TCntrVo;

@Mapper
public interface TCntrRepository {
	public List<TCntrVo> cntrListGateInOut(SearchVo searchVo);
	public TCntrVo findFnGetBknoByCntrNoAndDupNo(TCntrVo paramVo);
	public List<TCntrVo> terminalByOperYardList(SearchVo searchVo);
	public List<TCntrVo> cntrListNotGateOut(SearchVo searchVo);
	public List<TCntrVo> cntrListNotGateIn(SearchVo searchVo);
	public TCntrVo findYTblockAndYTbayByCntrNoAndStcking(SearchVo searchVo);
	public List<TCntrVo> terminalYardStackData(SearchVo searchVo);
	public TCntrVo cntrInfoDetailsData(SearchVo searchVo);
	public TCntrVo cntrInfoDetailsStockDays(TCntrVo tCntrVo);
	public List<TCntrVo> cntrListCntrRehandlingList(SearchVo searchVo);
	public List<TCntrVo> cntrListReeferCargo(SearchVo searchVo);
	public List<TCntrVo> cntrListCntrDanger(SearchVo searchVo);
	public List<TCntrVo> cntrListCntrCancel(SearchVo searchVo);
	public List<TCntrVo> cntrListLongStack(SearchVo searchVo);
	public List<TCntrVo> cntrListLongStackRenewal(SearchVo searchVo);
	public List<TCntrVo> cllOverStorage(SearchVo searchVo);
	public List<TCntrVo> byVslDisLod(SearchVo searchVo);
	public List<TCntrVo> cntrInfoStock(SearchVo searchVo);
	public TCntrVo cntrInfoFreeTimeFindByCntrNo(SearchVo searchVo);
	public TCntrVo findCntrInfoByShipCdAndCallNoAndCallYy(SearchVo searchVo);
	public List<TCntrVo> findCountFromOnDockBookingList(SearchVo searchVo);
	public List<TCntrVo> findCount1FromOnDockBookingList(SearchVo searchVo);
	public List<TCntrVo> onDockStock(SearchVo searchVo);
	public List<TCntrVo> onDockFreeVanPool(SearchVo searchVo);
	public List<TCntrVo> onDockDamage(SearchVo searchVo);
	public List<TCntrVo> onDockOverStock(SearchVo searchVo);
	public TCntrVo ediByVslCllDetail(SearchVo searchVo);
	public int updatePickCdByCntrNoAndDupNoAndOperCdAndCurStat(TBklstVo tBklstVo);
	public List<TCntrVo> intraDamageCom(SearchVo searchVo);
	public List<TCntrVo> intraIntrDamage(SearchVo searchVo);
	public List<TCntrVo> intraDamageReqCurStat(SearchVo searchVo);
	public TCntrVo findOneByCntrNoAndDupNo(SearchVo searchVo);
	public TCntrVo findCurStatAndDirIdByShipCdAndCallYyAndCallNoAndCntrNo(SearchVo searchVo);
	public TCntrVo findMaxDupNoAndCurStatByCntrNoGroupByDupNoAndCurStatOrderByDupNoDesc(SearchVo searchVo);
	public TCntrVo findSzCdAndTyCdAndOperCdAndTempAndTempIdAndPtiYnByCntrNoAndDupNo(SearchVo searchVo);
	public TCntrVo findByCntrNoAndDupNo(SearchVo searchVo);
	public TCntrVo afterCopinoFindCurStatAndFmCdByCntrNoAndDupNo(SearchVo searchVo);
	public TCntrVo findCurStatAndRtnCdAndGoutDtWithTCntrByCntrNoAndDupNo(SearchVo searchVo);
	public TCntrVo findBondIdFromSlcTCntrVyCntrNoAndDupNo(SearchVo searchVo);
	public TCntrVo findBondIdByCntrNoAndDupNo(SearchVo searchVo);
	public TCntrVo cntrInfoDetailsDataLatest(SearchVo searchVo);
	public List<TCntrVo> cntrInfoDetailsDataList(SearchVo searchVo);
	public List<Map<String, Object>> findCntrNoAndDupNoAndFnGetOverpriceExNewByCntrNoAndDupNo(SearchVo searchVo);
	public Map<String, Object> findOneCntrNoAndDupNoAndFnGetOverpriceExNewByCntrNoAndDupNo(SearchVo searchVo);
	public int updateCopinoYnByCntrNoAndShipCdAndCallYyAndCallNo(TCntrVo updateVo);
	public List<TCntrVo> onDockGatePassList(SearchVo searchVo);
	public TCntrVo findDupNoAndCurStatByCntrNo(TCntrVo cntrParam);
	public List<TCntrVo> findYtblockAndVanAndTeuByYTblock(TCntrVo paramVo);
	public List<TCntrVo> popupCntrDetailTwo(SearchVo searchVo);
	public int onDockInsertOrderSpareCheckTotalSum(SearchVo searchVo);
	public int onDockInsertOrderSpareCheckRequestSumCntr(SearchVo operListAdded);
	public int onDockInsertOrderSpareCheckDeliverySumCntr(SearchVo operListAdded);
	public int onDockInsertOrderSpareCheckAppointSumCntr(SearchVo operListAdded);
	public TCntrVo findOneByCntrNoLastest(TCntrVo tCntrVo);
	public TCntrVo findOneFromCntrInfoDetails(SearchVo searchVo);
	public List<TCntrVo> terminalYardStackDoNoList(SearchVo searchVo);
	public List<TCntrVo> onDockDeliveryConfirmList(SearchVo paramVo);
	public TCntrVo onDockDeliveryConfirmRs1(TCntrVo tCntrVo);
	public TCntrVo onDockDeliveryConfirmRss(SearchVo paramVo);
	public TCntrVo findByShipCdAndCallNoAndCallYyAndFmCdAndCurStatAndCntrNoAndOperCd(TCntrVo tCntrParam);
	public int updateCopinoYnByCntrNoAndDupNoAndOperCd(TCntrVo cntrUpdateVo);
	public List<TCntrVo> terminalEmptyContainer(SearchVo searchVo);
	IntegratedInformationResponse getIntegratedInformation(String cntrNo);

	ExportableInformationDTO getExportableInformation(String cntrNo);

}
