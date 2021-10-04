
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="javax.swing.text.html.HTML"%>
<%@page import="in.co.rays.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ctl.StudentCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Student Registration Page</title>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
	  yearRange:'1980:2020',
	  dateFormat:'dd/mm/yy'
    });
  } );
  </script>
</head>
<body>
    <jsp:useBean id="bean" class="in.co.rays.bean.StudentBean" scope="request"></jsp:useBean>
    	
	<form action="<%=ORSView.STUDENT_CTL%>" method="post">
    <%@include file="Header.jsp"%>
    <% 
    	List <CollegeBean> clist = (List <CollegeBean>)request.getAttribute("collegeList");
    
    %>
    
    <center>
        <h1>
        	<%
        		if( bean != null && bean.getId()>0){
        	%> 
        	<tr><th><font>Update Student</font></th></tr>
        	<% }else{%>
        	<tr><th><font>Add Student</font></th></tr>
        	<% }%>
        </h1>
		
		<div>
		<h1><font style="color: green"><%=ServletUtility.getSuccessMessage(request) %></font></h1>
		<h1><font style="color: red"><%=ServletUtility.getErrorMessage(request) %></font>
		</h1>
		</div>
		
		<input type="hidden" name="id" value="<%=bean.getId()%>">
		<input type="hidden" name="createdby" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedby" value="<%=bean.getModifiedBy()%>">
		<input type="hidden" name="createddatetime" value="<%=bean.getCreatedDateTime()%>">
		<input type="hidden" name="modifieddatetime" value="<%=bean.getModifiedDateTime()%>">

	<table>
	
		<tr>
		<th align="right">CollegeName <span style="color: red">*</span></th>
		<td><%=HTMLUtility.getList("collegename", String.valueOf(bean.getCollege_Id()), clist) %>
		<td style="position: fixed"><font color="red" ><%=ServletUtility.getErrorMessage("collegename", request)%></font>
		</td>
		</tr>
	  <tr><th style="padding: 3px"></th></tr>    	
		<tr>
		<th align="right">FirstName <span style="color: red">*</span></th>
		<td><input type="text" name="firstname" placeholder="Enter First Name" size="25" value="<%=DataUtility.getStringData(bean.getFirst_Name())%>"></td>
		<td style="position: fixed"><font  color="red"><%=ServletUtility.getErrorMessage("firstname", request)%></font>
		</td>
		</tr>
		  <tr><th style="padding: 3px"></th></tr>    
		<tr>
		<th align="right" >LastName <span style="color: red">*</span></th>
		<td><input type="text" name="lastname" placeholder="Enter Last Name" size="25" value="<%=DataUtility.getStringData(bean.getLast_Name())%>"></td>
		<td style="position: fixed"><font  color="red"><%=ServletUtility.getErrorMessage("lastname", request)%></font>
		</td>
		</tr>
		  <tr><th style="padding: 3px"></th></tr>    
		<tr>
					<th align="left">Date Of Birth<font color="red">*</font></th>
					<td><input type="text" name="dob" id="datepicker"
						placeholder="Enter Date Of Birth" readonly="readonly"
						
						value="<%=DataUtility.getDateString(bean.getDate_of_Birth())%>">
						&nbsp;<font style="position: fixed;" color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
						 <tr><th style="padding: 3px"></th></tr>    
		<tr>
		<th align="right">MobileNo <span style="color: red">*</span></th>
		<td><input type="text" name="mobile" maxlength="10" placeholder="Enter Mobile No" size="25" value="<%=DataUtility.getStringData(bean.getMobile_No())%>"></td>
		<td style="position: fixed" ><font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
		</td>
		</tr>
		  <tr><th style="padding: 3px"></th></tr>    
		<tr>
		<th align="right">Email-Id <span style="color: red">*</span></th>
		<td><input type="text" name="email" placeholder="Enter Email_Id" size="25" value="<%=DataUtility.getStringData(bean.getEmail())%>"></td>
		<td style="position: fixed" ><font color="red"><%=ServletUtility.getErrorMessage("email", request)%></font>
		</td>
		</tr>
		  <tr><th style="padding: 3px"></th></tr>    
		

	<tr>
	<th></th>
		<%
		if(bean.getId() > 0){ %>
		<td>
		<input type="submit" name="operation" value="<%=StudentCtl.OP_UPDATE%>">
		<input type="submit" name="operation" value="<%=StudentCtl.OP_CANCEL%>"></td>
		<%}else{ %>
		<td>
		<input type="submit" name="operation" value="<%=StudentCtl.OP_SAVE %>">
		<input type="submit" name="operation" value="<%=StudentCtl.OP_RESET%>"></td>
	
		<%} %>
	</tr>
	
	</table>
</form>
</center>
<%@ include file = "Footer.jsp" %>
</body>
</html>
