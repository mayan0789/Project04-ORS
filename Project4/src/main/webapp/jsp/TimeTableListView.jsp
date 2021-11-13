<%@page import="in.co.rays.model.TimetableModel"%>
<%@page import="in.co.rays.ctl.TimeTableListCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.bean.TimetableBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
 <script src="<%=ORSView.APP_CONTEXT %>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT %>/js/Checkbox11.js"></script>

<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> TimeTable List</title>

<script src="<%=ORSView.APP_CONTEXT %>/js/jquery.min.js""></script>
<script src="<%=ORSView.APP_CONTEXT %>/js/Checkbox11.js"></script>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script >
function DisableSunday(date){
	var day = date.getDay();
	// if day == 0 then it is sunday
	if(day == 0)
	{
		return [false];	
	}else{
		return [true];
		}
	}
$(function() {
		    $("#udate6").datepicker({
		      beforeShowDay : DisableSunday,
		    	changeMonth: true,
		      	 changeYear: true,
		      	yearRange :'0:+2',
				  dateFormat:'dd/mm/yy',
				
			//	  minDate : 0 
		  	  });
		  	});
</script>
</head>
<body >
<jsp:useBean id="bean" class="in.co.rays.bean.TimetableBean" scope="request"></jsp:useBean>
<%@include file = "Header.jsp" %>

<form action="<%=ORSView.TIMETABLE_LIST_CTL %>" method="post"> 
	
	<center>
		
	<div align="center">
	<h1>TimeTable List</h1>
	<h3>
	
		<font style="font: bold ; color: red"><%=ServletUtility.getErrorMessage(request) %></font>	
		<font style="font: bold ; color: green"><%=ServletUtility.getSuccessMessage(request) %></font>	
	</h3>
	</div>
	
	<%
		List cList = (List) request.getAttribute("courseList");
		List sList = (List) request.getAttribute("subjectList"); 
	%>
	<%
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);	
	int index = ((pageNo-1)*pageSize)+1;

	List list = ServletUtility.getList(request);
	Iterator<TimetableBean> it = list.iterator();
	 
	if(list.size() !=0){

	%>
	
	<table width ="100%">
		<tr>
		<td align="center">
		<label>Course Name :</label>
		<%=HTMLUtility.getList("clist", String.valueOf(bean.getCourse_Id()), cList) %>
		
		<label>Subject Name :</label>
		<%=HTMLUtility.getList("slist", String.valueOf(bean.getSubject_Id()), sList) %>
				
		<label>Date Of Exam</label>
		<input type ="text"  name="Exdate" readonly="readonly" id ="udate6" placeholder="Select Date" value="<%=DataUtility.getDateString(bean.getExam_Date()) %>">		
		&nbsp;
		<input type="submit" name="operation" value="<%=TimeTableListCtl.OP_SEARCH%>">
		&nbsp;
		<input type="submit" name="operation" value="<%=TimeTableListCtl.OP_RESET %>">
		</td>	
		</tr>
	</table>
<br>	
	<table border="1" width="100%" align="center" cellpadding=5px cellspacing="0">
		<tr align="center">
		
					<th> <input type="checkbox"  id="select_all" name="select">Select All </th>
						<th>S.No.</th>	
			<th>Course Name.</th>
			<th>Subject Name.</th>
<!-- 			<th>DESCRIPTION.</th> -->
			<th>SEMESTER.</th>
			<th>ExamDate.</th>
			<th>ExamTime.</th>
			<th>Edit</th>
			
		</tr>
	<%
	while(it.hasNext()){
	bean =it.next();	
	%>
	<tr align="center">
		<td><input type = "checkbox" class="checkbox" name="ids" value="<%=bean.getId() %>"></td>
		<td><%=index++ %></td>
		<td><%=bean.getCourse_Name() %></td>
		<td><%=bean.getSubject_Name() %></td>
		<%-- <td><%=bean1.getDescription() %></td> --%>
		<td><%=bean.getSemester() %></td>
		<td><%=bean.getExam_Date() %></td>
		<td><%=bean.getExam_Time() %></td>
		<td><a href ="TimeTableCtl?id=<%=bean.getId()%>">Edit</a></td>
	</tr>
		<% 
		}
		%>
	</table>

	<table width = "100%">
		<tr>
			<%if(pageNo==1){ %>
			<td align="left"><input type="submit" name="operation" disabled="disabled" value="<%=TimeTableListCtl.OP_PREVIOUS%>" ></td>
			<%}else{ %>
			<td align="left"><input type="submit" name="operation" value="<%=TimeTableListCtl.OP_PREVIOUS%>" ></td>
			<%} %>
			<td><input type="submit" name="operation" value="<%=TimeTableListCtl.OP_DELETE%>"></td>
			<td><input type="submit" name="operation" value="<%=TimeTableListCtl.OP_NEW%>"></td>	
			
				<%
					TimetableModel model = new TimetableModel();
				%>
			
		 <%if(list.size()<pageSize ||model.nextPk()-1 == bean.getId()){ 
		 
				
		 %>	
		 
			<td align="right"><input type="submit" disabled="disabled" name="operation" value="<%=TimeTableListCtl.OP_NEXT%>" ></td>
			<%}else{ %>
			<td align="right"><input type="submit" name="operation" value="<%=TimeTableListCtl.OP_NEXT%>" ></td>
			<%} %>
		</tr>
	</table>
	
					<%}if(list.size() == 0){ %>
            		<td align="center"><input type="submit" name="operation" value="<%=TimeTableListCtl.OP_BACK%>"></td>	
            		<% } %>
            
	
		<input type="hidden" name="operation" value="<%=pageNo %>">
		<input type="hidden" name="operation" value="<%=pageSize%>">
</form>
</center>
<br><br><br><br><br>
<%@include file = "Footer.jsp" %>
</body>
</html>