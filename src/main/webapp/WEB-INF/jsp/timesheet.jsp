<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import = "com.acc.entity.ResourceMaster"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
<TITLE>Timesheet</TITLE>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="css/timesheet.css">
<link rel="stylesheet" href="css/fullcalendar.css">
<link rel="stylesheet" href="css/fullcalendar.print.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<!-- <script 
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<script src="script/calendar.js"></script>
<script src="script/timesheetsubmit.js"></script>
<c:if test="${code == 'success'}">
<script>
  alert("Timesheet Submitted");
</script>
</c:if>
<c:if test="${code == 'failure'}">
<script>
  alert("Error submitting your Timesheet");
</script>
</c:if>
<c:if test="${submitPermit == 'notAllowed'}">
<script>
  alert("TimeLimit for submitting this period has expired");
</script>
</c:if>
<style>
td{
height:60px;

}

.legend
{
float: left;
width: 20px;
height: 20px;
/* margin: 5px; */
border: 1px solid rgba(0,0,0,.2);
}
.onshore
{
background:#13b4ff;
}
.offshore
{
background:#ffff00;
}


</style>
</HEAD>
<BODY onLoad="fillYears(); populateTable(document.dateChooser)">



<HR>
<input type  = "hidden" value = "${resource.employeeId}" id = "employeeId">
<FORM id = "dateChooser" NAME="dateChooser" action="calendarstore.htm" method="post">
	<input type = "hidden" id = "flag" name = "flag" value = "submit"/>
	<center>
	<TABLE ID="calendarTable" ALIGN="center" width="950" height="300" rules="all">
	
	<TR>
    	<TH ID="tableHeader" COLSPAN=7 height="30"></TH>
	</TR>
	<TR height="35">
    	<TD COLSPAN=7>
    <P>
    	<SELECT NAME="chooseMonth" 
        	onChange="populateTable(this.form)">
            		<OPTION SELECTED>January - February</OPTION>
            	<OPTION>February - March</OPTION>
            	<OPTION>March - April</OPTION>
            	<OPTION>April - May</OPTION>
            	<OPTION>May - June</OPTION>
            	<OPTION>June - July</OPTION>
            	<OPTION>July - August</OPTION>
            	<OPTION>August - September</OPTION>
            	<OPTION>September - October</OPTION>
            	<OPTION>October - November</OPTION>
            	<OPTION>November - December</OPTION>
            	<OPTION>December - January</OPTION>
    	</SELECT>
    	<SELECT NAME="chooseYear" onChange="populateTable(this.form)">
    	</SELECT>
    </P></TD>
	</TR>
	<TR height="27"><TH>Sun</TH><TH>Mon</TH><TH>Tue</TH><TH>Wed</TH>
	<TH>Thu</TH><TH>Fri</TH><TH>Sat</TH></TR>
	<TBODY ID="tableBody"></TBODY>
	</TABLE>
	</center>
	<br/><br/>
		<center>
		<input class="btn icon-btn btn-success" type="submit" name = "submit" id = "submit" value="submit"  style="visibility:hidden"/> <!-- style="visibility:hidden" -->
		<input class="btn icon-btn btn-warning" type="submit" name = "submit" id = "update" value="update" style="visibility:hidden"/>
		</center>
 </FORM>
<span class = "legend onshore"></span> &nbsp;<b>- OnShore Holiday</b>
<br><br>
<div><span class = "legend offshore"></span> &nbsp;<b>- OffShore Holiday</b></div>
</BODY>
</HTML>