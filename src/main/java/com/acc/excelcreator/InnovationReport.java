package com.acc.excelcreator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.acc.entity.EmployeeProject;
import com.acc.entity.Innovation;
import com.acc.entity.ProjectLocation;
import com.acc.entity.ResourceMaster;
import com.itextpdf.text.Font;

public class InnovationReport extends AbstractExcelView{

	
	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		
		
		ArrayList<Innovation> ideafrmSession = (ArrayList<Innovation>) session.getAttribute("idea");
		
		//  get session variable 
		//  set file name and fields
		HSSFSheet sheet = workbook.createSheet("Navriti Report");
        sheet.setDefaultColumnWidth(30);
        sheet.setDefaultRowHeight((short) 20);
	
        HSSFFont font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
		
        // creating of style
        
        CellStyle style1 = workbook.createCellStyle();  
        style1.setFillForegroundColor(HSSFColor.GREEN.index);
        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style1.setFont(font);
        style1.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        CellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style2.setFont(font);
        style2.setAlignment(CellStyle.ALIGN_CENTER);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		//setting the header
HSSFRow header = sheet.createRow(0);
        
        HSSFRow header1 = sheet.createRow(1);
        header1.createCell(2).setCellValue("Navriti Inovation Details");
        header1.getCell(2).setCellStyle(style2);
        header1.createCell(3).setCellValue("");
        header1.getCell(3).setCellStyle(style2);
        header1.createCell(4).setCellValue("");
        header1.getCell(4).setCellStyle(style2);
        header1.createCell(5).setCellValue("");
        header1.getCell(5).setCellStyle(style2);
        CellRangeAddress employeeDetailsCellRange = new CellRangeAddress(1,1,2,5);
        sheet.addMergedRegion(employeeDetailsCellRange);
        
        HSSFRow header2 = sheet.createRow(2);
        
        header2.createCell(0).setCellValue("Idea ID");
        header2.getCell(0).setCellStyle(style1);
             
        
        header2.createCell(1).setCellValue("Idea Owner");
        header2.getCell(1).setCellStyle(style1);
        
            
        header2.createCell(2).setCellValue("Idea Title");
        header2.getCell(2).setCellStyle(style1);
    
        header2.createCell(3).setCellValue("Idea  Criterion ");
        header2.getCell(3).setCellStyle(style1);
    
        
        header2.createCell(4).setCellValue("Idea Sub Criterion");
        header2.getCell(4).setCellStyle(style1);
    
        
        
        header2.createCell(5).setCellValue("Idea Description ");
        header2.getCell(5).setCellStyle(style1);
        
        header2.createCell(6).setCellValue("Current Effort(Hours)");
        header2.getCell(6).setCellStyle(style1);
        
        header2.createCell(7).setCellValue("Planned Effort(Hours)");
        header2.getCell(7).setCellStyle(style1);
        
        header2.createCell(8).setCellValue("Effort Saving(Hours)");
        header2.getCell(8).setCellStyle(style1);
        
        header2.createCell(9).setCellValue("Business Saving(Hours)  ");
        header2.getCell(9).setCellStyle(style1);
        
	
        // adding the data
        
        
        int rowCount = 3;
        int counter = 1;
        for(Innovation idea : ideafrmSession)
        {
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(idea.getIdeaId());
			aRow.createCell(1).setCellValue(idea.getEnterpriseId());
			aRow.createCell(2).setCellValue(idea.getIdeaTitle());
			aRow.createCell(3).setCellValue(idea.getCategory());
			aRow.createCell(4).setCellValue(idea.getSubCategory());
			aRow.createCell(5).setCellValue(idea.getIdeaDescription());
			aRow.createCell(6).setCellValue(idea.getiCurrentEffort());
			aRow.createCell(7).setCellValue(idea.getiPlannedEffort());
			aRow.createCell(8).setCellValue(idea.getiEffortSaving());
			aRow.createCell(9).setCellValue(idea.getiBusinessSaving());
     	
     	  
     	   counter++;
        }
        
        
        
        
        
        
        
		
	}
}


