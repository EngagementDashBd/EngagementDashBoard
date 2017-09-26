package com.acc.excelcreator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.acc.entity.EmployeeHub;
import com.acc.entity.EmployeeProject;
import com.acc.entity.ProjectLocation;
import com.acc.entity.ResourceMaster;

public class EmployeeHubExcelReport extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		
		Map<Long,EmployeeProject> employeeAndProject =  (Map<Long, EmployeeProject>) session.getAttribute("employeeAndProject");
		ArrayList<ResourceMaster> employees = (ArrayList<ResourceMaster>) session.getAttribute("employees");		
		Map<Long,EmployeeHub> employeeAndHub = (Map<Long, EmployeeHub>) session.getAttribute("employeeAndHub");
		ArrayList<ProjectLocation> projectLocations = (ArrayList<ProjectLocation>)session.getAttribute("projectLocationDetails");
		HSSFSheet sheet = workbook.createSheet("Employee Report");
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
        header1.createCell(2).setCellValue("Employee Hub Details");
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
        
        header2.createCell(0).setCellValue("S.No");
        header2.getCell(0).setCellStyle(style1);
             
        header2.createCell(1).setCellValue("Employee Name");
        header2.getCell(1).setCellStyle(style1);
        
        header2.createCell(2).setCellValue("Enterprise Id");
        header2.getCell(2).setCellStyle(style1);
        
        header2.createCell(3).setCellValue("Portfolio");
        header2.getCell(3).setCellStyle(style1);
        
        header2.createCell(4).setCellValue("Program");
        header2.getCell(4).setCellStyle(style1);
        
        header2.createCell(5).setCellValue("Skill");
        header2.getCell(5).setCellStyle(style1);
        
        header2.createCell(6).setCellValue("Location");
        header2.getCell(6).setCellStyle(style1);
        
        header2.createCell(7).setCellValue("Project");
        header2.getCell(7).setCellStyle(style1);
        
        header2.createCell(8).setCellValue("Bill Rate");
        header2.getCell(8).setCellStyle(style1);
        
        header2.createCell(9).setCellValue("LCR");
        header2.getCell(9).setCellStyle(style1);
               
        header2.createCell(10).setCellValue("Working Hours for 15 NP");
        header2.getCell(10).setCellStyle(style1);
        
        header2.createCell(11).setCellValue("Working Hours for 45 NP");
        header2.getCell(11).setCellStyle(style1);
        
        
        header2.createCell(12).setCellValue("15 Days Holding Cost");
        header2.getCell(12).setCellStyle(style1);
        
        header2.createCell(13).setCellValue("45 Days Holdig Cost");
        header2.getCell(13).setCellStyle(style1);
        
        header2.createCell(14).setCellValue("Recommend To Retain");
        header2.getCell(14).setCellStyle(style1);
        
        header2.createCell(15).setCellValue("Potential Future Role");
        header2.getCell(15).setCellStyle(style1);
        
        header2.createCell(16).setCellValue("Hub Start Date");
        header2.getCell(16).setCellStyle(style1);
        
        header2.createCell(17).setCellValue("Hub End Date");
        header2.getCell(17).setCellStyle(style1);
        
        header2.createCell(18).setCellValue("Project End Date");
        header2.getCell(18).setCellStyle(style1);
        
        header2.createCell(19).setCellValue("WBS for Hub Hours");
        header2.getCell(19).setCellStyle(style1);
        
        header2.createCell(20).setCellValue("HCSC Experience");
        header2.getCell(20).setCellStyle(style1);
        
        header2.createCell(21).setCellValue("Comments");
        header2.getCell(21).setCellStyle(style1);
        
       int rowCount = 3;
       int counter = 1;
       for(ResourceMaster employee : employees)
       {
    	   HSSFRow aRow = sheet.createRow(rowCount++);
    	   aRow.createCell(0).setCellValue(counter);
    	   aRow.createCell(1).setCellValue(employee.getEmployeeName());
    	   aRow.createCell(2).setCellValue(employee.getEnterpriseId());
    	   aRow.createCell(3).setCellValue(employee.getProject().getProgram().getPortfolio().getPortfolioName());
    	   aRow.createCell(4).setCellValue(employee.getProject().getProgram().getProgramName());
    	   aRow.createCell(5).setCellValue(employee.getTechnology().getTechnologyName());
    	   
    	   
    	   aRow.createCell(6).setCellValue(employee.getLocation().getLocationName());
    	   aRow.createCell(7).setCellValue(employee.getProject().getProjectName());
    	   aRow.createCell(8).setCellValue("");
    	   double lcr = employeeAndProject.get(employee.getEmployeeId()).getLcr();
    	   aRow.createCell(9).setCellValue(lcr);
    	   aRow.createCell(10).setCellValue("");
    	   aRow.createCell(11).setCellValue("");
    	   aRow.createCell(12).setCellValue(lcr * 9.0 * 15.0);
	       aRow.createCell(13).setCellValue(lcr * 9.0 * 45.0);
	       aRow.createCell(14).setCellValue(employeeAndProject.get(employee.getEmployeeId()).getRecommendToHold());
	       aRow.createCell(15).setCellValue(employeeAndHub.get(employee.getEmployeeId()).getPotentialFutureRole());
    	   SimpleDateFormat datetemp = new SimpleDateFormat("MM-dd-yyyy");
    	   String hubStartDate = datetemp.format(employeeAndHub.get(employee.getEmployeeId()).getHubStartDate());
    	   aRow.createCell(16).setCellValue(hubStartDate);    
    	   if(employeeAndHub.get(employee.getEmployeeId()).getHubEndDate() != null)
    	   {
    		  String hubEndDate = datetemp.format(employeeAndHub.get(employee.getEmployeeId()).getHubEndDate());
    		  aRow.createCell(17).setCellValue(hubEndDate);  
    	   }
    	   String projectEndDate = datetemp.format(employeeAndProject.get(employee.getEmployeeId()).getRoleEndDate());
    	   aRow.createCell(18).setCellValue(projectEndDate);   
    	   for(ProjectLocation projectLocation : projectLocations)
    	   {
    		   if(projectLocation.getLocationId() == employee.getLocationId() && projectLocation.getPortfolioId() == employee.getProject().getProgram().getPortfolio().getPortfolioId())
    			   aRow.createCell(19).setCellValue(projectLocation.getNonBillableWbs());
    	   }
    	   
	       aRow.createCell(20).setCellValue("");
	       aRow.createCell(21).setCellValue("");
    	  
    	   counter++;
       }

		
	}
}

