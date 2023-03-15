package kr.co.e1ct.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.co.e1ct.mappers.TSecretRepository;
import kr.co.e1ct.mappers.TSecretSubRepository;
import kr.co.e1ct.mappers.WBoardRepository;
import kr.co.e1ct.vo.TSecretSubVo;
import kr.co.e1ct.vo.TSecretVo;
import kr.co.e1ct.vo.UploadFileVo;
import kr.co.e1ct.vo.WBoardVo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {
	
	@Autowired
	private TSecretRepository tSecretRepository;
	
	@Autowired
	private TSecretSubRepository tSecretSubRepository;
	
	@Autowired
	private WBoardRepository wBoardRepository;

	@Autowired
	private FileService fileService;
	
	public List<TSecretVo> findMemberList() {
		return tSecretRepository.findAll();
	}

	public Map<String, Object> memberDelete(TSecretVo tSecretVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		TSecretSubVo tSecretSubVo = new TSecretSubVo();
		tSecretSubVo.setEmpNo( tSecretVo.getEmpNo() );
		tSecretSubRepository.deleteByEmpNo( tSecretSubVo );
		
		tSecretRepository.deleteByEmpNo( tSecretVo );
		
		resultMap.put( "code", 200 );
		resultMap.put( "msg", "정상처리 되었습니다." );
		
		
		return resultMap;
	}

	public Map<String, Object> slideSave(WBoardVo wBoardVo) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		wBoardVo.setMName( SecurityContextHolder.getContext().getAuthentication().getName() );
		wBoardVo.setMEmail( "" );
		wBoardVo.setMPop( "" );
		
		if( wBoardVo.getMSeq() == null ) {
			UploadFileVo uploadFileVo = fileService.store( wBoardVo.getAttachFile() );
			wBoardVo.setMFilename( String.valueOf( uploadFileVo.getUfIdx() ) );
			wBoardVo.setMFilesize( uploadFileVo.getUfFileSize() );
			
			int result = wBoardRepository.insert(wBoardVo);
			
			if( result > 0 ) {
				resultMap.put( "code", 200 );
				resultMap.put( "msg", "정상처리되었습니다." );
			} else {
				resultMap.put( "code", 0 );
				resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다." );
			}
			
		} else {
			
			if( !wBoardVo.getAttachFile().isEmpty() ) {
				UploadFileVo uploadFileVo = fileService.store( wBoardVo.getAttachFile() );
				wBoardVo.setMFilename( String.valueOf( uploadFileVo.getUfIdx() ) );
				wBoardVo.setMFilesize( uploadFileVo.getUfFileSize() );
			}

			if( wBoardVo.getMWriteday() == null ) {
				wBoardVo.setMWriteday( new SimpleDateFormat("yyyy-MM-dd").format( new Date() ) );
			}
			
			if( wBoardVo.getMPop() == null ) {
				wBoardVo.setMPop("");
				wBoardVo.setMPfrom("");
				wBoardVo.setMPto("");
				wBoardVo.setMPheight( 0L );
				wBoardVo.setMPwidth( 0L );
			}
			
			int result = wBoardRepository.update( wBoardVo );
			
			if( result > 0 ) {
				resultMap.put( "code", 200 );
				resultMap.put( "msg", "정상처리되었습니다." );
			} else {
				resultMap.put( "code", 0 );
				resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다." );
			}
			
		}
		
		return resultMap;
	}

	public Map<String, Object> slideDelete(WBoardVo wBoardVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int result = wBoardRepository.delete(wBoardVo);
		
		if( result > 0 ) {
			resultMap.put( "code", 200 );
			resultMap.put( "msg", "정상처리되었습니다." );
		} else {
			resultMap.put( "code", 0 );
			resultMap.put( "msg", "데이터베이스 오류로 인해 처리되지 않았습니다." );
		}
		
		return resultMap;
	}

	public WBoardVo findSlideOne(WBoardVo wBoardVo) {
		return wBoardRepository.findOne(wBoardVo);
	}

	public Map<String, Object> memberEnrolProc(TSecretVo tSecretVo) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if( tSecretRepository.findByEmpNo(tSecretVo) != null ) {
			resultMap.put( "code", 0 );
			resultMap.put( "msg", "등록된 ID 입니다." );
		} else {
			TSecretSubVo tSecretSubVo = new TSecretSubVo();
			tSecretSubVo.setEmpNo( tSecretVo.getEmpNo() );
			tSecretSubVo.setOperCd( tSecretVo.getOperCd() );
			
			tSecretSubRepository.insert( tSecretSubVo );
			
			tSecretRepository.insert( tSecretVo );
			
			resultMap.put( "code", 200 );
			resultMap.put( "msg", "정상처리되었습니다." );
		}
		
		log.info( tSecretVo.toString() );
		
		return resultMap;
	}
	
	public Map<String, Object> memberOperProc( TSecretVo tSecretVo ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		TSecretSubVo orgVo = new TSecretSubVo();
		orgVo.setEmpNo( tSecretVo.getEmpNo() );
		orgVo.setOperCd( tSecretVo.getOrgOperCd() );
		
		tSecretSubRepository.deleteByEmpNoAndOperCd( orgVo );
		
		TSecretSubVo tSecretSubVo = new TSecretSubVo();
		tSecretSubVo.setEmpNo( tSecretVo.getEmpNo() );
		tSecretSubVo.setOperCd( tSecretVo.getOperCd() );
		
		tSecretSubRepository.insert( tSecretSubVo );
		
		tSecretRepository.update( tSecretVo );
		
		resultMap.put( "code", 200 );
		resultMap.put( "msg", "정상처리되었습니다." );
		
		return resultMap;
	}

	public TSecretVo findMember(TSecretVo tSecretVo) {
		return tSecretRepository.findByEmpNo(tSecretVo);
	}

}
