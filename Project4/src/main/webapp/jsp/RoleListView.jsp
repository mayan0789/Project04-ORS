<%@page import="in.co.rays.model.RoleModel"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.RoleListCtl"%>
<%@page import="in.co.rays.ctl.BaseCtl"%>
<%@page import="in.co.rays.bean.RoleBean"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head> 
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
 <title> Role List</title></head>
<script src="<%=ORSView.APP_CONTEXT %>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT %>/js/Checkbox11.js"></script>

<body>

	<%@include file="Header.jsp"%>

	<center>
		<h1>Role List</h1>

		<form action="<%=ORSView.ROLE_LIST_CTL%>" method="POST">
			<jsp:useBean id="bean" class="in.co.rays.bean.RoleBean"
				scope="request"></jsp:useBean>

			<%
				List rolist = (List) request.getAttribute("roleList");
			%>

			<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
			
			<h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
						<h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
			
				<table width="100%">
					<tr>
						<td align="center"><label>Name :</label> &emsp; <%=HTMLUtility.getList("name", String.valueOf(bean.getId()), rolist)%>
							&nbsp; <input type="submit" name="operation"
							value="<%=RoleListCtl.OP_SEARCH%>"> &emsp; <input
							type="submit" name="operation" value="<%=RoleListCtl.OP_RESET%>"></td>

					</tr>
					<tr>  <td align="center"></td></tr>
				</table>
				<table border="1" width="100%" cellpadding="10" cellspacing="0">
					<tr>
						<th><input type="checkbox" id="select_all" name="select">
							Select All</th>
						<th>S.No.</th>
						<th>Name</th>
						<th>Description</th>
						<th>Edit</th>
					</tr>
					<%--  <tr>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </tr>
 --%>
					<%
						int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
						int index = ((pageNo - 1) * pageSize) + 1;

						List list = ServletUtility.getList(request);
						Iterator<RoleBean> it = list.iterator();
						while (it.hasNext()) {
							RoleBean ben = it.next();
					%>
					<tr align="center">
						
						
						<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=ben.getId()%>" <%if (ben.getId() == 1) {%>
						disabled="disabled" <%}%>></td>
						<td><%=index++%></td>
						
						<td><%=ben.getName()%></td>
						<td><%=ben.getDescription()%></td>
						
						<%
						if (ben.getId() == 1) {
					%>
					<td><a href="RoleCtl?id=<%=ben.getId()%>"
						onclick="return false;">Edit</a></td>
					<%
						} else {
					%>
					<td><a href="RoleCtl?id=<%=ben.getId()%>">Edit</a></td>
					<%
						}
					%>
						
					</tr>
					<%
						}
					%>
				</table>
				<table width="100%">
					<tr>
						<%
							if (pageNo == 1) {
						%>
						<td><input type="submit" name="operation" disabled="disabled"
							value="<%=RoleListCtl.OP_PREVIOUS%>"></td>
						<%
							} else {
						%>
						<td><input type="submit" name="operation"
							value="<%=RoleListCtl.OP_PREVIOUS%>"></td>
						<%
							}
						%>

						<td><input type="submit" name="operation"
							value="<%=RoleListCtl.OP_DELETE%>"></td>
						<td><input type="submit" name="operation"
							value="<%=RoleListCtl.OP_NEW%>"></td>


						<%
							RoleModel model = new RoleModel();
							if (list.size() < pageSize || model.nextPK() - 1 == bean.getId()) {
						%><td align="right"><input type="submit" name="operation"
							disabled="disabled" value="<%=RoleListCtl.OP_NEXT%>"></td>
						<%
							} else {
						%>
						<td align="right"><input type="submit" name="operation"
							value="<%=RoleListCtl.OP_NEXT%>"></td>
						<%
							}
						%>
					</tr>
				</table>
				<br><br><br><br><br><br>
			</form>
	</center>
	<%@include file="Footer.jsp"%>
</body>
</html>
