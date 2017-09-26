package com.acc.pdfcreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acc.entity.CalendarData;
import com.acc.entity.DayData;
import com.acc.entity.ResourceMaster;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class PDFBuilder extends AbstractITextPdfView {
	@Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
		Map<ResourceMaster, ArrayList> empObjectAndData = (Map<ResourceMaster, ArrayList>)model.get("empObjectAndData");
		String month = (String)model.get("month");
		Map<Long, String> employeeAndTeam = (Map<Long, String>)model.get("employeeAndTeam");
        doc.add(new Paragraph("Monthly Reports " + month));
        List<CalendarData> calendarDataList = (List<CalendarData>)model.get("calendarDataList");
        PdfPTable table = new PdfPTable(13);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {1,4,4,4,3,3,3,3,3,3,3,3,4});
        table.setSpacingBefore(10);
       
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);
        
      
        
        // write table header
        cell.setPhrase(new Phrase("S.No", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Team", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Employee Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Enterprise ID", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Job Level", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("WBS Element", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("No. of Days Worked(Shift A)", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Amount Per Day(Shift A)", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("No. of Days Worked(Shift B)", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Amount Per Day(Shift B)", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("No. of Days Worked(Total)", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Total Amount", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("*Dates for Oncall & Shift Allowances", font));
        table.addCell(cell);
       
        /*for (Map.Entry<ResourceMaster, ArrayList> e : empObjectAndData.entrySet()) {
        	 table.addCell(e.getKey().getEmployeeName());
        	ArrayList<Integer> shiftCount = e.getValue();
        	for (Integer temp : shiftCount) {
        		table.addCell(String.valueOf(temp));
    		}
        	if(shiftCount.isEmpty())
        	{
        		table.addCell("Null");
        		table.addCell("Null");
        		table.addCell("Null");
        	}
     		
		}*/
        Integer counter = 1;
        for (Map.Entry<ResourceMaster, ArrayList> e : empObjectAndData.entrySet()) {
       	 table.addCell(counter.toString());
       	 table.addCell(employeeAndTeam.get(e.getKey().getEmployeeId()));
       	 table.addCell(e.getKey().getEmployeeId().toString());
	       	table.addCell(e.getKey().getEnterpriseId().toString());
	       	table.addCell(e.getKey().getCareerLevel().getDesignation());
	       	table.addCell("");
	       	ArrayList<Integer> shiftCount = e.getValue();	       	
	       	table.addCell(shiftCount.get(1).toString());	       	
	       	table.addCell(e.getKey().getCareerLevel().getShiftbAmount().toString());
	       	table.addCell(shiftCount.get(2).toString());
	       	table.addCell(e.getKey().getCareerLevel().getShiftcAmount().toString());
	       	Integer  totalDays = shiftCount.get(1) + shiftCount.get(2);
	       	table.addCell(totalDays.toString());
	       	Integer amount = ((shiftCount.get(1))*(e.getKey().getCareerLevel().getShiftbAmount()))+((shiftCount.get(2))*(e.getKey().getCareerLevel().getShiftcAmount()));
	       	table.addCell(amount.toString());
	       	String dates;
       	 for(CalendarData calendarData : calendarDataList)
       	 {
       		 dates = " ";
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
       			 	table.addCell(dates);
       			 }
       		 }
       	 }
       	 
       	 
       	 counter ++;

       	 
       	
       /*	for (Integer temp : shiftCount) {
       		table.addCell(String.valueOf(temp));
   		}
       	if(shiftCount.isEmpty())
       	{
       		table.addCell("Null");
       		table.addCell("Null");
       		table.addCell("Null");
       	}  */
    		
		}
 
         
        doc.add(table);

	}
}
