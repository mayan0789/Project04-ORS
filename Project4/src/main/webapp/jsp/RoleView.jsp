<%@page import="in.co.rays.ctl.RoleCtl"%>
<%@page import="in.co.rays.ctl.BaseCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<html>
<head> 
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title> Add Role</title></head>
<body>
    <form action="<%=ORSView.ROLE_CTL%>" method="post">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.rays.bean.RoleBean"
            scope="request"></jsp:useBean>

        <center>
		<%
				if (bean.getId() > 0) {
			%>
			<h1>Update Role</h1>
			<%
				} else {
			%>
			<h1>Add Role</h1>
			<%
				}
			%>
		
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDateTime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDateTime())%>">
            

            <table>
                <tr>
                    <th align="left">Name<span style="color: red">*</span></th>
                    <td><input type="text" name="name" placeholder="Enter Name Here"
                        value="<%=DataUtility.getStringData(bean.getName())%>"></td> <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Description<span style="color: red">*</span></th>
                    <td><input type="text" name="description" placeholder="Enter a Description Here"
                        value="<%=DataUtility.getStringData(bean.getDescription())%>"></td> <td style="position : fixed;"><font
                        color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
<%
		if(bean.getId() > 0){ %>
		<td>
		<input type="submit" name="operation" value="<%=RoleCtl.OP_UPDATE%>">
		<input type="submit" name="operation" value="<%=RoleCtl.OP_CANCEL%>"></td>
		<%}else{ %>
		<td>
		<input type="submit" name="operation" value="<%=RoleCtl.OP_SAVE %>">
		<input type="submit" name="operation" value="<%=RoleCtl.OP_RESET%>"></td>
	
		<%} %>                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>
