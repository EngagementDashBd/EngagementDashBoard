package com.acc.excelcreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.acc.entity.CalendarData;
import com.acc.entity.CareerLevel;
import com.acc.entity.DayData;
import com.acc.entity.Portfolio;
import com.acc.entity.Program;
import com.acc.entity.Project;
import com.acc.entity.ProjectLocation;
import com.acc.entity.ResourceMaster;



public class ExcelBuilder extends AbstractExcelView {
	 @Override
	    protected void buildExcelDocument(Map<String, Object> model,
	            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	        // get data model which is passed by the Spring container
	     	Map<ResourceMaster, ArrayList> empObjectAndData = (Map<ResourceMaster, ArrayList>)model.get("empObjectAndData");
	     	List<CareerLevel> allCareerLevels = (List<CareerLevel>)model.get("allCareerLevels");
	     	List<CalendarData> calendarDataList = (List<CalendarData>)model.get("calendarDataList");
	     	List<Program> allPrograms = (List<Program>)model.get("allPrograms");
	     	ArrayList<Portfolio> allPortfolios = (ArrayList<Portfolio>)model.get("allPortfolios");
	    	List<Project> allProjects = (List<Project>)model.get("allProjects");
	     	String month = (String)model.get("month");
	     	List<ProjectLocation> projectLocationDetails = (List<ProjectLocation>)model.get("projectLocationDetails");
	     	Map<Long, String> employeeAndTeam = (Map<Long, String>)model.get("employeeAndTeam");
	     	// create a new Excel sheet
	        HSSFSheet sheet = workbook.createSheet("Monthly Report");
	        sheet.setDefaultColumnWidth(30);
	        sheet.setDefaultRowHeight((short) 20);
	
	     
	        // create style for header cells
	    
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
	        
	        CellStyle style3 = workbook.createCellStyle();
	        style3.setFillForegroundColor(HSSFColor.YELLOW.index);
	        style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        style3.setFont(font);
	        style3.setAlignment(CellStyle.ALIGN_CENTER);
	        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
	       
	        CellStyle style4 = workbook.createCellStyle();
	        style4.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
	        style4.setFillPattern(CellStyle.SOLID_FOREGROUND);
	     	style4.setFont(font);
	        style4.setAlignment(CellStyle.ALIGN_CENTER);
	        style4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        style4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        style4.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        style4.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        // create header row
	        HSSFRow header = sheet.createRow(0);
	         
	       /* header.createCell(0).setCellValue("Book Title");
	        header.getCell(0).setCellStyle(style);
	         
	        header.createCell(1).setCellValue("Author");
	        header.getCell(1).setCellStyle(style);
	         
	        header.createCell(2).setCellValue("ISBN");
	        header.getCell(2).setCellStyle(style);
	         
	        header.createCell(3).setCellValue("Published Date");
	        header.getCell(3).setCellStyle(style);
	         
	        header.createCell(4).setCellValue("Price");
	        header.getCell(4).setCellStyle(style);*/
	        HSSFRow header1 = sheet.createRow(1);
	        header1.createCell(2).setCellValue("EmployeeDetails");
	        header1.getCell(2).setCellStyle(style2);
	        header1.createCell(3).setCellValue("");
	        header1.getCell(3).setCellStyle(style2);
	        header1.createCell(4).setCellValue("");
	        header1.getCell(4).setCellStyle(style2);
	        CellRangeAddress employeeDetailsCellRange = new CellRangeAddress(1,1,2,4);
	        sheet.addMergedRegion(employeeDetailsCellRange);
	  
	        
	        header1.createCell(6).setCellValue("Shift B");
	        header1.getCell(6).setCellStyle(style4);
	        header1.createCell(7).setCellValue("");
	        header1.getCell(7).setCellStyle(style4);
	        CellRangeAddress shiftbCellRange = new CellRangeAddress(1,1,6,7); 
	        sheet.addMergedRegion(shiftbCellRange);
	        
	        header1.createCell(8).setCellValue("Shift C");
	        header1.getCell(8).setCellStyle(style4);
	        header1.createCell(9).setCellValue("");
	        header1.getCell(9).setCellStyle(style4);
	        CellRangeAddress shiftcCellRange = new CellRangeAddress(1,1,8,9); 
	        sheet.addMergedRegion(shiftcCellRange);
	        
	        header1.createCell(10).setCellValue("Total Shift Allowance");
	        header1.getCell(10).setCellStyle(style4);
	        header1.createCell(11).setCellValue("Total Shift Allowance");
	        header1.getCell(11).setCellStyle(style4);
	        CellRangeAddress shiftAllowanceCellRange = new CellRangeAddress(1,1,10,11);
	        sheet.addMergedRegion(shiftAllowanceCellRange);
	        
	        HSSFRow header2 = sheet.createRow(2);
	        
	        header2.createCell(0).setCellValue("S.No");
	        header2.getCell(0).setCellStyle(style1);
	        
	        header2.createCell(1).setCellValue("Team");
	        header2.getCell(1).setCellStyle(style1);
	        
	        header2.createCell(2).setCellValue("*Employee Personnel No.");
	        header2.getCell(2).setCellStyle(style2);
	        
	        header2.createCell(3).setCellValue("*Employee Enterprise ID");
	        header2.getCell(3).setCellStyle(style2);
	        
	        header2.createCell(4).setCellValue("*Job Level");
	        header2.getCell(4).setCellStyle(style2);
	        
	        header2.createCell(5).setCellValue("*WBS Element");
	        header2.getCell(5).setCellStyle(style3);
	        
	        header2.createCell(6).setCellValue("No. of Days Worked");
	        header2.getCell(6).setCellStyle(style4);
	        
	        header2.createCell(7).setCellValue("Amount Per Day");
	        header2.getCell(7).setCellStyle(style4);
	        
	        header2.createCell(8).setCellValue("No. of Days Worked");
	        header2.getCell(8).setCellStyle(style4);
	        
	        header2.createCell(9).setCellValue("Amount Per Day");
	        header2.getCell(9).setCellStyle(style4);
	        
	        header2.createCell(10).setCellValue("No. of Days Worked");
	        header2.getCell(10).setCellStyle(style4);
	        
	        header2.createCell(11).setCellValue("Amount");
	        header2.getCell(11).setCellStyle(style4);
	        
	        header2.createCell(12).setCellValue("*Dates for Oncall & Shift Allowances");
	    	header2.getCell(12).setCellStyle(style2);

	        // create data rows
	        int rowCount = 3;
	        int counter = 1;
	        int netAmount;
	        for (Map.Entry<ResourceMaster, ArrayList> e : empObjectAndData.entrySet()) {
	        	 netAmount = 0;
	        	 Integer portfolioId = null;
	        	 HSSFRow aRow = sheet.createRow(rowCount++);
	        	 if(e.getKey().getProjectId() != null)
	        		 portfolioId = e.getKey().getProject().getProgram().getPortfolio().getPortfolioId();
	        	 
	        	 for(ProjectLocation projLoc : projectLocationDetails)
	        	 {
	        		 if(projLoc.getPortfolioId() == portfolioId && projLoc.getLocationId() == e.getKey().getLocationId())
	        			 aRow.createCell(5).setCellValue(projLoc.getBillableWbs());
	        	 }
	        	 
	        	 aRow.createCell(0).setCellValue(counter);
	        	 aRow.createCell(2).setCellValue(e.getKey().getEmployeeId());
	        	 aRow.createCell(3).setCellValue(e.getKey().getEnterpriseId());
	        	 aRow.createCell(4).setCellValue(e.getKey().getCareerLevel().getDesignation());
	        	 //aRow.createCell(5).setCellValue(wbs);projectLocationDetails
	        	 ArrayList<Integer> shiftCount = e.getValue();
	        	 aRow.createCell(6).setCellValue(shiftCount.get(1));
	        	 aRow.createCell(8).setCellValue(shiftCount.get(2));
	        	 aRow.createCell(10).setCellValue(shiftCount.get(1) + shiftCount.get(2));
	        	 aRow.createCell(1).setCellValue(employeeAndTeam.get(e.getKey().getEmployeeId()));
	        	 aRow.createCell(7).setCellValue(e.getKey().getCareerLevel().getShiftbAmount());
	        	 aRow.createCell(9).setCellValue(e.getKey().getCareerLevel().getShiftcAmount());
	        	 netAmount = (shiftCount.get(1) * e.getKey().getCareerLevel().getShiftbAmount()) + (shiftCount.get(2) *e.getKey().getCareerLevel().getShiftcAmount());
    			 aRow.createCell(11).setCellValue(netAmount);
	        	/* for(CareerLevel careerLevel : allCareerLevels)
	        	 {
	        		 Integer careerLevelValue = careerLevel.getLevel();
	        		 if(careerLevelValue.equals(e.getKey().getLevel()))
	        		 {
	        			 aRow.createCell(7).setCellValue(careerLevel.getShiftbAmount());
	        			 aRow.createCell(9).setCellValue(careerLevel.getShiftcAmount());
	        			 
	        		 }
	        	 }*/
	        	 String dates;
	        	 for(CalendarData calendarData : calendarDataList)
	        	 {
	        		 dates = "";
	        		 if(calendarData.getEmployeeId().equals(e.getKey().getEmployeeId()))
	        		 {
	        			 for(DayData dayData : calendarData.getDayData())
	        			 {
	        				 if(dayData.getShift().equalsIgnoreCase("b") || dayData.getShift().equalsIgnoreCase("c"))
	        				 {
	        					 dates = dates + dayData.getDate() + ",";
	        				 }
	        			 }
	        			 if(!dates.isEmpty())
	        			 {
	        			 	int lastComma = dates.lastIndexOf(',');
	        			 	dates = dates.substring(0, dates.length() - 1);
	        			 	aRow.createCell(12).setCellValue(dates);
	        			 }
	        		 }
	        	 }
	        	 
	        	 
	        	 counter ++;
	        }
	    }
}
