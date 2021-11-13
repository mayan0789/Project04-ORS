<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<meta charset="ISO-8859-1">
</head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd/mm/yy',
			//defaultDate : "06/06/2001",
			changeMonth : true,
			changeYear : true,
			//yearRange: "c-20:c+0",
			//yearRange : "1980:2025",
		maxDate:'31/12/2003',
		// minDate:0
		yearRange : "-40:-18"
		});
	});
</script>
<body>
<%@page import="in.co.rays.bean.RoleBean"%>
<%@page import="in.co.rays.ctl.LoginCtl"%>
<%@page import="in.co.rays.bean.UserBean"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirst_Name() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }
%>

<table width="100%" border="0">
    <tr>
        <td width="90%" ><a href="<%=ORSView.WELCOME_CTL%>">Welcome</b></a> |
            <%
            if (userLoggedIn) {
        %> <a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</b></a> 

            <%
                } else {
            %> <a href="<%=ORSView.LOGIN_CTL%>">Login</b></a> <%
     }
 %></td>
        <td rowspan="2">
            <h1 align="Right">
                <img src="<%=ORSView.APP_CONTEXT%>/img/rays.jpg" width="318"
                    height="90">
            </h1>
        </td>

    </tr>
    
    <tr>
        <td >
            <h3>
                <%=welcomeMsg%></h3>
        </td>
    </tr>
    

    <%
        if (userLoggedIn) {
    %>

   
    <tr>
        <td colspan="2" >     
        <font style="font-size: 18px">
       	 
        <a href="<%=ORSView.MY_PROFILE_CTL%>">MyProfile</b></a> |       
         <a href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</b></a> |
       	 <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</b></a> |              
       	 <a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet MeritList</b></a> |
        
        <%
            if (userBean.getRoll_Id() == RoleBean.ADMIN) {
        %> 
       
        <a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> |       
        <a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> |
        <a href="<%=ORSView.USER_CTL%>">Add User</b></a> | 
        <a href="<%=ORSView.USER_LIST_CTL%>">User List</b></a> |         
        <a href="<%=ORSView.COLLEGE_CTL%>">Add College</b></a> |        
        <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> |
        <a href="<%=ORSView.ROLE_CTL%>">Add Role</b></a> |        
        <a href="<%=ORSView.ROLE_LIST_CTL%>">Role List</b></a> |
        <a href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> |
        <a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> |
        <a href="<%=ORSView.COURSE_CTL %>" >Add Course</b></a> |       
        <a href="<%=ORSView.COURSE_LIST_CTL %>">Course List</b></a> |       
        <a href="<%=ORSView.SUBJECT_CTL %>" >Add Subject</b></a> |       
        <a href="<%=ORSView.SUBJECT_LIST_CTL %>">Subject List</b></a> |       
        <a href="<%=ORSView.FACULTY_CTL %>" >Add Faculty</b></a> |       
        <a href="<%=ORSView.FACULTY_LIST_CTL %>">Faculty List</b></a> |
        <a href="<%=ORSView.TIMETABLE_CTL %>" >Add TimeTable</b></a> |       
        <a href="<%=ORSView.TIMETABLE_LIST_CTL %>">TimeTable List</b></a> |
        <a target = "blank" href="<%=ORSView.JAVA_DOC_VIEW%>"> Java Doc </a>
     
       <%
     		}
 		%>
 		 <%
            if (userBean.getRoll_Id() == RoleBean.STUDENT) {
        %> 
       
        <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> |
        <a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> |
        <a href="<%=ORSView.COURSE_LIST_CTL %>">Course List</b></a> |       
        <a href="<%=ORSView.SUBJECT_LIST_CTL %>">Subject List</b></a> |       
        <a href="<%=ORSView.FACULTY_LIST_CTL %>">Faculty List</b></a> |
        <a href="<%=ORSView.TIMETABLE_LIST_CTL %>">TimeTable List</b></a> |
       <%
     		}
 		%>
		
 		<%
            if (userBean.getRoll_Id() == RoleBean.KIOSK) {
        %> 
        <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> |
        <a href="<%=ORSView.TIMETABLE_LIST_CTL %>">TimeTable List</b></a> |
        <a href="<%=ORSView.COURSE_LIST_CTL %>">Course List</b></a> |       
       <%
     		}
 		%>
 		 <%
            if (userBean.getRoll_Id() == RoleBean.FACULTY) {
            System.out.println("======>><><>"+userBean.getRoll_Id());	
        %> 
       
        <a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> |       
        <a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> |
        <a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> |
        <a href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> |
        <a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> |
        <a href="<%=ORSView.COURSE_LIST_CTL %>">Course List</b></a> |       
        <a href="<%=ORSView.SUBJECT_CTL %>" >Add Subject</b></a> |       
        <a href="<%=ORSView.SUBJECT_LIST_CTL %>">Subject List</b></a> |       
        <a href="<%=ORSView.TIMETABLE_CTL %>" >Add TimeTable</b></a> |       
        <a href="<%=ORSView.TIMETABLE_LIST_CTL %>">TimeTable List</b></a> |
       <%
     		}
 		%>
 		 <%
            if (userBean.getRoll_Id() == RoleBean.COLLEGE) {
           System.out.println("======>><><>"+userBean.getRoll_Id());	
          %> 
       
        <a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> |       
        <a href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> |
        <a href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> |
        <a href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> |
        <a href="<%=ORSView.FACULTY_LIST_CTL %>">Faculty List</b></a> |
        <a href="<%=ORSView.TIMETABLE_LIST_CTL %>">TimeTable List</b></a> |
        <a href="<%=ORSView.COURSE_LIST_CTL %>">Course List</b></a> |       
       <%
     		}
 		%>
 		
 		</font>
 		</td>
    </tr>
    	<%
        	}
   		 %>
</table>
<hr>

</body>
</html>