<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="in.co.rays.ctl.LoginCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login </title>
</head>

<style>
body {
  background-image: url('img/architech.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
}
</style>
<body>
<form action="<%=ORSView.LOGIN_CTL%>" method="post">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>

<div align="center" >
            <h1>Login</h1>
            <H2><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></H2>
            <H2><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%></font></H2>
            
			<%
			String msg =(String) request.getAttribute("message");
            if(msg!= null){ 
            %>
            <h1 align="center"><font style="color: red"><%=msg %></font>
            <%} %></h1>
</div>
        
              <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDateTime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDateTime())%>">

            <table>
            
             <tr>
                    <th align="left">LoginId <span style="color: red">*</span></th>
                    <td ><input type="text" name="login"  value="<%=DataUtility.getStringData(bean.getLogin())%>">
                   <%System.out.println("========>.."+bean.getLogin());
                    %>
                   </td>
                    <td style="position: fixed;"><font color="red" ><%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
               	
          
                <tr>
                    <th>Password*</th>
                    <td><input type="password" name="password" size=30
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
                       
                       
                        color="red"> <%=ServletUtility.getErrorMessageHtml(request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=LoginCtl.OP_SIGN_IN %>"> &nbsp; <input type="submit"
                        name="operation" value="<%=LoginCtl.OP_SIGN_UP %>" > &nbsp;</td>
                </tr>
                <tr><th></th>
                 <td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget my password</b></a>&nbsp;</td> 
            </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>


</html>