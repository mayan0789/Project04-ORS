<%@page import="in.co.rays.model.StudentModel"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.StudentListCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>Student List</title></head>
 <script src="<%=ORSView.APP_CONTEXT %>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT %>/js/Checkbox11.js"></script>

<body>
    <%@include file="Header.jsp"%>
    <center>
        <h1>Student List</h1>

        <form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">
        <jsp:useBean id="bean" class="in.co.rays.bean.StudentBean" scope="request"></jsp:useBean>
		
		<h2> <font color = "red"><%=ServletUtility.getErrorMessage(request) %></font></h1>
		<h2> <font color = "green"><%=ServletUtility.getSuccessMessage(request) %></font></h1>
		
<%
		List stlist =(List) request.getAttribute("stulist");
	
	int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<StudentBean> it = list.iterator();
                    if(list.size()!=0){
                    %>
            <table width="100%">
                <tr>
                    <td align="center"><label> Name :</label>
                    <%=HTMLUtility.getList("name", String.valueOf(bean.getId()), stlist) %>
                        
                        <label>Email_Id:</label> <input type="text" name="email" placeholder="Enter a Login_id"
                        value="<%=ServletUtility.getParameter("email", request)%>">
                                                 
                        <input type="submit" name="operation" value="<%=StudentListCtl.OP_SEARCH %>">
                         &emsp; <input type="submit" name="operation"
						value="<%=StudentListCtl.OP_RESET%>"></td>
                        
                </tr>
            </table>
            <br>
            <table border="1" width="100%" cellpadding="10" cellspacing="0">
                <tr>
					<th> <input type="checkbox"  id="select_all" name="select">Select All </th>
                    <th>ID.</th>
                    <th>College.</th>
                    <th>First Name.</th>
                    <th>Last Name.</th>
                    <th>Date Of Birth.</th>
                    <th>Mobil No.</th>
                    <th>Email ID.</th>
                    <th>Edit</th>
                </tr>
                <%

                    while (it.hasNext()) {

                        StudentBean ben = it.next();   %>
                <tr align="center">
                <td> <input type="checkbox" class="checkbox" name="ids" value="<%=ben.getId()%>"></td>
                    <td><%=index++%></td>
                   <%--  <td><%=bean.getId()%></td> --%>
                    <td><%=ben.getCollege_Name()%></td>
                    <td><%=ben.getFirst_Name()%></td>
                    <td><%=ben.getLast_Name()%></td>
                    <td><%=ben.getDate_of_Birth()%></td>
                    <td><%=ben.getMobile_No()%></td>
                    <td><%=ben.getEmail()%></td>
                    <td><a href="StudentCtl?id=<%=ben.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
				<tr>
				<% if (pageNo==1){ %>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=StudentListCtl.OP_PREVIOUS%>"></td><%}else{ %>
						<td><input type="submit" name="operation" 
						value="<%=StudentListCtl.OP_PREVIOUS%>"></td>
						<%} %>
					
						<td><input type="submit" name="operation" value="<%=StudentListCtl.OP_DELETE %>"></td> 
					<td><input type="submit" name="operation" value="<%=StudentListCtl.OP_NEW%>"></td>	
		
						
						<% StudentModel model = new StudentModel(); 
						if(list.size()<pageSize || model.nextPK()-1 == bean.getId()){ 
						%><td align="right"><input type="submit" name="operation" disabled="disabled"
						value="<%=StudentListCtl.OP_NEXT%>"></td><%}else{ %>
					<td align="right"><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_NEXT%>"></td>
						<%} %>
				</tr>
			</table>
           <%
                    }
                    if(list.size() == 0){
			%>
			<td align="center"> <input type = "submit" name="operation" value="<%=StudentListCtl.OP_BACK%>"></td>
			<%} %>
<input type="hidden" name="pageNo" value="<%=pageNo%>">
 <input type="hidden" name="pageSize" value="<%=pageSize%>">
 <br><br><br><br><br><br>
        </form>
    <%@include file="Footer.jsp"%>

    </center>
</body>
</html>
