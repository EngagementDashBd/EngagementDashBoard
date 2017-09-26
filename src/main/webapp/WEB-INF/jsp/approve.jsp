<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import = "com.acc.entity.ResourceMaster"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
<TITLE>Approve</TITLE>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- 
<link rel="stylesheet" href="css/sideNav.css"> -->
<!-- <link rel="stylesheet" href="css/approve.css"> -->
<!-- <link rel="stylesheet" href="css/timesheet.css"> -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<script src="script/approve.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<style>
   #calendarTable {
  
    border-spacing: 10px 50px;
    font-family: verdana,arial,sans-serif;
	font-size:20px;
	color:#333333;
	-moz-border-radius:20px;
	border-collapse: collapse;
 	background: #707070;
	border-radius:20px;
	padding: 2000px;
}
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
<!-- New code -->
<script>
function getFirstDay(theYear, theMonth){
    var firstDate = new Date(theYear,theMonth,1)
    return firstDate.getDay()
}
// number of days in the month
function getMonthLen(theYear, theMonth) {
    var oneDay = 1000 * 60 * 60 * 24
    var thisMonth = new Date(theYear, theMonth, 1)
    var nextMonth = new Date(theYear, theMonth + 1, 1)
    var len = Math.ceil((nextMonth.getTime() - 
        thisMonth.getTime())/oneDay)
    return len
}
// create array of English month names
var theMonths = ["January","February","March","April","May","June","July","August",
"September","October","November","December"]
// return IE4+ or W3C DOM reference for an ID
function getObject(obj) {
    var theObj
    if (document.all) {
        if (typeof obj == "string") {
            return document.all(obj)
        } else {
            return obj.style
        }
    }
    if (document.getElementById) {
        if (typeof obj == "string") {
            return document.getElementById(obj)
        } else {
            return obj.style
        }
    }
    return null
}

/************************
  DRAW CALENDAR CONTENTS
*************************/
// clear and re-populate table based on form's selections
function populateTable(form) {
    var theMonth = form.chooseMonth.selectedIndex
    var theYear = parseInt(form.chooseYear.options[form.chooseYear.selectedIndex].text)
   
    var firstDay = getFirstDay(theYear, theMonth)
    var howMany = getMonthLen(theYear, theMonth)
    var secondHalfFirstDay =  (firstDay + 1) % 7
    
    var secondYear;
    var secondMonth = (theMonth + 1) % 12;
    if(theMonth < 11)
    	secondYear = theYear;
    else
    	secondYear = theYear + 1;
   
    getObject("tableHeader").innerHTML = "16th "+theMonths[theMonth] + " " + theYear + " - 15th " +  theMonths[secondMonth] + " " + secondYear
    
    var TBody = getObject("tableBody")
    // clear any existing rows
    while (TBody.rows.length > 0) {
        TBody.deleteRow(0)
    }
    var newR, newC
    var done=false
    
    var j=1;
    var dayCounter = 16;
    var counter = 16;
    while (!done) {
        // create new row at end
    	  newR = TBody.insertRow(TBody.rows.length)
          for (var i = 0; i < 7; i++) {
              // create new cell at end of row
              newC = newR.insertCell(newR.cells.length)
              if (TBody.rows.length == 1 && i < secondHalfFirstDay) {
                  // no content for boxes before first day
                  newC.innerHTML = ""    
                  continue;
              }
              if (counter == howMany + 15) {
                  // no more rows after this one
                  done = true
              }
              if(dayCounter > howMany)
              	dayCounter = 1
            
            if (counter <= howMany + 15)
			{
   				var select = document.createElement("select");
   				var s="shiftdetail"+dayCounter;
   				select.setAttribute("name",s);
   				select.setAttribute("id",s);
   				select.options.add( new Option("","a", true, true) );
   				select.options.add( new Option("shift B","b") );
   				select.options.add( new Option("shift C","c") );
   				newC.innerHTML = dayCounter 
   				newC.appendChild(select);
   				dayCounter++
   				counter ++
			}
		else
			{
			newC.innerHTML = "";
			}
        }
        
    }
    var employeeId = document.getElementById("employeeId").value;
}

function fillYears() {
    var today = new Date()
    var thisYear = today.getFullYear()
    var yearChooser = document.dateChooser.chooseYear
    for (i = thisYear; i < thisYear + 5; i++) {
        yearChooser.options[yearChooser.options.length] = new Option(i, i)
    }
    setCurrMonth(today)
}
// set month choice to current month
function setCurrMonth(today) {
    document.dateChooser.chooseMonth.selectedIndex = today.getMonth()
}

$(document).ready(function() {
		var myFunction = function myFunction(){
		var month = document.getElementById("chooseMonth").selectedIndex
		var year = document.getElementById("chooseYear").value
		var selectEmployee = document.getElementById("selectEmployee");
		var employeeId = selectEmployee.options[selectEmployee.selectedIndex].value;
		var length = 0;
		if(month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11)
			length = 31;
		else if(month == 3 || month == 5 || month == 8 || month == 10 )
			length = 30;
		else
		{
			 if( ( year % 4 == 0 && year % 100 != 0 ) || year % 400 == 0 )
				 length = 29;
			 else
				 length = 28;
		}
		 if(month < 11)
		    	secondYear = year;
		    else
		    	secondYear = year + 1;
		var secondMonth = (month + 1) % 12;
		event.preventDefault();
		$.ajax({
			url:"getCalendarData.do",
			dataType: 'json',
			data:{employeeId:employeeId,
				startMonth : month,
				endMonth : secondMonth,
				startYear:year,
				endYear: secondYear
			},

			type:"POST",
			success: function(data) {
				var holidayCount = 0;
				for(i = 1 ; i <= length ; i++)
				{
					var s = "shiftdetail"+i;
					for(var j = 0 ; j < data.dayData.length ; j++)
					{
						if(i == data.dayData[j].date)
						{	

							if (data.dayData[j].isOnShoreHoliday)
							{
								holidayCount++;
								document.getElementById(s).value = 'd';
								$('#'+s).css("background-color","#13b4ff");
							}
							else if(data.dayData[j].isOffShoreHoliday)
							{
								holidayCount++;
								document.getElementById(s).value = 'e';
								$('#'+s).css("background-color","#ffff00");
							}
							else
								document.getElementById(s).value = data.dayData[j].shift;
						
						}
					}
				}
				
				if(data.dayData.length == 0 || data.dayData.length == holidayCount)
					{
						alert("The employee has not submitted his/her shift details")
					   	for(var i = 1 ; i <= length ; i++)
						{
							var s = "shiftdetail"+i;	
							document.getElementById(s).value = 'a';
						
						}	
						
					}
				},
	            error: function (jqXHR, textStatus, errorThrown) {
	            	alert(textStatus)
	             alert(jqXHR.status)  
	             alert(errorThrown)
	            }
		});
		
	}
		 $("#selectEmployee").change(myFunction)
		 $("#chooseMonth").change(myFunction)
		 $("#chooseYear").change(myFunction)
	
	
});

</script>
<!-- New code -->
<c:if test="${code == 'approved'}">
<script>
  alert("Timesheet Approved");
</script>
</c:if>
<c:if test="${code == 'rejected'}">
<script>
  alert("Timesheet Rejected");
</script>
</c:if>
</HEAD>

<BODY onLoad="fillYears(); populateTable(document.dateChooser)">

<c:forEach items="${calendarData}" var="item">
	<input type = "hidden" id = "calendar" value = "${item}" >
</c:forEach>

	

<H1></H1>
<HR>
<FORM NAME="dateChooser" action="approveOrReject.htm" method = "post" >

<input type="hidden" name="employeeId" id="employeeId">
	<div id = "selectEmployeeDiv">
 		<select class="selectpicker" id = "selectEmployee"  name = "selectEmployee">
 			<option value = "">----Select an Employee----</option>
 			<c:forEach items="${employees}" var="employees">
				<option value="${employees.employeeId}">${employees.employeeName}</option>
			</c:forEach>
		</select>
	</div>
	<br><br><br>
	<center>
		<TABLE ID="calendarTable"  ALIGN="center" width="950" height="250" rules="all">
		<TR height = "10">
   			 <TH ID="tableHeader" COLSPAN=7 height="10"></TH>
		</TR>

<TR height="10">
    <TD COLSPAN=7>
    <P>
    
        <SELECT NAME="chooseMonth" 
        onChange="populateTable(this.form)" id = "chooseMonth">
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
    <SELECT NAME="chooseYear" onChange="populateTable(this.form)" id = "chooseYear">
    </SELECT>
    </P></TD>
</TR>
<TR height="27"><TH>Sun</TH><TH>Mon</TH><TH>Tue</TH><TH>Wed</TH>
<TH>Thu</TH><TH>Fri</TH><TH>Sat</TH></TR>
<TBODY ID="tableBody"></TBODY>
</TABLE>
</center>
<br><br>

<center>
	<Button class="btn icon-btn btn-success" name = "submit" type = submit value = "Approve"> <span class="glyphicon btn-glyphicon glyphicon-check img-circle text-success"></span>Approve</Button>&emsp;&emsp;
	<Button class="btn icon-btn btn-danger" name = "submit" type = submit value = "Reject"> <span class="glyphicon btn-glyphicon glyphicon-remove img-circle text-danger"></span>Reject</Button>&emsp;&emsp;
	</center>
 </FORM>
 <!-- 	<script>
	function openNav() {
	    document.getElementById("mySidenav").style.width = "20%";
	}
	function closeNav() {
	    document.getElementById("mySidenav").style.width = "0";
	 	document.getElementById("main").style.width="100%";
	}
	
	</script> -->
	<span class = "legend onshore"></span><b>- OnShore Holiday</b>
<br><br>
<span class = "legend offshore"></span><b>- OffShore Holiday</b>
</BODY>
</HTML>