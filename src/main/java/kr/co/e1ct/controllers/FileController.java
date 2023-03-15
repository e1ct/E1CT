package kr.co.e1ct.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.e1ct.services.FileService;
import kr.co.e1ct.util.MediaUtils;
import kr.co.e1ct.vo.UploadFileVo;

@Controller
@RequestMapping( "/file" )
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping( "/{ufIdx}" )
	@ResponseBody
	public ResponseEntity<?> serveFile( @PathVariable Long ufIdx ) {
		try {
			UploadFileVo uploadedFile = fileService.load( ufIdx );
			HttpHeaders headers = new HttpHeaders();
			
			String fileName = uploadedFile.getUfFileName();
			headers.add( HttpHeaders.CONTENT_DISPOSITION, "attachment); filename=\"" + new String( fileName.getBytes("UTF-8"), "ISO-8859-1" ) + "\"" );
			
			if( MediaUtils.containsImageMediaType( uploadedFile.getUfContentType() ) ) {
				headers.setContentType( MediaType.valueOf( uploadedFile.getUfContentType() ) );
			} else {
				headers.setContentType( MediaType.APPLICATION_OCTET_STREAM );
			}
			
			Resource resource = fileService.loadAsResource( uploadedFile.getUfSaveFileName() );
			return ResponseEntity.ok().headers( headers ).body( resource );
		} catch ( Exception e ) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
	@RequestMapping( "/upload" )
	@ResponseBody
	public ResponseEntity<?> upload( @RequestParam( "file" ) MultipartFile file ) {
		try {
			UploadFileVo uploadFileVo = fileService.store(file);
			return ResponseEntity.ok().body( "/file/" + uploadFileVo.getUfIdx() );
		} catch ( Exception e ) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
}
