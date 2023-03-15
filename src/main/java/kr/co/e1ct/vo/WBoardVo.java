package kr.co.e1ct.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WBoardVo {
	private Long 	MSeq;
	private String 	mName;
	private String 	mEmail;
	private String 	mTel = "";
	private String 	mTitle;
	private String 	mPwd = "";
	private String 	mWriteday;
	private Long 	mReadnum = 0L;
	private String 	MType;
	private String 	mFilename = "";
	private String 	mContent;
	private Long 	mFilesize;
	private String 	mIp;
	private String 	mSecret;
	private String 	mPop;
	private String 	mPfrom;
	private String 	mPto;
	private String 	mContent2;
	private String 	mLocal = "";
	private Long mPwidth = 0L;
	private Long mPheight = 0L;
	
	private MultipartFile attachFile;
	
	private int pageSize = 10;
	private int pageNumber = 0;
	
	private String searchType;
	private String searchValue;
	
	private UploadFileVo uploadFileVo;
	
	public void setCustomPageable( Pageable pageable ) {
		PageRequest pageRequest = PageRequest.of( pageable.getPageNumber() , pageable.getPageSize() );
		this.pageSize = pageRequest.getPageSize();
		this.pageNumber = pageRequest.getPageNumber();
	}
}
