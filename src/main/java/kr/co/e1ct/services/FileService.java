package kr.co.e1ct.services;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import kr.co.e1ct.mappers.UploadFileRepository;
import kr.co.e1ct.util.UploadFileUtils;
import kr.co.e1ct.vo.UploadFileVo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {
	
	private final Path rootLocation;
	
	@Autowired
	public FileService(String uploadPath) {
		this.rootLocation = Paths.get( uploadPath );
	}
	
	@Autowired
	private UploadFileRepository uploadFileRepository;
	
	public Stream<Long> loadAll() {
		List<UploadFileVo> files = uploadFileRepository.findAll();
		return files.stream().map( file -> file.getUfIdx() );
	}
	
	public UploadFileVo load( Long ufIdx ) {
		UploadFileVo uploadFileVo = UploadFileVo.builder().build();
		uploadFileVo.setUfIdx( ufIdx );
		return uploadFileRepository.findOne( uploadFileVo );
	}
	
	public Resource loadAsResource( String ufFileName ) throws Exception {
		try {
			if( ufFileName.toCharArray()[0] == '/' ) {
				ufFileName = ufFileName.substring(1);
			}
			
			Path file = loadPath( ufFileName );
			Resource resource = new UrlResource( file.toUri() );
			if( resource.exists() || resource.isReadable() ) {
				return resource;
			} else {
				throw new Exception( "Could not read file : " + ufFileName );
			}
		} catch ( Exception e ) {
			throw new Exception( "Could not read file : " + ufFileName );
		}
	}
	
	private Path loadPath( String ufFileName ) {
		return rootLocation.resolve( ufFileName );
	}
	
	public UploadFileVo store( MultipartFile file ) throws Exception {
		try {
			if( file.isEmpty() ) {
				throw new Exception( "Failed to store empty file " + file.getOriginalFilename() );
			}
			
			String saveFileName = UploadFileUtils.fileSave( rootLocation.toString(), file );
			
			if( saveFileName.toCharArray()[0] == '/' ) {
				saveFileName = saveFileName.substring(1);
			}
			
			Resource resource = loadAsResource( saveFileName );
			
			UploadFileVo saveFile = UploadFileVo.builder().build();
			saveFile.setUfSaveFileName( saveFileName );
			saveFile.setUfFileName( file.getOriginalFilename() );
			saveFile.setUfContentType( file.getContentType() );
			saveFile.setUfFilePath( rootLocation.toString().replace( File.pathSeparatorChar, '/' ) + File.separator + saveFileName );
			saveFile.setUfFileSize( resource.contentLength() );
			saveFile.setUfEnrolDt( LocalDateTime.now() );
			
			uploadFileRepository.save( saveFile );
			
			UploadFileVo uploaded = UploadFileVo.builder().build();
			uploaded.setUfIdx( saveFile.getUfIdx() );
			
			return uploadFileRepository.findOne( uploaded ); 
			
		} catch ( IOException e ) {
			throw new Exception( "Filed to store file " + file.getOriginalFilename(), e );
		}
	}
}
