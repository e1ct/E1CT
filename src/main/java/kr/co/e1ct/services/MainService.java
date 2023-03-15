package kr.co.e1ct.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.e1ct.mappers.TTcorderRepository;
import kr.co.e1ct.mappers.TVescallRepository;
import kr.co.e1ct.mappers.WBoardRepository;
import kr.co.e1ct.vo.SearchVo;
import kr.co.e1ct.vo.TTcorderVo;
import kr.co.e1ct.vo.TVescallVo;
import kr.co.e1ct.vo.WBoardVo;

@Service
public class MainService {
	
	@Autowired
	private TVescallRepository tVescallRepository;
	
	@Autowired
	private TTcorderRepository tTcorderRepository;
	
	@Autowired
	private WBoardRepository wBoardRepository;

	public List<TVescallVo> yardWorkService() {
		SearchVo searchVo = new SearchVo();
		
		Calendar c = Calendar.getInstance();
		c.setTime( new Date() );
		searchVo.setSearchStartDt( new SimpleDateFormat("yyyyMMdd").format( c.getTime() ) );
		c.add( Calendar.DATE, 20 );
		searchVo.setSearchEndDt( new SimpleDateFormat( "yyyyMMdd").format( c.getTime() ) );
		return tVescallRepository.yardWorkService( searchVo );
	}
	
	public TTcorderVo yardWorkCount() {
		return tTcorderRepository.yardWorkCount();
	}

	public List<WBoardVo> mainBoardList() {
		return wBoardRepository.mainBoardList();
	}

	public List<WBoardVo> slideList() {
		WBoardVo wBoardVo = new WBoardVo();
		wBoardVo.setMType("MS");
		return wBoardRepository.findByMType(wBoardVo);
	}

}
