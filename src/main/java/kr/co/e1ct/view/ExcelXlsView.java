package kr.co.e1ct.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import kr.co.e1ct.constant.ExcelConstant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelXlsView extends AbstractXlsView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader( "Content-Disposition", "attachment;filename=\"" + model.get( ExcelConstant.FILE_NAME ) + "\";" );
		response.setHeader( "Content-Transfer-Encoding", "binary" );
		
		List<List<String>> headerList = (List<List<String>>)model.get( ExcelConstant.HEAD );
		List<List<List<String>>> bodyList = (List<List<List<String>>>) model.get( ExcelConstant.BODY );
		
		Sheet sheet = workbook.createSheet();
		
		sheet.addMergedRegion( new CellRangeAddress(0, 1, 0, headerList.get(0).size()-1 ) );
		sheet.addMergedRegion( new CellRangeAddress(2, 2, 0, headerList.get(0).size()-1 ) );
		
		// title
		CellStyle titleCellStyle = workbook.createCellStyle();
		Font titleFont = workbook.createFont();
		titleFont.setBold( true );
		titleFont.setFontHeight( (short)220 );
		titleCellStyle.setAlignment( HorizontalAlignment.CENTER );
		titleCellStyle.setVerticalAlignment( VerticalAlignment.CENTER );
		titleCellStyle.setFont( titleFont );
		Row title = sheet.createRow(0);
		Cell titleCell = title.createCell(0); 
		titleCell.setCellValue( (String) model.get( ExcelConstant.TITLE ) );
		titleCell.setCellStyle( titleCellStyle );
		
		// filter
		Row filter = sheet.createRow(2);
		Cell filterCell = filter.createCell(0);
		filterCell.setCellValue( (String) model.get( ExcelConstant.FILTER ) );
		
		
		// header
		CellStyle headerCellStyle = workbook.createCellStyle();
		Font headerFont = workbook.createFont();
		headerCellStyle.setAlignment( HorizontalAlignment.CENTER );
		headerCellStyle.setVerticalAlignment( VerticalAlignment.CENTER );
		headerCellStyle.setFillForegroundColor( (short) 40 );
		headerFont.setBold( true );
		headerCellStyle.setFont( headerFont );
		
		int contentStartRowIndex = 3;
		
		for( int i=0; i<headerList.size(); i++ ) {
			Row header = sheet.createRow( contentStartRowIndex );
			
			for( int j=0; j<headerList.get(i).size(); j++ ) {
				if( !headerList.get(i).get(j).toString().equals( ExcelConstant.ROW_SPAN.toString() ) 
						&& !headerList.get(i).get(j).toString().equals( ExcelConstant.COL_SPAN.toString() ) ) {
					Cell cell = header.createCell( j );
					cell.setCellValue( headerList.get(i).get(j) );
					cell.setCellStyle( headerCellStyle );
				} else {
					if( headerList.get(i).get(j).toString().equals( ExcelConstant.ROW_SPAN.toString() ) ) {
						/*
						int rowSpanCnt = 0;
						for( int k=i; k<headerList.size(); k++ ) {
							if( headerList.get(k).get(j).toString().equals( ExcelConstant.ROW_SPAN.toString() ) ) {
								rowSpanCnt++;
							} else {
								break;
							}
						}
						
						
						boolean mergeable = true;
						for( int p=0; p<sheet.getMergedRegions().size(); p++ ) {
							if( sheet.getMergedRegion(p).containsRow( ( contentStartRowIndex + i ) ) && sheet.getMergedRegion(p).containsColumn(j) ) {
								mergeable = false;
								break;
							}
						}
						
						if( mergeable ) {
							sheet.addMergedRegion( new CellRangeAddress( contentStartRowIndex + i - 2, contentStartRowIndex + i - 1, j, j ) );
						} 
						*/
						sheet.addMergedRegion( new CellRangeAddress( contentStartRowIndex + i - 2, contentStartRowIndex + i - 1, j, j ) );
					} else if( headerList.get(i).get(j).toString().equals( ExcelConstant.COL_SPAN.toString() ) ) {
						int spanCnt = 0;
						for( int k=j; k<headerList.get(i).size(); k++ ) {
							if( headerList.get(i).get(k).toString().equals( ExcelConstant.COL_SPAN.toString() ) ) {
								spanCnt++;
							} else {
								break;
							}
						}
						
						sheet.addMergedRegion( new CellRangeAddress( contentStartRowIndex + i, contentStartRowIndex + i, j-1, j+spanCnt-1 ) );
						
						j += spanCnt-1;
					}
				}
			}
			contentStartRowIndex++;
		}
		
		// body
		for( int i=0; i<bodyList.size(); i++ ) {
			
			for( int j=0; j<bodyList.get(i).size(); j++ ) {
				Row row = sheet.createRow( j + contentStartRowIndex ); // header 제외
				
				List<String> body = bodyList.get( i ).get( j );
				
				for( int k=0; k<body.size(); k++ ) {
					if( body.get(k) == null ) {
						body.set( k, "" );
					}
					if( body.get(k).toString().equals( ExcelConstant.ROW_SPAN.toString() ) ) {
						sheet.addMergedRegion( new CellRangeAddress( j + contentStartRowIndex - 1, j + contentStartRowIndex, k, k ) );
						
					} else if( body.get(k).toString().equals( ExcelConstant.COL_SPAN.toString() ) ) {
						sheet.addMergedRegion( new CellRangeAddress( j + contentStartRowIndex, j + contentStartRowIndex, k-1, k ) );
					} else {
						Cell cell = row.createCell(k);
						cell.setCellValue( body.get(k) );
						sheet.autoSizeColumn( k );
						sheet.setColumnWidth( k , sheet.getColumnWidth(k) + 512 );
					}
				}
			}
		}
		
		
		workbook.write( response.getOutputStream() );
		workbook.close();
	}
}
