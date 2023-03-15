package kr.co.e1ct.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.co.e1ct.mappers.WBoardRepository;
import kr.co.e1ct.util.StringUtils;
import kr.co.e1ct.vo.UploadFileVo;
import kr.co.e1ct.vo.WBoardVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BoardService {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired WBoardRepository wBoardRepository;

	public List<WBoardVo> findPageByMType(WBoardVo wBoardVo) {
		return wBoardRepository.findPageByMType( wBoardVo );
	}

	public Map<String, Integer> findPageByMTypeCount(WBoardVo wBoardVo) {
		Map<String, Integer> pager = new HashMap<String, Integer>();
		
		int totalCount = wBoardRepository.findPageByMTypeCount(wBoardVo);
		int pageSize = wBoardVo.getPageSize();
		int pageNumber = wBoardVo.getPageNumber();
		log.info( totalCount + "/" + pageSize );
		int maxPage = (int) Math.ceil( totalCount / pageSize ) + ( totalCount % pageSize == 0 ? -1 : 0 );
		log.info( "maxpage : " + maxPage );
		int sequenceStart = (int) Math.floor( pageNumber / pageSize );
		int sequenceEnd = sequenceStart + 9 > maxPage ? maxPage : ( sequenceStart + 9 );
		
		pager.put( "pageSize", pageSize );
		pager.put( "pageNumber", pageNumber );
		pager.put( "totalCount", totalCount );
		pager.put( "sequenceStart", sequenceStart );
		pager.put( "sequenceEnd", sequenceEnd );
		pager.put( "maxPage", maxPage );
		
		log.info( pager.toString() );
		
		return pager;
	}

	public WBoardVo findOne(WBoardVo wBoardVo) {
		return wBoardRepository.findOne(wBoardVo);
	}

	public void mReadnumAdd(WBoardVo wBoardVo) {
		wBoardRepository.readCountAdd( wBoardVo );
	}

	public Map<String,Object> save(WBoardVo wBoardVo) throws Exception {
		log.info( "isEmpty : " + StringUtils.isEmpty( wBoardVo.getMSeq() ) );
		log.info( "mseq : " + wBoardVo.getMSeq() );
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if( wBoardVo.getMSeq() == null ) {
			// insert
			if( wBoardVo.getAttachFile() != null && !wBoardVo.getAttachFile().isEmpty() ) {
				UploadFileVo uploadFileVo = fileService.store( wBoardVo.getAttachFile() );
				wBoardVo.setMFilename( String.valueOf( uploadFileVo.getUfIdx() ) );
				wBoardVo.setMFilesize( uploadFileVo.getUfFileSize() );
			} else {
				wBoardVo.setMFilesize( 0L );
			}
			
			if( StringUtils.isEmpty( wBoardVo.getMName() ) ) {
				wBoardVo.setMName( SecurityContextHolder.getContext().getAuthentication().getName() );
			}
			
			if( wBoardVo.getMPop() == null ) {
				wBoardVo.setMPop("");
				wBoardVo.setMPfrom("");
				wBoardVo.setMPto("");
			}
			
			wBoardVo.setMIp( StringUtils.getRemoteIp( request ) );
			
			int result = wBoardRepository.insert( wBoardVo );
			if( result > 0 ) {
				resultMap.put( "code", 200 );
				resultMap.put( "msg", "처리되었습니다." );
			}
		} else {
			// update
			log.info( wBoardVo.toString() );
			WBoardVo updateVo = wBoardRepository.findOne( wBoardVo );
			updateVo.setMTitle( wBoardVo.getMTitle() );
			updateVo.setMContent( wBoardVo.getMContent() );
			updateVo.setMEmail( wBoardVo.getMEmail() );
			if( wBoardVo.getAttachFile() != null && !wBoardVo.getAttachFile().isEmpty() ) {
				UploadFileVo uploadFileVo = fileService.store( wBoardVo.getAttachFile() );
				updateVo.setMFilename( String.valueOf( uploadFileVo.getUfIdx() ) );
				updateVo.setMFilesize( uploadFileVo.getUfFileSize() );
			}
			
			if( wBoardVo.getMPop() == null ) {
				updateVo.setMPop("");
				updateVo.setMPfrom("");
				updateVo.setMPto("");
				updateVo.setMPwidth( 0L );
				updateVo.setMPheight( 0L );
			}
			updateVo.setMTel( wBoardVo.getMTel() );
			updateVo.setMIp( StringUtils.getRemoteIp( request ) );
			updateVo.setMPwd( wBoardVo.getMPwd() );
			
			log.info( updateVo.toString() );
			
			int result = wBoardRepository.update( updateVo );
			if( result > 0 ) {
				resultMap.put( "code", 200 );
				resultMap.put( "msg", "처리되었습니다." );
			}
		}
		return resultMap;
	}

	public Map<String, Object> delete(WBoardVo wBoardVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		WBoardVo wBoard = wBoardRepository.findOne( wBoardVo );
		
		if( wBoard == null ) {
			resultMap.put("code", 0);
			resultMap.put( "msg", "존재하지 않는 게시물입니다." );
		} else {
			int result = wBoardRepository.delete( wBoardVo );
			if( result > 0 ) {
				resultMap.put( "code", 200 );
				resultMap.put( "msg", "삭제되었습니다." );
			} else {
				resultMap.put( "code", 0 );
				resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다.");
			}
		}
		
		return resultMap;
	}

	public List<WBoardVo> findByMType(WBoardVo wBoardVo) {
		return wBoardRepository.findByMType( wBoardVo );
	}

}
