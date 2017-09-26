package com.acc.excelcreator;

import java.util.List;
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
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.acc.entity.Program;
import com.acc.entity.Project;

public class ProgramExcelReport extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		List<Program> programs = (List<Program>) session.getAttribute("programs");
		HSSFSheet sheet = workbook.createSheet("Program Report");
	        sheet.setDefaultColumnWidth(30);
	        sheet.setDefaultRowHeight((short) 20);
		
		Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
        
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
        
        HSSFRow header = sheet.createRow(0);
        
        HSSFRow header1 = sheet.createRow(1);
        header1.createCell(0).setCellValue("Program Details");
        header1.getCell(0).setCellStyle(style2);
        header1.createCell(1).setCellValue("");
        header1.getCell(1).setCellStyle(style2);
        header1.createCell(2).setCellValue("");
        header1.getCell(2).setCellStyle(style2);
        header1.createCell(3).setCellValue("");
        header1.getCell(3).setCellStyle(style2);
        CellRangeAddress employeeDetailsCellRange = new CellRangeAddress(1,1,0,3);
        sheet.addMergedRegion(employeeDetailsCellRange);
        
        HSSFRow header2 = sheet.createRow(2);
        
        header2.createCell(0).setCellValue("S.No");
        header2.getCell(0).setCellStyle(style1);
        
        header2.createCell(1).setCellValue("Program");
        header2.getCell(1).setCellStyle(style1);
       
        header2.createCell(2).setCellValue("Program Description");
        header2.getCell(2).setCellStyle(style1);
         
        header2.createCell(3).setCellValue("Portfolio");
        header2.getCell(3).setCellStyle(style1);
            
       int rowCount = 3;
       int counter = 1;
       for(Program program : programs)
       {
    	   HSSFRow aRow = sheet.createRow(rowCount++);
    	   aRow.createCell(0).setCellValue(counter);
    	   aRow.createCell(1).setCellValue(program.getProgramName());
    	   aRow.createCell(2).setCellValue(program.getDescription());
    	   aRow.createCell(3).setCellValue(program.getPortfolio().getPortfolioName());
    	   counter++;
       }
    	      
       }

		
	}

