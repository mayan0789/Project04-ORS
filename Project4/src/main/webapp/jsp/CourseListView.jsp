
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.model.CourseModel"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.CourseListCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.bean.CourseBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.ctl.CourseCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<html>
 <script src="<%=ORSView.APP_CONTEXT %>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT %>/js/Checkbox11.js"></script>

<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Course List</title>

<script src="<%=ORSView.APP_CONTEXT %>/js/jquery.min.js""></script>
<script src="<%=ORSView.APP_CONTEXT %>/js/Checkbox11.js"></script>

</head>

<body>
<jsp:useBean id="bean" class="in.co.rays.bean.CourseBean" scope="request" ></jsp:useBean>
  <form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">
  <%@include file="Header.jsp"%>

    <%
		List<CourseBean> courselist = (List<CourseBean>)request.getAttribute("CourseList"); 
	%>
  
    
    <center>
    
     <div align="center">
	        <h1>Course List</h1>
            <h2><font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
            <font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h2>
     </div>
     
	
       <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);                
                    int index = (pageNo - 1)*pageSize + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<CourseBean> it = list.iterator();

	       			if(list.size()!=0){
                    %>
            
            <table width="100%" align="center">
                <tr>
                 <td align="Center">
                 <label> Course Name :</label> 
                 	<%=HTMLUtility.getList("cname", String.valueOf(bean.getId()), courselist) %>
                 <%-- 	<input type="text" name="cname" placeholder="Enter Course Name" Size= "25" value="<%=DataUtility.getStringData(bean.getName()) %>">
					 --%>&emsp;
                     <input type="submit" name="operation" value="<%=CourseListCtl.OP_SEARCH%>">
        	         &emsp;
        	         <input type="submit" name="operation" value="<%=CourseListCtl.OP_RESET%>">
        	         
                 </td>
                </tr>
            </table>
            
            <br>
            
            <table border="1" width="100%" align="center" cellpadding="4" cellspacing="0">
                <tr>
                     <th><input type="checkbox" id="select_all" name="select"> Select All </th>
                <th>S.NO.</th>
                <th>Course Name.</th>
                <th>Duration.</th>
                <th>Description.</th>
                <th>Edit</th>
                </tr>
                
                <%
                	while(it.hasNext())
                	{
						bean = it.next();
                %>
                
                
                
       <tr align="center">
           	<td><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId() %>">
                    <td><%=index++%></td>
                    <td><%=bean.getCourse_Name()%></td>
                    <td><%=bean.getDuration()%></td>
                    <td><%=bean.getDescription()%></td>
                    <td><a href="CourseCtl?id=<%=bean.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                <%if(pageNo == 1){ %>
                    <td><input type="submit" name="operation" disabled="disabled" value="<%=CourseListCtl.OP_PREVIOUS%>">
       				<%}else{ %>
       				<td><input type="submit" name="operation"  value="<%=CourseListCtl.OP_PREVIOUS%>"></td>
       				<%} %>		
                     
                     <td><input type="submit" name="operation" value="<%=CourseListCtl.OP_DELETE%>"> </td>
                    <td> <input type="submit" name="operation" value="<%=CourseListCtl.OP_NEW%>"></td>
                    
                  <% CourseModel model = new CourseModel();                  
                  %>  
                  <% if(list.size() < pageSize || model.nextPk()-1 == bean.getId()){ %>
                  <td align="right"> <input type="submit" name="operation" disabled="disabled" value="<%=CourseListCtl.OP_NEXT%>"></td>
  					<%}else{ %>                   
  				  <td align="right"> <input type="submit" name="operation"  value="<%=CourseListCtl.OP_NEXT%>"></td>
   					<%} %>                 
                    
                </tr>
            </table>
            		<%}
	       			System.out.println("----------------00000------"+list.size());
	       			if(list.size() == 0 ){ %>
            		<td align="center"><input type="submit" name="operation" value="<%=CourseListCtl.OP_BACK%>"></td>	
            		<% } %>
            	
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
<br><br><br><br><br>	
 <%@include file="Footer.jsp"%>
</body>
</html>